package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanSettingsStatus
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanSettingsListItemPreview() {
    MaterialTheme {
        Column {
            OceanSettingsListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                actionText = "Click me",
                status = OceanSettingsStatus.DEFAULT,
                showDivider = true,
                onClick = {
                    println("click DEFAULT")
                }
            )

            OceanSettingsListItem(
                title = "Title",
                description = "Description grande Description grande ",
                caption = "Caption",
                actionText = "Click me",
                error = "Mensagem de erro",
                status = OceanSettingsStatus.ACTIVATED,
                showDivider = true,
                onClick = {
                    println("click ACTIVATED")
                }
            )

            OceanSettingsListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                actionText = "Click me",
                status = OceanSettingsStatus.BLOCKED,
                showDivider = true,
                onClick = {
                    println("click BLOCKED")
                }
            )

            OceanSettingsListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                actionText = "Click me",
                status = OceanSettingsStatus.BLOCKED_ACTIVATED,
                showDivider = true,
                error = "Mensagem de erro",
                onClick = {
                    println("click BLOCKED_ACTIVATED")
                }
            )

            OceanSettingsListItem(
                title = "Title",
                description = "Description",
                actionText = "Label",
                caption = "Caption",
                status = OceanSettingsStatus.PENDING,
                onClick = {
                    println("click PENDING")
                }
            )
        }
    }
}

@Composable
fun OceanSettingsListItem(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    caption: String? = null,
    onClick: (() -> Unit)? = null,
    actionText: String? = null,
    status: OceanSettingsStatus,
    error: String? = null,
    showDivider: Boolean = false
) {
    Column(modifier) {
        Row(
            modifier = Modifier
                .background(color = OceanColors.interfaceLightPure)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = OceanTextStyle.description
                )

                if (description != null) {
                    Text(
                        text = description,
                        style = OceanTextStyle.paragraph,
                        color = if (status == OceanSettingsStatus.ACTIVATED || status == OceanSettingsStatus.BLOCKED) OceanColors.interfaceDarkDeep else OceanColors.interfaceDarkUp
                    )
                }

                if (caption != null) {
                    OceanSpacing.StackXXS()
                    Text(
                        text = caption,
                        style = OceanTextStyle.caption,
                        color = OceanColors.interfaceDarkDown
                    )
                }

                if (error != null) {
                    OceanSpacing.StackXXS()
                    Text(
                        text = error,
                        style = OceanTextStyle.caption,
                        color = OceanColors.statusNegativePure
                    )
                }
            }

            val buttonStyle = when (status) {
                OceanSettingsStatus.DEFAULT -> OceanButtonStyle.PrimarySmall
                OceanSettingsStatus.ACTIVATED -> OceanButtonStyle.SecondarySmall
                else -> null
            }

            if (buttonStyle != null && actionText != null) {
                OceanButton(
                    text = actionText,
                    buttonStyle = buttonStyle,
                    onClick = { onClick?.invoke() }
                )
            }

            if (status == OceanSettingsStatus.BLOCKED || status == OceanSettingsStatus.BLOCKED_ACTIVATED) {
                OceanIcon(
                    iconType = OceanIcons.LOCK_CLOSED_SOLID,
                    modifier = Modifier.size(20.dp),
                    tint = OceanColors.interfaceDarkUp
                )
            }

            if (status == OceanSettingsStatus.PENDING) {
                OceanTag(
                    label = actionText ?: "",
                    type = OceanTagType.Warning
                )
            }
        }

        if (showDivider) {
            HorizontalDivider(
                color = OceanColors.interfaceLightDown
            )
        }
    }
}
