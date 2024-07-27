package com.atta.androiddevelopmenttest.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.atta.androiddevelopmenttest.R
import com.atta.androiddevelopmenttest.models.Article
import com.atta.androiddevelopmenttest.ui.samplerows.NewsArticleSampleRow
import com.atta.androiddevelopmenttest.ui.theme.AndroidDevelopmentTestTheme
import com.atta.androiddevelopmenttest.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidDevelopmentTestTheme {
                LaunchedEffect(Unit) {
                    window.statusBarColor= ContextCompat.getColor(this@SearchActivity, R.color.red)
                }
                MainScreen()
            }
        }
    }

    @Composable
    fun SetList(list: List<Article>) {
        val context = LocalContext.current
        LazyColumn {
            items(list.size) { index ->
                val data = list[index]
                NewsArticleSampleRow(data.title, data.description ?: "", data.publishedAt) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("article", data)
                    context.startActivity(intent)
                }
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
    var searchText by remember { mutableStateOf("") }

    val list = remember {
        mutableStateOf(mainViewModel.articles.value)
    }

    val filteredList by remember {
        derivedStateOf {
            list.value.filter {
                it.title.contains(searchText, ignoreCase = true)
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {

                    SearchBar(
                        searchText = searchText,
                        onSearchTextChanged = { searchText = it }
                    )

                },
                colors = topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White,
                ),
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            SetList(filteredList)
        }
    }
}

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    BasicTextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = Modifier
            .fillMaxWidth(fraction = 1f)
            .background(Color.LightGray, shape = MaterialTheme.shapes.small)
            .padding(start = 8.dp,top=8.dp, bottom = 8.dp, end = 24.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                if (searchText.isEmpty()) {
                    Text(
                        text = "Search...",
                        style = TextStyle(color = Color.Gray, fontSize = 18.sp)
                    )
                }
                innerTextField()
            }
        }
    )
}
}





