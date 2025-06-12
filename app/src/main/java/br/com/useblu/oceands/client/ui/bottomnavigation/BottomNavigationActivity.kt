package br.com.useblu.oceands.client.ui.bottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.databinding.ActivityBottomNavigationBinding
import br.com.useblu.oceands.components.compose.OceanBottomNavigation

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding
    private lateinit var viewModel: BottomNavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[BottomNavigationViewModel::class.java]
        binding.viewmodel = viewModel

        setContentView(binding.root)

        binding.navBottom.setContent {
            val models = viewModel.menuItems.observeAsState(initial = emptyList())
            OceanBottomNavigation(
                selectedIndex = viewModel.selectedIndex,
                models = models.value,
                colorStyle = viewModel.colorStyle
            )
        }

        binding.addItems.setOnClickListener {
            viewModel.addItem()
        }
    }
}
