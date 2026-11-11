package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListState
import br.com.useblu.oceands.components.compose.transactionlist.TransactionListDensity
import br.com.useblu.oceands.components.compose.transactionlist.TransactionListMainRow
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

enum class OceanTransactionFooterType {
    Default,
    Highlight
}

data class OceanTransactionFooterItem(
    val content: ContentListStyle,
    val amount: ContentListStyle.Amount
) {
    @Deprecated("Use OceanTransactionFooterItem(content, amount)")
    constructor(label: String, value: String, info: String? = null) : this(
        ContentListStyle.Default(title = label),
        ContentListStyle.Amount(amount = value, additionalData = info.orEmpty())
    )
}

data class OceanTransactionFooterNotice(
    val description: String,
    val title: String? = null,
    val icon: OceanIcons? = OceanIcons.CHECK_CIRCLE_SOLID
)

data class OceanTransactionFooterTotal(
    val label: String,
    val value: String
)

@Composable
fun OceanTransactionFooter(
    total: OceanTransactionFooterTotal,
    button: OceanButtonModel,
    modifier: Modifier = Modifier,
    type: OceanTransactionFooterType = OceanTransactionFooterType.Default,
    notice: OceanTransactionFooterNotice? = null,
    mainItem: OceanTransactionFooterItem? = null,
    details: List<OceanTransactionFooterItem> = emptyList()
) {
    val overlapDp = OceanSpacing.xs
    val density = LocalDensity.current
    val overlap = with(density) { overlapDp.roundToPx() }

    Column(modifier = modifier.fillMaxWidth().testTag(FOOTER_ROOT_TAG)) {
        if (notice != null) {
            footerNotice(
                notice,
                Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height - overlap) {
                            placeable.place(0, 0)
                        }
                    }
                    .zIndex(0f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    if (type == OceanTransactionFooterType.Highlight) {
                        OceanBorderRadius.MD.topCorners.shape()
                    } else {
                        androidx.compose.ui.graphics.RectangleShape
                    }
                )
                .background(
                    if (type == OceanTransactionFooterType.Default) {
                        OceanColors.interfaceLightPure
                    } else {
                        OceanColors.interfaceLightUp
                    }
                )
                .testTag(FOOTER_BODY_TAG)
                .zIndex(1f)
        ) {
            if (type == OceanTransactionFooterType.Default) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(OceanColors.interfaceLightDown)
                        .testTag(FOOTER_TOP_BORDER_TAG)
                )
            }

            if (mainItem != null) {
                footerRow(
                    mainItem,
                    TransactionListDensity.Default,
                    Modifier.testTag(FOOTER_MAIN_ROW_TAG)
                )
            }

            if (mainItem != null && details.isNotEmpty()) {
                footerDivider()
            }

            if (details.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = OceanSpacing.xxs)
                ) {
                    details.forEach { detail ->
                        footerRow(
                            detail,
                            TransactionListDensity.Compact,
                            Modifier.testTag(FOOTER_DETAIL_ROW_TAG)
                        )
                    }
                }
            }

            if (mainItem != null || details.isNotEmpty()) {
                footerDivider()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(FOOTER_TOTAL_TAG)
                    .padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OceanText(
                    total.label,
                    style = OceanTextStyle.paragraph.copy(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontFamily = OceanFontFamily.BaseBold,
                        color = OceanColors.interfaceDarkDeep
                    )
                )
                Spacer(Modifier.weight(1f))
                OceanText(
                    total.value,
                    style = OceanTextStyle.paragraph.copy(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontFamily = OceanFontFamily.BaseBold,
                        color = OceanColors.interfaceDarkDeep
                    )
                )
            }

            OceanButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = OceanSpacing.xs)
                    .padding(horizontal = OceanSpacing.xs)
                    .padding(bottom = OceanSpacing.xs)
                    .testTag(FOOTER_ACTION_TAG),
                button = button
            )
        }
    }
}

