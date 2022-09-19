package br.com.useblu.oceands.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.components.OceanBottomListSheet
import br.com.useblu.oceands.components.OceanSearchDialog
import br.com.useblu.oceands.databinding.OceanBottomListSheetItemBinding

class OceanBottomListSheetAdapter(
    private val oceanBottomSheet: OceanBottomListSheet,
    private val items: List<String>,
    private val onSelect: (Int) -> Unit,
    private val selected: Int = -1,
    private val limit: Int? = null,
    private val title: String? = null,
    private val hint: String? = null,
    private val manager: FragmentManager? = null,
) : RecyclerView.Adapter<OceanBottomListSheetAdapter.OceanBottomListSheetViewHolder>() {

    private lateinit var oceanSearchDialog: OceanSearchDialog
    private val itemsAll by lazy { mutableListOf<String>() }

    init {
        itemsAll.addAll(items)
        addItemSeeAll()
    }

    private fun addItemSeeAll() {
        limit?.let {
            if (items.size >= limit) {
                replaceList(items.subList(0, limit))
                itemsAll.add(SEE_ALL)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanBottomListSheetViewHolder {
        val itemBinding = OceanBottomListSheetItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OceanBottomListSheetViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: OceanBottomListSheetViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = itemsAll.size

    fun updateList(list: List<String>) {
        replaceList(list)
        notifyDataSetChanged()
    }

    private fun replaceList(items: List<String>) {
        itemsAll.clear()
        itemsAll.addAll(items)
    }

    fun filter(text: String?): Int {
        text?.let {
            val filtered = items.filter { it.contains(text, ignoreCase = true) }
            updateList(filtered)
            return filtered.size
        }
        return 0
    }

    inner class OceanBottomListSheetViewHolder(
        private val itemBinding: OceanBottomListSheetItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.textOceanParagraph.text = itemsAll[position]
            itemBinding.root.setOnClickListener {
                if (isItemSeeAll(position)) {
                    replaceList(items)
                    showOceanDialog()
                    oceanBottomSheet.dismiss()
                } else {
                    hideOceanDialog()
                    setSelectItem(position)
                }
            }
        }

        private fun isItemSeeAll(position: Int) = itemsAll[position] == SEE_ALL

        private fun setSelectItem(position: Int) {
            val positionItemSelect = items.indexOf(itemsAll[position])
            onSelect(positionItemSelect)
            itemBinding.isSelected = positionItemSelect == selected
        }

        private fun showOceanDialog() {
            oceanSearchDialog = OceanSearchDialog(title, hint, this@OceanBottomListSheetAdapter)
            oceanSearchDialog.show(
                manager!!,
                "dialog_search"
            )
        }

        private fun hideOceanDialog() {
            if (::oceanSearchDialog.isInitialized && oceanSearchDialog.isVisible) {
                oceanSearchDialog.dismiss()
            }
        }
    }


    companion object {
        const val SEE_ALL = "Ver todos"
    }
}