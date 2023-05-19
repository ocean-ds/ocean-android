package br.com.useblu.oceands.client.ui.headerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.databinding.ActivityHeaderAppBinding

class HeaderAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeaderAppBinding
    private lateinit var viewModel: HeaderAppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeaderAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[HeaderAppViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.reloadData()

        binding.toggleScrollButton.setOnClickListener {
            viewModel.onClickToggleScroll()
        }

        binding.togglePortabilidadeButton.setOnClickListener {
            viewModel.onClickPortabilidade()
        }
    }
}