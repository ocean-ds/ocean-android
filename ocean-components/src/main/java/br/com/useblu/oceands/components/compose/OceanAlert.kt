package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.compose.AlertStyle
import br.com.useblu.oceands.model.compose.OceanAlertType
import br.com.useblu.oceands.ui.compose.OceanColors


@Preview
@Composable
fun OceanAlertPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(8.dp)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OceanAlert(
            style = OceanAlertType.Default(
                description = "Default Alert Info",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Default(
                description = "Default Alert Warning",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Default(
                description = "Default Alert Positive",
                alertType = AlertStyle.StylePositive(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Default(
                description = "Default Alert Negative",
                alertType = AlertStyle.StyleNegative(),
            )
        )
        OceanAlert(
            style = OceanAlertType.EntitledShort(
                title = "Entitled Alert",
                description = "Entitled Alert Short Info",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            style = OceanAlertType.EntitledShort(
                title = "Entitled Alert",
                description = "Entitled Alert Short Warning",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            style = OceanAlertType.EntitledShort(
                title = "Entitled Alert",
                description = "Entitled Alert Short Positive",
                alertType = AlertStyle.StylePositive(),
            )
        )
        OceanAlert(
            style = OceanAlertType.EntitledShort(
                title = "Entitled Alert",
                description = "Entitled Alert Short Negative",
                alertType = AlertStyle.StyleNegative(),
            )
        )
        OceanAlert(
            style = OceanAlertType.EntitledLong(
                title = "Entitled Alert",
                description = "Entitled Alert Long Info",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            style = OceanAlertType.EntitledLong(
                title = "Entitled Alert",
                description = "Entitled Alert Long Warning",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            style = OceanAlertType.EntitledLong(
                title = "Entitled Alert",
                description = "Entitled Alert Long Positive",
                alertType = AlertStyle.StylePositive(),
            )
        )
        OceanAlert(
            style = OceanAlertType.EntitledLong(
                title = "Entitled Alert",
                description = "Entitled Alert Long Negative",
                alertType = AlertStyle.StyleNegative(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Info",
                label = "Label",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Warning",
                label = "Label",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Positive",
                label = "Label",
                alertType = AlertStyle.StylePositive(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Negative",
                label = "Label",
                alertType = AlertStyle.StyleNegative(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Labeled(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Info",
                label = "Label",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Labeled(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Warning",
                label = "Label",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Labeled(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Positive",
                label = "Label",
                alertType = AlertStyle.StylePositive(),
            )
        )
        OceanAlert(
            style = OceanAlertType.Labeled(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Negative",
                label = "Label",
                alertType = AlertStyle.StyleNegative(),
            )
        )
    }
}


@Composable
fun OceanAlert(
    modifier: Modifier = Modifier,
    style: OceanAlertType,
) {
    Column(modifier = modifier) {
        when (style) {
            is OceanAlertType.Default -> {
                OceanAlertDefaultStyle(
                    description = style.description,
                    alertType = style.alertType,
                )
            }

            is OceanAlertType.EntitledShort -> {
                OceanAlertEntitledShort(
                    title = style.title,
                    description = style.description,
                    alertType = style.alertType,
                )
            }

            is OceanAlertType.EntitledLong -> {
                OceanAlertEntitledLong(
                    title = style.title,
                    description = style.description,
                    alertType = style.alertType,
                )
            }

            is OceanAlertType.Labeled -> {
                OceanAlertLabeled(
                    title = style.title,
                    description = style.description,
                    label = style.label,
                    alertType = style.alertType,
                    onClickLable = { }
                )
            }
        }
    }
}

@Composable
fun OceanAlertDefaultStyle(
    description: String,
    alertType: AlertStyle,
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .background(
                color = alertType.alertBackgroundColor.invoke(),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            painter = painterResource(id = alertType.oceanIcon.icon),
            tint = alertType.iconTint.invoke(),
            contentDescription = null,
        )
        Text(
            text = description,
            style = alertType.descriptionStyle.invoke(),
            color = alertType.descriptionColor.invoke(),
            maxLines = 2,
        )
    }
}

@Composable
fun OceanAlertEntitledShort(
    title: String,
    description: String,
    alertType: AlertStyle,
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .background(
                color = alertType.alertBackgroundColor.invoke(),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = alertType.oceanIcon.icon),
                tint = alertType.iconTint.invoke(),
                contentDescription = null,
            )
        }
        Column {
            Text(
                text = title,
                style = alertType.titleStyle.invoke(),
                color = alertType.titleColor.invoke(),
                maxLines = 2,
            )
            Text(
                text = description,
                style = alertType.descriptionStyle.invoke(),
                color = alertType.descriptionColor.invoke(),
                maxLines = 2,
            )
        }
    }
}

@Composable
fun OceanAlertEntitledLong(
    title: String,
    description: String,
    alertType: AlertStyle,
) {
    Column(
        modifier = Modifier
            .background(
                color = alertType.alertBackgroundColor.invoke(),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(bottom = 12.dp),
            verticalAlignment = CenterVertically,
        ) {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = alertType.oceanIcon.icon),
                tint = alertType.iconTint.invoke(),
                contentDescription = null,
            )
            Text(
                text = title,
                style = alertType.titleStyle.invoke(),
                color = alertType.titleColor.invoke(),
                maxLines = 2,
            )
        }
        Text(
            text = description,
            style = alertType.descriptionStyle.invoke(),
            color = alertType.descriptionColor.invoke(),
            maxLines = 2,
        )
    }
}

@Composable
fun OceanAlertLabeled(
    title: String? = null,
    description: String,
    label: String,
    alertType: AlertStyle,
    onClickLable: () -> Unit
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .background(
                color = alertType.alertBackgroundColor.invoke(),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = alertType.oceanIcon.icon),
                tint = alertType.iconTint.invoke(),
                contentDescription = null,
            )
        }
        Column {
            if (title != null) {
                Text(
                    text = title,
                    style = alertType.titleStyle.invoke(),
                    color = alertType.titleColor.invoke(),
                    maxLines = 2,
                )
            }
            Text(
                text = description,
                style = alertType.descriptionStyle.invoke(),
                color = alertType.descriptionColor.invoke(),
                maxLines = 2,
            )
            OceanLink(
                modifier = Modifier.padding(top = 12.dp),
                text = label,
                isSmall = true,
                linkIcon = OceanLinkIcon.DEFAULT,
                onClick = onClickLable
            )
        }
    }
}
