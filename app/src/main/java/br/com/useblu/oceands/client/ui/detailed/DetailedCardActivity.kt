package br.com.useblu.oceands.client.ui.detailed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityDetailedCardBinding

class DetailedCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedCardBinding
    private lateinit var viewModel: DetailedCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailed_card)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[DetailedCardViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
