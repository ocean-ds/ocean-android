package br.com.useblu.oceands.client.ui.carousel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCarouselBinding

class CarouselActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarouselBinding
    private lateinit var viewModel: CarouselViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_carousel)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[CarouselViewModel::class.java]
        binding.viewmodel = viewModel

        initObservers()

        viewModel.loadData()
    }

    private fun initObservers() {}

}