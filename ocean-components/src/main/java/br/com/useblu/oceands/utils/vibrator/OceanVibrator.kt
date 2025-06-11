package br.com.useblu.oceands.utils.vibrator

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

interface OceanVibrator {
    fun vibrate(milliseconds: Long = 50)
}

@Composable
fun rememberVibrator(): OceanVibrator {
    val context = LocalContext.current

    return remember {
        object : OceanVibrator {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
            } else {
                context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }

            @RequiresPermission(Manifest.permission.VIBRATE)
            override fun vibrate(milliseconds: Long) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        milliseconds,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            }
        }
    }
}
