package br.com.useblu.oceands.client.ui.cardlistexpandable

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.cardlistexpandable.OceanCardListExpandablePreview

class OceanCardListExpandableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OceanCardListExpandablePreview()
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OceanCardListExpandablePreview()
}
