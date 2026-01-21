package br.com.useblu.oceands.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanBorderRadius.Corners

sealed interface OceanBorderRadius {
    /**
     * No Corner Border Radius
     */
    data object None : OceanBorderRadius

    /**
     * 4dp Corner Border Radius
     * @param corners Set of Corners to apply the border radius
     * Corners.All -> Apply the border radius to all corners
     * Corners.Top -> Apply the border radius to the top corners (start and end)
     * Corners.Bottom -> Apply the border radius to the bottom corners (start and end)
     * Corners.TopStart -> Apply the border radius to the top start corner (left)
     * Corners.TopEnd -> Apply the border radius to the top end corner (right)
     * Corners.BottomStart -> Apply the border radius to the bottom start corner (left)
     * Corners.BottomEnd -> Apply the border radius to the bottom end corner (right)
     * @see Corners
     */
    data class Tiny(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            /**
             * 4dp Corner Border Radius for all corners
             */
            val allCorners: OceanBorderRadius
                get() {
                    return Tiny(corners = setOf(Corners.All))
                }

            /**
             * 4dp Corner Border Radius for top corners
             */
            val topCorners: OceanBorderRadius
                get() {
                    return Tiny(corners = setOf(Corners.Top))
                }

            /**
             * 4dp Corner Border Radius for bottom corners
             */
            val bottomCorners: OceanBorderRadius
                get() {
                    return Tiny(corners = setOf(Corners.Bottom))
                }
        }
    }

    /**
     * 8dp Corner Border Radius
     * @param corners Set of Corners to apply the border radius
     * Corners.All -> Apply the border radius to all corners
     * Corners.Top -> Apply the border radius to the top corners (start and end)
     * Corners.Bottom -> Apply the border radius to the bottom corners (start and end)
     * Corners.TopStart -> Apply the border radius to the top start corner (left)
     * Corners.TopEnd -> Apply the border radius to the top end corner (right)
     * Corners.BottomStart -> Apply the border radius to the bottom start corner (left)
     * Corners.BottomEnd -> Apply the border radius to the bottom end corner (right)
     * @see Corners
     */
    data class SM(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            /**
             * 8dp Corner Border Radius for all corners
             */
            val allCorners: OceanBorderRadius
                get() {
                    return SM(corners = setOf(Corners.All))
                }

            /**
             * 8dp Corner Border Radius for top corners
             */
            val topCorners: OceanBorderRadius
                get() {
                    return SM(corners = setOf(Corners.Top))
                }

            /**
             * 8dp Corner Border Radius for bottom corners
             */
            val bottomCorners: OceanBorderRadius
                get() {
                    return SM(corners = setOf(Corners.Bottom))
                }
        }
    }

    /**
     * 12dp Corner Border Radius
     * @param corners Set of Corners to apply the border radius
     * Corners.All -> Apply the border radius to all corners
     * Corners.Top -> Apply the border radius to the top corners (start and end)
     * Corners.Bottom -> Apply the border radius to the bottom corners (start and end)
     * Corners.TopStart -> Apply the border radius to the top start corner (left)
     * Corners.TopEnd -> Apply the border radius to the top end corner (right)
     * Corners.BottomStart -> Apply the border radius to the bottom start corner (left)
     * Corners.BottomEnd -> Apply the border radius to the bottom end corner (right)
     * @see Corners
     */
    data class MD(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            /**
             * 12dp Corner Border Radius for all corners
             */
            val allCorners: OceanBorderRadius
                get() {
                    return MD(corners = setOf(Corners.All))
                }

            /**
             * 12dp Corner Border Radius for top corners
             */
            val topCorners: OceanBorderRadius
                get() {
                    return MD(corners = setOf(Corners.Top))
                }

            /**
             * 12dp Corner Border Radius for bottom corners
             */
            val bottomCorners: OceanBorderRadius
                get() {
                    return MD(corners = setOf(Corners.Bottom))
                }
        }
    }

    /**
     * 16dp Corner Border Radius
     * @param corners Set of Corners to apply the border radius
     * Corners.All -> Apply the border radius to all corners
     * Corners.Top -> Apply the border radius to the top corners (start and end)
     * Corners.Bottom -> Apply the border radius to the bottom corners (start and end)
     * Corners.TopStart -> Apply the border radius to the top start corner (left)
     * Corners.TopEnd -> Apply the border radius to the top end corner (right)
     * Corners.BottomStart -> Apply the border radius to the bottom start corner (left)
     * Corners.BottomEnd -> Apply the border radius to the bottom end corner (right)
     * @see Corners
     */
    data class LG(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            /**
             * 16dp Corner Border Radius for all corners
             */
            val allCorners: OceanBorderRadius
                get() {
                    return LG(corners = setOf(Corners.All))
                }

            /**
             * 16dp Corner Border Radius for top corners
             */
            val topCorners: OceanBorderRadius
                get() {
                    return LG(corners = setOf(Corners.Top))
                }

            /**
             * 16dp Corner Border Radius for bottom corners
             */
            val bottomCorners: OceanBorderRadius
                get() {
                    return LG(corners = setOf(Corners.Bottom))
                }
        }
    }

    /**
     * 56dp Corner Border Radius
     * @param corners Set of Corners to apply the border radius
     * Corners.All -> Apply the border radius to all corners
     * Corners.Top -> Apply the border radius to the top corners (start and end)
     * Corners.Bottom -> Apply the border radius to the bottom corners (start and end)
     * Corners.TopStart -> Apply the border radius to the top start corner (left)
     * Corners.TopEnd -> Apply the border radius to the top end corner (right)
     * Corners.BottomStart -> Apply the border radius to the bottom start corner (left)
     * Corners.BottomEnd -> Apply the border radius to the bottom end corner (right)
     * @see Corners
     */
    data class Pill(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            /**
             * 56dp Corner Border Radius for all corners
             */
            val allCorners: OceanBorderRadius
                get() {
                    return Pill(corners = setOf(Corners.All))
                }

            /**
             * 56dp Corner Border Radius for top corners
             */
            val topCorners: OceanBorderRadius
                get() {
                    return Pill(corners = setOf(Corners.Top))
                }

            /**
             * 56dp Corner Border Radius for bottom corners
             */
            val bottomCorners: OceanBorderRadius
                get() {
                    return Pill(corners = setOf(Corners.Bottom))
                }
        }
    }

    /**
     * 50% Corner Border Radius (circular)
     * @param corners Set of Corners to apply the border radius
     * Corners.All -> Apply the border radius to all corners
     * Corners.Top -> Apply the border radius to the top corners (start and end)
     * Corners.Bottom -> Apply the border radius to the bottom corners (start and end)
     * Corners.TopStart -> Apply the border radius to the top start corner (left)
     * Corners.TopEnd -> Apply the border radius to the top end corner (right)
     * Corners.BottomStart -> Apply the border radius to the bottom start corner (left)
     * Corners.BottomEnd -> Apply the border radius to the bottom end corner (right)
     * @see Corners
     */
    data class Circle(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            /**
             * 50% Corner Border Radius for all corners
             */
            val allCorners: OceanBorderRadius
                get() {
                    return Circle(corners = setOf(Corners.All))
                }

            /**
             * 50% Corner Border Radius for top corners
             */
            val topCorners: OceanBorderRadius
                get() {
                    return Circle(corners = setOf(Corners.Top))
                }

            /**
             * 50% Corner Border Radius for bottom corners
             */
            val bottomCorners: OceanBorderRadius
                get() {
                    return Circle(corners = setOf(Corners.Bottom))
                }
        }
    }

    //region Properties

    val value: Dp
        get() {
            return when (this) {
                is None -> Dp.Hairline
                is Tiny -> Dp(4f)
                is SM -> Dp(8f)
                is MD -> Dp(12f)
                is LG -> Dp(16f)
                is Pill -> Dp(56f)
                is Circle -> Dp.Infinity
                is OceanBorderRadiusSetCorners -> Dp.Infinity
            }
        }

    //endregion

    //region Public Methods

    fun shape(): RoundedCornerShape {
        val corners = when (this) {
            is None -> emptySet()
            is OceanBorderRadiusSetCorners -> corners
        }

        if (corners.contains(Corners.All)) {
            return RoundedCornerShape(corner = getCornerSize())
        }
        var topStart = if (corners.contains(Corners.TopStart)) getCornerSize() else CornerSize(0)
        var topEnd = if (corners.contains(Corners.TopEnd)) getCornerSize() else CornerSize(0)
        var bottomStart = if (corners.contains(Corners.BottomStart)) getCornerSize() else CornerSize(0)
        var bottomEnd = if (corners.contains(Corners.BottomEnd)) getCornerSize() else CornerSize(0)

        if (corners.contains(Corners.Top)) {
            topStart = getCornerSize()
            topEnd = getCornerSize()
        }

        if (corners.contains(Corners.Bottom)) {
            bottomStart = getCornerSize()
            bottomEnd = getCornerSize()
        }

        return RoundedCornerShape(
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart
        )
    }

    //endregion

    //region Private Methods

    private fun getCornerSize(): CornerSize {
        return when (this) {
            is Circle -> CornerSize(percent = 50)
            is None,
            is Tiny,
            is SM,
            is MD,
            is LG,
            is Pill,
            is OceanBorderRadiusSetCorners -> CornerSize(size = value)
        }
    }

    //endregion

    //region Structs Helpers

    enum class Corners {
        All,
        Top,
        Bottom,
        TopStart,
        TopEnd,
        BottomStart,
        BottomEnd
    }

    //endregion
}

//region Modifiers

fun Modifier.borderRadius(borderRadius: OceanBorderRadius): Modifier {
    return this
        .clip(shape = borderRadius.shape())
}

fun Modifier.borderBackground(color: Color, borderRadius: OceanBorderRadius): Modifier {
    return this
        .borderRadius(borderRadius)
        .background(color = color, shape = borderRadius.shape())
}

fun Modifier.borderBackground(brush: Brush, borderRadius: OceanBorderRadius): Modifier {
    return this
        .borderRadius(borderRadius)
        .background(brush = brush)
}

fun Modifier.borderWithBackground(
    borderWidth: Dp = 1.dp,
    borderColor: Color,
    backgroundColor: Color,
    borderRadius: OceanBorderRadius
): Modifier {
    return this
        .border(
            width = borderWidth,
            color = borderColor,
            shape = borderRadius.shape()
        )
        .borderBackground(
            color = backgroundColor,
            borderRadius = borderRadius
        )
}

//endregion

//region Group Type Helper

private interface OceanBorderRadiusSetCorners : OceanBorderRadius {
    val corners: Set<Corners>
}

//endregion
