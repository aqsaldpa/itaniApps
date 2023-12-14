package com.project.itaniapps

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.project.itaniapps.model.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {

    private val CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER (" +
            "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COLUMN_USER_NAME TEXT, " +
            "$COLUMN_USER_PASSWORD TEXT)"

    private val DROP_TABLE_USER = "DROP TABLE IF EXISTS $TABLE_USER"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USER)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_USER)
    }


    fun registerUser(user: User) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COLUMN_USER_NAME, user.username)
        value.put(COLUMN_USER_PASSWORD, user.password)

        db.insert(TABLE_USER, null, value)

        /*
        10 Desember 2023 9:37 PM
        Youtube : Menit 35:56
        https://www.youtube.com/watch?v=vI3-xkxXgqU
        */

//        db.delete(TABLE_USER, COLUMN_USER_ID, null)
//        db.close()
    }

    fun checkUserLogin(username: String, password: String): Boolean {
        val columns = arrayOf(
            COLUMN_USER_ID
        )
        val db = this.readableDatabase

        val selection = "$COLUMN_USER_NAME = ? AND $COLUMN_USER_PASSWORD = ?"

        val selectionArgs = arrayOf(username, password)

        val cursor: Cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount: Int = cursor.count
        cursor.close()
        db.close()
        return cursorCount > 0
    }

    fun checkUser(username: String): Boolean {
        val columns = arrayOf(
            COLUMN_USER_NAME
        )
        val db = this.readableDatabase

        val selection = "$COLUMN_USER_NAME = ?"

        val selectionArgs = arrayOf(username)

        val cursor: Cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount: Int = cursor.count
        cursor.close()
        db.close()
        return cursorCount > 0
    }


    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "itani.db"
        private const val TABLE_USER = "tbl_user"

        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_USER_NAME = "user_name"
        private const val COLUMN_USER_PASSWORD = "user_password"
    }
}