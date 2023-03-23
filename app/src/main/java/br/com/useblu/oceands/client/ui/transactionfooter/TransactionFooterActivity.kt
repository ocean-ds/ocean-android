package br.com.useblu.oceands.client.ui.transactionfooter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTransactionFooterBinding

class TransactionFooterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionFooterBinding
    private lateinit var viewModel: TransactionFooterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_footer)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TransactionFooterViewModel::class.java]
        binding.viewmodel = viewModel

        initObservables()
    }

    private fun initObservables() {
        viewModel.message.observe(this) { msg ->
            Toast.makeText(
                this, msg, Toast.LENGTH_SHORT
            ).show()
        }
    }
}
