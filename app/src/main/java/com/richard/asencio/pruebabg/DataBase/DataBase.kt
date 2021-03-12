package com.richard.asencio.pruebabg.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.richard.asencio.pruebabg.Entities.Productos
import com.richard.asencio.pruebabg.Entities.Usuario
import com.richard.asencio.pruebabg.Utils.DATA_BASE_NAME
import java.sql.SQLException

class DataBase : OrmLiteSqliteOpenHelper {

    companion object {
        private val DBNAME = DATA_BASE_NAME
        private val VERSION = 9
        private var db: SQLiteDatabase? = null
    }

    constructor(context: Context) : super(context,DBNAME,null,VERSION)

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        try {
            db = database
            TableUtils.createTable(connectionSource, Usuario::class.java)
            TableUtils.createTable(connectionSource, Productos::class.java)
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {
        try {
            TableUtils.dropTable<Usuario, Any>(connectionSource, Usuario::class.java, true)
            TableUtils.dropTable<Productos, Any>(connectionSource, Productos::class.java, true)
            onCreate(db, connectionSource)
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    override fun close() {
        super.close()
    }


    fun BeginTransaction() {
        if (db != null) {
            db?.beginTransaction()
        }
    }

    fun EndTransactionSuccess() {
        if (db != null) {
            db?.setTransactionSuccessful()
            db?.endTransaction()
        }
    }

    fun EndTransaction() {
        if (db != null) {
            db?.endTransaction()
        }
    }
}