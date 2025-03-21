package com.ung.sqlite.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns



object FReaderContract {
  // Table contents are grouped together in an anonymous object.
  object FEntry : BaseColumns {
    const val TABLE_NAME = "entry"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_SUBTITLE = "subtitle"
  }
}
private const val SQL_CREATE_ENTRIES =
  "CREATE TABLE ${FReaderContract.FEntry.TABLE_NAME} (" +
    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
    "${FReaderContract.FEntry.COLUMN_NAME_TITLE} TEXT," +
    "${FReaderContract.FEntry.COLUMN_NAME_SUBTITLE} TEXT)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FReaderContract.FEntry.TABLE_NAME}"


class FReaderDbHelper(context: Context) : SQLiteOpenHelper(context , DATABASE_NAME , null , DATABASE_VERSION) {
  val dbHelper = FReaderDbHelper(context)
  override fun onCreate(db: SQLiteDatabase) {
    db.execSQL(SQL_CREATE_ENTRIES)
  }
  override fun onUpgrade(db: SQLiteDatabase , oldVersion: Int , newVersion: Int) {
    // This database is only a cache for online data, so its upgrade policy is
    // to simply to discard the data and start over
    db.execSQL(SQL_DELETE_ENTRIES)
    onCreate(db)
  }
  override fun onDowngrade(db: SQLiteDatabase , oldVersion: Int , newVersion: Int) {
    onUpgrade(db, oldVersion, newVersion)
  }
  companion object {
    // If you change the database schema, you must increment the database version.
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "FeedReader.db"
  }
}
