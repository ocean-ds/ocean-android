package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanBalanceCardBinding
import br.com.useblu.oceands.model.OceanBalanceModel

class OceanBalanceAdapter(
    private val models: List<OceanBalanceModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = OceanBalanceCardBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(view)
    }

    inner class ViewHolder(private val binding: OceanBalanceCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: OceanBalanceModel) {
            binding.model = model
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(models[0])
    }
}