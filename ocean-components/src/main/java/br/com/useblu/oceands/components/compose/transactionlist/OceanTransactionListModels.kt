package br.com.useblu.oceands.components.compose.transactionlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.ui.compose.OceanColors

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

/**
 * Figma `Type` of the Transaction List Action supported on Android. `Swipe` is a platform
 * difference and is not offered.
 */
enum class OceanTransactionListActionType {
    /** Chevron trailing; the whole item calls `onClick` (default). */
    Chevron,

    /**
     * Kebab (`dotsVertical`) trailing in a 32dp round touch area; tapping it calls `onMenuClick`
     * and the screen shows the options in an Ocean bottom sheet. `menuActive` marks the open state.
     */
    Menu
}

/**
 * Closed set of colors for the leading icon of the Transaction List family (operator decision
 * 08/10/2026). A disabled item always uses `Interface/Light/Deep`, whatever the choice; the
 * `tint` of [OceanIconModel] is ignored by the family.
 */
enum class TransactionListIconColor {
    /** `Interface/Dark/Up` — item on a white background (default). */
    Default,

    /** `Interface/Dark/Down` — item on a colored background (e.g. `Status/Warning/Up` heroes). */
    OnColor,

    /** `Brand/Primary/Down` — more emphasis. */
    Highlight;

    internal val color: Color
        @Composable get() = when (this) {
            Default -> OceanColors.interfaceDarkUp
            OnColor -> OceanColors.interfaceDarkDown
            Highlight -> OceanColors.brandPrimaryDown
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
    val onClick: (() -> Unit)? = null,
    val iconColor: TransactionListIconColor = TransactionListIconColor.Default
)
