package br.com.useblu.oceands.components.compose.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.TransactionType
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

data class OceanTransactionListExpandableItem(
    val icon: OceanIcons = OceanIcons.LOCK_CLOSED_SOLID,
    val contentInfo: ContentListStyle,
    val contentValues: ContentListStyle,
    val onClick: () -> Unit = {}
)

@Composable
fun OceanTransactionListExpandable(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    valueIsPositive: Boolean = false,
    items: List<OceanTransactionListExpandableItem> = emptyList(),
    footerText: String = "",
    startExpanded: Boolean = false
) {
    var isExpanded by remember { mutableStateOf(startExpanded) }
    val animatedRotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "OceanTransactionListExpandableChevron"
    )

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(OceanColors.interfaceLightPure)
                .clickable { isExpanded = !isExpanded }
                .padding(OceanSpacing.xs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OceanText(
                text = title,
                style = OceanTextStyle.paragraph,
                color = OceanColors.interfaceDarkPure,
                modifier = Modifier.weight(1f)
            )

            OceanText(
                text = value,
                style = OceanTextStyle.paragraph,
                color = if (valueIsPositive) OceanColors.statusPositiveDeep else OceanColors.interfaceDarkPure,
                fontFamily = OceanFontFamily.BaseMedium,
                modifier = Modifier.padding(start = OceanSpacing.xxs)
            )

            OceanIcon(
                iconType = OceanIcons.CHEVRON_DOWN_SOLID,
                tint = OceanColors.interfaceDarkUp,
                modifier = Modifier
                    .padding(start = OceanSpacing.xxsExtra)
                    .size(20.dp)
                    .rotate(animatedRotation)
            )
        }

        AnimatedVisibility(visible = isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(OceanColors.interfaceLightPure)
            ) {
                items.forEachIndexed { index, item ->
                    val isLast = index == items.lastIndex

                    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(start = OceanSpacing.xs)
                                .padding(
                                    top = OceanSpacing.xs,
                                    bottom = if (isLast) OceanSpacing.xs else 0.dp
                                )
                                .fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .iconContainerBackground(true)
                                    .size(40.dp)
                            ) {
                                OceanIcon(
                                    iconType = item.icon,
                                    tint = OceanColors.interfaceDarkUp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(24.dp)
                                )
                            }

                            if (!isLast) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .width(1.dp)
                                        .background(OceanColors.interfaceLightDown)
                                )
                            }
                        }

                        OceanTransactionListItem(
                            style = TransactionListItemStyle.CommonStyle.Default(
                                contentInfo = item.contentInfo,
                                contentValues = item.contentValues,
                                onClick = item.onClick
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                if (footerText.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(OceanSpacing.xs),
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
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun OceanTransactionListExpandableRetençõesPreview() {
    val items = listOf(
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Retenção de saldo",
                description = "Boleto de Blu Instituição de Pag..."
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 150,00",
                type = TransactionType.OUTFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Retenção de saldo",
                description = "Boleto de Blu Instituição de Pag..."
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 200,00",
                type = TransactionType.OUTFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Retenção de saldo",
                description = "Boleto de Blu Instituição de P..."
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 1.500,00",
                type = TransactionType.OUTFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Retenção de saldo",
                description = "Boleto de Blu Instituição de Pag..."
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 300,00",
                type = TransactionType.OUTFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Retenção de saldo",
                description = "Boleto de Blu Instituição de Pag..."
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 100,00",
                type = TransactionType.OUTFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Retenção de multa e juros",
                description = "Boleto de Blu Instituição de Paga..."
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 10,00",
                type = TransactionType.OUTFLOW
            )
        )
    )

    OceanTransactionListExpandable(
        title = "Retenções",
        value = "- R$ 2.260,00",
        valueIsPositive = false,
        items = items,
        footerText = "Fim das retenções de saldo",
        startExpanded = true
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun OceanTransactionListExpandableCancelamentoPreview() {
    val items = listOf(
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Cancelamento de retenção",
                description = "Boleto de Blu Instituição de Paga...",
                caption = "Retenção lançada em 14/01/2026"
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 150,00",
                type = TransactionType.INFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Cancelamento de retenção",
                description = "Boleto de Blu Instituição de Paga...",
                caption = "Retenção lançada em 14/01/2026"
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 200,00",
                type = TransactionType.INFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Cancelamento de retenção",
                description = "Boleto de Blu Instituição de Pag...",
                caption = "Retenção lançada em 14/01/2026"
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 1.500,00",
                type = TransactionType.INFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Cancelamento de retenção",
                description = "Boleto de Blu Instituição de Paga...",
                caption = "Retenção lançada em 14/01/2026"
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 300,00",
                type = TransactionType.INFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Cancelamento de retenção",
                description = "Boleto de Blu Instituição de Paga...",
                caption = "Retenção lançada em 14/01/2026"
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 100,00",
                type = TransactionType.INFLOW
            )
        ),
        OceanTransactionListExpandableItem(
            contentInfo = ContentListStyle.Inverted(
                title = "Cancelamento de retenção",
                description = "Boleto de Blu Instituição de Pag...",
                caption = "Retenção lançada em 14/01/2026"
            ),
            contentValues = ContentListStyle.Transaction(
                value = "R$ 1.045,00",
                type = TransactionType.INFLOW
            )
        )
    )

    OceanTransactionListExpandable(
        title = "Cancelamento de retenções",
        value = "R$ 3.295,00",
        valueIsPositive = true,
        items = items,
        footerText = "Fim dos cancelamentos das retenções",
        startExpanded = true
    )
}
