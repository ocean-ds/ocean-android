package br.com.useblu.oceands.components.compose.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTextNotBlank
import br.com.useblu.oceands.components.compose.transactionlist.OceanChildTransactionListAction
import br.com.useblu.oceands.components.compose.transactionlist.OceanChildTransactionListReadOnly
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListChildItem
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListPosition
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListState
import br.com.useblu.oceands.components.compose.transactionlist.TrailingChevron
import br.com.useblu.oceands.components.compose.transactionlist.TransactionListIconColor
import br.com.useblu.oceands.components.compose.transactionlist.TransactionListMainRow
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

data class OceanTransactionListExpandableItem(
    val primaryLabel: String = "",
    val secondaryLabel: String = "",
    val dimmedLabel: String = "",
    val highlightedLabel: String = "",
    val primaryValue: Double? = null,
    val tagTitle: String = "",
    val tagType: OceanTagType = OceanTagType.Warning,
    val time: String = "",
    val onClick: () -> Unit = { }
)

@Suppress("DEPRECATION")
@Composable
fun OceanParentTransactionListExpandable(
    item: OceanTransactionListExpandableItem,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    OceanTransactionListItem(
        primaryLabel = item.primaryLabel,
        secondaryLabel = item.secondaryLabel,
        secondaryLabelMaxLines = 1,
        dimmedLabel = item.dimmedLabel,
        highlightedLabel = item.highlightedLabel,
        primaryValue = item.primaryValue,
        valueWithSignal = true,
        valueWithSignalPositive = false,
        valueIsHighlighted = true,
        tagTitle = item.tagTitle,
        tagType = item.tagType,
        time = item.time,
        showDivider = false,
        trailingIcon = if (isExpanded) OceanIcons.CHEVRON_UP_SOLID else OceanIcons.CHEVRON_DOWN_SOLID,
        onClick = onClick
    )
}

@Suppress("DEPRECATION")
@Composable
fun OceanChildTransactionListExpandable(
    item: OceanTransactionListExpandableItem
) {
    OceanTransactionListItem(
        primaryLabel = item.primaryLabel,
        secondaryLabel = item.secondaryLabel,
        secondaryLabelMaxLines = 1,
        dimmedLabel = item.dimmedLabel,
        highlightedLabel = item.highlightedLabel,
        primaryValue = item.primaryValue,
        valueWithSignal = true,
        valueWithSignalPositive = false,
        valueIsHighlighted = true,
        tagTitle = item.tagTitle,
        tagType = item.tagType,
        time = item.time,
        showDivider = false,
        trailingIcon = OceanIcons.CHEVRON_RIGHT_SOLID,
        paddingVertical = OceanSpacing.xxsExtra,
        primaryLabelStyle = OceanTextStyle.captionBold,
        secondaryLabelStyle = OceanTextStyle.description,
        primaryValueStyle = OceanTextStyle.heading5,
        primaryValueFormattedColor = if ((item.primaryValue ?: 0.0) <= 0.0) OceanColors.interfaceDarkDown else null,
        onClick = item.onClick
    )
}

@Suppress("LongParameterList", "kotlin:S107")
@Composable
fun OceanTransactionListExpandable(
    parent: OceanTransactionListExpandableItem,
    modifier: Modifier = Modifier,
    itemsIcon: OceanIcons? = null,
    itemsIconSize: Dp? = null,
    itemsIconTint: Color? = null,
    items: List<OceanTransactionListExpandableItem> = emptyList(),
    footerText: String = "",
    showDivider: Boolean = true,
    startExpanded: Boolean = false
) {
    var isExpanded by remember { mutableStateOf(startExpanded) }

    Column(modifier = modifier) {
        OceanParentTransactionListExpandable(
            item = parent,
            isExpanded = isExpanded,
            onClick = { isExpanded = !isExpanded }
        )

        AnimatedVisibility(visible = isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(OceanColors.interfaceLightPure)
            ) {
                items.forEachIndexed { index, itemContent ->
                    val isFirst = index == 0
                    val isLast = index == items.lastIndex

                    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                        itemsIcon?.let {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(start = OceanSpacing.xs)
                                    .fillMaxHeight()
                            ) {
                                if (!isFirst) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .width(1.dp)
                                            .background(OceanColors.interfaceLightDown)
                                    )
                                } else {
                                    Spacer(modifier = Modifier.weight(1f))
                                }

                                OceanIcon(
                                    iconType = it,
                                    tint = itemsIconTint ?: OceanColors.interfaceDarkUp,
                                    modifier = Modifier
                                        .padding(vertical = OceanSpacing.xxxs)
                                        .then(
                                            if (itemsIconSize != null) {
                                                Modifier.size(itemsIconSize)
                                            } else {
                                                Modifier
                                            }
                                        )
                                )

                                if (!isLast) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .width(1.dp)
                                            .background(OceanColors.interfaceLightDown)
                                    )
                                } else {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            OceanChildTransactionListExpandable(
                                item = itemContent
                            )
                        }
                    }
                }

                OceanTextNotBlank(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = OceanSpacing.xxs)
                        .padding(horizontal = OceanSpacing.xs)
                        .padding(bottom = OceanSpacing.sm),
                    text = footerText,
                    style = OceanTextStyle.caption,
                    color = OceanColors.interfaceDarkUp,
                    textAlign = TextAlign.Center
                )
            }
        }

        if (showDivider) {
            OceanDivider()
        }
    }
}

