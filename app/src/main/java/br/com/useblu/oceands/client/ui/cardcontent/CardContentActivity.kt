package br.com.useblu.oceands.client.ui.cardcontent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCardContentBinding
import br.com.useblu.oceands.client.databinding.ActivityTransactionListItemBinding
import br.com.useblu.oceands.client.ui.transactionlistitem.TransactionListItemViewModel

class CardContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardContentBinding
    private lateinit var viewModel: CardContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_content)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[CardContentViewModel::class.java]
        binding.viewmodel = viewModel

        initObservables()

        viewModel.loadData()
    }

    private fun initObservables() {
        viewModel.shouldShowMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}