package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
                FavoriteCollectionsGrid()
            }
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        placeholder = {
            Text(text = stringResource(id = R.string.placeholder_search))
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.secondary),
        modifier = modifier
            .heightIn(56.dp)
            .fillMaxWidth()
    )
}

data class BodyElementData(@DrawableRes val drawable: Int, @StringRes val text: Int)

private val bodyElementData = listOf(
    BodyElementData(R.drawable.ab1_inversions, text = R.string.ab1_inversions),
    BodyElementData(R.drawable.ab2_quick_yoga, text = R.string.ab2_quick_yoga),
    BodyElementData(R.drawable.ab3_stretching, text = R.string.ab3_stretching),
    BodyElementData(R.drawable.ab4_tabata, text = R.string.ab4_tabata),
    BodyElementData(R.drawable.ab5_hiit, text = R.string.ab5_hiit),
    BodyElementData(R.drawable.ab6_pre_natal_yoga, text = R.string.ab6_pre_natal_yoga)
)

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(bodyElementData) { bodyElement ->
            AlignYourBodyElement(drawable = bodyElement.drawable, text = bodyElement.text)
        }
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int, @StringRes text: Int, modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = drawable),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.h6,
            modifier = modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
        )
    }
}

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

data class DrawableStringPair(@DrawableRes val drawable: Int, @StringRes val text: Int)

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.height(120.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(logo = item.drawable, desc = item.text)
        }
    }
}

@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier, @DrawableRes logo: Int, @StringRes desc: Int
) {
    Surface(shape = MaterialTheme.shapes.small, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = modifier.width(216.dp)
        ) {
            Image(
                painter = painterResource(id = logo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.size(56.dp)
            )
            Text(
                text = stringResource(id = desc),
                style = MaterialTheme.typography.h6,
                modifier = modifier.padding(horizontal = 8.dp)
            )
        }
    }
}