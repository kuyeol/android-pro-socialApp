package com.ung.feedback.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ung.feedback.database.util.MyTypeConverter



@Database(entities = [PostEntity::class], version = 1, exportSchema = true)
@TypeConverters(
  MyTypeConverter::class,
)
abstract class PostDatabase : RoomDatabase() {

  abstract fun postDao(): PostDao

  companion object {


    @Volatile
    private var POSTDATA: PostDatabase? = null

    fun getPostData(context: Context): PostDatabase = POSTDATA ?: synchronized(this) {
      POSTDATA ?: createPostDatabase(context).also { POSTDATA = it }
    }

    private fun createPostDatabase(context: Context) = Room.databaseBuilder(
      context.applicationContext, PostDatabase::class.java, "post.db"
    ).build()

  }


}
