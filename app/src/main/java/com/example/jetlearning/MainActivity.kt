package com.example.jetlearning

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
                PulsateEffect()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    JetlearningTheme {
        PulsateEffect()
    }
}

@Composable
fun PulsateEffect() {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.pressClick()
        ) {
            Text(text = "Click Me")
        }

        Button(
            onClick = { },
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.advancedShadow(
                color = Color.Gray,
                alpha = .5f,
                cornersRadius = 12.dp,
                shadowBlurRadius = 12.dp,
                offsetY = 7.dp
            )

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dialog_email),
                contentDescription = null
            )
        }
    }
}