package br.com.useblu.oceands.client.ui.informativecard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityInformativeCardBinding

class InformativeCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformativeCardBinding
    private lateinit var viewModel: InformativeCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_informative_card)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[InformativeCardViewModel::class.java]
        binding.viewmodel = viewModel
    }
}