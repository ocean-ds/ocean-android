package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.ContentListSize
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
    const val MENU = "transaction_list_menu"
    const val LEADING_ICON = "transaction_list_leading_icon"
    const val AMOUNT = "transaction_list_amount"
}

private val LEADING_ICON_SIZE = 24.dp
private val CHILD_ICON_SIZE = 16.dp
private val AMOUNT_SKELETON_WIDTH = 86.dp
private val TITLE_SKELETON_WIDTH = 96.dp
private val SKELETON_HEIGHT = 16.dp
private val TIMELINE_WIDTH = 1.dp

/** The Amount block takes at most half of the row (overflow rule shared with ocean-web). */
internal const val AMOUNT_MAX_WIDTH_FRACTION = 0.5f

/**
 * Content block + Amount block, gap 8 (Figma `Content`).
 *
 * Overflow rule (same as ocean-web): the Content is never squeezed — it takes all the width the
 * Amount leaves (weight 1, fill) — and the Amount is limited to [AMOUNT_MAX_WIDTH_FRACTION] of the
 * row, so a long Tag ends in an ellipsis instead of pushing the Content. A custom layout instead of
 * `BoxWithConstraints` because the child rows measure intrinsic heights (`IntrinsicSize.Min`).
 */
@Composable
internal fun TransactionListContent(
    modifier: Modifier = Modifier,
    content: ContentListStyle,
    amount: ContentListStyle.Amount?,
    enabled: Boolean,
    defaultSize: ContentListSize = ContentListSize.Md
) {
    val gap = OceanSpacing.xxs
    val measurePolicy = remember(gap) { ContentAmountMeasurePolicy(gap) }
    Layout(
        modifier = modifier,
        measurePolicy = measurePolicy,
        content = {
            OceanContentList(
                style = content.withTokens(defaultSize),
                enabled = enabled
            )
            amount?.let {
                OceanContentList(
                    modifier = Modifier.testTag(TransactionListTestTags.AMOUNT),
                    style = it.withTokens(defaultSize),
                    enabled = enabled
                )
            }
        }
    )
}

/**
 * Children: Content, then the optional Amount. The Amount is measured first, capped at [AMOUNT_MAX_WIDTH_FRACTION] of the width; the Content gets exactly the
 * rest (the `weight(1f, fill = true)` of a Row). Both centred vertically. The intrinsics follow the
 * same split, so `IntrinsicSize.Min` in the child rows gives the real height.
 */
private class ContentAmountMeasurePolicy(private val gap: Dp) : MeasurePolicy {

