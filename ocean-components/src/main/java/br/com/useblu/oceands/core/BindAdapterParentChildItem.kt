package br.com.useblu.oceands.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.ItemParentTextListBinding
import io.sulek.ssml.OnSwipeListener
import io.sulek.ssml.SSMLLinearLayoutManager
import kotlinx.android.synthetic.main.item_parent_text_list.view.*

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

    children?.let {
        recyclerView.adapter = ChildrenAdapter(
            list = it,
            onClicked = clickItem,
            onClickedButtonEdit = clickEdit,
            onClickedButtonDelete = clickDelete,
            onLongClickPressed = longClickItem
        )
        recyclerView.layoutManager = SSMLLinearLayoutManager(recyclerView.context)
    }
}

class ChildrenAdapter(
    val list: List<OceanChildTextItem>,
    val onClicked: ((Int) -> Unit)?,
    val onClickedButtonEdit: ((Int) -> Unit)?,
    val onClickedButtonDelete: ((Int) -> Unit)?,
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
            onItemButtonEditClicked = {
                onClickedButtonEdit?.invoke(it)
            },
            onItemButtonDeleteClicked = {
                onClickedButtonDelete?.invoke(it)
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

        var stateExpanded = false

        fun bind(
            oceanChildTextItem: OceanChildTextItem, position: Int,
            onItemClicked: (Int) -> Unit,
            onItemButtonEditClicked: (Int) -> Unit,
            onItemButtonDeleteClicked: (Int) -> Unit,
            onItemLongClicked: (Int) -> Unit
        ) {

            binding.item = oceanChildTextItem
            binding.click = {
                onItemClicked.invoke(position)
            }
            binding.clickEditButton = {
                onItemButtonEditClicked.invoke(position)
            }
            binding.clickDeleteButton = {
                onItemButtonDeleteClicked.invoke(position)
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

            binding.swipeContainer.setOnSwipeListener(object : OnSwipeListener {
                override fun onSwipe(isExpanded: Boolean) {
                    stateExpanded = isExpanded
                }
            })
            itemView.swipeContainer.apply(stateExpanded)
            binding.executePendingBindings()
        }
    }
}