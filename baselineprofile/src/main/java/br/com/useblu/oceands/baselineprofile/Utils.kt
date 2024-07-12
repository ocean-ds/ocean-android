package br.com.useblu.oceands.baselineprofile

import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

fun UiDevice.navigateScreenAndGoBack(
    screenTitle: String,
    screenAction: () -> Unit = {}
) {
    val condition = Until.findObject(By.text(screenTitle))
    findObject(By.scrollable(true)).scrollUntil(Direction.DOWN, condition)

    val foundItem = findObject(By.text(screenTitle)) ?: return

    foundItem.click()
    waitForIdle()
    screenAction()
    pressBack()
    waitForIdle()
}

fun UiDevice.runBaselineSteps() {
    navigateScreenAndGoBack("Accordion")
    navigateScreenAndGoBack("Alerts")
    navigateScreenAndGoBack("Badges")
    navigateScreenAndGoBack("Balance")
    navigateScreenAndGoBack("Buttons")
    navigateScreenAndGoBack("Bottom Navigation")
    navigateScreenAndGoBack("Card Cross Sell")
    navigateScreenAndGoBack("Card Group")
    navigateScreenAndGoBack("Card Item")
    //navigateScreenAndGoBack("Carousel")
    navigateScreenAndGoBack("Chart Bar")
    navigateScreenAndGoBack("Chart Card")
    navigateScreenAndGoBack("Checkbox")
    navigateScreenAndGoBack("Chips")
    navigateScreenAndGoBack("Descriptor List Item")
    navigateScreenAndGoBack("Detailed Card")
    navigateScreenAndGoBack("Donut")
    navigateScreenAndGoBack("File Uploader")
    navigateScreenAndGoBack("Footer Blu")
    navigateScreenAndGoBack("Group CTA")
    navigateScreenAndGoBack("Header App")
    navigateScreenAndGoBack("Informative Card")
    navigateScreenAndGoBack("Input")
    navigateScreenAndGoBack("Options Card")
    navigateScreenAndGoBack("Progress Bar")
    navigateScreenAndGoBack("Radio")
    navigateScreenAndGoBack("Shortcuts")
    navigateScreenAndGoBack("Status List Item")
    navigateScreenAndGoBack("Step")
    navigateScreenAndGoBack("Switch")
    navigateScreenAndGoBack("Tab")
    navigateScreenAndGoBack("Tag")
    navigateScreenAndGoBack("Text Link")
    navigateScreenAndGoBack("Text List Expandable")
    navigateScreenAndGoBack("Text List Icon Item")
}
