package br.com.useblu.oceands.bindingadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.ItemParentTextListBinding
import br.com.useblu.oceands.extensions.addSwipeLeft
import br.com.useblu.oceands.model.OceanChildTextItem

@BindingAdapter(
    "setChildren",
    "clickItem",
    "clickEdit",
    "clickDelete",
    "longClickItem"
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

        holder.bind(item, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ChildrenViewHolder(
        val binding: ItemParentTextListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            oceanChildTextItem: OceanChildTextItem,
            position: Int
        ) {
            binding.item = oceanChildTextItem

            binding.expansionLayoutChildContainer.setOnClickListener {
                onClicked?.invoke(position)
            }

            binding.expansionLayoutChildContainer.setOnLongClickListener {
                onLongClickPressed?.invoke(position)
                false
            }
        }
    }
}
