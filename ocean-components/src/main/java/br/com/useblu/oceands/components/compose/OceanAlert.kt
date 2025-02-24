package br.com.useblu.oceands.components.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.compose.AlertStyle
import br.com.useblu.oceands.model.compose.OceanAlertType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground

@Preview
@Composable
fun OceanAlertPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val context = LocalContext.current
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Positive",
                link = "Label",
                alertType = AlertStyle.StylePositive(),
                onClick = { println("Label Clicked") }
            )
        )

        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.WithAction(
                description = "Alert With Action and long text to validate spacing and breakline",
                alertType = AlertStyle.StyleWarning(
                    oceanIcon = null
                ),
                actionTitle = "Action",
                action = {
                    Toast.makeText(
                        context,
                        "Alert Action",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        )

        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Default(
                description = "Default Alert Info",
                alertType = AlertStyle.StyleInfo()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Default(
                description = "Default Alert Warning",
                alertType = AlertStyle.StyleWarning()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Default(
                description = "Default Alert Positive",
                alertType = AlertStyle.StylePositive()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Default(
                description = "Default Alert Negative",
                alertType = AlertStyle.StyleNegative()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Entitled Alert",
                description = "Entitled Alert Short Info",
                alertType = AlertStyle.StyleInfo(),
                tooltip = "Tooltip"
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Entitled Alert 2",
                description = "Entitled Alert Short Warning",
                alertType = AlertStyle.StyleWarning()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Entitled Alert 3",
                description = "Confirme suas informações para acessar as funções da conta.",
                alertType = AlertStyle.StylePositive(),
                button = "Button" to { }
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Entitled Alert 4",
                description = "Entitled Alert Short Negative",
                alertType = AlertStyle.StyleNegative()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledLong(
                title = "Entitled Alert 5",
                description = "Entitled Alert Long Info",
                alertType = AlertStyle.StyleInfo()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledLong(
                title = "Entitled Alert 6",
                description = "Entitled Alert Long Warning",
                alertType = AlertStyle.StyleWarning()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledLong(
                title = "Entitled Alert 7",
                description = "Entitled Alert Long Positive",
                alertType = AlertStyle.StylePositive()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledLong(
                title = "Entitled Alert 8",
                description = "Entitled Alert Long Negative",
                alertType = AlertStyle.StyleNegative()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Info",
                link = "Label",
                alertType = AlertStyle.StyleInfo()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Warning",
                link = "Label",
                alertType = AlertStyle.StyleWarning()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Positive",
                link = "Label",
                alertType = AlertStyle.StylePositive()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Negative",
                link = "Label",
                alertType = AlertStyle.StyleNegative()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Info",
                link = "Label",
                alertType = AlertStyle.StyleInfo()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                title = "Labeled Alert 2",
                description = "Entitled Alert Labeled Warning",
                link = "Label",
                alertType = AlertStyle.StyleWarning()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                title = "Labeled Alert 3",
                description = "Entitled Alert Labeled Positive",
                link = "Label",
                alertType = AlertStyle.StylePositive()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                title = "Labeled Alert 4",
                description = "Entitled Alert Labeled Negative",
                link = "Label",
                alertType = AlertStyle.StyleNegative()
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Labeled Alert 5",
                description = "Entitled Alert Labeled Negative 2",
                alertType = AlertStyle.StyleInfo(),
                button = "Action" to {
                    Toast.makeText(
                        context,
                        "Alert Action",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Labeled Alert 6",
                description = "Entitled Alert Labeled Negative 3",
                alertType = AlertStyle.StyleWarning(),
                button = "Action" to {
                    Toast.makeText(
                        context,
                        "Alert Action 2",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Labeled Alert 7",
                description = "Entitled Alert Labeled Negative 4",
                alertType = AlertStyle.StyleNegative(),
                button = "Action" to {
                    Toast.makeText(
                        context,
                        "Alert Action 3",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Bookmarked(
                title = "Labeled Alert 8",
                description = stringResource(R.string.text_with_html),
                alertType = AlertStyle.StyleNegative()
            )
        )

        OceanSpacing.StackLG()
    }
}

@Composable
fun OceanAlert(
    modifier: Modifier = Modifier,
    type: OceanAlertType
) {
    when (type) {
        is OceanAlertType.Default -> {
            OceanAlertDefaultStyle(
                modifier = modifier,
                description = type.description,
                style = type.alertType
            )
        }

        is OceanAlertType.EntitledShort -> {
            OceanAlertEntitledShort(
                modifier = modifier,
                title = type.title,
                description = type.description,
                style = type.alertType,
                button = type.button,
                tooltip = type.tooltip
            )
        }

        is OceanAlertType.EntitledLong -> {
            OceanAlertEntitledLong(
                modifier = modifier,
                title = type.title,
                description = type.description,
                style = type.alertType
            )
        }

        is OceanAlertType.Labeled -> {
            OceanAlertLabeled(
                modifier = modifier,
                title = type.title,
                description = type.description,
                link = type.link,
                linkType = type.linkType,
                linkIcon = type.linkIcon,
                style = type.alertType,
                onClick = type.onClick
            )
        }

        is OceanAlertType.Bookmarked -> {
            OceanAlertBookmarked(
                modifier = modifier,
                title = type.title,
                description = type.description,
                style = type.alertType
            )
        }

        is OceanAlertType.WithAction -> {
            OceanAlertWithAction(
                modifier = modifier,
                description = type.description,
                style = type.alertType,
                actionTitle = type.actionTitle,
                action = type.action
            )
        }
    }
}

@Composable
fun OceanAlertDefaultStyle(
    modifier: Modifier = Modifier,
    description: String,
    style: AlertStyle
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
            .borderBackground(
                color = style.alertBackgroundColor(),
                borderRadius = OceanBorderRadius.SM.allCorners
            )
            .padding(OceanSpacing.xs)
    ) {
        style.oceanIcon?.let {
            Icon(
                modifier = Modifier.padding(end = OceanSpacing.xxs),
                painter = painterResource(id = it.icon),
                tint = style.iconTint(),
                contentDescription = null
            )
        }

        val color = style.descriptionColor()
        val textSize = style.descriptionStyle().fontSize

        OceanHtmlText(
            text = description,
            color = color,
            fontSize = textSize,
            fontFamily = R.font.font_family_base_regular,
            maxLines = 2
        )
    }
}

@Composable
fun OceanAlertEntitledShort(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    style: AlertStyle,
    button: Pair<String, () -> Unit>? = null,
    tooltip: String = ""
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
            .borderBackground(
                color = style.alertBackgroundColor(),
                borderRadius = OceanBorderRadius.SM.allCorners
            )
            .padding(OceanSpacing.xs)
    ) {
        style.oceanIcon?.let {
            Icon(
                modifier = Modifier.padding(end = OceanSpacing.xxs),
                painter = painterResource(id = it.icon),
                tint = style.iconTint(),
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = CenterVertically
            ) {
                OceanText(
                    text = title,
                    style = style.titleStyle(),
                    color = style.titleColor(),
                    maxLines = 2
                )

                OceanSpacing.StackXXXS()

                if (tooltip.isNotBlank()) {
                    OceanTooltip(tooltip) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ocean_icon_info_solid),
                            tint = OceanColors.interfaceLightDeep,
                            contentDescription = null
                        )
                    }
                }
            }

            val color = style.descriptionColor()
            val textSize = style.descriptionStyle().fontSize

            OceanHtmlText(
                text = description,
                color = color,
                fontSize = textSize,
                fontFamily = R.font.font_family_base_regular,
                maxLines = 2
            )
        }

        button?.let {
            Column(
                modifier = Modifier
                    .padding(start = OceanSpacing.xs),
                horizontalAlignment = Alignment.End
            ) {
                OceanButton(
                    text = it.first,
                    buttonStyle = alertButtonStyle(style),
                    onClick = it.second
                )
            }
        }
    }
}

private fun alertButtonStyle(style: AlertStyle) = when (style) {
    is AlertStyle.StyleInfo -> OceanButtonStyle.PrimarySmall
    is AlertStyle.StyleWarning -> OceanButtonStyle.PrimaryWarningSmall
    is AlertStyle.StyleNegative -> OceanButtonStyle.PrimaryCriticalSmall
    else -> OceanButtonStyle.PrimarySmall
}

@Composable
fun OceanAlertEntitledLong(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    style: AlertStyle
) {
    Column(
        modifier = modifier
            .borderBackground(
                color = style.alertBackgroundColor(),
                borderRadius = OceanBorderRadius.SM.allCorners
            )
            .padding(OceanSpacing.xs)
    ) {
        BaseAlertContent(
            style = style,
            title = title
        )

        val color = style.descriptionColor()
        val textSize = style.descriptionStyle().fontSize

        OceanHtmlText(
            text = description,
            color = color,
            fontSize = textSize,
            fontFamily = R.font.font_family_base_regular,
            maxLines = 2
        )
    }
}

@Composable
fun OceanAlertLabeled(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String,
    link: String,
    linkType: OceanLinkType,
    linkIcon: OceanLinkIcon,
    style: AlertStyle,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
            .borderBackground(
                color = style.alertBackgroundColor(),
                borderRadius = OceanBorderRadius.SM.allCorners
            )
            .padding(OceanSpacing.xs)
    ) {
        style.oceanIcon?.let {
            Column {
                Icon(
                    modifier = Modifier.padding(end = OceanSpacing.xxs),
                    painter = painterResource(id = it.icon),
                    tint = style.iconTint(),
                    contentDescription = null
                )
            }
        }
        Column {
            if (title.isNotBlank()) {
                OceanText(
                    text = title,
                    style = style.titleStyle(),
                    color = style.titleColor(),
                    maxLines = 2
                )
            }

            val color = style.descriptionColor()
            val textSize = style.descriptionStyle().fontSize

            OceanHtmlText(
                text = description,
                color = color,
                fontSize = textSize,
                fontFamily = R.font.font_family_base_regular,
                maxLines = 2
            )

            OceanLink(
                modifier = Modifier.padding(top = OceanSpacing.xxsExtra),
                type = linkType,
                text = link,
                isSmall = true,
                linkIcon = linkIcon,
                onClick = { onClick() }
            )
        }
    }
}

@Composable
fun OceanAlertBookmarked(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    style: AlertStyle
) {
    Column(
        modifier = modifier
            .borderBackground(
                color = style.alertBackgroundColor(),
                borderRadius = OceanBorderRadius.SM.allCorners
            )
            .padding(OceanSpacing.xs)
    ) {
        BaseAlertContent(
            style = style,
            title = title
        )

        val color = style.descriptionColor()
        val textSize = style.descriptionStyle().fontSize

        OceanHtmlText(
            text = description,
            color = color,
            fontSize = textSize,
            fontFamily = R.font.font_family_base_regular
        )
    }
}

@Composable
private fun BaseAlertContent(
    style: AlertStyle,
    title: String
) {
    Row(
        modifier = Modifier.padding(bottom = OceanSpacing.xxsExtra),
        verticalAlignment = CenterVertically
    ) {
        style.oceanIcon?.let {
            Icon(
                modifier = Modifier.padding(end = OceanSpacing.xxs),
                painter = painterResource(id = it.icon),
                tint = style.iconTint(),
                contentDescription = null
            )
        }
        OceanText(
            text = title,
            style = style.titleStyle(),
            color = style.titleColor(),
            maxLines = 2
        )
    }
}

@Composable
fun OceanAlertWithAction(
    modifier: Modifier = Modifier,
    description: String,
    style: AlertStyle,
    actionTitle: String,
    action: () -> Unit
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
            .borderBackground(
                color = style.alertBackgroundColor(),
                borderRadius = OceanBorderRadius.SM.allCorners
            )
            .padding(OceanSpacing.xs)
    ) {
        style.oceanIcon?.let {
            Icon(
                modifier = Modifier.padding(end = OceanSpacing.xxs),
                painter = painterResource(id = it.icon),
                tint = style.iconTint(),
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            OceanHtmlText(
                text = description,
                color = style.descriptionColor(),
                fontSize = style.descriptionStyle().fontSize,
                fontFamily = R.font.font_family_base_regular,
                maxLines = 2
            )
        }

        OceanSpacing.StackXXS()

        OceanButton(
            text = actionTitle,
            buttonStyle = alertButtonStyle(style),
            onClick = action
        )
    }
}
