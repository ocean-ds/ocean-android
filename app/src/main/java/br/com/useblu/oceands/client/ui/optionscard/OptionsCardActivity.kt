package br.com.useblu.oceands.client.ui.optionscard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityOptionsCardBinding

class OptionsCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOptionsCardBinding
    private lateinit var viewModel: OptionsCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_options_card)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[OptionsCardViewModel::class.java]
        binding.viewmodel = viewModel
    }
}