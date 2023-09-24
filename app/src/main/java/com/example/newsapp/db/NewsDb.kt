package com.example.newsapp.db

import android.content.Context
import androidx.room.*
import com.example.newsapp.model.ArticleX
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Database(entities = [ArticleX::class], version = 1, exportSchema = false)
abstract class NewsDb @Inject constructor(
    private val context: Context
) : RoomDatabase() {

    abstract fun dao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDb? = null

        fun getDatabase(context: Context): NewsDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDb::class.java,
                    "news.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
