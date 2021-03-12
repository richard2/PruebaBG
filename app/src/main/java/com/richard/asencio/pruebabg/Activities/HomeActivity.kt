package com.richard.asencio.pruebabg.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.richard.asencio.pruebabg.Adapters.AdapterProductosList
import com.richard.asencio.pruebabg.DataBase.DataBaseHelper
import com.richard.asencio.pruebabg.Entities.IProductos
import com.richard.asencio.pruebabg.Entities.Productos
import com.richard.asencio.pruebabg.Entities.ProductosModel
import com.richard.asencio.pruebabg.Entities.Usuario
import com.richard.asencio.pruebabg.PruebaBGApplication
import com.richard.asencio.pruebabg.R
import com.richard.asencio.pruebabg.Utils.BASE_URL_EC
import com.richard.asencio.pruebabg.Utils.TAG_ERROR
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.home_layout.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class HomeActivity : BaseActivity() {

    private var editText: EditText? = null
    var dialog: AlertDialog? = null
    var ordenar = arrayOf("ALFABETICAMENTE", "ORDENAR POR PRECIO")
    var NEW_SPINNER_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) { supportActionBar?.hide() }
        this.addLyout(R.layout.home_layout)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, ordenar)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                NEW_SPINNER_ID = position
                showProductos()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })

        cerrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Atención")
            builder.setMessage("¿Está seguro que desea cerrar sesión?")

            builder.setPositiveButton("Aceptar") { dialog, which ->
                DataBaseHelper.instance.deleteUsuario(PruebaBGApplication.databaseHelper.getDao(Usuario::class.java))
                DataBaseHelper.instance.deleteProductos(PruebaBGApplication.databaseHelper.getDao(Productos::class.java))

                val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }


            builder.show()
        }

        loadDataProdcto()
        
    }

    private fun loadDataProdcto(){

        try {

            val client = OkHttpClient.Builder()
                .connectTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(80, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
                .build()

            var url = BASE_URL_EC
            val resAdapter = Retrofit.Builder()
                .baseUrl(url)
                .client(unSafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()


            var productomodel = ProductosModel()
            productomodel.transaccion = "generico"
            productomodel.tipo = "4"

            val request = resAdapter.create(IProductos::class.java)
            val call = request.getProductos(productomodel)
            call.enqueue(object : Callback<IProductos.dataProducts> {
                override fun onResponse(
                    call: Call<IProductos.dataProducts>,
                    response: Response<IProductos.dataProducts>
                ) {
                    if (response.isSuccessful()) {
                        try {
                            val data = response.body()
                            if (data != null) {
                                if (data.codigoRetorno.equals("0001")) {

                                    val productos = data.data!!

                                    DataBaseHelper.instance.deleteProductos(
                                        PruebaBGApplication.databaseHelper.getDao(
                                            Productos::class.java
                                        )
                                    )
                                    for (product in productos) {
                                        DataBaseHelper.instance.saveProductos(
                                            product, PruebaBGApplication.databaseHelper.getDao(
                                                Productos::class.java
                                            )
                                        )
                                    }

                                    dismissLoadingOver()
                                    showProductos()
                                    return
                                } else {
                                    dismissLoadingOver()
                                    showMessage("" + data.mensajeRetorno)
                                    return
                                }
                            }

                            showProductos()
                            return
                        } catch (e: Exception) {
                            Log.e(TAG_ERROR, e.toString())
                            showProductos()
                        }
                    }
                    showProductos()
                }

                override fun onFailure(call: Call<IProductos.dataProducts>, t: Throwable) {
                    Log.e(TAG_ERROR, t.toString())
                    showProductos()
                }
            })
        } catch (e: Exception) {
            Log.e(TAG_ERROR, e.toString())
            showProductos()
        }

    }

    fun unSafeOkHttpClient() : OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts:  Array<TrustManager> = arrayOf(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            })

            // Install the all-trusting trust manager
            val  sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            if (trustAllCerts.isNotEmpty() &&  trustAllCerts.first() is X509TrustManager) {
                okHttpClient.sslSocketFactory(
                    sslSocketFactory,
                    trustAllCerts.first() as X509TrustManager
                )
                okHttpClient.hostnameVerifier { _, _ -> true }
            }

            return okHttpClient
        } catch (e: Exception) {
            return okHttpClient
        }
    }

    private fun showProductos(){

        dismissLoadingOver()

        var produtos :  List<Productos>? = null


        if (NEW_SPINNER_ID == 0){
            produtos = DataBaseHelper.instance.getProductosAlfabeticamente(
                PruebaBGApplication.databaseHelper.getDao(
                    Productos::class.java
                )
            )
        }else{
            produtos = DataBaseHelper.instance.getProcductosPrecio(
                PruebaBGApplication.databaseHelper.getDao(
                    Productos::class.java
                )
            )
        }

        if (produtos != null){
            if (produtos!!.size > 0){

                val layoutManager = LinearLayoutManager(this)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                lista_productos.setLayoutManager(layoutManager)
                val adapterProductos = AdapterProductosList(
                    this, false,
                    produtos!! as ArrayList<Productos>
                )
                lista_productos.setAdapter(adapterProductos)
                return
            }
        }

        //showMessage("No se encontraron productos asociados")

    }




}
