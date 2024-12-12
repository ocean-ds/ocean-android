package br.com.useblu.oceands.client.ui.headerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.databinding.ActivityHeaderAppBinding
import br.com.useblu.oceands.components.compose.OceanBottomNavigation
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.header.OceanHeader
import br.com.useblu.oceands.ui.compose.OceanButtonStyle

class HeaderAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeaderAppBinding
    private lateinit var viewModel: HeaderAppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeaderAppBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[HeaderAppViewModel::class.java]

        binding.composeView.setContent {
            val headerModel = viewModel.headerAppModel.observeAsState()

            Scaffold(
                topBar = {
                    headerModel.value?.let {
                        OceanHeader(headerModel = it)
                    }
                },
                bottomBar = {
                    OceanBottomNavigation(0, emptyList())
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(top = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OceanButton(
                        text = "Toggle scroll",
                        buttonStyle = OceanButtonStyle.SecondaryMedium,
                        onClick = {
                            viewModel.onClickToggleScroll()
                        }
                    )

                    OceanButton(
                        text = "Toggle loading",
                        buttonStyle = OceanButtonStyle.SecondaryMedium,
                        onClick = {
                            viewModel.onClickToggleLoading()
                        }
                    )

                    OceanButton(
                        text = "Toggle esconder saldo",
                        buttonStyle = OceanButtonStyle.SecondaryMedium,
                        onClick = {
                            viewModel.onClickToggleHideBalance()
                        }
                    )
                }
            }
        }
    }
}