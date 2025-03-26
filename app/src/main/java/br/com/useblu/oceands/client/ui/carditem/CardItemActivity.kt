package br.com.useblu.oceands.client.ui.carditem

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.cardlistitem.OceanCardListItemPreview

class CardItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanCardListItemPreview()
        }
    }
}
