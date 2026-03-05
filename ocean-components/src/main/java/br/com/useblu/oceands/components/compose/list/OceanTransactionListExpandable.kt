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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.TransactionType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanTransactionListExpandable(
    title: String,
    value: Double,
    modifier: Modifier = Modifier,
    itemsIcon: OceanIcons = OceanIcons.LOCK_CLOSED_SOLID,
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
                                iconType = itemsIcon,
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

                        Box(modifier = Modifier.weight(1f)) {
                            itemContent()
                        }
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
private fun OceanTransactionListExpandableRetainPreview() {
    val retainInfo: (String) -> ContentListStyle = { description ->
        ContentListStyle.Inverted(
            title = "Retenção de saldo",
            description = description
        )
    }

    OceanTransactionListExpandable(
        title = "Retenções",
        value = -2260.00,
        items = listOf(
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = retainInfo("Boleto de Blu Instituição de Pag..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 150,00",
                            type = TransactionType.OUTFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = retainInfo("Boleto de Blu Instituição de Pag..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 200,00",
                            type = TransactionType.OUTFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = retainInfo("Boleto de Blu Instituição de P..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 1.500,00",
                            type = TransactionType.OUTFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = retainInfo("Boleto de Blu Instituição de Pag..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 300,00",
                            type = TransactionType.OUTFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = retainInfo("Boleto de Blu Instituição de Pag..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 100,00",
                            type = TransactionType.OUTFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = ContentListStyle.Inverted(
                            title = "Retenção de multa e juros",
                            description = "Boleto de Blu Instituição de Paga..."
                        ),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 10,00",
                            type = TransactionType.OUTFLOW
                        ),
                        onClick = {}
                    )
                )
            }
        ),
        footerText = "Fim das retenções de saldo",
        startExpanded = true
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun OceanTransactionListExpandableCancelPreview() {
    val cancelInfo: (String) -> ContentListStyle = { description ->
        ContentListStyle.Inverted(
            title = "Cancelamento de retenção",
            description = description,
            caption = "Retenção lançada em 14/01/2026"
        )
    }

    OceanTransactionListExpandable(
        title = "Cancelamento de retenções",
        value = 3295.00,
        items = listOf(
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = cancelInfo("Boleto de Blu Instituição de Paga..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 150,00",
                            type = TransactionType.INFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = cancelInfo("Boleto de Blu Instituição de Paga..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 200,00",
                            type = TransactionType.INFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = cancelInfo("Boleto de Blu Instituição de Pag..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 1.500,00",
                            type = TransactionType.INFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = cancelInfo("Boleto de Blu Instituição de Paga..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 300,00",
                            type = TransactionType.INFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = cancelInfo("Boleto de Blu Instituição de Paga..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 100,00",
                            type = TransactionType.INFLOW
                        ),
                        onClick = {}
                    )
                )
            },
            {
                OceanTransactionListItem(
                    style = TransactionListItemStyle.CommonStyle.Default(
                        contentInfo = cancelInfo("Boleto de Blu Instituição de Pag..."),
                        contentValues = ContentListStyle.Transaction(
                            value = "R$ 1.045,00",
                            type = TransactionType.INFLOW
                        ),
                        onClick = {}
                    )
                )
            }
        ),
        footerText = "Fim dos cancelamentos das retenções",
        startExpanded = true
    )
}
