package br.com.useblu.oceands.client.ui.input

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityInputBinding
import br.com.useblu.oceands.client.databinding.ActivityParagraphBinding
import br.com.useblu.oceands.client.ui.buttons.ButtonsViewModel

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private lateinit var viewModel: InputViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[InputViewModel::class.java]
        binding.viewmodel = viewModel
    }
}