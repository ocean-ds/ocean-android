package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.ui.compose.OceanColors

/**
 * Child Transaction List Read Only (Figma `26758-376`, MR-615): child item with the timeline and
 * no action. [content] and [amount] default to `size = Sm` (Figma child layout); pass `Md` to override.
 *
 * @param position where the item sits in its group: draws the timeline above/below the icon.
 * @param iconColor color of the leading [icon] from the closed set; disabled always uses
 *   `Interface/Light/Deep`. The `tint` of [OceanIconModel] is ignored.
 */
@Composable
fun OceanChildTransactionListReadOnly(
    content: ContentListStyle,
    modifier: Modifier = Modifier,
    amount: ContentListStyle.Amount? = null,
    position: OceanTransactionListPosition = OceanTransactionListPosition.Standalone,
    state: OceanTransactionListState = OceanTransactionListState.Default,
    icon: OceanIconModel? = null,
    iconColor: TransactionListIconColor = TransactionListIconColor.Default
) {
    ChildTransactionListRow(
        modifier = Modifier.background(OceanColors.interfaceLightPure).then(modifier),
        content = content,
        amount = amount,
        icon = icon,
        position = position,
        state = state,
        iconColor = iconColor
    )
}

/**
 * Child Transaction List Action (Figma `24323-3663`, MR-615): child item with the timeline and a
 * chevron; pressed/hovered shows the `Interface/Light/Up` highlight. Disabled and loading items
 * do not call [onClick]. [content] and [amount] default to `size = Sm`; pass `Md` to override.
 *
 * @param iconColor color of the leading [icon] from the closed set; disabled always uses
 *   `Interface/Light/Deep`. The `tint` of [OceanIconModel] is ignored.
 */
@Suppress("LongParameterList")
@Composable
fun OceanChildTransactionListAction(
    content: ContentListStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    amount: ContentListStyle.Amount? = null,
    position: OceanTransactionListPosition = OceanTransactionListPosition.Standalone,
    state: OceanTransactionListState = OceanTransactionListState.Default,
    icon: OceanIconModel? = null,
    iconColor: TransactionListIconColor = TransactionListIconColor.Default
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val enabled = state == OceanTransactionListState.Default

    ChildTransactionListRow(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure)
            .then(modifier)
            .background(highlightBackground(enabled && (isPressed || isHovered)))
            .hoverable(interactionSource = interactionSource, enabled = enabled)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            ),
        content = content,
        amount = amount,
        icon = icon,
        position = position,
        state = state,
        iconColor = iconColor,
        trailing = { TrailingChevron(enabled = enabled) }
    )
}

@Preview(showBackground = true, heightDp = 640)
@Composable
private fun OceanChildTransactionListPreview() {
    Column {
        OceanTransactionListPosition.entries.forEach { position ->
            OceanChildTransactionListAction(
                content = TransactionListPreviewData.content,
                amount = TransactionListPreviewData.amount,
                icon = TransactionListPreviewData.childIcon,
                position = position,
                onClick = {}
            )
        }
        OceanTransactionListPosition.entries.forEach { position ->
            OceanChildTransactionListReadOnly(
                content = TransactionListPreviewData.content,
                amount = TransactionListPreviewData.amount,
                icon = TransactionListPreviewData.childIcon,
                position = position,
                state = OceanTransactionListState.Disabled
            )
        }
    }
}
