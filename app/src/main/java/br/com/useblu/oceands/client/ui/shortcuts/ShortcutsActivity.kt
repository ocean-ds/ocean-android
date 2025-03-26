package br.com.useblu.oceands.client.ui.shortcuts

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.OceanShortcutPreview
import br.com.useblu.oceands.components.compose.OceanTheme

class ShortcutsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OceanTheme {
                OceanShortcutPreview()
            }
        }
    }
}
