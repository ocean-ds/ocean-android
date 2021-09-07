package br.com.useblu.oceands.client.ui.tag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityAlertBinding
import br.com.useblu.oceands.client.databinding.ActivityTagBinding
import br.com.useblu.oceands.client.ui.alert.AlertViewModel

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