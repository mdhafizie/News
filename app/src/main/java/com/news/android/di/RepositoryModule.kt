package com.news.android.di

import com.news.android.data.repository.BookmarkRepository
import com.news.android.data.repository.BookmarkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(
        repositoryImpl: BookmarkRepositoryImpl
    ): BookmarkRepository
}

