package com.example.newsapp.repo

import com.example.newsapp.api.NewsApiService
import com.example.newsapp.db.NewsDb
import com.example.newsapp.model.ArticleX
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApiService:NewsApiService,private val newsDb: NewsDb) {

    suspend fun getArticles(query: String, fromDate: String, sortBy: String, apiKey: String): List<ArticleX>? {

        val response = newsApiService.getArticles(query,fromDate,sortBy,apiKey)
        if (response.isSuccessful){
            return response.body()?.articles
        }
        return null
    }

    suspend fun insertArticle(articleX: List<ArticleX>){
        newsDb.dao().insertUser(articleX)
    }
}