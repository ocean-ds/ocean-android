package br.com.useblu.oceands.client.ui.toobar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTopbarBinding

class TopbarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopbarBinding
    private lateinit var viewModel: TopbarViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_topbar)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TopbarViewModel::class.java]
        binding.viewmodel = viewModel
    }
}