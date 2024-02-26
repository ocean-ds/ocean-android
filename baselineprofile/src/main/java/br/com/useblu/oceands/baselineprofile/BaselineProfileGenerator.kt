package br.com.useblu.oceands.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() {
        rule.collect(
            packageName = "br.com.useblu.oceands.client",
            includeInStartupProfile = true
        ) {
            pressHome()
            startActivityAndWait()

            navigateScreenAndGoBack("Accordion")
            navigateScreenAndGoBack("Alerts")
            navigateScreenAndGoBack("Badges")
            navigateScreenAndGoBack("Balance")
            navigateScreenAndGoBack("Buttons")

            device.findObject(By.scrollable(true)).scroll(Direction.DOWN, 0.5f)

            navigateScreenAndGoBack("Bottom Navigation")
            navigateScreenAndGoBack("BottomSheet with Image")
        }
    }
}