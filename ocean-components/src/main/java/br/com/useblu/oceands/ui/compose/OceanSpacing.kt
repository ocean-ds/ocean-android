package br.com.useblu.oceands.ui.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.OceanDS
import br.com.useblu.oceands.tokens.OceanSpacingTokens

@Immutable
object OceanSpacing {
    private val tokens get() = OceanDS.spacingTokens ?: object : OceanSpacingTokens {}

    /**
     * 4dp (base)
     */
    val xxxs @Composable get() = tokens.xxxs

    /**
     * 8dp (base)
     */
    val xxs @Composable get() = tokens.xxs

    /**
     * 12dp (base)
     */
    val xxsExtra @Composable get() = tokens.xxsExtra

    /**
     * 16dp (base)
     */
    val xs @Composable get() = tokens.xs

    /**
     * 24dp (base)
     */
    val sm @Composable get() = tokens.sm

    /**
     * 32dp (base)
     */
    val md @Composable get() = tokens.md

    /**
     * 40dp (base)
     */
    val lg @Composable get() = tokens.lg

    /**
     * 48dp (base)
     */
    val xl @Composable get() = tokens.xl

    /**
     * 64dp (base)
     */
    val xxl @Composable get() = tokens.xxl

    /**
     * 80dp (base)
     */
    val xxxl @Composable get() = tokens.xxxl

    /**
     * 4dp (base)
     */
    @Composable
    fun StackXXXS() {
        Spacer(modifier = Modifier.size(xxxs))
    }

    /**
     * 8dp
     */
    @Composable
    fun StackXXS() {
        Spacer(modifier = Modifier.size(xxs))
    }

    /**
     * 12dp
     */
    @Composable
    fun StackXXSExtra() {
        Spacer(modifier = Modifier.size(xxsExtra))
    }

    /**
     * 16dp
     */
    @Composable
    fun StackXS() {
        Spacer(modifier = Modifier.size(xs))
    }

    /**
     * 24dp
     */
    @Composable
    fun StackSM() {
        Spacer(modifier = Modifier.size(sm))
    }

    /**
     * 32dp
     */
    @Composable
    fun StackMD() {
        Spacer(modifier = Modifier.size(md))
    }

    /**
     * 40dp
     */
    @Composable
    fun StackLG() {
        Spacer(modifier = Modifier.size(lg))
    }

    /**
     * 48dp
     */
    @Composable
    fun StackXL() {
        Spacer(modifier = Modifier.size(xl))
    }

    /**
     * 64dp
     */
    @Composable
    fun StackXXL() {
        Spacer(modifier = Modifier.size(xxl))
    }

    /**
     * 80dp
     */
    @Composable
    fun StackXXXL() {
        Spacer(modifier = Modifier.size(xxxl))
    }
}
