package br.com.useblu.oceands.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This test class generates a basic startup baseline profile for the target package.
 *
 * We recommend you start with this but add important user flows to the profile to improve their performance.
 * Refer to the [baseline profile documentation](https://d.android.com/topic/performance/baselineprofiles)
 * for more information.
 *
 * You can run the generator with the "Generate Baseline Profile" run configuration in Android Studio or
 * the equivalent `generateBaselineProfile` gradle task:
 * ```
 * ./gradlew :app:generateReleaseBaselineProfile
 * ```
 * The run configuration runs the Gradle task and applies filtering to run only the generators.
 *
 * Check [documentation](https://d.android.com/topic/performance/benchmarking/macrobenchmark-instrumentation-args)
 * for more information about available instrumentation arguments.
 *
 * After you run the generator, you can verify the improvements running the [StartupBenchmarks] benchmark.
 *
 * When using this class to generate a baseline profile, only API 33+ or rooted API 28+ are supported.
 *
 * The minimum required version of androidx.benchmark to generate a baseline profile is 1.2.0.
 **/
@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() {
        // This example works only with the variant with application id `br.com.useblu.oceands.client`."
        rule.collect(
            packageName = "br.com.useblu.oceands.client",
            includeInStartupProfile = true
        ) {
            pressHome()
            startActivityAndWait()

            device.findObject(By.text("Buttons")).click()

            device.waitForIdle()

            device.pressBack()

            device.waitForIdle()

            device.findObject(By.text("Bottom Navigation")).click()

            device.waitForIdle()

            device.pressBack()

            device.waitForIdle()

            val scrollable = device.findObject(By.scrollable(true))
            val headerApp = scrollable.scrollUntil(Direction.DOWN, Until.findObject(By.text("Header App")))

            device.waitForIdle()

            headerApp.click()

            device.waitForIdle()
        }
    }
}