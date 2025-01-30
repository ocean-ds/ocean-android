package br.com.useblu.oceands.client.ui.onboarding

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.OceanOnboardingPreview
import br.com.useblu.oceands.components.compose.OceanTheme

class OceanOnboardingPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OceanTheme {
                OceanOnboardingPreview()
            }
        }
    }
}