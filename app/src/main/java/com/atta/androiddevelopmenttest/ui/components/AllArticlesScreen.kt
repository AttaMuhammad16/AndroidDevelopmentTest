package com.atta.androiddevelopmenttest.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.ui.samplerows.NewsArticleSampleRow
import com.atta.androiddevelopmenttest.ui.screens.DetailActivity
import com.atta.androiddevelopmenttest.ui.screens.SearchActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllArticlesScreen(list:List<Article>,context:Context) {

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.White,
        topBar = {
            TopAppBar(title = { Text(text = "News Articles") }, colors = topAppBarColors(
                containerColor = Color.Red,
                titleContentColor = Color.White,
            ),
                actions = {
                    Icon(imageVector = Icons.Default.Search, contentDescription ="", modifier = Modifier.padding(end = 10.dp).clickable {
                        context.startActivity(Intent(context,SearchActivity::class.java))
                    }, tint = Color.White)
                }
            )
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
                            val intent=Intent(context,DetailActivity::class.java)
                            intent.putExtra("article",data)
                            context.startActivity(intent)
                        })
                    }
                }
            }else{
                CircularProgressIndicator(color = Color.Red, strokeCap = StrokeCap.Round, strokeWidth = 4.dp)
            }
        }
    }
}