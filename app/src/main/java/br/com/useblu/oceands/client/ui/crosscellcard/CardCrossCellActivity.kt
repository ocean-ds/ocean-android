package br.com.useblu.oceands.client.ui.crosscellcard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCardCrossCellBinding

class CardCrossCellActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardCrossCellBinding
    private lateinit var viewModel: CardCrossCellViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_cross_cell)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[CardCrossCellViewModel::class.java]
        binding.viewmodel = viewModel
    }

}