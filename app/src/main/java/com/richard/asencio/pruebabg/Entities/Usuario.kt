package com.richard.asencio.pruebabg.Entities

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.richard.asencio.pruebabg.Utils.TABLE_USER
import java.io.Serializable

@DatabaseTable(tableName = TABLE_USER)
class Usuario : Serializable {

    @DatabaseField(generatedId = true)
    private var id: Int = 0
    @DatabaseField(columnName = "email")
    var email: String = String()
    @DatabaseField(columnName = "nombre")
    var nombre: String = String()
    @DatabaseField(columnName = "plan")
    var plan: Int = 0
    @DatabaseField(columnName = "telefono")
    var Imei: String = String()
    @DatabaseField(columnName = "token")
    var token: String = String()

    constructor()
    constructor(id: Int, email: String, nombre: String, plan: Int, Imei: String, token: String) {
        this.id = id
        this.email = email
        this.nombre = nombre
        this.plan = plan
        this.Imei = Imei
        this.token = token
    }


}