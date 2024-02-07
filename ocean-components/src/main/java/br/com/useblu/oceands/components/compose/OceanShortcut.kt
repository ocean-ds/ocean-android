package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanShortcutPreview() {
    val models = listOf(
        OceanShortcutModel(
            label = "TinyVertical",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            action = {}
        ),
        OceanShortcutModel(
            label = "TinyVertical",
            icon = OceanIcons.ACADEMIC_CAP_SOLID
        ),

        OceanShortcutModel(
            label = "TinyHorizontal",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.TinyHorizontal
        ),
        OceanShortcutModel(
            label = "TinyHorizontal",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.TinyHorizontal
        ),

        OceanShortcutModel(
            label = "TinyVertical Blocked",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            blocked = true
        ),
        OceanShortcutModel(
            label = "TinyHor Blocked",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            blocked = true,
            layout = OceanShortcutLayout.TinyHorizontal
        ),

        OceanShortcutModel(
            label = "Small",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.Small,
            badgeType = OceanBadgeType.WARNING,
            count = "120"
        ),
        OceanShortcutModel(
            label = "Small",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.Small
        ),

        OceanShortcutModel(
            label = "Small",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.Small
        ),
        OceanShortcutModel(
            label = "MediumHorizontal",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.MediumHorizontal,
            description = "Lorem ipsum dolor sit amet, consectetur."
        ),

        OceanShortcutModel(
            label = "MediumVertical Disabled",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.MediumVertical,
            description = "Lorem ipsum dolor sit amet, consectetur.",
            disabled = true
        ),
        OceanShortcutModel(
            label = "MediumVertical Disabled",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.MediumVertical,
            description = "Lorem ipsum dolor sit amet, consectetur.",
            disabled = true
        ),

        OceanShortcutModel(
            label = "Medium Vertical No Descr",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.MediumVertical,
            description = "",
        ),
        OceanShortcutModel(
            label = "MdVer No Descr",
            icon = OceanIcons.ACADEMIC_CAP_SOLID,
            layout = OceanShortcutLayout.MediumVertical,
            description = "",
        )
    )

    OceanShortcutList(
        models = models,
        columns = 2
    )
}

@Composable
fun OceanShortcutList(
    models: List<OceanShortcutModel>,
    columns: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        for (i in models.indices step columns) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                for (j in 0 until columns) {
                    if (i + j < models.size) {
                        OceanShortcut(
                            model = models[i + j],
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            OceanSpacing.StackXS()
        }
    }
}

@Composable
fun OceanShortcut(
    model: OceanShortcutModel,
    modifier: Modifier = Modifier
) {
    OceanShortcut(
        label = model.label,
        icon = model.icon,
        modifier = modifier,
        description = model.description,
        count = model.count,
        badgeType = model.badgeType,
        action = model.action,
        layout = model.layout,
        blocked = model.blocked,
        disabled = model.disabled
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OceanShortcut(
    label: String,
    icon: OceanIcons,
    modifier: Modifier = Modifier,
    description: String? = null,
    count: String? = null,
    badgeType: OceanBadgeType? = null,
    action: (() -> Unit)? = null,
    layout: OceanShortcutLayout = OceanShortcutLayout.TinyVertical,
    blocked: Boolean = false,
    disabled: Boolean = false
) {
    val backgroundColor = if (disabled) {
        OceanColors.interfaceLightDown
    } else OceanColors.interfaceLightPure

    val iconColor = if (disabled) {
        OceanColors.interfaceDarkUp
    } else OceanColors.brandPrimaryDown

    val titleColor = if (disabled) {
        OceanColors.interfaceDarkUp
    } else OceanColors.interfaceDarkDeep

    val borderColor = if (disabled) {
        OceanColors.interfaceLightDown
    } else OceanColors.interfaceLightDown

    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        ),
        enabled = !disabled && !blocked && action != null,
        onClick = {
            action?.invoke()
        }
    ) {
        Box {
            if (blocked) {
                OceanIcon(
                    iconType = OceanIcons.LOCK_CLOSED_SOLID,
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .size(16.dp)
                        .align(Alignment.TopEnd),
                    tint = OceanColors.interfaceDarkUp
                )
            }

            if (count != null && badgeType != null) {
                OceanBadge(
                    text = count,
                    type = badgeType,
                    size = OceanBadgeSize.Medium,
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .align(Alignment.TopEnd)
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                layout.GetTextIconLayout {
                    OceanIcon(
                        iconType = icon,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )

                    layout.GetSpacer()

                    Text(
                        text = label,
                        style = OceanTextStyle.heading5,
                        color = titleColor
                    )
                }

                if (layout.canShowDescription() && !description.isNullOrBlank()) {
                    val descriptionColor = if (disabled) {
                        OceanColors.interfaceDarkUp
                    } else OceanColors.interfaceDarkDown

                    OceanSpacing.StackXXS()

                    Text(
                        text = description,
                        style = OceanTextStyle.caption,
                        color = descriptionColor
                    )
                }
            }
        }
    }
}

@Immutable
enum class OceanShortcutLayout {
    TinyVertical,
    TinyHorizontal,
    Small,
    MediumVertical,
    MediumHorizontal;

    @Composable
    internal fun GetSpacer() {
        when (this) {
            TinyVertical,
            TinyHorizontal -> OceanSpacing.StackXXS()
            Small -> Spacer(modifier = Modifier.size(30.dp))
            MediumVertical -> OceanSpacing.StackXS()
            MediumHorizontal -> OceanSpacing.StackXXS()
        }
    }

    @Composable
    internal fun GetTextIconLayout(content: @Composable () -> Unit) {
        when (this) {
            TinyVertical,
            Small,
            MediumVertical -> {
                Column {
                    content()
                }
            }
            TinyHorizontal,
            MediumHorizontal -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    content()
                }
            }
        }
    }

    fun canShowDescription() = this == MediumHorizontal || this == MediumVertical
}

@Immutable
data class OceanShortcutModel(
    val label: String,
    val description: String? = null,
    val icon: OceanIcons,
    val count: String? = null,
    val badgeType: OceanBadgeType? = null,
    val action: (() -> Unit)? = null,
    val layout: OceanShortcutLayout = OceanShortcutLayout.TinyVertical,
    val blocked: Boolean = false,
    val disabled: Boolean = false
)