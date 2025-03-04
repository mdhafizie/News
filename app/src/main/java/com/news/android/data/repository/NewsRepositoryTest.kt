/*
package com.news.android.data.repository

import com.news.android.data.model.NewsResponse
import com.news.android.data.remote.NewsApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryTest {
    @Mock lateinit var apiService: NewsApiService
    private lateinit var repository: NewsRepository

    @Before
    fun setup() {
        repository = NewsRepository(apiService)
    }

    @Test
    fun `fetch news returns data`() = runBlockingTest {
        val mockResponse = NewsResponse(listOf(Article("Title", "Desc", "URL")))
        Mockito.`when`(apiService.getNews("android", 1, 10, "YOUR_API_KEY")).thenReturn(mockResponse)
        val result = repository.fetchNews("android", 1)
        Assert.assertEquals(1, result.size)
    }
}*/
