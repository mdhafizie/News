package com.news.android.data.remote

import com.news.android.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = "64a4cdedb68f487aa44cb26782052117"?:"84c8ffe674244229b62799d33774504a"
    ): NewsResponse
}