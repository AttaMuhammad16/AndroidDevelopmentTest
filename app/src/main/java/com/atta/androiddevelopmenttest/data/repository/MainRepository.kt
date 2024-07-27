package com.atta.androiddevelopmenttest.data.repository

import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.models.ArticlesResponse
import com.atta.androiddevelopmenttest.utils.Constants.API_KEY
import com.atta.androiddevelopmenttest.utils.MyResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun fetchArticles(query :String,from:String,sortedBy:String,apiKey:String):MyResult<List<Article>>
}