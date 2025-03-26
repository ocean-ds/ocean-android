package br.com.useblu.oceands.client.ui.radio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityRadiosBinding

class RadioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRadiosBinding
    private lateinit var viewModel: RadiosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_radios)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[RadiosViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