/**
 * Transaction List Expandable with the MR-615 family blocks (Figma `24289-64430`): the parent is
 * a Transaction List Action whose chevron points down/up; when expanded it shows the [items] as
 * child items with the timeline (position derived from the index) and the footer.
 *
 * @param footer slot for the footer; when `null`, [footerText] is shown centered in `caption`.
 * @param onExpandedChange called with the new state on every toggle.
 * @param iconColor color of the parent's leading [icon] (children use their own
 *   [OceanTransactionListChildItem.iconColor]); disabled always uses `Interface/Light/Deep` and the
 *   `tint` of [OceanIconModel] is ignored.
 */
@Suppress("LongParameterList", "kotlin:S107")
@Composable
fun OceanTransactionListExpandable(
    content: ContentListStyle,
    modifier: Modifier = Modifier,
    amount: ContentListStyle.Amount? = null,
    items: List<OceanTransactionListChildItem> = emptyList(),
    state: OceanTransactionListState = OceanTransactionListState.Default,
    icon: OceanIconModel? = null,
    footerText: String = "",
    footer: (@Composable () -> Unit)? = null,
    showDivider: Boolean = true,
    startExpanded: Boolean = false,
    onExpandedChange: (Boolean) -> Unit = {},
    iconColor: TransactionListIconColor = TransactionListIconColor.Default
) {
    var isExpanded by rememberSaveable { mutableStateOf(startExpanded) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val enabled = state == OceanTransactionListState.Default
    val showChildren = isExpanded && state != OceanTransactionListState.Loading

    Column(modifier = Modifier.background(OceanColors.interfaceLightPure).then(modifier)) {
        Box(
            modifier = Modifier
                .background(
                    if (enabled && (isPressed || isHovered)) {
                        OceanColors.interfaceLightUp
                    } else {
                        Color.Transparent
                    }
                )
                .hoverable(interactionSource = interactionSource, enabled = enabled)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = enabled,
                    role = Role.Button,
                    onClickLabel = if (isExpanded) "collapse" else "expand",
                    onClick = {
                        isExpanded = !isExpanded
                        onExpandedChange(isExpanded)
                    }
                )
        ) {
            OceanTransactionListParentRow(
                content = content,
                amount = amount,
                icon = icon,
                state = state,
                isExpanded = isExpanded,
                iconColor = iconColor
            )
        }

        if (!showChildren && showDivider) {
            OceanDivider(modifier = Modifier.padding(horizontal = OceanSpacing.xs))
        }

        AnimatedVisibility(visible = showChildren) {
            Column(modifier = Modifier.fillMaxWidth()) {
                items.forEachIndexed { index, item ->
                    val position = OceanTransactionListPosition.of(index, items.size)
                    val onClick = item.onClick
                    if (onClick != null) {
                        OceanChildTransactionListAction(
                            content = item.content,
                            amount = item.amount,
                            icon = item.icon,
                            iconColor = item.iconColor,
                            position = position,
                            state = if (enabled) item.state else OceanTransactionListState.Disabled,
                            onClick = onClick
                        )
                    } else {
                        OceanChildTransactionListReadOnly(
                            content = item.content,
                            amount = item.amount,
                            icon = item.icon,
                            iconColor = item.iconColor,
                            position = position,
                            state = if (enabled) item.state else OceanTransactionListState.Disabled
                        )
                    }
                }

                if (footer != null) {
                    footer()
                } else {
                    OceanTextNotBlank(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = OceanSpacing.xxs)
                            .padding(horizontal = OceanSpacing.xs)
                            .padding(bottom = OceanSpacing.sm),
                        text = footerText,
                        style = OceanTextStyle.caption,
                        color = OceanColors.interfaceDarkUp,
                        textAlign = TextAlign.Center
                    )
                }

                if (showDivider) {
                    OceanDivider(modifier = Modifier.padding(horizontal = OceanSpacing.xs))
                }
            }
        }
    }
}

