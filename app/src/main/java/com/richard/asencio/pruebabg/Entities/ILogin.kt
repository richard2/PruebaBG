package com.richard.asencio.pruebabg.Entities

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ILogin {

    @Headers("Content-Type: application/json")
    @POST("usuarios")
    open fun getLogin(@Body json: LoginModel): Call<dataLogin>

    class dataLogin {
        var codigoRetorno: String? = null
        var mensajeRetorno: String? = null
        var usuario: Usuario? = null
        var token: String? = null

        constructor()
        constructor(
            codigoRetorno: String?,
            mensajeRetorno: String?,
            usuario: Usuario?,
            token: String?
        ) {
            this.codigoRetorno = codigoRetorno
            this.mensajeRetorno = mensajeRetorno
            this.usuario = usuario
            this.token = token
        }


    }
}