package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun PreviewOceanTopBarInverse() {
    Column(
        modifier = Modifier.background(OceanColors.interfaceLightPure)
    ) {
        OceanTopBarInverse(
            title = "Portabilidade",
            icon = OceanIcons.X_OUTLINE,
            onClickIcon = {},
            onClickToolbar = {},
            visibleShadow = true
        )

        OceanSpacing.StackSM()

        OceanTopBarInverse(
            title = "Portabilidade",
            onClickIcon = {},
            onClickToolbar = {},
            visibleShadow = true,
            iconInvisible = true,
            menuIcon = OceanIcons.HELP_OUTLINE
        )

        OceanSpacing.StackSM()

        OceanTopBarInverse(
            title = "Portabilidade",
            onClickIcon = {},
            onClickToolbar = {},
            visibleShadow = true,
            iconInvisible = true
        )

        OceanSpacing.StackSM()

        var expanded by remember { mutableStateOf(false) }

        OceanTopBarInverse(
            title = "Portabilidade DropDown",
            onClickIcon = {},
            onClickToolbar = {},
            visibleShadow = true,
            iconInvisible = true,
            actions = {
                Column {
                    IconButton(
                        onClick = { expanded = true }
                    ) {
                        OceanIcon(
                            tint = OceanColors.brandPrimaryPure,
                            iconType = OceanIcons.DOTS_VERTICAL_OUTLINE
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier.background(
                            color = OceanColors.interfaceLightPure,
                            shape = RoundedCornerShape(size = 8.dp)
                        ),
                        expanded = expanded,
                        onDismissRequest = { }
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Ver contrato") },
                            onClick = { },
                            leadingIcon = {
                                OceanIcon(
                                    OceanIcons.DOCUMENT_TEXT_OUTLINE
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    color = OceanColors.statusNegativePure,
                                    text = "Recusar contrato"
                                )
                                   },
                            onClick = { },
                            leadingIcon = {
                                OceanIcon(
                                    iconType = OceanIcons.X_CIRCLE_SOLID,
                                    tint = OceanColors.statusNegativePure
                                )
                            }
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun OceanTopBarInverse(
    title: String,
    icon: OceanIcons? = null,
    onClickIcon: () -> Unit,
    actions: @Composable () -> Unit,
    onClickToolbar: () -> Unit = {},
    visibleShadow: Boolean = false,
    iconInvisible: Boolean = false,
) {
    TopBar(
        onClickToolbar = onClickToolbar,
        icon = icon,
        iconInvisible = iconInvisible,
        onClickIcon = onClickIcon,
        title = title,
        visibleShadow = visibleShadow,
        actions = actions
    )
}

@Composable
fun OceanTopBarInverse(
    title: String,
    icon: OceanIcons? = null,
    onClickIcon: () -> Unit,
    menuIcon: OceanIcons? = null,
    onClickMenuIcon: () -> Unit = {},
    onClickToolbar: () -> Unit = {},
    visibleShadow: Boolean = false,
    iconInvisible: Boolean = false
) {
    TopBar(
        onClickToolbar = onClickToolbar,
        icon = icon,
        iconInvisible = iconInvisible,
        onClickIcon = onClickIcon,
        title = title,
        menuIcon = menuIcon,
        onClickMenuIcon = onClickMenuIcon,
        visibleShadow = visibleShadow
    )
}

@Composable
private fun TopBar(
    onClickToolbar: () -> Unit,
    icon: OceanIcons?,
    iconInvisible: Boolean,
    onClickIcon: () -> Unit,
    title: String,
    menuIcon: OceanIcons? = null,
    onClickMenuIcon: (() -> Unit)? = null,
    actions: (@Composable () -> Unit)? = null,
    visibleShadow: Boolean
) {
    Column(
        modifier = Modifier.background(OceanColors.interfaceLightPure)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable {
                    onClickToolbar()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            val topBarIcon = icon ?: OceanIcons.ARROW_LEFT_OUTLINE

            if (!iconInvisible) {
                IconButton(
                    modifier = Modifier.size(56.dp),
                    onClick = { onClickIcon() }
                ) {
                    Icon(
                        painter = painterResource(id = topBarIcon.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = OceanColors.brandPrimaryPure
                    )
                }
            } else {
                OceanSpacing.StackXXS()
                OceanSpacing.StackXL()
            }

            OceanSpacing.StackXS()

            Text(
                text = title,
                fontSize = OceanFontSize.sm,
                fontFamily = OceanFontFamily.HighlightExtraBold,
                color = OceanColors.brandPrimaryPure,
                modifier = Modifier.weight(1f),
                textAlign = if (iconInvisible) TextAlign.Center else TextAlign.Start
            )

            OceanSpacing.StackXS()
            
            if (menuIcon != null && onClickMenuIcon != null) {
                IconButton(
                    modifier = Modifier.size(56.dp),
                    onClick = { onClickMenuIcon() }
                ) {
                    Icon(
                        painter = painterResource(id = menuIcon.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = OceanColors.brandPrimaryPure
                    )
                }
            } else {
                OceanSpacing.StackXXS()
                OceanSpacing.StackXL()
            }

            actions?.invoke()
        }

        if (visibleShadow) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0x0C0D1414),
                                Color.Transparent
                            )
                        )
                    )
            )
        }
    }
}