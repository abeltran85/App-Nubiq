package es.fairshall.appnubiq.Db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import es.fairshall.appnubiq.Model.User
import java.util.*

class DatabaseHandlerUser (context: Context) : SQLiteOpenHelper(context, DatabaseHandlerUser.DB_NAME, null, DatabaseHandlerUser.DB_VERSION){
        override fun onCreate(db: SQLiteDatabase) {
            val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID_CLIENT INTEGER PRIMARY KEY, $NOMBRE TEXT,$PASSWORD TEXT);"
            db.execSQL(CREATE_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
            db.execSQL(DROP_TABLE)
            onCreate(db)
        }

        fun addUser(user: User): Boolean {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(NOMBRE, user.nombre)
            values.put(PASSWORD, user.password)
            val _success = db.insert(TABLE_NAME, null, values)
            db.close()
            Log.v("InsertedId", "$_success")
            return (Integer.parseInt("$_success") != -1)
        }

        fun getUser(_id: Int): User {
            val user = User()
            val db = writableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID_CLIENT = $_id"
            val cursor = db.rawQuery(selectQuery, null)

            cursor?.moveToFirst()
            user.id_client = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_CLIENT)))
            user.nombre = cursor.getString(cursor.getColumnIndex(NOMBRE))
            user.password = cursor.getString(cursor.getColumnIndex(PASSWORD))
            cursor.close()
            return user
        }

        companion object {
            private val DB_VERSION = 1
            private val DB_NAME = "nubiqdb.db"
            private val TABLE_NAME = "User"
            private val ID_CLIENT = "Id"
            private val NOMBRE = "Name"
            private val PASSWORD = "Password"
        }

}