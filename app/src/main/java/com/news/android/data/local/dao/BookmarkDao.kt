package com.news.android.data.local.dao

import androidx.room.*
import com.news.android.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM articles")
    fun getAllBookmarks(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(article: Article)

    @Query("DELETE FROM articles WHERE url = :url")
    suspend fun removeBookmark(url: String)

    @Query("SELECT EXISTS (SELECT 1 FROM articles WHERE url = :url)")
    suspend fun isBookmarked(url: String): Boolean
}

