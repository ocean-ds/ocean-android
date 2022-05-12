package br.com.useblu.oceands.core

import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.SwipeHelper

fun RecyclerView.addSwipeLeft(
    edit: ((Int) -> Unit)?,
    remove: ((Int) -> Unit)?,
) {
    var swipeHelper: SwipeHelper? = null
    swipeHelper = object : SwipeHelper(
        context, this@addSwipeLeft
    ) {
        override fun instantiateUnderlayButton(
            viewHolder: RecyclerView.ViewHolder?,
            underlayButtons: MutableList<UnderlayButton>?
        ) {
            val position = viewHolder?.bindingAdapterPosition
            position?.let {
                remove?.let {
                    underlayButtons?.add(UnderlayButton(
                        context,
                        R.string.all_text_exclude,
                        R.drawable.ic_trash_outline,
                        R.color.ocean_color_status_negative_pure,
                        R.color.ocean_color_interface_light_pure,
                        object : UnderlayButtonClickListener {
                            override fun onClick(pos: Int) {
                                swipeHelper?.hideOptions(pos)
                                remove.invoke(pos)
                            }
                        }
                    ))
                }
                edit?.let {
                    underlayButtons?.add(UnderlayButton(
                        context,
                        R.string.transfer_button_item_edit,
                        R.drawable.ic_pencil_outline,
                        R.color.ocean_color_interface_dark_up,
                        R.color.ocean_color_interface_light_pure,
                        object : UnderlayButtonClickListener {
                            override fun onClick(pos: Int) {
                                swipeHelper?.hideOptions(pos)
                                edit.invoke(pos)
                            }
                        }
                    ))
                }
            }
        }
    }
}