package br.com.useblu.oceands.client.ui.headerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.databinding.ActivityHeaderAppBinding
import br.com.useblu.oceands.components.compose.header.OceanHeaderApp

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

        binding.toggleScrollButton.setOnClickListener {
            viewModel.onClickToggleScroll()
        }

        binding.toggleLoadingButton.setOnClickListener {
            viewModel.onClickToggleLoading()
        }

        binding.toggleHideButton.setOnClickListener {
            viewModel.onClickToggleHideBalance()
        }

        binding.composeView.setContent {
            val headerModel = viewModel.headerAppModel.observeAsState()

            headerModel.value?.let {
                OceanHeaderApp(model = it)
            }
        }
    }
}