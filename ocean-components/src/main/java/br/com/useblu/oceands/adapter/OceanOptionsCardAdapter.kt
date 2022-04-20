package br.com.useblu.oceands.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.ItemOptionCardAdapterOceanBinding
import br.com.useblu.oceands.databinding.ItemOptionCardDisabledAdapterOceanBinding

class OceanOptionsCardAdapter(
    private var items: List<OceanOptionCardItem>,
    val selectedItem: (OceanOptionCardItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItemPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        when (viewType) {
            OptionsCardState.DISABLED.ordinal -> {
                val itemBinding = ItemOptionCardDisabledAdapterOceanBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return OceanOptionCardDisabledViewHolder(itemBinding)
            }
            else -> {
                val itemBinding = ItemOptionCardAdapterOceanBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return OceanOptionCardViewHolder(itemBinding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when {
            item.disabled -> OptionsCardState.DISABLED.ordinal
            item.recommend -> OptionsCardState.RECOMMEND.ordinal
            else -> OptionsCardState.ENABLED.ordinal
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val item = items[position]

        when (getItemViewType(position)) {
            OptionsCardState.DISABLED.ordinal -> {
                (holder as OceanOptionCardDisabledViewHolder).bindView(item, position)
            }
            OptionsCardState.RECOMMEND.ordinal -> {
                (holder as OceanOptionCardViewHolder).bindView(item, position)
            }
            else -> {
                (holder as OceanOptionCardViewHolder).bindView(item, position)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class OceanOptionCardViewHolder(
        private val itemBinding: ItemOptionCardAdapterOceanBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(oceanOption: OceanOptionCardItem, position: Int) {
            itemBinding.item = oceanOption
            itemBinding.selected = position == selectedItemPosition
            itemBinding.cardView.setOnClickListener {
                selectedItem.invoke(oceanOption)
                selectedItemPosition = position
            }
            itemBinding.executePendingBindings()
        }
    }

    inner class OceanOptionCardDisabledViewHolder(
        private val itemBinding: ItemOptionCardDisabledAdapterOceanBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(oceanOption: OceanOptionCardItem, position: Int) {
            itemBinding.item = oceanOption
            itemBinding.selected = position == selectedItemPosition
            itemBinding.cardView.setOnClickListener {
                selectedItem.invoke(oceanOption)
                selectedItemPosition = position
            }
            itemBinding.executePendingBindings()
        }
    }
}

enum class OptionsCardState {
    ENABLED, DISABLED, RECOMMEND
}

data class OceanOptionCardItem(
    val icon: Drawable? = null,
    val title: String? = "",
    val subTitle: String? = "",
    val disabled: Boolean = false,
    val recommend: Boolean = false,
    val recommendColor: Int? = null,
    val recommendDescription: String? = "",
)