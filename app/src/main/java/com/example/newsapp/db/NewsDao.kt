package com.example.newsapp.db


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.model.ArticleX

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAllUsers(): LiveData<List<ArticleX>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: List<ArticleX>)

    @Update
    suspend fun updateUser(user: ArticleX)

    @Delete
    suspend fun deleteUser(user: ArticleX)
}