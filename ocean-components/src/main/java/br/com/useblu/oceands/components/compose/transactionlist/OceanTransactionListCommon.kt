package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.OceanContentList
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanShimmering
import br.com.useblu.oceands.components.compose.withTokens
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

internal object TransactionListTestTags {
    const val DIVIDER = "transaction_list_divider"
    const val CHEVRON = "transaction_list_chevron"
    const val LOADING = "transaction_list_loading"
    const val LINE_ABOVE = "transaction_list_line_above"
    const val LINE_BELOW = "transaction_list_line_below"
    const val CONTROL = "transaction_list_control"
}

private val LEADING_ICON_SIZE = 24.dp
private val CHILD_ICON_SIZE = 16.dp
private val AMOUNT_SKELETON_WIDTH = 86.dp
private val TITLE_SKELETON_WIDTH = 96.dp
private val SKELETON_HEIGHT = 16.dp
private val TIMELINE_WIDTH = 1.dp

/** Content block + Amount block, gap 8 (Figma `Content`). */
@Composable
internal fun TransactionListContent(
    modifier: Modifier = Modifier,
    content: ContentListStyle,
    amount: ContentListStyle.Amount?,
    enabled: Boolean
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        OceanContentList(
            modifier = Modifier.weight(1f),
            style = content.withTokens(),
            enabled = enabled
        )
        amount?.let {
            OceanContentList(
                style = it,
                enabled = enabled
            )
        }
    }
}

/** Loading skeleton of the Content: two lines on the start, two 86dp bars on the end. */
@Composable
internal fun TransactionListContentSkeleton(modifier: Modifier = Modifier) {
    OceanShimmering { brush ->
        Row(
            modifier = modifier.testTag(TransactionListTestTags.LOADING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
            ) {
                SkeletonBar(Modifier.width(TITLE_SKELETON_WIDTH), brush)
                SkeletonBar(Modifier.fillMaxWidth(), brush)
            }
            Column(
                modifier = Modifier.width(AMOUNT_SKELETON_WIDTH),
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
            ) {
                SkeletonBar(Modifier.fillMaxWidth(), brush)
                SkeletonBar(Modifier.fillMaxWidth(), brush)
            }
        }
    }
}

@Composable
internal fun LeadingIconSkeleton() {
    OceanShimmering { brush ->
        Spacer(
            modifier = Modifier
                .size(LEADING_ICON_SIZE)
                .borderBackground(
                    brush = brush,
                    borderRadius = OceanBorderRadius.Tiny.allCorners
                )
        )
    }
}

@Composable
private fun SkeletonBar(modifier: Modifier, brush: androidx.compose.ui.graphics.Brush) {
    Spacer(
        modifier = modifier
            .height(SKELETON_HEIGHT)
            .borderBackground(
                brush = brush,
                borderRadius = OceanBorderRadius.Tiny.allCorners
            )
    )
}

@Composable
internal fun LeadingIcon(
    icon: OceanIconModel,
    enabled: Boolean,
    defaultSize: Dp = LEADING_ICON_SIZE
) {
    OceanIcon(
        iconType = icon.icon,
        modifier = Modifier.size(icon.size ?: defaultSize),
        tint = icon.tint.takeOrElse {
            if (enabled) OceanColors.interfaceDarkUp else OceanColors.interfaceLightDeep
        }
    )
}

/** Chevron of the Action variants; disabled uses `Interface/Light/Deep` (CA-8). */
@Composable
internal fun TrailingChevron(
    enabled: Boolean,
    icon: OceanIcons = OceanIcons.CHEVRON_RIGHT_SOLID
) {
    OceanIcon(
        iconType = icon,
        modifier = Modifier
            .size(20.dp)
            .testTag(TransactionListTestTags.CHEVRON),
        tint = if (enabled) OceanColors.interfaceDarkUp else OceanColors.interfaceLightDeep
    )
}

