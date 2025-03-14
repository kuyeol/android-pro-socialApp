package com.ung.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.provider.BaseColumns
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.ung.sqlite.database.DBHelper
import com.ung.sqlite.database.FReaderContract
import com.ung.sqlite.ui.main.SectionsPagerAdapter
import com.ung.sqlite.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
  private lateinit var binding : ActivityMainBinding
  lateinit var dbHelper : DBHelper
  lateinit var database : SQLiteDatabase

  //  val db = dbClient.writableDatabase
//  val values = ContentValues().apply {
//    put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE ,
//        "The title"
//    )
//    put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE ,
//        "The subtitle"
//    )
//  }
  override fun onCreate(savedInstanceState : Bundle?) {
    super.onCreate(savedInstanceState)

    dbHelper = DBHelper(this ,
                        "newdb.db" ,
                        null ,
                        1
    )
    database = dbHelper.writableDatabase
    val db = dbHelper.writableDatabase
    val values = ContentValues().apply {
      put(FReaderContract.FEntry.COLUMN_NAME_TITLE ,
          "AA")
      put(FReaderContract.FEntry.COLUMN_NAME_SUBTITLE ,
          "BB")
    }
    db?.insert(FReaderContract.FEntry.TABLE_NAME ,
               null ,
               values
    )
// 새 행을 삽입하고 새 행의 기본 키 값을 반환
    val newRowId = db?.insert(FReaderContract.FEntry.TABLE_NAME ,
                              null ,
                              values
    )
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val sectionsPagerAdapter = SectionsPagerAdapter(this ,
                                                    supportFragmentManager
    )
    val viewPager : ViewPager = binding.viewPager
    viewPager.adapter = sectionsPagerAdapter
    val tabs : TabLayout = binding.tabs
    tabs.setupWithViewPager(viewPager)
    val fab : FloatingActionButton = binding.fab


    fab.setOnClickListener { view ->
      Snackbar.make(view ,
                    "Replace with your own action" ,
                    Snackbar.LENGTH_LONG
      )
        .setAction("Action" ,
                   null
        )
        .setAnchorView(R.id.fab)
        .show()
    }
  }

  override fun onDestroy() {
    dbHelper.close()
    super.onDestroy()
  }
}



