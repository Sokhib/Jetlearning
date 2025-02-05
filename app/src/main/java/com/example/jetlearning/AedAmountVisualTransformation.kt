import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat

class AedAmountVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return aedAmountFilter(text)
    }

    private fun aedAmountFilter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.replace(Regex("[^\\d]"), "")
        var formattedText = ""
        var number = ""

        if (trimmed.isNotEmpty()) {
            number = trimmed.toDouble().toString()

            val amount = number
            val decimalFormat = DecimalFormat("#,##0.00") // Format with 2 decimal places
            formattedText = decimalFormat.format(amount.toDouble())
        }

        val out = "AED $formattedText"
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 0) return 4
                if (offset >= trimmed.length) return out.length
                return formattedText.length + 4 - (number.length - trimmed.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 4) return 0
                val numberOffset = offset - 4
                val originalNumberOffset = numberOffset
                if (originalNumberOffset <= 0) {
                    return 0
                }
                val numberOfCharactersToRemove = (number.length - trimmed.length)

                return numberOffset + numberOfCharactersToRemove
            }
        }

        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}