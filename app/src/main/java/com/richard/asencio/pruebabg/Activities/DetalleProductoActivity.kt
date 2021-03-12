package com.richard.asencio.pruebabg.Activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.richard.asencio.pruebabg.Entities.Productos
import com.richard.asencio.pruebabg.R
import com.richard.asencio.pruebabg.Utils.BASE_URL_EC
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.detalle_producto_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat


class DetalleProductoActivity : BaseActivity() {

    private var producto : Productos? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addLyout(R.layout.detalle_producto_layout)

        cerrar.visibility = View.GONE

        if (intent.getSerializableExtra("product") != null){
            producto = intent.getSerializableExtra("product") as? Productos
            nombre_producto.setText(""+producto!!.descripcion)
            precio.setText("$ "+producto!!.precio)
            precio_antes.setText(" "+producto!!.detalle)
            setImage(BASE_URL_EC +producto!!.imagen, imagen_producto!!)
        }

    }


    fun setImage(url: String, imageview: ImageView){
        Glide.with(imageview)
            .load(url)
            .into(imageview)
    }

    private fun showError(){
        dismissLoadingOver()
        showMessage("Intente nuevamente, lo sentimos!!")
    }


}
