package br.com.useblu.oceands.client.ui.transactionlistitem

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTransactionListItemBinding
import br.com.useblu.oceands.core.OceanTransactionListUIModel

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

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    private fun initObservers() {
        viewModel.clickedItem.observe(this) { index ->
            with(selectedItems) {
                if (contains(index)) remove(index) else add(index)

                if (isEmpty()) viewModel.selectionMode.postValue(false)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        binding.transactionListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TransactionListItemAdapter(selectionMode = viewModel.selectionMode) {
                viewModel.click(it)
            }.apply {
                val list = listOf(
                    OceanTransactionListUIModel(
                        tagTitle = "Tag 1",
                        value = "-1000000.00",
                        primaryLabel = "A LABEL WHICH IS VERY LARGE"
                    ),
                    OceanTransactionListUIModel(
                        tagTitle = "Tag 2",
                        value = "-1000000.00",
                        primaryLabel = "A LABEL WHICH IS VERY LARGE"
                    ),
                    OceanTransactionListUIModel(
                        tagTitle = "Tag 3",
                        value = "-1000000.00",
                        primaryLabel = "A LABEL WHICH IS VERY LARGE"
                    ),
                    OceanTransactionListUIModel(
                        tagTitle = "Tag 4",
                        value = "-1000000.00",
                        primaryLabel = "A LABEL WHICH IS VERY LARGE"
                    ),
                    OceanTransactionListUIModel(
                        tagTitle = "Tag 5",
                        value = "-1000000.00",
                        primaryLabel = "A LABEL WHICH IS VERY LARGE"
                    ),
                    OceanTransactionListUIModel(
                        tagTitle = "Tag 6",
                        value = "-1000000.00",
                        primaryLabel = "A LABEL WHICH IS VERY LARGE"
                    ),
                    OceanTransactionListUIModel(
                        tagTitle = "Tag 7",
                        value = "-1000000.00",
                        primaryLabel = "A LABEL WHICH IS VERY LARGE"
                    ),
                    OceanTransactionListUIModel(
                        tagTitle = "Tag 8",
                        value = "-1000000.00",
                        primaryLabel = "A LABEL WHICH IS VERY LARGE"
                    )
                )
                submitList(list)
            }
        }
    }
}
