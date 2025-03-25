package br.com.useblu.oceands.client.ui.tag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTagBinding

class TagActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTagBinding
    private lateinit var viewModel: TagViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tag)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TagViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
