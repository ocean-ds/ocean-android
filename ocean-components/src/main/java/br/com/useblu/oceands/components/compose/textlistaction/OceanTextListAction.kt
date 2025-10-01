package br.com.useblu.oceands.components.compose.textlistaction

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTextNotBlank
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.extensions.compose.disabledOverlay
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanTextListAction(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    kind: OceanTextListActionKind,
    enabled: Boolean = true,
    divider: Boolean = true,
    isLoading: Boolean = false
) {
    Column(
        modifier = modifier
            .clickable(enabled = kind.isContainerAction()) {
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
            Column(
                modifier = Modifier.weight(1f)
            ) {
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

            ActionRepresentation(kind = kind)
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
        }
    }
}
