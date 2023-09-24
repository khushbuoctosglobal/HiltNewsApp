package com.example.newsapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsListLayoutBinding
import com.example.newsapp.model.ArticleX
import java.util.*

class NewsAdapter(var itemList: List<ArticleX>):RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    private var filteredList = itemList.toMutableList()

   inner class ViewHolder(val binding: NewsListLayoutBinding):RecyclerView.ViewHolder(binding.root){
         fun bind(articleX: ArticleX){
             binding.article = articleX
             binding.executePendingBindings()
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val binding = NewsListLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun update(articleX: MutableList<ArticleX>){
        itemList = articleX
        notifyDataSetChanged()
    }
}