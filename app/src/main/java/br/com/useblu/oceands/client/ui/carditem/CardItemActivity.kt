package br.com.useblu.oceands.client.ui.carditem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCardItemBinding

class CardItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardItemBinding
    private lateinit var viewModel: CardItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[CardItemViewModel::class.java]
        binding.viewmodel = viewModel

        initObservables()
    }

    private fun initObservables() {
        viewModel.shouldShowMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}