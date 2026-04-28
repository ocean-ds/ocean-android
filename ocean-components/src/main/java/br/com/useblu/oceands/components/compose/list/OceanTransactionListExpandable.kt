package br.com.useblu.oceands.components.compose.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanTextNotBlank
import br.com.useblu.oceands.model.OceanTagType
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
