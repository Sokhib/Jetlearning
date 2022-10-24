package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {}
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    JetlearningTheme {}
}