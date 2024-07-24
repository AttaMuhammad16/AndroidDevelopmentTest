package com.atta.androiddevelopmenttest.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.atta.androiddevelopmenttest.R
import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.ui.theme.AndroidDevelopmentTestTheme
import com.atta.androiddevelopmenttest.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundleArticle=intent.getParcelableExtra<Article>("article")
        setContent {
            AndroidDevelopmentTestTheme {
                LaunchedEffect(Unit) {
                    window.statusBarColor= ContextCompat.getColor(this@DetailActivity, R.color.red)
                }
                Scaffold(topBar = {
                    TopAppBar(title = { Text(text = "Detailed", style = TextStyle(color = Color.White, fontSize = 17.sp), modifier = Modifier.padding(start = 5.dp)) }, colors = TopAppBarColors(containerColor = Color.Red, titleContentColor = Color.White, actionIconContentColor = Color.White, navigationIconContentColor = Color.White, scrolledContainerColor = Color.White), navigationIcon = { Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            finish()
                        }
                    )})
                }, modifier = Modifier.fillMaxSize(), containerColor = Color.White) { innerPaddin->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingValues(top = innerPaddin.calculateTopPadding()+10.dp, start = 10.dp, end = 10.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(10.dp),
                        onClick = {
                        }
                    ) {
                        Text(
                            text = bundleArticle?.title?:"Not found",
                            style = TextStyle(fontSize = 18.sp, color = Color.Red, fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )
                        Text(
                            text = "Author: ${bundleArticle?.author}",
                            style = TextStyle(fontSize = 15.sp, color = Color.Black),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )
                        Image(
                            painter = rememberAsyncImagePainter(bundleArticle?.urlToImage),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(300.dp)
                        )

                        Text(
                            text = bundleArticle?.description?:"Not found",
                            style = TextStyle(fontSize = 15.sp, color = Color.Black),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )


                    }
                }

            }
        }
    }
}



