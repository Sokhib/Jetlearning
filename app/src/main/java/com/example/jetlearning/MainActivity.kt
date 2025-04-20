package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
//                AnimatedTextOnClick()
//                AnimatedContentCounter()
//                AnimatedContentV2Counter()
                AnimatedContentV3Counter()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview() {
    JetlearningTheme {
        AnimatedContentV3Counter()
    }
}

@Composable
fun AnimatedTextOnClick() {
    var isVisible by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { isVisible = !isVisible }) {
            Text("Toggle Visibility")
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn() + expandIn(), // slideIn slideOut, scaleIn, scaleOut
            exit = fadeOut() + shrinkOut()
        ) {
            Text(text = "Hello! I'm now visible.")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentCounter(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    Column {
        Button(onClick = { count++ }) {
            Text("Click counter")
        }

        AnimatedContent(targetState = count, modifier = modifier) { targetCount ->
            Text(text = "Clicked $targetCount times")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentV2Counter(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    Column {
        Button(onClick = { count++ }) {
            Text("Click counter")
        }

        AnimatedContent(
            targetState = count, modifier = modifier,
            transitionSpec = {
                slideInHorizontally { -it } + fadeIn() with slideOutHorizontally { it } + fadeOut() using SizeTransform(
                    false
                )
            }) { targetCount ->
            Text(text = "Clicked $targetCount times")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentV3Counter(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    Column {
        Button(onClick = { count++ }) {
            Text("Click counter")
        }

        Crossfade(targetState = count, modifier = modifier) { targetCount ->
            Text(text = "Clicked $targetCount times")
        }
    }
}