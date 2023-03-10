package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {}
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview(@PreviewParameter(NamePreviewProvider::class) name: String) {
    JetlearningTheme {
        Hello(name = name)
    }
}

@Composable
fun Hello(name: String) {
    Text(text = name, fontSize = with(LocalDensity.current) {
        32.toSp()
    })

}