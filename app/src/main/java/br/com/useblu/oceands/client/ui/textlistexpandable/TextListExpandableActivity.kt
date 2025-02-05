package br.com.useblu.oceands.client.ui.textlistexpandable

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.expandabletextlisticonitem.OceanExpandableTextListIconItemPreview

class TextListExpandableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OceanTheme {
                OceanExpandableTextListIconItemPreview()
            }
        }
    }
}