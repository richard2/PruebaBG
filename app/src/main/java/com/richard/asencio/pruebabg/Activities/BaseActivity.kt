package com.richard.asencio.pruebabg.Activities

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.base_layout.*
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.net.wifi.WifiManager
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.snackbar.Snackbar
import com.richard.asencio.pruebabg.R
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.math.BigInteger
import java.net.NetworkInterface

/*
* MARK: Abstract class BaseActivity
* PRODUCED BY: Richard Asencio
* CREATED: 01/March/2019
*/
open class BaseActivity : AppCompatActivity(), DialogInterface.OnDismissListener{

    lateinit var progress: ProgressDialog
    lateinit var snackbar: Snackbar

    val Context.isConnected: Boolean
        @SuppressLint("MissingPermission")
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_layout)
        txt_reintentar.setOnClickListener { doRetry() }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun addLyout(layout: Int){

       val view = LayoutInflater.from(this).inflate(layout, null, false)
       view.setLayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
           LinearLayout.LayoutParams.MATCH_PARENT))
       content_linear.addView(view);
    }

    @SuppressLint("MissingPermission")
    fun getIpAddress(): String? {
        var WIFI = false
        var MOBILE = false
        val CM = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = CM.allNetworkInfo
        for (netInfo in networkInfo) {
            if (netInfo.typeName.equals("WIFI", ignoreCase = true))
                if (netInfo.isConnected)
                    WIFI = true
            if (netInfo.typeName.equals("MOBILE", ignoreCase = true))
                if (netInfo.isConnected)
                    MOBILE = true
        }

        if (WIFI == true) {
            return GetDeviceipWiFiData()
        }

        if (MOBILE == true) {
            return GetDeviceipMobileData()
        }
        return null
    }

    fun GetDeviceipMobileData(): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val networkinterface = en.nextElement()
                val enumIpAddr = networkinterface.getInetAddresses()
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.hostAddress
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e("Current IP", ex.toString())
        }

        return null
    }

    fun GetDeviceipWiFiData(): String {
        val wm = getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
        return  BigInteger.valueOf(wm.getDhcpInfo().netmask.toLong()).toString()

    }

    //MARK: Show progressDialog over layout (indeterminated)
    fun showLoadingOver(){
        if (!::progress.isInitialized) {
            progress = ProgressDialog(this)
            progress.setOnDismissListener(this)
            progress.isIndeterminate = true
            progress.setCancelable(true)
            progress.setCanceledOnTouchOutside(false)
            progress.setMessage(getString(R.string.lbl_cargando))
        }

        progress.show()
        if (retry_linear != null) { retry_linear.visibility = View.GONE }
        if (content_linear != null) { content_linear.visibility = View.VISIBLE }

    }

    fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    //MARK: Dismiss progressDialog over layout (indeterminated)
    fun dismissLoadingOver(){
        if (::progress.isInitialized) {
            if (progress.isShowing) {
                progress.dismiss()
            }
        }
        if (retry_linear != null) { retry_linear.visibility = View.GONE }
        if (content_linear != null) { content_linear.visibility = View.VISIBLE }
    }

    fun showMessage(message: String){
        var mensaje = getString(R.string.lbl_error_default)
        if (message != null){ mensaje = message }
        val builder = AlertDialog.Builder(this@BaseActivity)
        builder.setIcon(R.mipmap.ic_launcher)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(mensaje)
        builder.setPositiveButton(getString(R.string.lbl_aceptar)){dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
        if (retry_linear != null) { retry_linear.visibility = View.GONE }
        if (content_linear != null) { content_linear.visibility = View.VISIBLE }
    }

    //MARK: Listener hide keyboard touch outside
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val view = currentFocus
        val ret = super.dispatchTouchEvent(event)

        if (view is EditText) {
            val w = currentFocus
            val scrcoords = IntArray(2)
            w!!.getLocationOnScreen(scrcoords)
            val x = event.rawX + w.left - scrcoords[0]
            val y = event.rawY + w.top - scrcoords[1]

            if (event.action == MotionEvent.ACTION_UP && (x < w.left || x >= w.right
                        || y < w.top || y > w.bottom)
            ) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)
            }
        }
        return ret
    }

    //MARK: Cancel listener progress
    override fun onDismiss(dialog: DialogInterface?) {
        this.doCancel()
    }

    //MARK: Implements member cancel listener progress
    open fun doCancel(){

    }

    fun showLoadingIn(){
        dismissLoadingOver()
        if (retry_linear != null) { retry_linear.visibility = View.VISIBLE }
        if (content_linear != null) { content_linear.visibility = View.GONE }
        if (txt_reintentar != null) { txt_reintentar.visibility = View.GONE }
        if (progress_bar != null) { progress_bar.visibility = View.VISIBLE }
    }

    fun dismissLoadingIn(){
        dismissLoadingOver()
        if (retry_linear != null) { retry_linear.visibility = View.GONE }
        if (content_linear != null) { content_linear.visibility = View.VISIBLE }
        if (txt_reintentar != null) { txt_reintentar.visibility = View.GONE }
        if (progress_bar != null) { progress_bar.visibility = View.GONE }
    }

    fun showRetry(){
        dismissLoadingOver()
        if (retry_linear != null) { retry_linear.visibility = View.VISIBLE }
        if (content_linear != null) { content_linear.visibility = View.GONE }
        if (txt_reintentar != null) { txt_reintentar.visibility = View.VISIBLE }
        if (progress_bar != null) { progress_bar.visibility = View.GONE }
    }

    //MARK: Implements member retry loading
    open fun doRetry(){

    }

}
