package br.com.useblu.oceands.client.ui.optionscard

import android.os.Bundle
import android.widget.Toast
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

        initObservers()
    }

    private fun initObservers() {
        viewModel.itemSelect.observe(this, {
            when (it) {
                R.id.card_options_one -> {
                    Toast.makeText(this, "Item selecionado 1", Toast.LENGTH_SHORT).show()
                }
                R.id.card_options_two -> {
                    Toast.makeText(this, "Item selecionado 2", Toast.LENGTH_SHORT).show()
                }
                R.id.card_options_thee -> {
                    Toast.makeText(this, "Item selecionado 3", Toast.LENGTH_SHORT).show()

                }
                R.id.card_options_for -> {
                    Toast.makeText(this, "Item selecionado 4", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}