package br.com.useblu.oceands.client.ui.statuslistitem

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.OceanStatusListItemPreview

class StatusListItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanStatusListItemPreview()
        }
    }
}
