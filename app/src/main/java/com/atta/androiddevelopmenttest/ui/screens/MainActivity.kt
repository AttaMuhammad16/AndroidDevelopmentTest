package com.atta.androiddevelopmenttest.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.atta.androiddevelopmenttest.ui.theme.AndroidDevelopmentTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidDevelopmentTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {

                    }
                }
            }
        }
    }
}

