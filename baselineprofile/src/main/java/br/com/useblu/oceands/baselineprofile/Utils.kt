package br.com.useblu.oceands.baselineprofile

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By

fun MacrobenchmarkScope.navigateScreenAndGoBack(
    screenTitle: String,
    screenAction: () -> Unit = {}
) {
    device.findObject(By.text(screenTitle)).click()
    device.waitForIdle()
    screenAction()
    device.pressBack()
    device.waitForIdle()
}