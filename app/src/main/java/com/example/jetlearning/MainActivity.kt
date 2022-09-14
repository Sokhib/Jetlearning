package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
                Surface(
                    color = MaterialTheme.colors.primary
                ) {

                    FancyLike()
                }
            }
        }
    }
}

@Composable
fun FancyLike() {
    var isLiked by remember {
        mutableStateOf(false)
    }
    var filledLikeScale by remember {
        mutableStateOf(0f)
    }
    var showEmptyLike by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(isLiked) {
        if (isLiked) {
            animate(
                0f, 1.0f, animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ) { value, _ ->
                filledLikeScale = value
                if (value >= 1f) showEmptyLike = false
            }
        }
    }
    if (showEmptyLike) {
        Icon(Icons.Default.FavoriteBorder, "", Modifier.clickable {
            isLiked = !isLiked
        })
    }
    if (filledLikeScale > 0f) {
        Icon(
            Icons.Default.Favorite,
            "",
            Modifier
                .scale(filledLikeScale)
                .clickable {
                    filledLikeScale = 0f
                    isLiked = !isLiked
                    showEmptyLike = true
                })
    }
}


@Composable
fun OrangeBox() {
    var big by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier
        .padding(10.dp)
        .border(4.dp, Color.Black, CircleShape)
        .clickable { big = !big }
        .size(animateDpAsState(targetValue = if (big) 128.dp else 64.dp).value)
        .clip(RoundedCornerShape(24.dp))
    )
}


@Composable
fun AvatarView() {
    Image(
        painter = painterResource(id = R.drawable.ic_andr_icon),
        contentDescription = "",
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Brush.sweepGradient(listOf(Color.Yellow, Color.Cyan)), CircleShape)
            .border(4.dp, Color.White, CircleShape)
            .clip(CircleShape)
            .clickable {}
    )
}

@Composable
fun TrackList(tracks: List<Int> = listOf(1, 2, 3, 4, 5)) {
    var selectedTrack by rememberSaveable { mutableStateOf<Int?>(null) }
    LazyColumn {
        items(tracks) { track ->
            Track(
                isSelectedTrack = track == selectedTrack,
                track = track,
                onClick = { selectedTrack = track }
            )
        }
    }
}

@Composable
fun Track(
    isSelectedTrack: Boolean,
    track: Int,
    onClick: () -> Unit
) {
    Row(modifier = Modifier.clickable(onClick = onClick)) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_airplay_24),
            colorFilter = ColorFilter.tint(if (isSelectedTrack) Color.Green else Color.Black),
            contentDescription = "queen",
            modifier = Modifier
                .padding(8.dp)
                .size(56.dp)
        )
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "$track")
            Text(
                text = "Queen",
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetlearningTheme {
        TrackList()
    }
}