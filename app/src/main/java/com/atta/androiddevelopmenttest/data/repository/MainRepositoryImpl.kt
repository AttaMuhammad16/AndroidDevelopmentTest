package com.atta.androiddevelopmenttest.data.repository

import com.atta.androiddevelopmenttest.data.retrofit.ApiService
import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.utils.MyResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.await
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService) :MainRepository {
    override suspend fun fetchArticles(query: String, from: String, sortedBy: String, apiKey: String):MyResult<List<Article>> {
       return withContext(Dispatchers.IO){
           try {
              val response=apiService.getArticles(query,from,sortedBy,apiKey).await().articles
               MyResult.Success(response)
           }catch (e:HttpException){
               MyResult.Error(e.message?:"Something wrong HttpException")
           }catch (e:Exception){
               MyResult.Error(e.message?:"Something wrong Exception")
           }
        }

    }
}