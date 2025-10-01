package br.com.useblu.oceands.client.ui.textlistaction

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.textlistaction.OceanTextListActionPreview

class OceanTextListActionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OceanTextListActionPreview()
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OceanTextListActionPreview()
}
