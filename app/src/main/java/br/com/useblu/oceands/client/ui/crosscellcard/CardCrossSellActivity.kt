package br.com.useblu.oceands.client.ui.crosscellcard

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCardCrossSellBinding

class CardCrossSellActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardCrossSellBinding
    private lateinit var viewModel: CardCrossSellViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_cross_sell)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[CardCrossSellViewModel::class.java]
        binding.viewmodel = viewModel
    }

}