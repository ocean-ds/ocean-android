package br.com.useblu.oceands.components.compose.expandabletextlisticonitem.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons

sealed interface OceanExpandableTextListIconItemChildType<ChildReferenceKey> {
    data class Default<ChildReferenceKey>(
        override val items: List<OceanExpandableTextListIconItemChild<ChildReferenceKey>>,
        val actionType: ActionType = ActionType.Custom()
    ) : OceanExpandableTextListIconItemChildType<ChildReferenceKey> {

        override val actionIcon: OceanIcons? = when (actionType) {
            is ActionType.Edit -> OceanIcons.DOTS_VERTICAL_SOLID
            is ActionType.Custom -> actionType.icon
        }

        sealed interface ActionType {
            data class Edit(val options: List<Option>, val onClick: (Int) -> Unit): ActionType {
                data class Option(val title: String, val color: Color? = null)
            }
            data class Custom(val icon: OceanIcons? = null): ActionType
        }
    }

    data class WithSwipe<ChildReferenceKey>(
        override val items: List<OceanExpandableTextListIconItemChild<ChildReferenceKey>>,
        val contentSize: Dp,
        val content: @Composable (ChildReferenceKey) -> Unit
    ) : OceanExpandableTextListIconItemChildType<ChildReferenceKey> {
        override val actionIcon: OceanIcons? = null
    }
    val actionIcon: OceanIcons?
    val items: List<OceanExpandableTextListIconItemChild<ChildReferenceKey>>
}