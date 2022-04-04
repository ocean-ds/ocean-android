package br.com.useblu.oceands.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.ItemParentTextListBinding

@BindingAdapter("app:setChildren")
fun setInflateChildren(recyclerView: RecyclerView, children: List<OceanChildTextItem>?) {

    children?.let {
        recyclerView.adapter=ChildrenAdapter(it)
        recyclerView.layoutManager=LinearLayoutManager(recyclerView.context)
    }
}

class ChildrenAdapter(val list: List<OceanChildTextItem>): RecyclerView.Adapter<ChildrenAdapter.ChildrenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ItemParentTextListBinding.inflate(layoutInflater,parent, false)
        return ChildrenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ChildrenViewHolder(val binding: ItemParentTextListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OceanChildTextItem) {
            binding.item=item
            binding.executePendingBindings()
        }
    }

}