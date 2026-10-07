package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.model.compose.OceanIconModel

/** Figma `State` shared by the Transaction List family (MR-615). */
enum class OceanTransactionListState {
    Default,
    Disabled,
    Loading
}

/** Figma `Position` of a child item inside a group: drives the timeline line above/below the icon. */
enum class OceanTransactionListPosition {
    Standalone,
    First,
    Middle,
    Last;

    internal val hasLineAbove: Boolean get() = this == Middle || this == Last
    internal val hasLineBelow: Boolean get() = this == First || this == Middle

    companion object {
        /** Position of the item at [index] in a group of [count] items. */
        fun of(index: Int, count: Int): OceanTransactionListPosition = when {
            count <= 1 -> Standalone
            index == 0 -> First
            index == count - 1 -> Last
            else -> Middle
        }
    }
}

/** Figma `Controller type` of the Transaction List Selectable. */
enum class OceanTransactionListController {
    Checkbox,
    Radio
}

/**
 * One child of [br.com.useblu.oceands.components.compose.list.OceanTransactionListExpandable].
 * With [onClick] it renders as [OceanChildTransactionListAction]; without it, as
 * [OceanChildTransactionListReadOnly].
 */
@Immutable
data class OceanTransactionListChildItem(
    val content: ContentListStyle,
    val amount: ContentListStyle.Amount? = null,
    val icon: OceanIconModel? = null,
    val state: OceanTransactionListState = OceanTransactionListState.Default,
    val onClick: (() -> Unit)? = null
)
