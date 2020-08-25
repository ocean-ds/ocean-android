package br.com.useblu.oceands.client

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.databinding.ActivityMainBinding
import br.com.useblu.oceands.client.viewmodel.HomeViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.viewmodel = viewModel

        initObservers()
    }

    private fun initObservers() {
        viewModel.showToast.observe(this, Observer {
            Toast.makeText(this, "Sample Click", Toast.LENGTH_LONG).show()
        })
    }
}