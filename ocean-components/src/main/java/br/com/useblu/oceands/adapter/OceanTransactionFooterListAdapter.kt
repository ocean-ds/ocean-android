package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanTransactionFooterItemBinding

class OceanTransactionFooterListAdapter(
    private val items: List<OceanTransactionFooterItem>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val itemBinding = OceanTransactionFooterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OceanTransactionFooterItemListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as OceanTransactionFooterItemListViewHolder).bindView(position)
    }

    override fun getItemCount(): Int = items.size

    inner class OceanTransactionFooterItemListViewHolder(
        private val itemBinding: OceanTransactionFooterItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.item = items[position]
            itemBinding.executePendingBindings()
        }
    }

}

data class OceanTransactionFooterItem(
    val label: String? = null,
    val tooltip: String? = null,
    val value: String? = null,
    val newValue: String? = null,
    val color: String? = null,
    val icon: String? = null,
    val isBold: Boolean? = false,
    val isStrike: Boolean? = !newValue.isNullOrBlank()
)
