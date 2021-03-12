package com.richard.asencio.pruebabg.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.library.BuildConfig
import com.google.gson.Gson
import com.richard.asencio.pruebabg.DataBase.DataBaseHelper
import com.richard.asencio.pruebabg.Entities.*
import com.richard.asencio.pruebabg.PruebaBGApplication
import com.richard.asencio.pruebabg.R
import com.richard.asencio.pruebabg.Utils.BASE_URL_EC
import com.richard.asencio.pruebabg.Utils.TAG_ERROR
import com.richard.asencio.pruebabg.Utils.Utility
import kotlinx.android.synthetic.main.new_login_layout.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import kotlin.experimental.and



class LoginActivity : BaseActivity() {

    private var editText: EditText? = null
    var dialog: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) { supportActionBar?.hide() }
        this.addLyout(R.layout.new_login_layout)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            getWindow().setStatusBarColor(Color.parseColor("#F9F9F9"))
        }

        txt_version.setText("v" + BuildConfig.VERSION_NAME);

        user.setColorFilter(Color.parseColor("#1d3144"))

        //txt_recuperar.setOnClickListener { recuperarClave() }


        var usuario = DataBaseHelper.instance.getUsuarios(
            PruebaBGApplication.databaseHelper.getDao(
                Usuario::class.java
            )
        )

        if (usuario != null){
            if (usuario.size > 0)
            {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        
    }

    fun ingresarAction(view: View){
        if (validate()){
            login()
        }else{
            showMessage(getString(R.string.lbl_completar))
        }
    }

    fun validate(): Boolean{
        if ( edtUsuario.text.length == 0 ) { return false }
        if ( edtContrasenia.text.length == 0 ) { return false }
        return true
    }

    fun login(){
        showLoadingOver()
        if (!isConnected) {
            dismissLoadingOver()
            showMessage(getString(R.string.lbl_no_internet))
            return
        }
        try {

            val client = OkHttpClient.Builder()
                .connectTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(80, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
                .build()

            var url = BASE_URL_EC

            Utility.trustEveryone()
            val okHttpClient: OkHttpClient
            okHttpClient = getOkHttpBuilder().build()



            val resAdapter = Retrofit.Builder()
                .baseUrl(url)
                //.client(Utility.getUnsafeOkHttpClient().build())
                .client(unSafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val request = resAdapter.create(ILogin::class.java)
            val usuario = edtUsuario.text.toString().trim()
            val clave = edtContrasenia.text.toString().trim()


            var loginDatos = LoginModel()

            var datosUser = datosUsuario()

            datosUser.email = usuario
            datosUser.password = Utility.SHA1(clave)

            loginDatos.transaccion = "autenticarUsuario"
            loginDatos.datosUsuario = datosUser

            val gson = Gson()
            val json = gson.toJson(loginDatos)

            val call = request.getLogin(loginDatos)
            call.enqueue(object : Callback<ILogin.dataLogin> {
                override fun onResponse(
                    call: Call<ILogin.dataLogin>,
                    response: Response<ILogin.dataLogin>
                ) {
                    if (response.isSuccessful()) {
                        try {
                            val data = response.body()
                            if (data != null) {

                                if (data.codigoRetorno.equals("0001")){

                                    val usuario = data.usuario!!
                                    usuario.token = data.token!!
                                    DataBaseHelper.instance.deleteUsuario(PruebaBGApplication.databaseHelper.getDao(Usuario::class.java))
                                    DataBaseHelper.instance.saveUsuario(usuario!!, PruebaBGApplication.databaseHelper.getDao(Usuario::class.java))


                                    dismissLoadingOver()
                                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                    return
                                }else{
                                    dismissLoadingOver()
                                    showMessage(""+data.mensajeRetorno)
                                    return
                                }




                            }
                        } catch (e: Exception) {
                            Log.e(TAG_ERROR, e.toString())
                            dismissLoadingOver()
                            showMessage(getString(R.string.lbl_error_default))
                        }
                    }
                    dismissLoadingOver()
                    showMessage(getString(R.string.lbl_error_default))
                }

                override fun onFailure(call: Call<ILogin.dataLogin>, t: Throwable) {
                    Log.e(TAG_ERROR, t.toString())
                    dismissLoadingOver()
                    showMessage(getString(R.string.lbl_error_default))
                }
            })
        } catch (e: Exception) {
            Log.e(TAG_ERROR, e.toString())
            dismissLoadingOver()
            showMessage(getString(R.string.lbl_error_default))
        }
    }

    fun unSafeOkHttpClient() : OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts:  Array<TrustManager> = arrayOf(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?){}
                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun getAcceptedIssuers(): Array<X509Certificate>  = arrayOf()
            })

            // Install the all-trusting trust manager
            val  sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            if (trustAllCerts.isNotEmpty() &&  trustAllCerts.first() is X509TrustManager) {
                okHttpClient.sslSocketFactory(sslSocketFactory, trustAllCerts.first() as X509TrustManager)
                okHttpClient.hostnameVerifier { _, _ -> true }
            }

            return okHttpClient
        } catch (e: Exception) {
            return okHttpClient
        }
    }

    fun getSha1Hex(clearString: String): String? {
        return try {
            val messageDigest: MessageDigest = MessageDigest.getInstance("SHA-1")
            messageDigest.update(clearString.toByteArray(charset("UTF-8")))
            val bytes: ByteArray = messageDigest.digest()
            val buffer = StringBuilder()
            for (b in bytes) {
                buffer.append(Integer.toString((b and 0xff.toByte()) + 0x100, 16).substring(1))
            }
            buffer.toString()
        } catch (ignored: java.lang.Exception) {
            ignored.printStackTrace()
            null
        }
    }

    fun getOkHttpBuilder(): OkHttpClient.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            OkHttpClient().newBuilder()
        } else {
            // Workaround for the error "Caused by: com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException: Could not validate certificate: Certificate expired at".
            getUnsafeOkHttpClient()
        }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
            )
            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory,
                trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }



    fun goHome(){

          //  startActivity(intent)
            finish()
    }


}
