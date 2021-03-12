package com.richard.asencio.pruebabg.DataBase

import com.j256.ormlite.dao.Dao
import com.richard.asencio.pruebabg.Entities.Productos
import com.richard.asencio.pruebabg.Entities.Usuario
import com.richard.asencio.pruebabg.Utils.TABLE_PRODUCTOS
import com.richard.asencio.pruebabg.Utils.TABLE_USER
import java.sql.SQLException


class DataBaseHelper {

    companion object {
        val instance = DataBaseHelper()
    }

    //MARK: HELPER USUARIO
    fun saveUsuario(user: Usuario, userDao: Dao<Usuario, Int>?) {
        try {
            userDao?.create(user);
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    @Throws(SQLException::class)
    fun getUsuarios(userDao: Dao<Usuario, Int>): List<Usuario>? {
        var users: List<Usuario>? = null
        val query = "SELECT * FROM "+TABLE_USER
        val rawResults = userDao.queryRaw<Usuario>(query, userDao.getRawRowMapper())
        users = rawResults.getResults()
        return users
    }

    @Throws(SQLException::class)
    fun deleteUsuario(userDao: Dao<Usuario, Int>) {
        val deleteBuilder = userDao.deleteBuilder()
        deleteBuilder.delete()
    }


    //MARK: HELPER PRODUCTOS
    fun saveProductos(user: Productos, userDao: Dao<Productos, Int>?) {
        try {
            userDao?.create(user);
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    @Throws(SQLException::class)
    fun getProductos(userDao: Dao<Productos, Int>): List<Productos>? {
        var users: List<Productos>? = null
        val query = "SELECT * FROM "+ TABLE_PRODUCTOS
        val rawResults = userDao.queryRaw<Productos>(query, userDao.getRawRowMapper())
        users = rawResults.getResults()
        return users
    }

    fun getProductosAlfabeticamente(userDao: Dao<Productos, Int>): List<Productos>? {
        var users: List<Productos>? = null
        val query = "SELECT * FROM "+ TABLE_PRODUCTOS+" ORDER BY descripcion ASC"
        val rawResults = userDao.queryRaw<Productos>(query, userDao.getRawRowMapper())
        users = rawResults.getResults()
        return users
    }

    fun getProcductosPrecio(userDao: Dao<Productos, Int>): List<Productos>? {
        var users: List<Productos>? = null
        val query = "SELECT * FROM "+ TABLE_PRODUCTOS+" ORDER BY CAST(precio as DECIMAL) ASC"
        val rawResults = userDao.queryRaw<Productos>(query, userDao.getRawRowMapper())
        users = rawResults.getResults()
        return users
    }

    @Throws(SQLException::class)
    fun deleteProductos(userDao: Dao<Productos, Int>) {
        val deleteBuilder = userDao.deleteBuilder()
        deleteBuilder.delete()
    }

}