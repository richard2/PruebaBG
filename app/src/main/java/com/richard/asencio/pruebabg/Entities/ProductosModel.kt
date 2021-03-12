package com.richard.asencio.pruebabg.Entities

import java.io.Serializable


class ProductosModel : Serializable {

    var transaccion: String = String()
    var tipo: String = String()

    constructor()
    constructor(transaccion: String, tipo: String) {
        this.transaccion = transaccion
        this.tipo = tipo
    }


}