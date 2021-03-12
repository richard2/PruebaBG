package com.richard.asencio.pruebabg.Adapters

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.richard.asencio.pruebabg.Activities.DetalleProductoActivity
import com.richard.asencio.pruebabg.Entities.Productos
import com.richard.asencio.pruebabg.R
import com.richard.asencio.pruebabg.Utils.BASE_URL_EC
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat


open class AdapterProductosList(
    val context: Context,
    val isFavorite: Boolean, var arreglo: ArrayList<Productos> ): RecyclerView.Adapter<AdapterProductosList.Holder>() {


    var progress: ProgressDialog? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_producto, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return arreglo.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindVisita(context,isFavorite, arreglo.get(position), position)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val card_view = itemView?.findViewById<CardView>(R.id.card_view)
        val imagen = itemView?.findViewById<ImageView>(R.id.imagen)
        val precio = itemView?.findViewById<TextView>(R.id.precio)
        val adicionar = itemView?.findViewById<TextView>(R.id.adicionar)
        val descripcion = itemView?.findViewById<TextView>(R.id.descripcion)
        val txt_stock = itemView?.findViewById<LinearLayout>(R.id.txt_stock)
        val precio_antes = itemView?.findViewById<TextView>(R.id.precio_antes)
        val name = itemView?.findViewById<TextView>(R.id.name)


        fun bindVisita(context: Context,isFavorite : Boolean, item: Productos, position: Int){


            txt_stock!!.visibility = View.GONE

            precio_antes!!.visibility = View.INVISIBLE
            setImage(BASE_URL_EC+item!!.imagen, imagen!!)
            val decim = DecimalFormat("0.00")
            precio?.setText("$ "+ item.precio)
            name?.setText(""+item.descripcion)
            descripcion?.setText(""+item!!.detalle)


            card_view?.setOnClickListener {
                val intent = Intent(context, DetalleProductoActivity::class.java)
                intent.putExtra("product", item)
                context.startActivity(intent)
            }


        }

    }

    fun setImage(url: String, imageview: ImageView){
        Glide.with(imageview)
            .load(url)
            .into(imageview)
    }
}