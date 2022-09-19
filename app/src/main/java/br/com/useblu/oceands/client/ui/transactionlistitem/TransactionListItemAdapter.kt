package br.com.useblu.oceands.client.ui.transactionlistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.model.OceanTransactionListUIModel
import br.com.useblu.oceands.databinding.OceanTransactionListItemBinding

class TransactionListItemAdapter(
    private val selectionMode: MutableLiveData<Boolean>,
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

    inner class TransactionListItemViewHolder(
        private val binding: OceanTransactionListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OceanTransactionListUIModel, position: Int) {
            binding.tagTitle = item.tagTitle
            binding.primaryLabel = item.primaryLabel
            binding.value = item.value
            binding.hasCheckbox = true
            binding.selectionMode = selectionMode
            binding.click = onItemClicked
            binding.index = position
            binding.executePendingBindings()
        }
    }
}
