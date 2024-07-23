package com.atta.androiddevelopmenttest.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialogDefaults.titleContentColor
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.atta.androiddevelopmenttest.R
import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.ui.samplerows.NewsArticleSampleRow
import com.atta.androiddevelopmenttest.ui.theme.AndroidDevelopmentTestTheme
import com.atta.androiddevelopmenttest.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    private var list= listOf<Article>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.fetchArticles(from ="2024-06-23")

        setContent {
            AndroidDevelopmentTestTheme {

                LaunchedEffect(Unit) {
                    window.statusBarColor=ContextCompat.getColor(this@MainActivity, R.color.red)
                }

                list=mainViewModel.articles.collectAsState().value

                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.White,
                    topBar = {
                        TopAppBar(title = { Text(text = "News Articles")}, colors = topAppBarColors(
                        containerColor = Color.Red,
                        titleContentColor = Color.White,
                       ))
                    }
                ) { innerPadding ->

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (list.isNotEmpty()){
                            LazyColumn {
                                items(list.size){it->
                                    val data=list[it]
                                    NewsArticleSampleRow(data.title,data.description?:"",data.publishedAt, onClickCallBack = {

                                    })
                                }
                            }
                        }else{
                            CircularProgressIndicator(color = Color.Red, strokeCap = StrokeCap.Round, strokeWidth = 4.dp)
                        }
                    }
                }

            }
        }
    }
}

