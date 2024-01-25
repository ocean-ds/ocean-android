package br.com.useblu.oceands.client.ui.transactionlistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanTransactionListItemBinding
import br.com.useblu.oceands.model.OceanTransactionListUIModel

class TransactionListItemAdapter(
    private val onItemClicked: (Int) -> Unit
) : ListAdapter<OceanTransactionListUIModel, TransactionListItemAdapter.TransactionListItemViewHolder>(
    Companion
) {
    companion object : DiffUtil.ItemCallback<OceanTransactionListUIModel>() {
        override fun areItemsTheSame(
            oldItem: OceanTransactionListUIModel,
            newItem: OceanTransactionListUIModel
        ): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: OceanTransactionListUIModel,
            newItem: OceanTransactionListUIModel
        ): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OceanTransactionListItemBinding.inflate(layoutInflater, parent, false)
        return TransactionListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    private var selectionMode = false
    private var selectedIndexes = listOf<Int>()
    fun updateSelectionMode(isSelectionMode: Boolean, selectedIndexes: List<Int>) {
        if (selectionMode != isSelectionMode) {
            selectionMode = isSelectionMode
            this.selectedIndexes = selectedIndexes
            notifyItemRangeChanged(0, itemCount)
        }
    }

    inner class TransactionListItemViewHolder(
        private val binding: OceanTransactionListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OceanTransactionListUIModel, position: Int) {
            binding.tagTitle = item.tagTitle
            binding.primaryLabel = item.primaryLabel
            binding.value = item.value
            binding.secondaryValue = item.secondaryValue
            binding.hasCheckbox = true
            binding.selectionMode = selectionMode
            binding.click = onItemClicked
            binding.index = position
            binding.checkbox.checked = selectedIndexes.contains(position)
        }
    }
}
