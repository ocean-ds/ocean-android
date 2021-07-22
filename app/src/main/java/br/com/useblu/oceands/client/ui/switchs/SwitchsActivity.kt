package br.com.useblu.oceands.client.ui.switchs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivitySwitchsBinding

class SwitchsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySwitchsBinding
    private lateinit var viewModel: SwitchsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_switchs)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[SwitchsViewModel::class.java]
        binding.viewmodel = viewModel
    }
}