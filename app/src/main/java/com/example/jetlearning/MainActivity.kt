package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    TitlesOfBook()
                }
            }
        }
    }
}


@Composable
fun TitlesOfBook() {
    LazyColumn {
        val books = listOf("Zero to One", "Otets", "Compose Cookbook")
        item {
            Text(text = "List of Books", modifier = Modifier.size(56.dp), color = Color.Magenta)
        }
        items(books) { book ->
            Text(text = book)
        }
        item {
            Text(
                text = "Tv Shows",
                modifier = Modifier
                    .padding(16.dp)
                    .size(56.dp),
                color = Color.Red,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif
            )
        }
        val tvShows = listOf("Sambaqa", "Jerbaqa")
        items(tvShows) { tvShow ->
            Text(text = tvShow)
        }
    }
}