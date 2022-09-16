package br.com.useblu.oceands.client.ui.transactionlistitem

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTransactionListItemBinding
import br.com.useblu.oceands.model.OceanTransactionListUIModel

class TransactionListItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionListItemBinding
    private lateinit var viewModel: TransactionListItemViewModel
    private val selectedItems = arrayListOf<Int>()
    private lateinit var recyclerViewAdapter: TransactionListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_list_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TransactionListItemViewModel::class.java]
        binding.viewmodel = viewModel
        recyclerViewAdapter = getRecyclerViewAdapter()

        viewModel.loadData()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    private fun initObservers() {
        viewModel.selectionMode.observe(this) { isEntering ->
            if(!isEntering) selectedItems.clear()
        }
        viewModel.clickedItem.observe(this) { index ->
            with(selectedItems) {
                if (isEmpty()) {
                    viewModel.selectionMode.postValue(true)
                } else {
                }
                if (contains(index)) remove(index) else add(index)

                if (isEmpty()) viewModel.selectionMode.postValue(false)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        binding.transactionListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
    }

    private fun getRecyclerViewAdapter(): TransactionListItemAdapter {
        return TransactionListItemAdapter(selectionMode = viewModel.selectionMode) {
            viewModel.click(it)
        }.apply {
            val list = (0..9).map {
                OceanTransactionListUIModel(
                    tagTitle = "Tag $it",
                    value = "-1000000.00",
                    primaryLabel = "A LABEL WHICH IS VERY LARGE"
                )
            }.toList()
            submitList(list)
        }
    }
}
