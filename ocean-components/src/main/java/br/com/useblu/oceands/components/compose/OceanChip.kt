package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.Badge
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipFilterOptions
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.model.OceanFilterChip
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun OceanChipPreview() {
    val models = listOf(
        OceanBasicChip(
            id = "1",
            label = "Basic Chip",
            icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE,
            state = OceanChipItemState.INACTIVE_HOVER,
            badge = Badge(5, OceanBadgeType.PRIMARY),
            onClick = {},
        ),
        OceanFilterChip(
            label = "Filtro Teste",
            id = "9999",
            badge = null,
            state = OceanChipItemState.DEFAULT,
            filterOptions = OceanChipFilterOptions.MultipleChoice(
                title = "Status do Pagamento",
                optionsItems = emptyList(),
                primaryButtonLabel = "Salvar",
                secondaryButtonLabel = "Limpar",
                onPrimaryButtonClick = {},
                onSecondaryButtonClick = {}
            )
        ),
        OceanFilterChip(
            label = "Filtro Teste",
            id = "9999",
            badge = Badge(5, OceanBadgeType.PRIMARY_INVERTED),
            state = OceanChipItemState.DEFAULT,
            filterOptions = OceanChipFilterOptions.MultipleChoice(
                title = "Status do Pagamento",
                optionsItems = emptyList(),
                primaryButtonLabel = "Salvar",
                secondaryButtonLabel = "Limpar",
                onPrimaryButtonClick = {},
                onSecondaryButtonClick = {}
            )
        )
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        models.forEach {
            OceanChip(model = it)
        }
    }

}

@Composable
fun OceanChip(
    model: OceanChip
) {
    when (model) {
        is OceanBasicChip -> {
            OceanBasicChip(
                model = model
            )
        }
        is OceanFilterChip -> {
            OceanFilterChip(
                model = model
            )
        }
    }
}

@Composable
fun OceanBasicChip(
    model: OceanBasicChip
) {
    Row(
        modifier = Modifier
            .height(32.dp)
            .background(
                color = getBackgroundColor(model),
                shape = RoundedCornerShape(24.dp)
            )
            .clip(shape = RoundedCornerShape(24.dp))
            .clickable {
                model.onClick(true)
            }
            .padding(horizontal = 12.dp),
        verticalAlignment = CenterVertically
    ) {
        model.icon?.let {
            OceanIcon(
                iconType = it,
                modifier = Modifier.size(16.dp),
                tint = getContentColor(model)
            )

            OceanSpacing.StackXXXS()
        }

        Text(
            text = model.label,
            fontFamily = OceanFontFamily.BaseBold,
            color = getContentColor(model)
        )

        model.badge?.let {
            OceanSpacing.StackXXXS()

            OceanBadge(
                text = model.badge.text.toString(),
                type = it.type,
                size = OceanBadgeSize.Small
            )
        }
    }
}

@Composable
fun OceanFilterChip(
    model: OceanFilterChip
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .height(32.dp)
            .background(
                color = getBackgroundColor(model),
                shape = RoundedCornerShape(24.dp)
            )
            .clip(shape = RoundedCornerShape(24.dp))
            .clickable {
                model.filterOptions.showBottomSheet(context)
            }
            .padding(start = 12.dp, end = 8.dp),
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = model.label,
            fontFamily = OceanFontFamily.BaseBold,
            color = getContentColor(model)
        )

        model.badge?.let {
            OceanSpacing.StackXXXS()

            OceanBadge(
                text = model.badge.text.toString(),
                type = it.type,
                size = OceanBadgeSize.Small
            )
        }

        OceanSpacing.StackXXXS()

        OceanIcon(
            iconType = OceanIcons.CHEVRON_DOWN_SOLID,
            modifier = Modifier.size(16.dp),
            tint = getContentColor(model)
        )
    }
}

@Composable
private fun getContentColor(
    item: OceanChip
) = when (item.state) {
    OceanChipItemState.INACTIVE_HOVER -> OceanColors.brandPrimaryPure
    OceanChipItemState.DISABLED_ACTIVE,
    OceanChipItemState.DISABLED_INACTIVE -> OceanColors.interfaceDarkUp
    OceanChipItemState.ACTIVE_HOVER,
    OceanChipItemState.DEFAULT -> OceanColors.interfaceLightPure
}

@Composable
private fun getBackgroundColor(
    item: OceanChip
) = when (item.state) {
    OceanChipItemState.INACTIVE_HOVER -> OceanColors.interfaceLightDown
    OceanChipItemState.ACTIVE_HOVER -> OceanColors.brandPrimaryDown
    OceanChipItemState.DEFAULT -> OceanColors.brandPrimaryPure
    OceanChipItemState.DISABLED_ACTIVE,
    OceanChipItemState.DISABLED_INACTIVE -> OceanColors.interfaceLightDown
}