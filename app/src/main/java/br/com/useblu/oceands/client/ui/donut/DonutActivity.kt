package br.com.useblu.oceands.client.ui.donut

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityDonutBinding

class DonutActivity : AppCompatActivity() {
    private lateinit var viewModel: DonutViewModel
    private lateinit var binding: ActivityDonutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_donut)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[DonutViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.selectedItem.observe(this) {
            Toast.makeText(this, "Item selecionado: ${it?.title} ${it?.value}", Toast.LENGTH_SHORT).show()
        }
    }
}
