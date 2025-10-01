package br.com.useblu.oceands.client.ui.textlistaction

sealed interface OceanTextListActionKind {
    data object Chevron : OceanTextListActionKind
    // TODO: implement Menu and Swipe options
}
