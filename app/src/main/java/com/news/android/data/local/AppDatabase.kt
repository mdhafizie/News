package com.news.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.news.android.data.local.dao.BookmarkDao
import com.news.android.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}