@Composable
private fun OceanTransactionListParentRow(
    content: ContentListStyle,
    amount: ContentListStyle.Amount?,
    icon: OceanIconModel?,
    state: OceanTransactionListState,
    isExpanded: Boolean,
    iconColor: TransactionListIconColor
) {
    TransactionListMainRow(
        content = content,
        amount = amount,
        icon = icon,
        state = state,
        iconColor = iconColor,
        trailing = {
            TrailingChevron(
                enabled = state == OceanTransactionListState.Default,
                icon = if (isExpanded) OceanIcons.CHEVRON_UP_SOLID else OceanIcons.CHEVRON_DOWN_SOLID
            )
        }
    )
}

@Preview
@Composable
fun OceanTransactionListExpandablePreview() {
    val retainValue = -150.00
    val cancelValue = 150.00

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        OceanTransactionListExpandable(
            parent = OceanTransactionListExpandableItem(
                primaryLabel = "Title",
                secondaryLabel = "Description",
                dimmedLabel = "Caption",
                primaryValue = 0.0,
                time = "Additional data",
                tagTitle = "Label",
                tagType = OceanTagType.Positive
            ),
            itemsIcon = OceanIcons.LOCK_CLOSED_SOLID,
            items = listOf(
                OceanTransactionListExpandableItem(
                    primaryLabel = "Title",
                    secondaryLabel = "Description",
                    dimmedLabel = "Caption",
                    primaryValue = 0.0,
                    time = "Additional data",
                    tagTitle = "Label",
                    tagType = OceanTagType.Positive
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = "Title",
                    secondaryLabel = "Description",
                    dimmedLabel = "Caption",
                    primaryValue = 0.0,
                    time = "Additional data",
                    tagTitle = "Label",
                    tagType = OceanTagType.Positive
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = "Title",
                    secondaryLabel = "Description",
                    dimmedLabel = "Caption",
                    primaryValue = 0.0,
                    time = "Additional data",
                    tagTitle = "Label",
                    tagType = OceanTagType.Positive
                )
            ),
            footerText = "Supporting text that providing context.",
            startExpanded = true
        )

        OceanTransactionListExpandable(
            parent = OceanTransactionListExpandableItem(
                primaryLabel = "Retenções",
                primaryValue = -2260.00
            ),
            itemsIcon = OceanIcons.LOCK_CLOSED_SOLID,
            items = listOf(
                OceanTransactionListExpandableItem(
                    primaryLabel = "Retenção de saldo",
                    secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                    primaryValue = retainValue
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = "Retenção de saldo",
                    secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                    primaryValue = retainValue
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = "Retenção de saldo",
                    secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                    primaryValue = retainValue
                )
            ),
            footerText = "Fim das retenções de saldo",
            startExpanded = true
        )

        OceanTransactionListExpandable(
            parent = OceanTransactionListExpandableItem(
                primaryLabel = "Cancelamento de retenções",
                primaryValue = 3295.00
            ),
            itemsIcon = OceanIcons.LOCK_OPEN_SOLID,
            items = listOf(
                OceanTransactionListExpandableItem(
                    primaryLabel = "Cancelamento de retenção",
                    secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                    dimmedLabel = "Retenção lançada em 14/01/2026",
                    primaryValue = cancelValue
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = "Cancelamento de retenção",
                    secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                    dimmedLabel = "Retenção lançada em 14/01/2026",
                    primaryValue = cancelValue
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = "Cancelamento de retenção",
                    secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                    dimmedLabel = "Retenção lançada em 14/01/2026",
                    primaryValue = cancelValue
                )
            ),
            footerText = "Fim dos cancelamentos das retenções",
            startExpanded = true
        )
    }
}
