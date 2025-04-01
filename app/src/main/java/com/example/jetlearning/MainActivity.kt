package com.example.jetlearning

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
                CustomLogButton()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview() {
    JetlearningTheme {
        CustomLogButton()
    }
}

@Composable
fun CustomLogButton() {
    Log.d("TAG", "1") //Only first time
    var counter by remember {
        Log.d("TAG", "2") // Only first time
        mutableStateOf(0)
    }
    Button(onClick = { counter += 1 }) {
        Log.d("TAG", "3") // Every time, but why
        // (Because state change could affect other composables,
        // that's why all block inside button (in which counter is used) gets recomposition.)
        Text(text = "Just Static Text ")
        Text(text = "$counter")
    }
}