package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "news_table")
data class ArticleX(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String?,
    val url: String,
    val urlToImage: String
)