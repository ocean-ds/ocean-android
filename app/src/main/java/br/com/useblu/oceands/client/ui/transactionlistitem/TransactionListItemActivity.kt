package br.com.useblu.oceands.client.ui.transactionlistitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityShortcutsBinding
import br.com.useblu.oceands.client.databinding.ActivityTransactionListItemBinding
import br.com.useblu.oceands.client.ui.shortcuts.ShortcutsViewModel

class TransactionListItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionListItemBinding
    private lateinit var viewModel: TransactionListItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_list_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TransactionListItemViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.loadData()
    }

}