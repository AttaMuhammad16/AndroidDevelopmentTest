package com.atta.androiddevelopmenttest.ui.samplerows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NewsArticleSampleRow(articleTitle: String, description: String, date: String,onClickCallBack:()->Unit) {
    Card(
        modifier = Modifier.padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp),
        onClick = onClickCallBack
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(
                text = articleTitle,
                style = TextStyle(fontSize = 18.sp, color = Color.Red, fontWeight = FontWeight.Bold)
            )
            if (!description.isNullOrEmpty()){
                Text(
                    text = description,
                    style = TextStyle(fontSize = 15.sp, color = Color.Black)
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = date,
                style = TextStyle(fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.End
            )
        }
    }
}