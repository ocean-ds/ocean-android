package br.com.useblu.oceands.client.ui.alert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityAlertBinding

class AlertActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlertBinding
    private lateinit var viewModel: AlertViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alert)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[AlertViewModel::class.java]
        binding.viewmodel = viewModel
    }
}