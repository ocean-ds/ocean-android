package br.com.useblu.oceands.client.ui.textlistitem

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.OceanTextListItemPreview

class TextListItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanTextListItemPreview()
        }
    }
}
