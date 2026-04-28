package br.com.useblu.oceands.components.compose.textlistreadonly

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.OceanContentList
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.OceanTagLayout
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.model.compose.OceanTextListReadOnlyState
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanTextListReadOnly(
    modifier: Modifier = Modifier,
    contentListStyle: ContentListStyle,
    state: OceanTextListReadOnlyState = OceanTextListReadOnlyState.Default,
    icon: OceanIconModel? = null,
    tag: OceanTagStyle? = null,
    showDivider: Boolean = false
) {
    val enabled = state != OceanTextListReadOnlyState.Disabled
    val isLoading = state == OceanTextListReadOnlyState.Loading

    Column(
        modifier = modifier.background(OceanColors.interfaceLightPure)
    ) {
        Row(
            modifier = Modifier.padding(OceanSpacing.xs),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
        ) {
            icon?.let {
                OceanIcon(
                    iconType = it.icon,
                    modifier = it.size?.let { size -> Modifier.size(size) } ?: Modifier,
                    tint = it.tint
                )
            }

            OceanContentList(
                modifier = Modifier.weight(1f),
                style = contentListStyle,
                isLoading = isLoading,
                enabled = enabled
            )

            tag?.takeIf { !isLoading }?.let {
                OceanTag(
                    style = it,
                    enabled = enabled
                )
            }
        }

        if (showDivider) {
            OceanDivider(
                modifier = Modifier.padding(horizontal = OceanSpacing.xs)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, heightDp = 900)
@Composable
fun OceanTextListReadOnlyPreview() = OceanTheme {
    Column(
        modifier = Modifier.background(OceanColors.interfaceLightPure),
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
    ) {
        // highlightLead + inverted — Blu balance header
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Inverted(
                title = "Saldo total na Blu",
                description = "R$ 2.500,00",
                descriptionStyle = OceanTextStyle.lead
            )
        )

        // default with caption
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Default(
                title = "Title",
                description = "Description",
                caption = "Caption"
            )
        )

        // highlight + tag
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Inverted(
                title = "Title",
                description = "Description",
                descriptionStyle = OceanTextStyle.lead
            ),
            tag = OceanTagStyle.Highlight(
                label = "Novo",
                type = OceanTagType.Highlight,
                layout = OceanTagLayout.Small()
            )
        )

        // default with leading icon + divider
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Default(
                title = "Title",
                description = "Description"
            ),
            icon = OceanIconModel(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                tint = OceanColors.interfaceDarkDown
            ),
            showDivider = true
        )

        // disabled
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Default(
                title = "Title",
                description = "Description",
                caption = "Caption"
            ),
            state = OceanTextListReadOnlyState.Disabled
        )

        // loading
        OceanTextListReadOnly(
            contentListStyle = ContentListStyle.Default(
                title = "Title",
                description = "Description"
            ),
            state = OceanTextListReadOnlyState.Loading
        )
    }
}