    override fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): MeasureResult {
        val contentMeasurable = measurables.first()
        val amountMeasurable = measurables.getOrNull(1)
        val gapPx = if (amountMeasurable != null) gap.roundToPx() else 0
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        val bounded = constraints.hasBoundedWidth

        val amountPlaceable = amountMeasurable?.measure(
            if (bounded) loose.copy(maxWidth = amountMaxWidth(constraints.maxWidth)) else loose
        )
        val amountWidth = amountPlaceable?.width ?: 0
        val contentWidth = (constraints.maxWidth - amountWidth - gapPx).coerceAtLeast(0)
        val contentPlaceable = contentMeasurable.measure(
            if (bounded) loose.copy(minWidth = contentWidth, maxWidth = contentWidth) else loose
        )

        val width = if (bounded) {
            constraints.maxWidth
        } else {
            (contentPlaceable.width + gapPx + amountWidth).coerceAtLeast(constraints.minWidth)
        }
        val height = maxOf(contentPlaceable.height, amountPlaceable?.height ?: 0)
            .coerceIn(constraints.minHeight, constraints.maxHeight)

        return layout(width, height) {
            contentPlaceable.placeRelative(0, (height - contentPlaceable.height) / 2)
            amountPlaceable?.placeRelative(width - amountWidth, (height - amountPlaceable.height) / 2)
        }
    }

    override fun IntrinsicMeasureScope.minIntrinsicHeight(
        measurables: List<IntrinsicMeasurable>,
        width: Int
    ): Int = intrinsicHeight(measurables, width) { m, w -> m.minIntrinsicHeight(w) }

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(
        measurables: List<IntrinsicMeasurable>,
        width: Int
    ): Int = intrinsicHeight(measurables, width) { m, w -> m.maxIntrinsicHeight(w) }

    override fun IntrinsicMeasureScope.minIntrinsicWidth(
        measurables: List<IntrinsicMeasurable>,
        height: Int
    ): Int = intrinsicWidth(measurables) { it.minIntrinsicWidth(height) }

    override fun IntrinsicMeasureScope.maxIntrinsicWidth(
        measurables: List<IntrinsicMeasurable>,
        height: Int
    ): Int = intrinsicWidth(measurables) { it.maxIntrinsicWidth(height) }

    private fun IntrinsicMeasureScope.intrinsicHeight(
        measurables: List<IntrinsicMeasurable>,
        width: Int,
        heightOf: (IntrinsicMeasurable, Int) -> Int
    ): Int {
        val content = measurables.first()
        val amount = measurables.getOrNull(1)
            ?: return heightOf(content, width)
        if (width == Constraints.Infinity) return maxOf(heightOf(content, width), heightOf(amount, width))
        val amountWidth = minOf(amount.maxIntrinsicWidth(Constraints.Infinity), amountMaxWidth(width))
        val contentWidth = (width - amountWidth - gap.roundToPx()).coerceAtLeast(0)
        return maxOf(heightOf(content, contentWidth), heightOf(amount, amountWidth))
    }

    private fun IntrinsicMeasureScope.intrinsicWidth(
        measurables: List<IntrinsicMeasurable>,
        widthOf: (IntrinsicMeasurable) -> Int
    ): Int {
        val gapPx = if (measurables.size > 1) gap.roundToPx() else 0
        return measurables.sumOf(widthOf) + gapPx
    }

    private fun amountMaxWidth(width: Int) = (width * AMOUNT_MAX_WIDTH_FRACTION).toInt()
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
    color: Color,
    defaultSize: Dp = LEADING_ICON_SIZE
) {
    // OceanIconModel.tint is ignored on purpose: the family only takes the closed set of colors.
    OceanIcon(
        iconType = icon.icon,
        modifier = Modifier
            .size(icon.size ?: defaultSize)
            .testTag(TransactionListTestTags.LEADING_ICON),
        tint = if (enabled) color else OceanColors.interfaceLightDeep
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

/**
 * Overlay of the pressed/hovered state: `Interface/Light/Up`. Transparent at rest, so a background
 * set by the screen through `modifier` (e.g. a `Status/Warning/Up` hero) shows through.
 */
@Composable
internal fun highlightBackground(highlighted: Boolean): Color =
    if (highlighted) OceanColors.interfaceLightUp else Color.Transparent

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
    iconColor: TransactionListIconColor? = null,
    density: TransactionListDensity = TransactionListDensity.Default,
    trailing: (@Composable RowScope.() -> Unit)? = null
) {
    val enabled = state != OceanTransactionListState.Disabled
    val compact = density == TransactionListDensity.Compact

    if (state == OceanTransactionListState.Loading) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = OceanSpacing.xs,
                    vertical = if (compact) OceanSpacing.xxs else OceanSpacing.xs
                ),
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
        Timeline(icon = icon, position = position, enabled = enabled, iconColor = iconColor)

        TransactionListContent(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = if (compact) OceanSpacing.xxs else OceanSpacing.xxsExtra),
            content = content,
            amount = amount,
            enabled = enabled,
            defaultSize = ContentListSize.Sm
        )

        trailing?.invoke(this)
    }
}

@Composable
private fun Timeline(
    icon: OceanIconModel?,
    position: OceanTransactionListPosition,
    enabled: Boolean,
    iconColor: TransactionListIconColor?
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
                LeadingIcon(
                    icon = it,
                    enabled = enabled,
                    // Children default to Interface/Light/Down; an explicit color overrides it.
                    color = iconColor?.color ?: OceanColors.interfaceLightDown,
                    defaultSize = CHILD_ICON_SIZE
                )
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
    iconColor: TransactionListIconColor = TransactionListIconColor.Default,
    density: TransactionListDensity = TransactionListDensity.Default,
    trailingGap: Dp = 12.dp,
    endPadding: Dp = OceanSpacing.xs,
    trailing: (@Composable () -> Unit)? = null
) {
    val enabled = state != OceanTransactionListState.Disabled
    val isLoading = state == OceanTransactionListState.Loading
    val vertical = if (density == TransactionListDensity.Compact) OceanSpacing.xxs else OceanSpacing.xs

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = OceanSpacing.xs,
                top = vertical,
                bottom = vertical,
                end = endPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            if (isLoading) LeadingIconSkeleton() else LeadingIcon(icon = icon, enabled = enabled, color = iconColor.color)
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

/**
 * Kebab of the Menu type (Figma `_Contextual Menu`): `dotsVertical` 20dp centred in a 32dp round
 * touch area. Active (menu open) fills the area with `Interface/Light/Up` and tints the icon
 * `Brand/Primary/Pure`; disabled tints it `Interface/Light/Deep`.
 */
@Composable
internal fun TrailingMenu(
    enabled: Boolean,
    active: Boolean,
    onClick: () -> Unit
) {
    val showActive = enabled && active
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(if (showActive) OceanColors.interfaceLightUp else Color.Transparent)
            .clickable(
                enabled = enabled,
                role = Role.Button,
                onClickLabel = "menu",
                onClick = onClick
            )
            .semantics { selected = showActive }
            .testTag(TransactionListTestTags.MENU),
        contentAlignment = Alignment.Center
    ) {
        OceanIcon(
            iconType = OceanIcons.DOTS_VERTICAL_SOLID,
            modifier = Modifier.size(20.dp),
            tint = when {
                !enabled -> OceanColors.interfaceLightDeep
                showActive -> OceanColors.brandPrimaryPure
                else -> OceanColors.interfaceDarkUp
            }
        )
    }
}
