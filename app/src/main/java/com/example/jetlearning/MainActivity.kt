package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetlearning.ui.theme.JetlearningTheme
import android.R as RUi

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
                painter = painterResource(id = RUi.drawable.ic_dialog_email),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AnimateVisibilityExample() {
    var isVisible by remember {
        mutableStateOf(false)
    }

    Column {
        Button(
            onClick = { isVisible = !isVisible },
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {
            Text("Click me")
        }

        AnimatedVisibility(visible = isVisible, enter = fadeIn() + slideInHorizontally()) {
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .size(200.dp)
            )
        }
    }

}