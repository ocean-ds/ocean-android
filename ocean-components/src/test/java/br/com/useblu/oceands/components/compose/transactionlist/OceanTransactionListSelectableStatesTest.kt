package br.com.useblu.oceands.components.compose.transactionlist

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/**
 * Every Figma `State` of the Selectable (24320-5708, Platform App) × `Controller type` (CT-3 / CA-3):
 * Default, Hover, Indeterminate, Selected, Disabled, Disabled Selected, Error and Loading.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class OceanTransactionListSelectableStatesTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val item get() = composeTestRule.onNodeWithText("Description")
    private val control get() = composeTestRule.onNodeWithTag(TransactionListTestTags.CONTROL, useUnmergedTree = true)

    private fun render(
        controller: OceanTransactionListController,
        selected: Boolean = false,
        state: OceanTransactionListState = OceanTransactionListState.Default,
        indeterminate: Boolean = false,
        showError: Boolean = false,
        values: MutableList<Boolean> = mutableListOf()
    ): MutableList<Boolean> {
        composeTestRule.setContent {
            Column(Modifier.width(360.dp).testTag("root")) {
                OceanTransactionListSelectable(
                    content = Samples.content,
                    amount = Samples.amount,
                    controller = controller,
                    selected = selected,
                    state = state,
                    indeterminate = indeterminate,
                    showError = showError,
                    onSelectedChange = { values += it }
                )
            }
        }
        return values
    }

    /** Draws the window and returns the pixel 2dp inside the item's top-left corner. */
    private fun itemCornerPixel(): Int {
        composeTestRule.waitForIdle()
        val view = composeTestRule.activity.window.decorView.rootView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        view.draw(Canvas(bitmap))
        val bounds = composeTestRule.onNodeWithTag("root").fetchSemanticsNode().boundsInWindow
        val inset = (2 * view.resources.displayMetrics.density).toInt()
        return bitmap.getPixel(bounds.left.toInt() + inset, bounds.top.toInt() + inset)
    }

    private fun tokens(): Pair<Color, Color> {
        var up = Color.Unspecified
        var pure = Color.Unspecified
        composeTestRule.setContent {
            up = OceanColors.interfaceLightUp
            pure = OceanColors.interfaceLightPure
        }
        return up to pure
    }

    @Test
    fun defaultShowsUnselectedControl() = OceanTransactionListController.entries.forEach { controller ->
        composeTestRule.activityRule.scenario.recreate()
        render(controller)
        control.assertExists()
        item.assertIsNotSelected()
    }

    @Test
    fun selectedShowsSelectedControl() = OceanTransactionListController.entries.forEach { controller ->
        composeTestRule.activityRule.scenario.recreate()
        render(controller, selected = true)
        control.assertExists()
        item.assertIsSelected()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun hoverHighlightsTheItemWithInterfaceLightUp() {
        val (lightUp, lightPure) = tokens()
        OceanTransactionListController.entries.forEach { controller ->
            composeTestRule.activityRule.scenario.recreate()
            render(controller)
            assertEquals("$controller rest", lightPure.toArgb(), itemCornerPixel())

            item.performMouseInput { enter(center) }
            assertEquals("$controller hover", lightUp.toArgb(), itemCornerPixel())

            item.performMouseInput { exit() }
            assertEquals("$controller after hover", lightPure.toArgb(), itemCornerPixel())
        }
    }

    @Test
    fun indeterminateCheckboxSelectsOnTap() {
        val values = render(OceanTransactionListController.Checkbox, indeterminate = true)
        control.assertExists()
        item.performClick()
        assertEquals(listOf(true), values)
    }

    @Test
    fun disabledDoesNotChange() = OceanTransactionListController.entries.forEach { controller ->
        composeTestRule.activityRule.scenario.recreate()
        val values = render(controller, state = OceanTransactionListState.Disabled)
        control.assertExists()
        item.assertIsNotSelected()
        item.performClick()
        assertTrue("$controller disabled", values.isEmpty())
    }

    @Test
    fun disabledSelectedKeepsSelectionAndDoesNotChange() = OceanTransactionListController.entries.forEach { controller ->
        composeTestRule.activityRule.scenario.recreate()
        val values = render(controller, selected = true, state = OceanTransactionListState.Disabled)
        control.assertExists()
        item.assertIsSelected()
        item.performClick()
        assertTrue("$controller disabled selected", values.isEmpty())
    }

    @Test
    fun errorKeepsTheItemInteractive() = OceanTransactionListController.entries.forEach { controller ->
        composeTestRule.activityRule.scenario.recreate()
        val values = render(controller, showError = true)
        control.assertExists()
        item.performClick()
        assertEquals("$controller error", listOf(true), values)
    }

    @Test
    fun loadingShowsSkeletonWithoutControl() = OceanTransactionListController.entries.forEach { controller ->
        composeTestRule.activityRule.scenario.recreate()
        val values = render(controller, state = OceanTransactionListState.Loading)
        composeTestRule.onNodeWithTag(TransactionListTestTags.LOADING, useUnmergedTree = true).assertExists()
        control.assertDoesNotExist()
        composeTestRule.onNodeWithText("Description").assertDoesNotExist()
        composeTestRule.onNodeWithTag(TransactionListTestTags.LOADING, useUnmergedTree = true).performClick()
        assertTrue("$controller loading", values.isEmpty())
    }
}
