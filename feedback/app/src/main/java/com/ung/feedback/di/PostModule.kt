package com.ung.feedback.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.ung.feedback.database.PostEntity
import com.ung.feedback.database.PostDao
import com.ung.feedback.database.PostDatabase
import com.ung.feedback.repository.IPostRepository
import com.ung.feedback.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton



object PostModule {
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Singleton
  @Binds
  abstract fun bindTaskRepository(repository: PostRepository): IPostRepository
}



@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Singleton
  @Provides
  fun provideDataBase(@ApplicationContext context: Context): PostDatabase {
    return Room.databaseBuilder(
      context.applicationContext,
      PostDatabase::class.java,
      "postTable.db"
    ).build()
  }

  @Provides
  fun provideTaskDao(database: PostDatabase): PostDao = database.postDao()

}
