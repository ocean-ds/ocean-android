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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanTransactionListExpandable(
    title: String,
    value: Double,
    modifier: Modifier = Modifier,
    itemsIcon: OceanIcons? = null,
    items: List<@Composable () -> Unit> = emptyList(),
    footerText: String = "",
    startExpanded: Boolean = false
) {
    var isExpanded by remember { mutableStateOf(startExpanded) }

    Column(modifier = modifier) {
        OceanTransactionListItem(
            primaryLabel = title,
            primaryValue = value,
            valueWithSignal = true,
            valueWithSignalPositive = false,
            valueIsHighlighted = true,
            trailingIcon = if (isExpanded) OceanIcons.CHEVRON_UP_SOLID else OceanIcons.CHEVRON_DOWN_SOLID,
            showDivider = false,
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
                                    tint = OceanColors.interfaceDarkUp,
                                    modifier = Modifier
                                        .padding(vertical = OceanSpacing.xxxs)
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
                            itemContent()
                        }
                    }
                }

                if (footerText.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = OceanSpacing.xxs)
                            .padding(horizontal = OceanSpacing.xs)
                            .padding(bottom = OceanSpacing.sm),
                        contentAlignment = Alignment.Center
                    ) {
                        OceanText(
                            text = footerText,
                            style = OceanTextStyle.caption,
                            color = OceanColors.interfaceDarkUp
                        )
                    }
                }
            }
        }

        OceanDivider()
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
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
            title = "Retenções",
            itemsIcon = OceanIcons.LOCK_CLOSED_SOLID,
            value = -2260.00,
            items = listOf(
                {
                    OceanChildTransactionListItem(
                        primaryLabel = "Retenção de saldo",
                        secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                        primaryValue = retainValue,
                        time = "Additional data",
                        tagTitle = "Label",
                        tagType = OceanTagType.Positive
                    )
                },
                {
                    OceanChildTransactionListItem(
                        primaryLabel = "Retenção de saldo",
                        secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                        primaryValue = retainValue,
                        time = "Additional data",
                        tagTitle = "Label",
                        tagType = OceanTagType.Positive
                    )
                },
                {
                    OceanChildTransactionListItem(
                        primaryLabel = "Retenção de saldo",
                        secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                        primaryValue = retainValue,
                        time = "Additional data",
                        tagTitle = "Label",
                        tagType = OceanTagType.Positive
                    )
                }
            ),
            footerText = "Fim das retenções de saldo",
            startExpanded = true
        )

        OceanTransactionListExpandable(
            title = "Cancelamento de retenções",
            itemsIcon = OceanIcons.LOCK_OPEN_SOLID,
            value = 3295.00,
            items = listOf(
                {
                    OceanChildTransactionListItem(
                        primaryLabel = "Cancelamento de retenção",
                        secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                        dimmedLabel = "Retenção lançada em 14/01/2026",
                        primaryValue = cancelValue,
                        time = "Additional data",
                        tagTitle = "Label",
                        tagType = OceanTagType.Positive
                    )
                },
                {
                    OceanChildTransactionListItem(
                        primaryLabel = "Cancelamento de retenção",
                        secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                        dimmedLabel = "Retenção lançada em 14/01/2026",
                        primaryValue = cancelValue,
                        time = "Additional data",
                        tagTitle = "Label",
                        tagType = OceanTagType.Positive
                    )
                },
                {
                    OceanChildTransactionListItem(
                        primaryLabel = "Cancelamento de retenção",
                        secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                        dimmedLabel = "Retenção lançada em 14/01/2026",
                        primaryValue = cancelValue,
                        time = "Additional data",
                        tagTitle = "Label",
                        tagType = OceanTagType.Positive
                    )
                }
            ),
            footerText = "Fim dos cancelamentos das retenções",
            startExpanded = true
        )
    }
}