@Composable
private fun footerNotice(
    notice: OceanTransactionFooterNotice,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(OceanBorderRadius.MD.topCorners.shape())
            .background(OceanColors.statusPositiveUp)
            .testTag(FOOTER_NOTICE_TAG)
    ) {
        Box(
            Modifier
                .matchParentSize()
                .background(OceanColors.statusPositiveDeep.copy(alpha = 0.12f))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = OceanSpacing.xs,
                    start = OceanSpacing.xs,
                    end = OceanSpacing.xs,
                    bottom = OceanSpacing.md
                ),
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            notice.icon?.let { icon ->
                Box(Modifier.testTag(FOOTER_NOTICE_ICON_TAG)) {
                    OceanIcon(
                        iconType = icon,
                        modifier = Modifier.size(20.dp),
                        tint = OceanColors.statusPositiveDeep
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
            ) {
                notice.title?.let { title ->
                    OceanText(
                        text = title,
                        modifier = Modifier.testTag(FOOTER_NOTICE_TITLE_TAG),
                        style = OceanTextStyle.paragraph.copy(
                            fontSize = 14.sp,
                            lineHeight = 18.48.sp,
                            fontFamily = OceanFontFamily.BaseExtraBold,
                            color = OceanColors.interfaceDarkDeep
                        )
                    )
                }
                OceanText(
                    text = notice.description,
                    style = OceanTextStyle.caption.copy(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontFamily = OceanFontFamily.BaseRegular,
                        color = OceanColors.interfaceDarkDeep
                    )
                )
            }
        }
    }
}

@Composable
private fun footerRow(
    item: OceanTransactionFooterItem,
    density: TransactionListDensity,
    modifier: Modifier = Modifier
) {
    val content = when (val style = item.content) {
        is ContentListStyle.Default ->
            style.copy(size = ContentListSize.Sm, type = style.type ?: ContentListType.Default)

        is ContentListStyle.Inverted -> style.copy(size = ContentListSize.Sm)
        is ContentListStyle.Strikethrough -> style.copy(size = ContentListSize.Sm)
        is ContentListStyle.Amount -> style.copy(size = ContentListSize.Sm)
        is ContentListStyle.Transaction -> style
    }

    TransactionListMainRow(
        modifier = modifier,
        content = content,
        amount = item.amount.copy(size = ContentListSize.Sm),
        icon = null,
        state = OceanTransactionListState.Default,
        density = density
    )
}

@Composable
private fun footerDivider() {
    OceanDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = OceanSpacing.xs)
            .testTag(FOOTER_DIVIDER_TAG)
    )
}

private const val FOOTER_BODY_TAG = "transaction-footer-body"
private const val FOOTER_ROOT_TAG = "transaction-footer-root"
private const val FOOTER_NOTICE_TAG = "transaction-footer-notice"
private const val FOOTER_NOTICE_ICON_TAG = "transaction-footer-notice-icon"
private const val FOOTER_NOTICE_TITLE_TAG = "transaction-footer-notice-title"
private const val FOOTER_MAIN_ROW_TAG = "transaction-footer-main-row"
private const val FOOTER_DETAIL_ROW_TAG = "transaction-footer-detail-row"
private const val FOOTER_TOTAL_TAG = "transaction-footer-total"
private const val FOOTER_ACTION_TAG = "transaction-footer-action"
private const val FOOTER_DIVIDER_TAG = "transaction-footer-divider"
private const val FOOTER_TOP_BORDER_TAG = "transaction-footer-top-border"

@Preview(showBackground = true)
@Composable
private fun OceanTransactionFooterContentPreview() {
    OceanTheme {
        OceanTransactionFooter(
            total = OceanTransactionFooterTotal("Total", "R$ 1.250,00"),
            button = OceanButtonModel(
                text = "Continuar",
                onClick = {},
                buttonStyle = br.com.useblu.oceands.ui.compose.OceanButtonStyle.PrimaryMedium
            ),
            type = OceanTransactionFooterType.Highlight,
            notice = OceanTransactionFooterNotice(
                title = "Desconto aplicado",
                description = "Texto de apoio do aviso."
            ),
            mainItem = OceanTransactionFooterItem(
                content = ContentListStyle.Default(title = "Subtotal"),
                amount = ContentListStyle.Amount(amount = "R$ 1.250,00")
            ),
            details = listOf(
                OceanTransactionFooterItem(
                    content = ContentListStyle.Default(title = "Parcelamento"),
                    amount = ContentListStyle.Amount(
                        amount = "5x de R$ 250,00",
                        additionalData = "sem acréscimo"
                    )
                )
            )
        )
    }
}
