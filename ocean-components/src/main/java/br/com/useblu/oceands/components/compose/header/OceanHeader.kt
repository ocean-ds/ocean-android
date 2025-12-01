package br.com.useblu.oceands.components.compose.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.components.compose.OceanBluPlus
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.balance.blubalance.OceanBluBalance
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemActionType
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemInteraction
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemModel
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemType
import br.com.useblu.oceands.components.compose.header.model.OceanHeaderStyle
import br.com.useblu.oceands.extensions.compose.topBarBackground
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanHeaderAppAction
import br.com.useblu.oceands.model.compose.OceanHeaderAppModel
import br.com.useblu.oceands.model.compose.OceanHeaderType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

val modelPreview = OceanHeaderAppModel(
    clientName = "Fabricante 3 - Fluxo dia atual teste 2",
    formattedCnpj = "32.677.554/0001-14",
    items = listOf(
        OceanBalanceItemModel(
            type = OceanBalanceItemType.Main(
                title = "First Label",
                value = "-35,63"
            ),
            interaction = OceanBalanceItemInteraction.Expandable(
                bluBalanceItems = listOf(
                    "Second Label" to "10,00",
                    "Third Label" to "50,00"
                )
            )
        ),

        OceanBalanceItemModel(
            type = OceanBalanceItemType.Text(
                text = "Confira tudo o que entrou e saiu da sua Conta Digital Blu"
            ),
            interaction = OceanBalanceItemInteraction.Action(
                type = OceanBalanceItemActionType.ButtonSimple(
                    title = "Extrato",
                    onClick = { }
                ),
                action = { }
            )
        )
    ),
    isLoading = false,
    isHeaderCollapsed = false,
    appActions = listOf(
        OceanHeaderAppAction(
            key = "bell_example",
            icon = OceanIcons.BELL_OUTLINE,
            badgeCount = 2,
            action = { println(it) }
        ),
        OceanHeaderAppAction(
            key = 1,
            icon = OceanIcons.CHAT_ALT_THREE_OUTLINE,
            badgeCount = 0,
            action = { println(it) }
        )
    )
)

@Composable
fun MinimalHeader(
    model: OceanHeaderAppModel,
    modifier: Modifier = Modifier,
    type: OceanHeaderType,
    showBalanceToggle: Boolean,
    isContentHidden: Boolean,
    toggleContentHidden: () -> Unit
) {
    val backgroundColor = when (type) {
        OceanHeaderType.PRIMARY -> OceanColors.brandPrimaryPure
        OceanHeaderType.SECONDARY -> OceanColors.interfaceLightPure
    }

    val textColor = when (type) {
        OceanHeaderType.PRIMARY -> OceanColors.interfaceLightPure
        OceanHeaderType.SECONDARY -> OceanColors.brandPrimaryPure
    }

    val cnpjColor = when (type) {
        OceanHeaderType.PRIMARY -> OceanColors.brandPrimaryUp
        OceanHeaderType.SECONDARY -> OceanColors.interfaceDarkUp
    }

    Row(
        modifier = modifier
            .background(backgroundColor)
            .height(OceanSpacing.xxl),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = OceanSpacing.xs)
                .clickable { model.onClickTitle() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = model.clientName,
                    fontFamily = OceanFontFamily.HighlightExtraBold,
                    color = textColor,
                    fontSize = OceanFontSize.xs,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, false)
                )

                Spacer(modifier = Modifier.size(2.dp))

                OceanIcon(
                    iconType = OceanIcons.CHEVRON_DOWN_SOLID,
                    modifier = Modifier.size(16.dp),
                    tint = textColor
                )
            }

            OceanSpacing.StackXXXS()

            Text(
                text = model.formattedCnpj,
                fontFamily = OceanFontFamily.BaseRegular,
                color = cnpjColor,
                fontSize = OceanFontSize.xxxs
            )
        }

        if (!model.hideBluPlus) {
            OceanBluPlus(
                bluPlusValue = model.bluPlusValue,
                onClick = model.onClickBluPlus
            )

            OceanSpacing.StackXXXS()
        }

        val actions = if (showBalanceToggle) {
            listOf(
                OceanHeaderAppAction<Any>(
                    key = "visibility_toggle",
                    icon = if (isContentHidden) {
                        OceanIcons.EYE_OFF_ALT_OUTLINE
                    } else {
                        OceanIcons.EYE_OUTLINE
                    },
                    action = {
                        toggleContentHidden()
                    }
                )
            ) + model.appActions
        } else {
            model.appActions
        }

        actions.forEach { headerAction ->
            MinimalHeaderAction(
                icon = headerAction.icon,
                badgeCount = headerAction.badgeCount,
                iconTint = textColor,
                onClick = { headerAction.action(headerAction.key) }
            )
        }

        MinimalHeaderAction(
            icon = OceanIcons.MENU_OUTLINE,
            badgeCount = model.badgeCount,
            iconTint = textColor,
            onClick = model.onClickMenu
        )

        OceanSpacing.StackXXXS()
    }
}

