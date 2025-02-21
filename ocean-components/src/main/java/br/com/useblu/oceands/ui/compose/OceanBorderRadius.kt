package br.com.useblu.oceands.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import br.com.useblu.oceands.ui.compose.OceanBorderRadius.Corners

sealed interface OceanBorderRadius {
    data object None : OceanBorderRadius
    data class Tiny(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            val allCorners: OceanBorderRadius
                get() {
                    return Tiny(corners = setOf(Corners.All))
                }

            val topCorners: OceanBorderRadius
                get() {
                    return Tiny(corners = setOf(Corners.Top))
                }

            val bottomCorners: OceanBorderRadius
                get() {
                    return Tiny(corners = setOf(Corners.Bottom))
                }
        }
    }

    data class SM(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            val allCorners: OceanBorderRadius
                get() {
                    return SM(corners = setOf(Corners.All))
                }

            val topCorners: OceanBorderRadius
                get() {
                    return SM(corners = setOf(Corners.Top))
                }

            val bottomCorners: OceanBorderRadius
                get() {
                    return SM(corners = setOf(Corners.Bottom))
                }
        }
    }

    data class MD(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            val allCorners: OceanBorderRadius
                get() {
                    return MD(corners = setOf(Corners.All))
                }

            val topCorners: OceanBorderRadius
                get() {
                    return MD(corners = setOf(Corners.Top))
                }

            val bottomCorners: OceanBorderRadius
                get() {
                    return MD(corners = setOf(Corners.Bottom))
                }
        }
    }

    data class LG(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            val allCorners: OceanBorderRadius
                get() {
                    return LG(corners = setOf(Corners.All))
                }

            val topCorners: OceanBorderRadius
                get() {
                    return LG(corners = setOf(Corners.Top))
                }

            val bottomCorners: OceanBorderRadius
                get() {
                    return LG(corners = setOf(Corners.Bottom))
                }
        }
    }

    data class Pill(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            val allCorners: OceanBorderRadius
                get() {
                    return Pill(corners = setOf(Corners.All))
                }

            val topCorners: OceanBorderRadius
                get() {
                    return Pill(corners = setOf(Corners.Top))
                }

            val bottomCorners: OceanBorderRadius
                get() {
                    return Pill(corners = setOf(Corners.Bottom))
                }
        }
    }

    data class Circle(override val corners: Set<Corners> = emptySet()) : OceanBorderRadiusSetCorners {
        companion object {
            val allCorners: OceanBorderRadius
                get() {
                    return Circle(corners = setOf(Corners.All))
                }

            val topCorners: OceanBorderRadius
                get() {
                    return Circle(corners = setOf(Corners.Top))
                }

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

    val allCorners: OceanBorderRadius
        get() {
            return getCorners(setOf(Corners.All))
        }

    val topCorners: OceanBorderRadius
        get() {
            return getCorners(setOf(Corners.Top))
        }

    val bottomCorners: OceanBorderRadius
        get() {
            return getCorners(setOf(Corners.Bottom))
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

    private fun getCorners(corners: Set<Corners>): OceanBorderRadius {
        return when (this) {
            is None -> None
            is Tiny -> Tiny(corners = corners)
            is SM -> SM(corners = corners)
            is MD -> MD(corners = corners)
            is LG -> LG(corners = corners)
            is Pill -> Pill(corners = corners)
            is Circle -> Circle(corners = corners)
            is OceanBorderRadiusSetCorners -> None
        }
    }

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
        .background(color = color, shape = borderRadius.shape())
}

fun Modifier.borderBackground(brush: Brush, borderRadius: OceanBorderRadius): Modifier {
    return this
        .background(brush = brush, shape = borderRadius.shape())
}

//endregion

//region Group Type Helper

private interface OceanBorderRadiusSetCorners : OceanBorderRadius {
    val corners: Set<Corners>
}

//endregion
