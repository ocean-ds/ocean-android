package br.com.useblu.oceands.client.ui.buttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityButtonsBinding


class ButtonsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityButtonsBinding
    private lateinit var viewModel: ButtonsViewModel
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_buttons
        )
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ButtonsViewModel::class.java]
        binding.viewmodel = viewModel
        viewModel.setButtonType("Primary.medium.unblocked")
    }

}

