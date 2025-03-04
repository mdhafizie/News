package com.news.android.data.repository

import android.util.Log
import com.news.android.data.model.Article
import com.news.android.data.remote.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: NewsApiService) {
    suspend fun fetchNews(query: String = "general", page: Int, pageSize: Int = 10): List<Article> {
        return try {
            apiService.getNews(query, page, pageSize).articles.orEmpty()
        } catch (e: Exception) {
            Log.e("NewsRepository", "Error fetching news: ${e.message}", e)
            emptyList()
        }
    }

}

