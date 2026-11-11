package br.com.useblu.oceands.client.ui.dropdownmenu

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.OceanDropDownMenuPreview

class DropdownMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanDropDownMenuPreview()
        }
    }
}
