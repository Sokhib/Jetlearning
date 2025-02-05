package com.example.jetlearning

import AedAmountVisualTransformation
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetlearning.ui.theme.JetlearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {}
        }
    }
}


@Preview(
    showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true
)
@Composable
fun OnboardingPreview() {
    JetlearningTheme {
//        LoaderComponent()
        ErrorScreen()
//        AedAmountTextField()
    }
}

@Composable
fun LoaderComponent(modifier: Modifier = Modifier.size(100.dp)) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(
            progress = 0.4f,
            strokeWidth = 1.dp,
            color = Color.Green,
            modifier = Modifier.size(33.dp)
        )
        Text(
            text = "Loading...",
            modifier = Modifier.align(Alignment.BottomCenter),
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun ErrorScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ErrorImage()
        HeadingText()
        TextWithBoldSuffix()
        val context = LocalView.current.context
        ContactSupportText({
            Toast.makeText(context, "Url opened", Toast.LENGTH_SHORT).show()
        })
        PrimaryOutlinedButton(onClick = {
            Toast.makeText(
                context,
                "Retry clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }, text = "Retry")
    }
}

@Composable
fun ErrorImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.btc),
        contentDescription = "Default notification image",
        modifier = modifier
            .fillMaxWidth(0.5f)
            .clip(RoundedCornerShape(25.dp))
            .border(BorderStroke(2.dp, Color.Green), RoundedCornerShape(25.dp))
            .padding(8.dp)
    )
}

@Composable
private fun HeadingText(
    text: String = "Whoops, we couldn't load the data!", modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(horizontal = 16.dp)
    )
}


@Composable
private fun TextWithBoldSuffix(
    prefix: String = "Error code: ", suffix: String = "AA-39", modifier: Modifier = Modifier
) {
    Text(text = buildAnnotatedString {
        append(prefix)
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append(suffix)
        }
    })
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun ContactSupportText(
    onContactClicked: () -> Unit, modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        append("Seeing this message too often?\n")
        append("Contact ")
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline, color = Color.Blue
            )
        ) {
            withAnnotation(tag = "help_tag", annotation = "www.google.com") {
                append("tech support")
            }
        }
    }
    val context = LocalView.current.context
    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "help_tag", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    openUrl(context = context, annotation.item)
                    onContactClicked()
                }
        },
        style = TextStyle.Default.merge(TextStyle(textAlign = TextAlign.Center)),
        modifier = modifier
    )
}

private fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

@Composable
fun PrimaryOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.padding(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colors.primary, // Using primary color for text
            backgroundColor = Color.LightGray // No background color
        ),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Text(text = text)
    }
}

@Composable
private fun AedAmountTextField() {
    OutlinedTextField(
        value = "1000000",
        onValueChange = {},
        visualTransformation = AedAmountVisualTransformation()
    )
}