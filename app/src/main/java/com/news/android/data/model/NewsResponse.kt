package com.news.android.data.model

data class NewsResponse(
    val status: String = "",
    val totalResults: Int =0,
    val articles: List<Article> ?= emptyList()
)
