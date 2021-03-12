package com.richard.asencio.pruebabg

import com.j256.ormlite.dao.Dao
import java.sql.SQLException
import androidx.multidex.MultiDexApplication
import com.richard.asencio.pruebabg.DataBase.DataBase
import com.richard.asencio.pruebabg.Entities.Usuario
import uk.co.chrisjenx.calligraphy.CalligraphyConfig



class PruebaBGApplication : MultiDexApplication() {

    companion object {
        lateinit var instance: PruebaBGApplication
        lateinit var databaseHelper: DataBase
        private var usuario: Dao<Usuario, Int>? = null

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        databaseHelper = DataBase(this)

        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("Fonts/Montserrat-Regular.ttf")
                .build()
        )
    }

    @Throws(SQLException::class)
    fun getUsuarioDao(): Dao<Usuario, Int>? {
        if (usuario == null) {
            usuario = databaseHelper.getDao(Usuario::class.java)
        }
        return usuario;
    }

    override fun onTerminate() {
        super.onTerminate()
        //if (databaseHelper != null) { databaseHelper?.close()  }

    }
}