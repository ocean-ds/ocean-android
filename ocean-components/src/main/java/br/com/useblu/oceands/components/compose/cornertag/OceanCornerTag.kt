package br.com.useblu.oceands.components.compose.cornertag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily

/**
 * Highlight Corner Tag — internal component intended to be overlaid at the
 * top-right corner of a card container (currently consumed by [OceanCardListItem]).
 *
 * Spec: Figma Ocean DS Core (PRD Pagnet#16178, Jira MR-490).
 */

enum class OceanCornerTagColor {
    PrimaryDown,
    ComplementaryPure
}

data class OceanCornerTagStyle(
    val label: String,
    val color: OceanCornerTagColor = OceanCornerTagColor.PrimaryDown
)

@Composable
fun OceanCornerTag(
    label: String,
    modifier: Modifier = Modifier,
    color: OceanCornerTagColor = OceanCornerTagColor.PrimaryDown
) {
    if (label.isBlank()) return

    val backgroundColor: Color = when (color) {
        OceanCornerTagColor.PrimaryDown -> OceanColors.brandPrimaryDown
        OceanCornerTagColor.ComplementaryPure -> OceanColors.complementaryPure
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 8.dp))
            .background(backgroundColor)
            .height(20.dp)
            .defaultMinSize(minWidth = 0.dp)
            .padding(horizontal = 8.dp)
            .semantics { contentDescription = label },
        contentAlignment = Alignment.Center
    ) {
        OceanText(
            text = label,
            style = TextStyle(
                color = OceanColors.interfaceLightPure,
                fontFamily = OceanFontFamily.BaseExtraBold,
                // No 10sp token in ocean-tokens — smallest is OceanFontSize.xxxs (12sp).
                // Hardcoded literal follows the precedent set by OceanTagLayout.Small
                // and the Web PR (ocean-web#1244).
                fontSize = 10.sp
            )
        )
    }
}
