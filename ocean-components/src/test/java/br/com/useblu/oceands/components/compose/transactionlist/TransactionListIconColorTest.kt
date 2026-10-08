package br.com.useblu.oceands.components.compose.transactionlist

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.list.OceanTransactionListExpandable
import br.com.useblu.oceands.model.compose.OceanIconModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/** Leading icon color comes from [TransactionListIconColor]; disabled always wins (decision 08/10). */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class TransactionListIconColorTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // A custom tint that must be ignored by the family.
    private val solidIcon = OceanIconModel(icon = OceanIcons.PLACEHOLDER_SOLID, tint = Color.Red)

    private var darkUp = Color.Unspecified
    private var darkDown = Color.Unspecified
    private var primaryDown = Color.Unspecified
    private var lightDeep = Color.Unspecified

    private fun render(content: @Composable () -> Unit) {
        composeTestRule.setContent {
            darkUp = OceanColors.interfaceDarkUp
            darkDown = OceanColors.interfaceDarkDown
            primaryDown = OceanColors.brandPrimaryDown
            lightDeep = OceanColors.interfaceLightDeep
            Column(Modifier.width(360.dp)) { content() }
        }
    }

    /** Most frequent opaque, non-white pixel inside the leading icon. */
    private fun iconColor(): Int {
        composeTestRule.waitForIdle()
        val view = composeTestRule.activity.window.decorView.rootView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        view.draw(Canvas(bitmap))
        val b = composeTestRule.onNodeWithTag(TransactionListTestTags.LEADING_ICON, useUnmergedTree = true)
            .fetchSemanticsNode().boundsInWindow
        val counts = HashMap<Int, Int>()
        for (x in b.left.toInt() until b.right.toInt()) {
            for (y in b.top.toInt() until b.bottom.toInt()) {
                val pixel = bitmap.getPixel(x, y)
                if (pixel ushr 24 == 0xFF && pixel != android.graphics.Color.WHITE) {
                    counts[pixel] = (counts[pixel] ?: 0) + 1
                }
            }
        }
        return counts.maxBy { it.value }.key
    }

    private fun readOnly(
        color: TransactionListIconColor,
        state: OceanTransactionListState = OceanTransactionListState.Default
    ) = render {
        OceanTransactionListReadOnly(content = Samples.content, icon = solidIcon, iconColor = color, state = state)
    }

    @Test
    fun defaultIsInterfaceDarkUpAndIgnoresCustomTint() {
        render { OceanTransactionListReadOnly(content = Samples.content, icon = solidIcon) }
        assertEquals(darkUp.toArgb(), iconColor())
    }

    @Test
    fun onColorIsInterfaceDarkDown() {
        readOnly(TransactionListIconColor.OnColor)
        assertEquals(darkDown.toArgb(), iconColor())
    }

    @Test
    fun highlightIsBrandPrimaryDown() {
        readOnly(TransactionListIconColor.Highlight)
        assertEquals(primaryDown.toArgb(), iconColor())
    }

    @Test
    fun disabledAlwaysForcesInterfaceLightDeep() {
        readOnly(TransactionListIconColor.Highlight, OceanTransactionListState.Disabled)
        assertEquals(lightDeep.toArgb(), iconColor())
    }

    @Test
    fun actionUsesTheChosenColor() {
        render {
            OceanTransactionListAction(
                content = Samples.content,
                icon = solidIcon,
                iconColor = TransactionListIconColor.OnColor,
                onClick = {}
            )
        }
        assertEquals(darkDown.toArgb(), iconColor())
    }

    @Test
    fun childUsesTheChosenColorAndDisabledWins() {
        render {
            OceanChildTransactionListAction(
                content = Samples.content,
                icon = solidIcon,
                iconColor = TransactionListIconColor.Highlight,
                state = OceanTransactionListState.Disabled,
                onClick = {}
            )
        }
        assertEquals(lightDeep.toArgb(), iconColor())
    }

    @Test
    fun childReadOnlyUsesTheChosenColor() {
        render {
            OceanChildTransactionListReadOnly(
                content = Samples.content,
                icon = solidIcon,
                iconColor = TransactionListIconColor.Highlight
            )
        }
        assertEquals(primaryDown.toArgb(), iconColor())
    }

    @Test
    fun screenBackgroundShowsThroughForOnColorRows() {
        var warningUp = Color.Unspecified
        composeTestRule.setContent {
            warningUp = OceanColors.statusWarningUp
            OceanTransactionListReadOnly(
                modifier = Modifier.background(warningUp).testTag("hero"),
                content = Samples.content,
                icon = solidIcon,
                iconColor = TransactionListIconColor.OnColor,
                showDivider = false
            )
        }
        composeTestRule.waitForIdle()
        val view = composeTestRule.activity.window.decorView.rootView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        view.draw(Canvas(bitmap))
        val b = composeTestRule.onNodeWithTag("hero").fetchSemanticsNode().boundsInWindow
        assertEquals(warningUp.toArgb(), bitmap.getPixel(b.left.toInt() + 4, b.top.toInt() + 4))
    }

    @Test
    fun expandableParentUsesTheChosenColor() {
        render {
            OceanTransactionListExpandable(
                content = ContentListStyle.Inverted(title = "Parent", description = "Parent description"),
                icon = solidIcon,
                iconColor = TransactionListIconColor.OnColor
            )
        }
        assertEquals(darkDown.toArgb(), iconColor())
    }
}
