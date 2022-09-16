package br.com.useblu.oceands.bindingadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.ItemParentTextListBinding
import br.com.useblu.oceands.extensions.addSwipeLeft
import br.com.useblu.oceands.model.OceanChildTextItem

@BindingAdapter(
    "app:setChildren",
    "app:clickItem",
    "app:clickEdit",
    "app:clickDelete",
    "app:longClickItem"
)
fun setInflateChildren(
    recyclerView: RecyclerView,
    children: List<OceanChildTextItem>?,
    clickItem: ((Int) -> Unit)?,
    clickEdit: ((Int) -> Unit)?,
    clickDelete: ((Int) -> Unit)?,
    longClickItem: ((Int) -> Unit)?
) {

    children?.let { items ->
        recyclerView.run {
            adapter = ChildrenAdapter(
                list = items,
                onClicked = clickItem,
                onLongClickPressed = longClickItem
            )
            layoutManager = LinearLayoutManager(recyclerView.context)
            addSwipeLeft(
                items = items,
                edit = clickEdit,
                remove = clickDelete
            )
        }
    }
}

class ChildrenAdapter(
    val list: List<OceanChildTextItem>,
    val onClicked: ((Int) -> Unit)?,
    val onLongClickPressed: ((Int) -> Unit)?
) : RecyclerView.Adapter<ChildrenAdapter.ChildrenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemParentTextListBinding.inflate(layoutInflater, parent, false)
        return ChildrenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) {
        val item = list[position]

        holder.bind(
            item,
            position,
            onItemClicked = {
                onClicked?.invoke(it)
            },
            onItemLongClicked = {
                onLongClickPressed?.invoke(it)
            }
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ChildrenViewHolder(
        val binding: ItemParentTextListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            oceanChildTextItem: OceanChildTextItem,
            position: Int,
            onItemClicked: (Int) -> Unit,
            onItemLongClicked: (Int) -> Unit
        ) {

            binding.item = oceanChildTextItem

            binding.expansionLayoutChildContainer.setOnClickListener {
                onItemClicked.invoke(position)
            }

            binding.titleItemChild.setOnLongClickListener {
                onItemLongClicked.invoke(position)
                false
            }
            binding.titleItemChild.setOnClickListener {
                onItemClicked.invoke(position)
            }
            binding.subtitleItemChild.setOnLongClickListener {
                onItemLongClicked.invoke(position)
                false
            }
            binding.subtitleItemChild.setOnClickListener {
                onItemClicked.invoke(position)
            }
            binding.imageChild.setOnLongClickListener {
                onItemLongClicked.invoke(position)
                false
            }
            binding.imageChild.setOnClickListener {
                onItemClicked.invoke(position)
            }
            binding.executePendingBindings()
        }
    }
}