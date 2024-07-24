package com.atta.androiddevelopmenttest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atta.androiddevelopmenttest.data.repository.MainRepository
import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.utils.Constants.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private var mainRepository: MainRepository):ViewModel() {

    private var _articles= MutableStateFlow(listOf<Article>())
    val articles:StateFlow<List<Article>> = _articles

    private val _isError = MutableStateFlow("")
    val isError=_isError

    fun fetchArticles(query :String="tesla",from:String="2024-07-22",sortedBy:String="publishedAt",apiKey:String= API_KEY){
        viewModelScope.launch {
            val fetchedArticles=mainRepository.fetchArticles(query, from, sortedBy, apiKey)
            fetchedArticles.whenSuccess {
                _articles.value=it
            }
            fetchedArticles.whenError {
                _isError.value=it.message.toString()
            }
        }
    }

}