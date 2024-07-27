package com.atta.androiddevelopmenttest.data.retrofit

import com.atta.androiddevelopmenttest.models.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=tesla&from=2024-06-23&sortBy=publishedAt&apiKey=3c20e69e602943ec84c13c0fab501e12



// it will make a complete url for server request
interface ApiService {
    @GET("v2/everything")
    suspend fun getArticles(
        @Query("q") query: String = "tesla",
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
        @Query("language") language: String = "en",
    ): ArticlesResponse
}
