package com.hazem.newsapp.data.remote

import com.hazem.newsapp.data.model.Articles
import com.hazem.newsapp.data.model.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {
@GET("v2/top-headlines?apiKey=2e0f2766004d4d9ebcfadbba0d89305e")
suspend fun getNews(
    @Query("country") country: String?,
    @Query("category") category: String?
): Response<News>

}
//v2/top-headlines?country=us&category=business&apiKey=2e0f2766004d4d9ebcfadbba0d89305e