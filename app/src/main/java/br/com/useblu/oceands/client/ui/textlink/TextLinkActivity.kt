package br.com.useblu.oceands.client.ui.textlink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTextLinkBinding

class TextLinkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextLinkBinding
    private lateinit var viewModel: TextLinkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_link)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[TextLinkViewModel::class.java]
        binding.viewmodel = viewModel
    }
}