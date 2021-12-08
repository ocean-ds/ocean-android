package br.com.useblu.oceands.client.ui.chips

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityChipsBinding

class ChipsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChipsBinding
    private lateinit var viewModel: ChipsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chips)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ChipsViewModel::class.java]
        binding.viewmodel = viewModel
        initObservers()
    }

    private fun initObservers() {
        viewModel.selectedItem.observe(this) {
            Toast.makeText(this, "id: ${it?.id} label:${it?.label}", Toast.LENGTH_SHORT).show()
        }
    }
}
