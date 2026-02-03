package br.com.useblu.oceands.client.ui.buttons

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.PreviewButtonInteractive

class ButtonsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewButtonInteractive()
        }
    }
}
