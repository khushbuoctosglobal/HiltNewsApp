package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.ArticleX
import com.example.newsapp.model.NewsErrorHandling
import com.example.newsapp.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) :ViewModel(){

    private val articles_ = MutableLiveData<NewsErrorHandling>()
    val articles : LiveData<NewsErrorHandling> = articles_

    fun fetchArticle(query: String, fromDate: String, sortBy: String, apiKey: String) {

        viewModelScope.launch {
           /* val result_ = repository.getArticles(query,fromDate,sortBy,apiKey)
            articles_.value = result_*/
            articles_.value = NewsErrorHandling.Loading
            try {
                val result = repository.getArticles(query, fromDate, sortBy, apiKey)
                articles_.value = result?.let { NewsErrorHandling.Success(it) }
            } catch (e: Exception) {
                articles_.value = NewsErrorHandling.Error(e)
            }
        }
    }

    fun insertArticle(articleX: List<ArticleX>){
        viewModelScope.launch {
            repository.insertArticle(articleX)
        }
    }
}