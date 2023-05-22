package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanBalanceBluCardBinding
import br.com.useblu.oceands.databinding.OceanBalanceOthersCardBinding
import br.com.useblu.oceands.model.OceanHeaderAppModel

class OceanBalanceAdapter(
    private var headerAppModel: OceanHeaderAppModel
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = 2

    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return if (viewType == 0) {
            val view = OceanBalanceBluCardBinding.inflate(layoutInflater, parent, false)
            BalanceBluViewHolder(view)
        } else {
            val view = OceanBalanceOthersCardBinding.inflate(layoutInflater, parent, false)
            BalanceOthersViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BalanceBluViewHolder -> {
                holder.bind()
            }
            is BalanceOthersViewHolder -> {
                holder.bind()
            }
        }
    }

    fun updateModel(model: OceanHeaderAppModel) {
        this.headerAppModel = model
        notifyItemRangeChanged(0, itemCount)
    }

    inner class BalanceBluViewHolder(private val binding: OceanBalanceBluCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.model = headerAppModel.balanceBluModel
            binding.isContentHidden = headerAppModel.isContentHidden
            binding.isLoading = headerAppModel.isLoading
        }
    }

    inner class BalanceOthersViewHolder(private val binding: OceanBalanceOthersCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.model = headerAppModel.balanceOthersModel
            binding.isContentHidden = headerAppModel.isContentHidden
            binding.isLoading = headerAppModel.isLoading
        }
    }
}