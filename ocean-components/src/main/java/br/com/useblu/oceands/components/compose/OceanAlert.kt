package br.com.useblu.oceands.components.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.extensions.parseAsHtml
import br.com.useblu.oceands.model.compose.AlertStyle
import br.com.useblu.oceands.model.compose.OceanAlertType
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors


@Preview
@Composable
fun OceanAlertPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val context = LocalContext.current
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Default(
                description = "Default Alert Info",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Default(
                description = "Default Alert Warning",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Default(
                description = "Default Alert Positive",
                alertType = AlertStyle.StylePositive(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Default(
                description = "Default Alert Negative",
                alertType = AlertStyle.StyleNegative(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Entitled Alert",
                description = "Entitled Alert Short Info",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Entitled Alert",
                description = "Entitled Alert Short Warning",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Entitled Alert",
                description = "Confirme suas informações para acessar as funções da conta.",
                alertType = AlertStyle.StylePositive(),
                button = "Button" to { }
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Entitled Alert",
                description = "Entitled Alert Short Negative",
                alertType = AlertStyle.StyleNegative(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledLong(
                title = "Entitled Alert",
                description = "Entitled Alert Long Info",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledLong(
                title = "Entitled Alert",
                description = "Entitled Alert Long Warning",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledLong(
                title = "Entitled Alert",
                description = "Entitled Alert Long Positive",
                alertType = AlertStyle.StylePositive(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledLong(
                title = "Entitled Alert",
                description = "Entitled Alert Long Negative",
                alertType = AlertStyle.StyleNegative(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Info",
                label = "Label",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Warning",
                label = "Label",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Positive",
                label = "Label",
                alertType = AlertStyle.StylePositive(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                description = "Untitled Alert Labeled Negative",
                label = "Label",
                alertType = AlertStyle.StyleNegative(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Info",
                label = "Label",
                alertType = AlertStyle.StyleInfo(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Warning",
                label = "Label",
                alertType = AlertStyle.StyleWarning(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Positive",
                label = "Label",
                alertType = AlertStyle.StylePositive(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.Labeled(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Negative",
                label = "Label",
                alertType = AlertStyle.StyleNegative(),
            )
        )
        OceanAlert(
            modifier = Modifier.fillMaxWidth(),
            type = OceanAlertType.EntitledShort(
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Negative",
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
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Negative",
                alertType = AlertStyle.StyleWarning(),
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
                title = "Labeled Alert",
                description = "Entitled Alert Labeled Negative",
                alertType = AlertStyle.StyleNegative(),
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
            type = OceanAlertType.Bookmarked(
                title = "Labeled Alert",
                description = "<li>Menu1</li><li>Menu2</li><li>Menu3</li>",
                alertType = AlertStyle.StyleNegative()
            )
        )
    }
}


@Composable
fun OceanAlert(
    modifier: Modifier = Modifier,
    type: OceanAlertType,
) {
    when (type) {
        is OceanAlertType.Default -> {
            OceanAlertDefaultStyle(
                modifier = modifier,
                description = type.description,
                style = type.alertType,
            )
        }

        is OceanAlertType.EntitledShort -> {
            OceanAlertEntitledShort(
                modifier = modifier,
                title = type.title,
                description = type.description,
                style = type.alertType,
                button = type.button
            )
        }

        is OceanAlertType.EntitledLong -> {
            OceanAlertEntitledLong(
                modifier = modifier,
                title = type.title,
                description = type.description,
                style = type.alertType,
            )
        }

        is OceanAlertType.Labeled -> {
            OceanAlertLabeled(
                modifier = modifier,
                title = type.title,
                description = type.description,
                label = type.label,
                style = type.alertType,
                onClickLable = { }
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
    }
}

@Composable
fun OceanAlertDefaultStyle(
    modifier: Modifier = Modifier,
    description: String,
    style: AlertStyle,
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
            .background(
                color = style.alertBackgroundColor.invoke(),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            painter = painterResource(id = style.oceanIcon.icon),
            tint = style.iconTint.invoke(),
            contentDescription = null,
        )
        Text(
            text = description,
            style = style.descriptionStyle.invoke(),
            color = style.descriptionColor.invoke(),
            maxLines = 2,
        )
    }
}

@Composable
fun OceanAlertEntitledShort(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    style: AlertStyle,
    button: Pair<String, () -> Unit>? = null
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
            .background(
                color = style.alertBackgroundColor.invoke(),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            painter = painterResource(id = style.oceanIcon.icon),
            tint = style.iconTint.invoke(),
            contentDescription = null
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = style.titleStyle.invoke(),
                color = style.titleColor.invoke(),
                maxLines = 2
            )
            Text(
                text = description,
                style = style.descriptionStyle.invoke(),
                color = style.descriptionColor.invoke(),
                maxLines = 2
            )
        }

        button?.let {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp),
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
    style: AlertStyle,
) {
    Column(
        modifier = modifier
            .background(
                color = style.alertBackgroundColor.invoke(),
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
                painter = painterResource(id = style.oceanIcon.icon),
                tint = style.iconTint.invoke(),
                contentDescription = null,
            )
            Text(
                text = title,
                style = style.titleStyle.invoke(),
                color = style.titleColor.invoke(),
                maxLines = 2,
            )
        }
        Text(
            text = description,
            style = style.descriptionStyle.invoke(),
            color = style.descriptionColor.invoke(),
            maxLines = 2,
        )
    }
}

@Composable
fun OceanAlertLabeled(
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String,
    label: String,
    style: AlertStyle,
    onClickLable: () -> Unit
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = modifier
            .background(
                color = style.alertBackgroundColor.invoke(),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = style.oceanIcon.icon),
                tint = style.iconTint.invoke(),
                contentDescription = null,
            )
        }
        Column {
            if (title != null) {
                Text(
                    text = title,
                    style = style.titleStyle.invoke(),
                    color = style.titleColor.invoke(),
                    maxLines = 2,
                )
            }
            Text(
                text = description,
                style = style.descriptionStyle.invoke(),
                color = style.descriptionColor.invoke(),
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

@Composable
fun OceanAlertBookmarked(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    style: AlertStyle
) {
    Column(
        modifier = modifier
            .background(
                color = style.alertBackgroundColor.invoke(),
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
                painter = painterResource(id = style.oceanIcon.icon),
                tint = style.iconTint.invoke(),
                contentDescription = null,
            )
            Text(
                text = title,
                style = style.titleStyle.invoke(),
                color = style.titleColor.invoke(),
                maxLines = 2,
            )
        }
        Text(
            text = description.parseAsHtml().toString(),
            style = style.descriptionStyle.invoke(),
            color = style.descriptionColor.invoke(),
        )
    }
}
