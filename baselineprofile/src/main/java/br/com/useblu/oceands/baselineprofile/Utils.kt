package br.com.useblu.oceands.baselineprofile

import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice

fun UiDevice.navigateScreenAndGoBack(
    screenTitle: String,
    screenAction: () -> Unit = {}
) {
    findObject(By.text(screenTitle)).click()
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

    findObject(By.scrollable(true)).scroll(Direction.DOWN, 1f)
    findObject(By.scrollable(true)).scroll(Direction.DOWN, 1f)

    navigateScreenAndGoBack("Card Cross Sell")
    navigateScreenAndGoBack("Card Group")
    navigateScreenAndGoBack("Card Item")
    navigateScreenAndGoBack("Carousel")
    navigateScreenAndGoBack("Chart Bar")
    navigateScreenAndGoBack("Chart Card")
    navigateScreenAndGoBack("Checkbox")
    navigateScreenAndGoBack("Chips")
    navigateScreenAndGoBack("Descriptor List Item")
    navigateScreenAndGoBack("Detailed Card")
    navigateScreenAndGoBack("Donut")

    findObject(By.scrollable(true)).scroll(Direction.DOWN, 1f)

    navigateScreenAndGoBack("File Uploader")
    navigateScreenAndGoBack("Footer Blu")
    navigateScreenAndGoBack("Group CTA")
    navigateScreenAndGoBack("Header App")
    navigateScreenAndGoBack("Informative Card")
    navigateScreenAndGoBack("Input")
}
