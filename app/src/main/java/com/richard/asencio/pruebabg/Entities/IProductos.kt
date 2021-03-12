package com.richard.asencio.pruebabg.Entities

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface IProductos {

    @Headers("Content-Type: application/json")
    @POST("productos")
    open fun getProductos(@Body json: ProductosModel): Call<dataProducts>

    class dataProducts {
        var codigoRetorno: String? = null
        var mensajeRetorno: String? = null
        var data: List<Productos>? = null

        constructor()
        constructor(codigoRetorno: String?, mensajeRetorno: String?, data: List<Productos>?) {
            this.codigoRetorno = codigoRetorno
            this.mensajeRetorno = mensajeRetorno
            this.data = data
        }


    }
}