@Composable
private fun MinimalHeaderAction(
    icon: OceanIcons,
    badgeCount: Int,
    iconTint: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clickable {
                onClick()
            }
    ) {
        OceanIcon(
            iconType = icon,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center),
            tint = iconTint
        )

        val shouldShowBadge = badgeCount > 0
        if (shouldShowBadge) {
            Box(
                modifier = Modifier
                    .padding(OceanSpacing.xxs)
                    .align(Alignment.TopEnd)
            ) {
                OceanBadge(
                    text = badgeCount.toString(),
                    type = OceanBadgeType.WARNING,
                    size = OceanBadgeSize.Small
                )
            }
        }
    }
}

@Composable
@Preview
fun OceanHeaderMinimalPreview() {
    OceanHeader(
        headerModel = modelPreview,
        style = OceanHeaderStyle.Minimal
    )
}

@Composable
@Preview
fun OceanHeaderSmallPreview() {
    OceanTheme {
        OceanHeader(
            headerModel = modelPreview.copy(isHeaderCollapsed = true),
            style = OceanHeaderStyle.Small
        )
    }
}

@Composable
@Preview
private fun OceanHeaderDefaultPreview() {
    OceanHeader(
        headerModel = modelPreview.copy(isHeaderCollapsed = false),
        style = OceanHeaderStyle.Small
    )
}

@Composable
@Preview
fun OceanHeaderSecondaryPreview() {
    OceanTheme {
        OceanHeader(
            headerModel = modelPreview,
            style = OceanHeaderStyle.Small,
            type = OceanHeaderType.SECONDARY
        )
    }
}

@Composable
@Preview
fun OceanHeaderSecondaryWithoutBalanceTogglePreview() {
    OceanTheme {
        OceanHeader(
            headerModel = modelPreview,
            style = OceanHeaderStyle.Small,
            type = OceanHeaderType.SECONDARY,
            showBalanceToggle = false
        )
    }
}

@Composable
fun OceanHeader(
    modifier: Modifier = Modifier,
    headerModel: OceanHeaderAppModel,
    style: OceanHeaderStyle = OceanHeaderStyle.Small,
    type: OceanHeaderType = OceanHeaderType.PRIMARY,
    showBalanceToggle: Boolean = true
) {
    var isContentHidden by remember { mutableStateOf(false) }

    val backgroundColor = when (type) {
        OceanHeaderType.PRIMARY -> OceanColors.brandPrimaryPure
        OceanHeaderType.SECONDARY -> OceanColors.interfaceLightPure
    }

    Column(
        modifier = modifier
            .topBarBackground(color = backgroundColor)
    ) {
        MinimalHeader(
            model = headerModel,
            type = type,
            showBalanceToggle = showBalanceToggle,
            isContentHidden = isContentHidden,
            toggleContentHidden = {
                isContentHidden = !isContentHidden
            }
        )

        if (style is OceanHeaderStyle.Small) {
            SmallHeader(
                model = headerModel,
                isContentHidden = isContentHidden
            )
        }
    }
}

@Composable
private fun SmallHeader(
    model: OceanHeaderAppModel,
    isContentHidden: Boolean
) {
    when {
        model.hideBalance -> return
        model.isHeaderCollapsed -> {
            OceanBluBalance(
                modifier = Modifier
                    .background(color = Color(0xFF2244E8)),
                items = model.items.take(1),
                hideContent = isContentHidden,
                isLoading = model.isLoading,
                onClickDelegate = {
                    model.toggleHeaderCollapse()
                }
            )
        }

        else -> {
            OceanBluBalance(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.xs)
                    .padding(top = OceanSpacing.xxxs, bottom = OceanSpacing.xs)
                    .borderBackground(
                        color = Color(0xFF2244E8),
                        borderRadius = OceanBorderRadius.SM.allCorners
                    ),
                items = model.items,
                hideContent = isContentHidden,
                isLoading = model.isLoading
            )
        }
    }
}
