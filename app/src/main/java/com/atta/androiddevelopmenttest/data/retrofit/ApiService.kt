package com.atta.androiddevelopmenttest.data.retrofit

import com.atta.androiddevelopmenttest.models.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("v2/everything")
    fun getArticles(
        @Query("q") query: String="tesla",
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
        @Query("language") language: String="en",
    ): Call<ArticlesResponse>
}
