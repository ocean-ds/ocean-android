package br.com.useblu.oceands.components.compose.textlistaction

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanShimmering
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.OceanTagLayout
import br.com.useblu.oceands.components.compose.OceanTextNotBlank
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.extensions.compose.disabledOverlay
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanTagModel
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderRadius
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanTextListAction(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    tag: OceanTagModel? = null,
    kind: OceanTextListActionKind,
    enabled: Boolean = true,
    divider: Boolean = true,
    isLoading: Boolean = false
) {
    Column(
        modifier = modifier
            .clickable(enabled = kind.isContainerAction() && !isLoading) {
                (kind as? OceanTextListActionKindContainerAction)?.action?.invoke()
            }
            .background(OceanColors.interfaceLightPure)
            .disabledOverlay(isDisabled = !enabled)
    ) {
        Row(
            modifier = Modifier
                .padding(OceanSpacing.xs),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
        ) {
            Content(
                modifier = Modifier.weight(1f),
                title = title,
                description = description,
                isLoading = isLoading
            )

            tag?.let {
                OceanTag(
                    model = it,
                    layout = OceanTagLayout.Medium()
                )
            }

            if (!isLoading) {
                ActionRepresentation(kind = kind)
            }
        }
        if (divider) {
            OceanDivider(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.xs)
            )
        }
    }
}

@Composable
fun OceanTextListActionLoading(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    divider: Boolean = true
) {
    OceanTextListAction(
        modifier = modifier,
        title = "",
        description = "",
        kind = OceanTextListActionKind.Chevron {},
        enabled = enabled,
        divider = divider,
        isLoading = true
    )
}

@Composable
private fun Content(
    modifier: Modifier,
    title: String,
    description: String,
    isLoading: Boolean
) {
    Column(
        modifier = modifier
    ) {
        if (isLoading) {
            LoadingContent()
            return
        }
        OceanTextNotBlank(
            text = title,
            style = OceanTextStyle.paragraph,
            color = OceanColors.interfaceDarkDeep
        )

        OceanTextNotBlank(
            text = description,
            style = OceanTextStyle.description,
            color = OceanColors.interfaceDarkDown
        )
    }
}

@Composable
private fun LoadingContent() {
    OceanShimmering { brush ->
        Row(
            modifier = Modifier.padding(bottom = OceanSpacing.xxs)
        ) {
            Spacer(
                modifier = Modifier
                    .width(96.dp)
                    .height(16.dp)
                    .borderRadius(borderRadius = OceanBorderRadius.Tiny.allCorners)
                    .background(brush)
            )
        }
        Row {
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxSize()
                    .borderRadius(borderRadius = OceanBorderRadius.Tiny.allCorners)
                    .background(brush)
            )
        }
    }
}

@Composable
private fun ActionRepresentation(
    kind: OceanTextListActionKind
) {
    when (kind) {
        is OceanTextListActionKind.Chevron -> {
            OceanIcon(
                iconType = OceanIcons.CHEVRON_RIGHT_SOLID,
                tint = OceanColors.interfaceDarkUp
            )
        }
    }
}

@Preview
@Composable
fun OceanTextListActionPreview() = OceanTheme {
    val context = LocalContext.current
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(OceanColors.interfaceLightPure)
        ) {
            OceanTextListAction(
                title = "Title",
                description = "Description",
                kind = OceanTextListActionKind.Chevron {
                    Toast.makeText(context, "Clicked 1", Toast.LENGTH_SHORT).show()
                }
            )
            OceanTextListAction(
                title = "Title",
                kind = OceanTextListActionKind.Chevron {
                    Toast.makeText(context, "Clicked 2", Toast.LENGTH_SHORT).show()
                }
            )
            OceanTextListAction(
                title = "Title",
                description = "WithTag",
                tag = OceanTagModel(
                    text = "Tag",
                    type = OceanTagType.Positive
                ),
                kind = OceanTextListActionKind.Chevron {
                    Toast.makeText(context, "Clicked 3", Toast.LENGTH_SHORT).show()
                }
            )
            OceanTextListActionLoading()
        }
    }
}
