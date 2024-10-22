package com.atta.androiddevelopmenttest.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.core.content.ContextCompat
import com.atta.androiddevelopmenttest.R
import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.ui.components.AllArticlesScreen
import com.atta.androiddevelopmenttest.ui.theme.AndroidDevelopmentTestTheme
import com.atta.androiddevelopmenttest.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity @Inject constructor(private var mainViewModel: MainViewModel): ComponentActivity() {

    private var list= listOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.fetchArticles()

        setContent {

            AndroidDevelopmentTestTheme {
                LaunchedEffect(Unit) {
                    window.statusBarColor=ContextCompat.getColor(this@MainActivity, R.color.red)
                }
                list=mainViewModel.articles.collectAsState().value
                AllArticlesScreen(list = list, context = this@MainActivity)
            }

        }
    }
}
