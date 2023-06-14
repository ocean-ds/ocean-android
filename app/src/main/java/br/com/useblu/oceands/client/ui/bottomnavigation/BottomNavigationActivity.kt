package br.com.useblu.oceands.client.ui.bottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding
    private lateinit var viewModel: BottomNavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[BottomNavigationViewModel::class.java]
        binding.viewmodel = viewModel

        setContentView(binding.root)

        viewModel.menuItems.observe(this) {
            binding.navBottom.apply {
                clearMenuItems()

                if (it.isNotEmpty()) {
                    for (item in it) {
                        addMenuItem(item)
                    }
                }
            }
        }

        viewModel.addItem.observe(this) {
            binding.navBottom.addMenuItem(it)
        }

        binding.addItems.setOnClickListener {
            viewModel.addItem()
        }
    }
}