package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanShortcutPreview() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            OceanShortcut(
                label = "Label",
                icon = OceanIcons.ACADEMIC_CAP_SOLID,
            )
        }

        item {
            OceanShortcut(
                label = "Label",
                icon = OceanIcons.ACADEMIC_CAP_SOLID,
            )
        }

        item {
            OceanShortcut(
                label = "Label",
                icon = OceanIcons.ACADEMIC_CAP_SOLID,
                blocked = true
            )
        }
    }
}

@Composable
fun OceanShortcut(
    label: String,
    icon: OceanIcons,
    modifier: Modifier = Modifier,
    count: String? = null,
    description: String? = null,
    badgeType: OceanBadgeType? = null,
    action: () -> Unit = {},
    layout: OceanShortcutLayout = OceanShortcutLayout.TinyVertical,
    blocked: Boolean = false
) {

    Box(
        modifier = modifier
            .background(
                color = OceanColors.interfaceLightPure,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                action()
            }
    ) {
        if (blocked) {
            OceanIcon(
                iconType = OceanIcons.LOCK_CLOSED_SOLID,
                modifier = Modifier
                    .size(16.dp)
                    .padding(top = 8.dp, end = 8.dp)
                    .align(Alignment.TopEnd)
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            OceanIcon(
                iconType = icon,
                tint = OceanColors.brandPrimaryDown,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = label,
                style = OceanTextStyle.heading5,
                color = OceanColors.interfaceDarkDown
            )
        }
    }
}

enum class OceanShortcutLayout {
    TinyVertical,
    TinyHorizontal,
    Small,
    MediumVertical,
    MediumHorizontal;


}