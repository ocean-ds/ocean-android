package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanChartLegendBinding
import br.com.useblu.oceands.model.chart.OceanChartModel

class OceanChartLegendAdapter(
    private val data: OceanChartModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val itemBinding = OceanChartLegendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as ViewHolder).bindView(position)
    }

    override fun getItemCount(): Int = data.items.size

    inner class ViewHolder(
        private val itemBinding: OceanChartLegendBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.item = data.items[position]
            itemBinding.root.setOnClickListener {
                if (data.items.all { it.selected }) {
                    data.onItemSelected.invoke(position)
                } else {
                    if (data.items[position].selected) {
                        data.onNothingSelected.invoke()
                    } else {
                        data.onItemSelected.invoke(position)
                    }
                }
            }
        }
    }
}
