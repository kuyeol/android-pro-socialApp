package com.example.sqldemo.sqlite

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.sqldemo.AndroidApplication
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileWriter

class DbOpener(context : Context) :SQLiteOpenHelper(context, DATABASE, null, VERSION)  {

    companion object {

        const val VERSION = 1
        const val DATABASE = "app_database.db"
    }


    override fun onCreate(db : SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db : SQLiteDatabase ,
                           oldVersion : Int ,
                           newVersion : Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db : SQLiteDatabase ,
                             oldVersion : Int ,
                             newVersion : Int) {
        onUpgrade(db ,
                  oldVersion ,
                  newVersion)
    }




  fun exportSchemaToSQLFile(context: Context) {
    val db = readableDatabase
    val cursor = db.rawQuery("SELECT * FROM entry", null)

    val schemaBuilder = StringBuilder()
    while (cursor.moveToNext()) {
      schemaBuilder.append(cursor.getString(0)).append(";\n")
    }
    cursor.close()

    val file = File(context.getExternalFilesDir(null), "schema.sql")
    FileWriter(file).use { it.write(schemaBuilder.toString()) }

    Log.d("SCHEMA_EXPORT", "Schema SQL exported to: ${file.absolutePath}")
  }
}

object FReaderContract {
    // Table contents are grouped together in an anonymous object.
    object FEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_SUBTITLE = "sub"
    }
}

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${FReaderContract.FEntry.TABLE_NAME} (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "${FReaderContract.FEntry.COLUMN_NAME_TITLE} TEXT," +
        "${FReaderContract.FEntry.COLUMN_NAME_SUBTITLE} TEXT)"
private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FReaderContract.FEntry.TABLE_NAME}"


