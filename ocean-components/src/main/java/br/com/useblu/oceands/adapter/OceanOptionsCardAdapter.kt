package br.com.useblu.oceands.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.core.dp
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

    @SuppressLint("NotifyDataSetChanged")
    private fun onItemSelected(oceanOption: OceanOptionCardItem, position: Int) {
        selectedItem.invoke(oceanOption)
        if(oceanOption.disabled.not()) {
            selectedItemPosition = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = items.size

    inner class OceanOptionCardViewHolder(
        private val itemBinding: ItemOptionCardAdapterOceanBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(oceanOption: OceanOptionCardItem, position: Int) {
            itemBinding.run {
                item = oceanOption
                selected = position == selectedItemPosition
                cardView.setOnClickListener {
                    onItemSelected(oceanOption, position)
                }
            }
        }
    }

    inner class OceanOptionCardDisabledViewHolder(
        private val itemBinding: ItemOptionCardDisabledAdapterOceanBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bindView(oceanOption: OceanOptionCardItem, position: Int) {
            itemBinding.run {
                item = oceanOption
                selected = position == selectedItemPosition
                cardView.setOnClickListener {
                    onItemSelected(oceanOption, position)
                }
            }
        }
    }
}

enum class OptionsCardState {
    ENABLED, DISABLED, RECOMMEND
}

data class OceanOptionCardItem(
    val data: Any,
    val icon: String? = null,
    val heightSize: OceanOptionCardSize = OceanOptionCardSize.MEDIUM,
    val title: String? = "",
    val subTitle: String? = "",
    val disabled: Boolean = false,
    val recommend: Boolean = false,
    val recommendColor: String? = null,
    val recommendDescription: String? = "",
)

enum class OceanOptionCardSize(val size: Int = 0) {
    SMALL(64), MEDIUM(96), LARGE(180)
}
