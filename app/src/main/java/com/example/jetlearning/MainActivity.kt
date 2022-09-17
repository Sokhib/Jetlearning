package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    var user by remember {
                        mutableStateOf(User("So", "Soft Dev"))
                    }
                    GreetingCard(user) {
                        user = User(Math.random().toString(), "Android Dev")
                    }
                }
            }
        }
    }
}

// If value is not changed, particular composable is not going through recomposition
@Composable
fun GreetingCard(user: User, onClick: () -> Unit) {
    Text(text = user.name)
    Greeting(user, onClick)
}

@Composable
fun Greeting(user: User, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = user.title)
    }
}

data class User(var name: String, var title: String)
