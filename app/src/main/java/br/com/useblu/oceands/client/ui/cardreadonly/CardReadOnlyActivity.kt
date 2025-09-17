package br.com.useblu.oceands.client.ui.cardreadonly

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.OceanCardReadOnlyPreview

class CardReadOnlyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanCardReadOnlyPreview()
        }
    }
}
