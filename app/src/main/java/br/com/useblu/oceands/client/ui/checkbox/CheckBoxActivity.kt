package br.com.useblu.oceands.client.ui.checkbox

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCheckboxBinding
import br.com.useblu.oceands.components.compose.OceanCheckboxPreview

class CheckBoxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckboxBinding
    private lateinit var viewModel: CheckBoxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkbox)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[CheckBoxViewModel::class.java]
        binding.viewmodel = viewModel
        setContent {
            OceanCheckboxPreview()
        }
    }
}
