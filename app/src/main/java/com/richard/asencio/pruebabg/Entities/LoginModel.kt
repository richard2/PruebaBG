package com.richard.asencio.pruebabg.Entities

import java.io.Serializable


class LoginModel : Serializable {

    var transaccion: String = String()
    var datosUsuario: datosUsuario = datosUsuario()

    constructor()
    constructor(transaccion: String, datosUsuario: datosUsuario) {
        this.transaccion = transaccion
        this.datosUsuario = datosUsuario
    }


}