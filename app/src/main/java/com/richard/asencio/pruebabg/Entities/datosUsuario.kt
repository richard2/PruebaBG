package com.richard.asencio.pruebabg.Entities

import java.io.Serializable


class datosUsuario : Serializable {

    var email: String = String()
    var password: String = String()

    constructor()
    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }


}