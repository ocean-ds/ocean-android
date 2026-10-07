package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.ui.compose.OceanColors

/**
 * Transaction List Read Only (Figma `26559-4449`, MR-615): an item with no action.
 *
 * @param content the `_Content List / Default` block (use [ContentListStyle.Default] or
 * [ContentListStyle.Inverted] with `size`/`type`).
 * @param amount the `_Content List / Amount` block, sized independently of [content].
 */
@Composable
fun OceanTransactionListReadOnly(
    content: ContentListStyle,
    modifier: Modifier = Modifier,
    amount: ContentListStyle.Amount? = null,
    state: OceanTransactionListState = OceanTransactionListState.Default,
    icon: OceanIconModel? = null,
    showDivider: Boolean = true
) {
    Column(modifier = modifier.background(OceanColors.interfaceLightPure)) {
        TransactionListMainRow(
            content = content,
            amount = amount,
            icon = icon,
            state = state
        )

        if (showDivider) {
            TransactionListDivider()
        }
    }
}

@Preview(showBackground = true, heightDp = 420)
@Composable
private fun OceanTransactionListReadOnlyPreview() {
    Column {
        OceanTransactionListState.entries.forEach { state ->
            OceanTransactionListReadOnly(
                content = TransactionListPreviewData.content,
                amount = TransactionListPreviewData.amount,
                icon = TransactionListPreviewData.icon,
                state = state
            )
        }
    }
}
