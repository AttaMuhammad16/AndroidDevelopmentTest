package com.atta.androiddevelopmenttest.data.repository

import com.atta.androiddevelopmenttest.data.retrofit.ApiService
import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.utils.MyResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.await
import retrofit2.awaitResponse
import javax.inject.Inject



class MainRepositoryImpl @Inject constructor(private val apiService: ApiService) :MainRepository {

    override suspend fun fetchArticles(query: String, from: String, sortedBy: String, apiKey: String):MyResult<List<Article>> {
       return withContext(Dispatchers.IO){
           try {
              val response=apiService.getArticles(query,from,sortedBy,apiKey)
               MyResult.Success(response.articles)
           }catch (e:HttpException){
               MyResult.Error(e.message?:"Something went wrong with the HTTP request")
           }catch (e:Exception){
               MyResult.Error(e.message?:"An unexpected error occurred")
           }
        }
    }




}