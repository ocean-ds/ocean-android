package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

@Composable
fun OceanCardReadOnly(
    modifier: Modifier = Modifier,
    contentListStyle: ContentListStyle,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    targetBorderColor: Color? = null
) {
    val backgroundColor = OceanColors.interfaceLightPure
    val borderColor = targetBorderColor ?: OceanColors.interfaceLightDown

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        ),
        shape = OceanBorderRadius.SM.allCorners.shape(),
        border = BorderStroke(1.dp, borderColor),
        enabled = enabled,
        onClick = { /* No-op */ }
    ) {
        OceanContentList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(OceanSpacing.xs),
            style = contentListStyle,
            isLoading = isLoading,
            enabled = enabled
        )
    }
}

@Preview
@Composable
fun OceanCardReadOnlyPreview() {
    OceanTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(OceanColors.interfaceLightPure)
                .padding(OceanSpacing.xs)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
        ) {
            OceanCardReadOnly(
                contentListStyle = ContentListStyle.Default(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                )
            )

            OceanCardReadOnly(
                contentListStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                )
            )

            OceanCardReadOnly(
                contentListStyle = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    caption = "Caption",
                    captionStyle = OceanTextStyle.captionBold.copy(color = OceanColors.brandPrimaryPure)
                )
            )
        }
    }
}
