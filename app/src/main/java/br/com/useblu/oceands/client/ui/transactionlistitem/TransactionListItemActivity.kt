package br.com.useblu.oceands.client.ui.transactionlistitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTransactionListItemBinding

class TransactionListItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionListItemBinding
    private lateinit var viewModel: TransactionListItemViewModel
    private val selectedItems = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_list_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TransactionListItemViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.loadData()
        initObservers()
    }

    private fun initObservers() {
        viewModel.clickedItem.observe(this) { index ->
            with(selectedItems) {
                if (contains(index)) remove(index) else add(index)

                if (isEmpty()) viewModel.selectionMode.postValue(false)
            }
        }
    }

}
