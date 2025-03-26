package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanDetailedCardItemBinding
import br.com.useblu.oceands.model.OceanDetailedItem

class OceanDetailedAdapter(
    private val items: List<OceanDetailedItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = OceanDetailedItemViewHolder(
        OceanDetailedCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) = (holder as OceanDetailedItemViewHolder).bindView(position)

    override fun getItemCount(): Int = items.size

    inner class OceanDetailedItemViewHolder(
        private val itemBinding: OceanDetailedCardItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.item = items[position]
            itemBinding.executePendingBindings()
        }
    }
}
