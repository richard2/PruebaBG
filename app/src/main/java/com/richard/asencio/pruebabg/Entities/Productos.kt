package com.richard.asencio.pruebabg.Entities

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.richard.asencio.pruebabg.Utils.TABLE_PRODUCTOS
import com.richard.asencio.pruebabg.Utils.TABLE_USER
import java.io.Serializable

@DatabaseTable(tableName = TABLE_PRODUCTOS)
class Productos : Serializable {

    @DatabaseField(generatedId = true)
    private var identifer: Int = 0
    @DatabaseField(columnName = "id")
    var id: Int = 0
    @DatabaseField(columnName = "descripcion")
    var descripcion: String = String()
    @DatabaseField(columnName = "precio")
    var precio: String = String()
    @DatabaseField(columnName = "estado")
    var estado: Boolean = false
    @DatabaseField(columnName = "detalle")
    var detalle: String = String()
    @DatabaseField(columnName = "imagen")
    var imagen: String = String()

    constructor()
    constructor(
        identifer: Int,
        id: Int,
        descripcion: String,
        precio: String,
        estado: Boolean,
        detalle: String,
        imagen: String
    ) {
        this.identifer = identifer
        this.id = id
        this.descripcion = descripcion
        this.precio = precio
        this.estado = estado
        this.detalle = detalle
        this.imagen = imagen
    }


}