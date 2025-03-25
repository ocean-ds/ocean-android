package br.com.useblu.oceands.client.ui.balance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityBalanceBinding

class BalanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBalanceBinding
    private lateinit var viewModel: BalanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_balance)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[BalanceViewModel::class.java]
        binding.viewmodel = viewModel

        initObservers()

        viewModel.loadData()
    }

    private fun initObservers() {
    }
}
