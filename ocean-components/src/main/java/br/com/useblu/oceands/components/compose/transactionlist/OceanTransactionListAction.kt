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

/**
 * Transaction List Action (Figma `24289-64384`, Type Chevron, MR-615): the whole item is the
 * touch target; pressed/hovered shows the `Interface/Light/Up` highlight. Disabled and loading
 * items do not call [onClick].
 */
@Composable
fun OceanTransactionListAction(
    content: ContentListStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    amount: ContentListStyle.Amount? = null,
    state: OceanTransactionListState = OceanTransactionListState.Default,
    icon: OceanIconModel? = null,
    showDivider: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val enabled = state == OceanTransactionListState.Default

    Column(
        modifier = modifier
            .background(highlightBackground(enabled && (isPressed || isHovered)))
            .hoverable(interactionSource = interactionSource, enabled = enabled)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
    ) {
        TransactionListMainRow(
            content = content,
            amount = amount,
            icon = icon,
            state = state,
            trailing = { TrailingChevron(enabled = enabled) }
        )

        if (showDivider) {
            TransactionListDivider()
        }
    }
}

@Preview(showBackground = true, heightDp = 420)
@Composable
private fun OceanTransactionListActionPreview() {
    Column {
        OceanTransactionListState.entries.forEach { state ->
            OceanTransactionListAction(
                content = TransactionListPreviewData.content,
                amount = TransactionListPreviewData.amount,
                icon = TransactionListPreviewData.icon,
                state = state,
                onClick = {}
            )
        }
    }
}
