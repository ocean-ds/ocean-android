package br.com.useblu.oceands.components.compose

import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat.getFont
import br.com.useblu.oceands.R
import br.com.useblu.oceands.extensions.parseAsHtml
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontSize

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun OceanHtmlPreview() {
    Column {
        OceanHtmlText(
            text = stringResource(R.string.text)
        )
    }
}

@Composable
fun OceanHtmlText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = OceanColors.interfaceDarkPure,
    fontFamily: Int = R.font.font_family_base_regular,
    fontSize: TextUnit = OceanFontSize.xxxs,
    maxLines: Int = Int.MAX_VALUE
) {
    AndroidView(
        modifier = modifier,
        factory = {
            TextView(it).apply {
                this.setTextColor(color.toArgb())
                this.textSize = fontSize.value
                this.typeface = getFont(context, fontFamily)
                this.maxLines = maxLines
            }
        },
        update = {
            it.text = text.parseAsHtml()
        }
    )
}
