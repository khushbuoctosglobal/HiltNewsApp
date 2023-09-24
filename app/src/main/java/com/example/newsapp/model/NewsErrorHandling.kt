package com.example.newsapp.model

sealed class NewsErrorHandling{
    data class Success(val articles: List<ArticleX>): NewsErrorHandling()
    data class Error(val exception: Exception) : NewsErrorHandling()
    object Loading : NewsErrorHandling()
}