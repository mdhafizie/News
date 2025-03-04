package com.news.android.data.repository

import com.news.android.data.model.Article
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarks(): Flow<List<Article>>
    suspend fun toggleBookmark(article: Article)
}