/** Divider with 16dp horizontal inset (Figma `Divider`). */
@Composable
internal fun TransactionListDivider() {
    OceanDivider(
        modifier = Modifier
            .padding(horizontal = OceanSpacing.xs)
            .testTag(TransactionListTestTags.DIVIDER)
    )
}

/** Background of the pressed/hovered state: `Interface/Light/Up`. */
@Composable
internal fun highlightBackground(highlighted: Boolean): Color =
    if (highlighted) OceanColors.interfaceLightUp else OceanColors.interfaceLightPure

/**
 * Row of the child items: 16dp horizontal padding, timeline column (24dp) with the icon,
 * gap 12 and the Content with 12dp vertical padding (Figma `_Child Transaction List`).
 */
@Composable
internal fun ChildTransactionListRow(
    modifier: Modifier,
    content: ContentListStyle,
    amount: ContentListStyle.Amount?,
    icon: OceanIconModel?,
    position: OceanTransactionListPosition,
    state: OceanTransactionListState,
    trailing: (@Composable RowScope.() -> Unit)? = null
) {
    val enabled = state != OceanTransactionListState.Disabled

    if (state == OceanTransactionListState.Loading) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(OceanSpacing.xs),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxsExtra)
        ) {
            if (icon != null) LeadingIconSkeleton()
            TransactionListContentSkeleton(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = OceanSpacing.xxs)
            )
        }
        return
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = OceanSpacing.xs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxsExtra)
    ) {
        Timeline(icon = icon, position = position, enabled = enabled)

        TransactionListContent(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = OceanSpacing.xxsExtra),
            content = content,
            amount = amount,
            enabled = enabled
        )

        trailing?.invoke(this)
    }
}

@Composable
private fun Timeline(
    icon: OceanIconModel?,
    position: OceanTransactionListPosition,
    enabled: Boolean
) {
    Column(
        modifier = Modifier
            .width(LEADING_ICON_SIZE)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimelineLine(
            visible = position.hasLineAbove,
            tag = TransactionListTestTags.LINE_ABOVE
        )
        icon?.let {
            Box(modifier = Modifier.padding(OceanSpacing.xxxs)) {
                LeadingIcon(icon = it, enabled = enabled, defaultSize = CHILD_ICON_SIZE)
            }
        }
        TimelineLine(
            visible = position.hasLineBelow,
            tag = TransactionListTestTags.LINE_BELOW
        )
    }
}

@Composable
private fun androidx.compose.foundation.layout.ColumnScope.TimelineLine(
    visible: Boolean,
    tag: String
) {
    if (visible) {
        Box(
            modifier = Modifier
                .weight(1f)
                .width(TIMELINE_WIDTH)
                .background(OceanColors.interfaceLightDown)
                .testTag(tag)
        )
    } else {
        Spacer(modifier = Modifier.weight(1f))
    }
}

/**
 * Main row of the top-level items: 16dp padding, leading icon, Content and trailing slot with
 * gap [trailingGap] (12 for Read Only/Action, 16 for Selectable).
 */
@Composable
internal fun TransactionListMainRow(
    modifier: Modifier = Modifier,
    content: ContentListStyle,
    amount: ContentListStyle.Amount?,
    icon: OceanIconModel?,
    state: OceanTransactionListState,
    trailingGap: Dp = 12.dp,
    trailing: (@Composable () -> Unit)? = null
) {
    val enabled = state != OceanTransactionListState.Disabled
    val isLoading = state == OceanTransactionListState.Loading

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(OceanSpacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            if (isLoading) LeadingIconSkeleton() else LeadingIcon(icon = icon, enabled = enabled)
            Spacer(modifier = Modifier.width(OceanSpacing.xxsExtra))
        }

        if (isLoading) {
            TransactionListContentSkeleton(modifier = Modifier.weight(1f))
        } else {
            TransactionListContent(
                modifier = Modifier.weight(1f),
                content = content,
                amount = amount,
                enabled = enabled
            )
        }

        if (trailing != null && !isLoading) {
            Spacer(modifier = Modifier.width(trailingGap))
            trailing()
        }
    }
}
