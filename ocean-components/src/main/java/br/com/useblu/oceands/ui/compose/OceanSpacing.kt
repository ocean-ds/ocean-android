package br.com.useblu.oceands.ui.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.OceanDS

@Immutable
object OceanSpacing {
    private val token get() = OceanDS.spacingToken

    /**
     * 4dp (base)
     */
    val xxxs get() = token.xxxs

    /**
     * 8dp (base)
     */
    val xxs get() = token.xxs

    /**
     * 12dp (base)
     */
    val xxsExtra get() = token.xxsExtra

    /**
     * 16dp (base)
     */
    val xs get() = token.xs

    /**
     * 24dp (base)
     */
    val sm get() = token.sm

    /**
     * 32dp (base)
     */
    val md get() = token.md

    /**
     * 40dp (base)
     */
    val lg get() = token.lg

    /**
     * 48dp (base)
     */
    val xl get() = token.xl

    /**
     * 64dp (base)
     */
    val xxl get() = token.xxl

    /**
     * 80dp (base)
     */
    val xxxl get() = token.xxxl

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
