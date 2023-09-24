package com.example.newsapp.model

data class Article(
    val articles: List<ArticleX>,
    val status: String,
    val totalResults: Int
)