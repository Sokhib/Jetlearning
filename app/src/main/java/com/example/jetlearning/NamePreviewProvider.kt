package com.example.jetlearning

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NamePreviewProvider(override val values: Sequence<String> = sequenceOf("So", "Name", "Sa")) :
    PreviewParameterProvider<String> {
}