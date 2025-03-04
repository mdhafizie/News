package com.news.android.data.repository

import com.news.android.data.local.dao.BookmarkDao
import com.news.android.data.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: BookmarkDao
) : BookmarkRepository {

    override fun getBookmarks(): Flow<List<Article>> {
        return dao.getAllBookmarks()
    }

    override suspend fun toggleBookmark(article: Article) {
        if (dao.isBookmarked(article.url)) {
            dao.removeBookmark(article.url)
        } else {
            dao.addBookmark(article)
        }
    }
}
