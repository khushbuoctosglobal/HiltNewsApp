package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.model.ArticleX
import com.example.newsapp.model.NewsErrorHandling
import com.example.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var viewModel:NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private var articles : List<ArticleX> = arrayListOf()
    private var filteredList = articles.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.fetchArticle("tesla","2023-08-02","publishedAt","ca8f5fae13c34d35b9a4317ff9e61b59")

        viewModel.articles.observe(this) { result ->
            when (result) {
                is NewsErrorHandling.Success -> {
                     articles = result.articles
                    Log.d("MainActivity","get ${articles[0].content}")
                    newsAdapter  = NewsAdapter(articles)
                    binding.recView.adapter = newsAdapter
                    binding.ivError.visibility = View.GONE
                }
                is NewsErrorHandling.Error -> {
                    binding.ivError.visibility = View.VISIBLE
                }
                NewsErrorHandling.Loading -> {

                }
            }
        }
        viewModel.insertArticle(articles)

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?) = menuInflater.inflate(R.menu.toolbar_menu, menu).let{
         true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_print -> {
                startActivity(Intent(this, BookmarkActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun filter(query: String) {
        val lowerCaseQuery = query.toLowerCase(Locale.getDefault())
        filteredList.clear()
        for(x in articles) {
            x.title?.let {
                if (x.title.contains(lowerCaseQuery, true)) {
                    filteredList.add(x)
                }
            }
        }
        newsAdapter.update(filteredList)
    }

    fun Double.format(digits: Int): String {
        return "%.${digits}f".format(this)
    }
}