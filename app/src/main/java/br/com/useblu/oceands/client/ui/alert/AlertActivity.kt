package br.com.useblu.oceands.client.ui.alert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityAlertBinding
import br.com.useblu.oceands.components.compose.OceanAlertPreview

class AlertActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alert)
        binding.lifecycleOwner = this
        binding.composeView.setContent {
            OceanAlertPreview()
        }
    }
}
