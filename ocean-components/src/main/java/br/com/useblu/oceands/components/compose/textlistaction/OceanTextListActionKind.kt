package br.com.useblu.oceands.components.compose.textlistaction

sealed interface OceanTextListActionKind {
    data class Chevron(override val action: () -> Unit) : OceanTextListActionKind, OceanTextListActionKindContainerAction
    // TODO: implement Menu and Swipe options

    fun isContainerAction(): Boolean = this is OceanTextListActionKindContainerAction
}

interface OceanTextListActionKindContainerAction {
    val action: () -> Unit
}
