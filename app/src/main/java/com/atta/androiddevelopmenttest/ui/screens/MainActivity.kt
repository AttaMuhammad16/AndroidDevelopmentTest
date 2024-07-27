package com.atta.androiddevelopmenttest.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.atta.androiddevelopmenttest.R
import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.ui.components.AllArticlesScreen
import com.atta.androiddevelopmenttest.ui.theme.AndroidDevelopmentTestTheme
import com.atta.androiddevelopmenttest.ui.viewmodel.MainViewModel
import com.atta.androiddevelopmenttest.utils.MyResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel
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
