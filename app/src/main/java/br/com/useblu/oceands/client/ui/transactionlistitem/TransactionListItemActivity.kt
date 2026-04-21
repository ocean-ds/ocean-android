package br.com.useblu.oceands.client.ui.transactionlistitem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTransactionListItemBinding
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.TransactionType
import br.com.useblu.oceands.components.compose.list.OceanTransactionListItem
import br.com.useblu.oceands.components.compose.list.TransactionListItemStyle
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.OceanTransactionListUIModel
import br.com.useblu.oceands.utils.OceanIcons

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

        binding.extractPreviewCompose.setContent {
            ExtractScreenPreview()
        }

        viewModel.loadData()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    private fun initObservers() {
        viewModel.selectionMode.observe(this) { isEntering ->
            if (!isEntering) selectedItems.clear()
            recyclerViewAdapter.updateSelectionMode(isEntering, selectedItems)
        }

        viewModel.clickedItem.observe(this) { index ->
            with(selectedItems) {
                if (contains(index)) remove(index) else add(index)
                viewModel.selectionMode.postValue(isNotEmpty())
            }
        }
    }

    private fun initRecyclerView() {
        binding.transactionListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
    }

    private fun getRecyclerViewAdapter(): TransactionListItemAdapter {
        return TransactionListItemAdapter {
            viewModel.click(it)
        }.apply {
            val list = (0..9).map {
                OceanTransactionListUIModel(
                    tagTitle = "Tag $it",
                    value = "-1000000.00",
                    secondaryValue = "123456.00",
                    primaryLabel = "A LABEL WHICH IS VERY LARGE"
                )
            }.toList()
            submitList(list)
        }
    }
}

@Composable
private fun ExtractScreenPreview() {
    Column {
        OceanTransactionListItem(
            icon = OceanIcons.INFLOW_OUTLINE,
            primaryLabel = "Recebimento Pix",
            secondaryLabel = "João da Silva",
            dimmedLabel = "ID E1234567890ABC",
            primaryValue = 1500.00,
            valueWithSignal = true,
            valueWithSignalPositive = false,
            valueIsHighlighted = true,
            trailingIcon = OceanIcons.CHEVRON_RIGHT_OUTLINE,
            onClick = {}
        )
        OceanTransactionListItem(
            icon = OceanIcons.OUTFLOW_OUTLINE,
            primaryLabel = "Pagamento de boleto",
            secondaryLabel = "Energia Elétrica S.A.",
            dimmedLabel = "ID B9876543210XYZ",
            tagTitle = "Agendado",
            tagType = OceanTagType.Warning,
            primaryValue = -250.90,
            valueWithSignal = true,
            valueWithSignalPositive = false,
            valueIsHighlighted = true,
            trailingIcon = OceanIcons.CHEVRON_RIGHT_OUTLINE,
            onClick = {}
        )
        OceanTransactionListItem(
            icon = OceanIcons.OUTFLOW_OUTLINE,
            primaryLabel = "Transferência Pix",
            secondaryLabel = "Maria Souza",
            dimmedLabel = "ID P5551234567CDE",
            tagTitle = "Cancelado",
            tagType = OceanTagType.Negative,
            tagIcon = OceanIcons.EXCLAMATION_CIRCLE_SOLID,
            primaryValue = -75.50,
            valueWithSignal = true,
            valueWithSignalPositive = false,
            valueIsHighlighted = true,
            valueIsCanceled = true,
            trailingIcon = OceanIcons.CHEVRON_RIGHT_OUTLINE,
            onClick = {}
        )
        OceanTransactionListItem(
            icon = OceanIcons.INFLOW_OUTLINE,
            primaryLabel = "Recebimento cartão",
            secondaryLabel = "Venda parcelada 3x",
            dimmedLabel = "ID C7890123456FGH",
            primaryValue = 450.00,
            valueWithSignal = true,
            valueWithSignalPositive = false,
            valueIsHighlighted = true,
            trailingIcon = OceanIcons.CHEVRON_RIGHT_OUTLINE,
            showDivider = false,
            onClick = {}
        )

        OceanTransactionListItem(
            style = TransactionListItemStyle.CommonStyle.Default(
                contentInfo = ContentListStyle.Inverted(
                    title = "Recebimento Pix",
                    description = "João da Silva",
                    caption = "ID E1234567890ABC"
                ),
                contentValues = ContentListStyle.Transaction(
                    value = "R$ 1.500,00",
                    type = TransactionType.INFLOW
                ),
                onClick = {}
            )
        )
        OceanTransactionListItem(
            style = TransactionListItemStyle.CommonStyle.Default(
                contentInfo = ContentListStyle.Inverted(
                    title = "Pagamento de boleto",
                    description = "Energia Elétrica S.A.",
                    caption = "ID B9876543210XYZ"
                ),
                contentValues = ContentListStyle.Transaction(
                    value = "R$ 250,90",
                    type = TransactionType.OUTFLOW
                ),
                onClick = {}
            )
        )
        OceanTransactionListItem(
            style = TransactionListItemStyle.CommonStyle.Default(
                contentInfo = ContentListStyle.Inverted(
                    title = "Recebimento cartão",
                    description = "Venda parcelada 3x",
                    caption = "ID C7890123456FGH"
                ),
                contentValues = ContentListStyle.Transaction(
                    value = "R$ 450,00",
                    type = TransactionType.INFLOW
                ),
                onClick = {}
            )
        )
    }
}
