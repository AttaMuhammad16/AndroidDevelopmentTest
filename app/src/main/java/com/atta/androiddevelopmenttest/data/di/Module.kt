package com.atta.androiddevelopmenttest.data.di

import com.atta.androiddevelopmenttest.data.repository.MainRepository
import com.atta.androiddevelopmenttest.data.repository.MainRepositoryImpl
import com.atta.androiddevelopmenttest.data.retrofit.ApiService
import com.atta.androiddevelopmenttest.ui.viewmodel.MainViewModel
import com.atta.androiddevelopmenttest.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRepositoryObj(apiService: ApiService):MainRepository{
        return MainRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideViewModel(mainRepository: MainRepository):MainViewModel{
        return MainViewModel(mainRepository)
    }

}