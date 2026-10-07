package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.input.OceanSelectableBox
import br.com.useblu.oceands.components.compose.input.OceanSelectableRadio
import br.com.useblu.oceands.ui.compose.OceanSpacing

/**
 * Transaction List Selectable (Figma `24320-5708`, Platform App, MR-615): the controller sits on
 * the trailing side and the whole item toggles it.
 *
 * @param selected controlled selection state.
 * @param onSelectedChange called with the new state; [OceanTransactionListController.Radio]
 * only selects (always `true`), like the Ocean radio.
 * @param indeterminate shows the checkbox indeterminate mark (Checkbox only).
 * @param showError shows the controller error border.
 */
@Suppress("LongParameterList")
@Composable
fun OceanTransactionListSelectable(
    content: ContentListStyle,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    amount: ContentListStyle.Amount? = null,
    controller: OceanTransactionListController = OceanTransactionListController.Checkbox,
    state: OceanTransactionListState = OceanTransactionListState.Default,
    indeterminate: Boolean = false,
    showError: Boolean = false,
    showDivider: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val enabled = state == OceanTransactionListState.Default
    val toggle = {
        val newValue = controller == OceanTransactionListController.Radio || indeterminate || !selected
        onSelectedChange(newValue)
    }

    Column(
        modifier = modifier
            .background(highlightBackground(enabled && isHovered))
            .hoverable(interactionSource = interactionSource, enabled = enabled)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = if (controller == OceanTransactionListController.Radio) Role.RadioButton else Role.Checkbox,
                onClick = toggle
            )
            .semantics {
                this.selected = selected
                stateDescription = if (selected) "selected" else "not selected"
            }
    ) {
        TransactionListMainRow(
            content = content,
            amount = amount,
            icon = null,
            state = state,
            trailingGap = OceanSpacing.xs,
            trailing = {
                Controller(
                    controller = controller,
                    selected = selected,
                    indeterminate = indeterminate,
                    showError = showError,
                    enabled = state != OceanTransactionListState.Disabled,
                    onToggle = toggle
                )
            }
        )

        if (showDivider) {
            TransactionListDivider()
        }
    }
}

@Composable
private fun Controller(
    controller: OceanTransactionListController,
    selected: Boolean,
    indeterminate: Boolean,
    showError: Boolean,
    enabled: Boolean,
    onToggle: () -> Unit
) {
    val modifier = Modifier.testTag(TransactionListTestTags.CONTROL)
    when (controller) {
        OceanTransactionListController.Checkbox -> OceanSelectableBox(
            modifier = modifier,
            selected = selected || indeterminate,
            unsettled = indeterminate,
            showError = showError,
            enabled = enabled,
            onSelectedBox = { onToggle() }
        )

        OceanTransactionListController.Radio -> Box(modifier = modifier) {
            OceanSelectableRadio(
                isSelected = selected,
                showError = showError,
                enabled = enabled,
                onSelectedBox = onToggle
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 520)
@Composable
private fun OceanTransactionListSelectablePreview() {
    var checked by remember { mutableStateOf(true) }
    var radio by remember { mutableStateOf(false) }
    Column {
        OceanTransactionListSelectable(
            content = TransactionListPreviewData.content,
            amount = TransactionListPreviewData.amount,
            selected = checked,
            onSelectedChange = { checked = it }
        )
        OceanTransactionListSelectable(
            content = TransactionListPreviewData.content,
            amount = TransactionListPreviewData.amount,
            controller = OceanTransactionListController.Radio,
            selected = radio,
            onSelectedChange = { radio = it }
        )
        OceanTransactionListSelectable(
            content = TransactionListPreviewData.content,
            amount = TransactionListPreviewData.amount,
            selected = false,
            indeterminate = true,
            onSelectedChange = {}
        )
        OceanTransactionListSelectable(
            content = TransactionListPreviewData.content,
            amount = TransactionListPreviewData.amount,
            selected = true,
            state = OceanTransactionListState.Disabled,
            onSelectedChange = {}
        )
        OceanTransactionListSelectable(
            content = TransactionListPreviewData.content,
            amount = TransactionListPreviewData.amount,
            selected = false,
            state = OceanTransactionListState.Loading,
            onSelectedChange = {}
        )
    }
}
