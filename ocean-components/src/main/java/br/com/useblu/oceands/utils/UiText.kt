package br.com.useblu.oceands.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource

@Stable
sealed interface UiText {
    @Immutable
    class StringResource(
        @StringRes val stringRes: Int,
        vararg val args: Any
    ) : UiText

    @Immutable
    data class DynamicString(val string: String) : UiText

    @Immutable
    data object Empty : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is StringResource -> stringResource(id = stringRes, args)
            is DynamicString -> string
            is Empty -> ""
        }
    }
}