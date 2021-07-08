package br.com.useblu.oceands

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.databinding.OceanBottomListSheetBinding
import br.com.useblu.oceands.databinding.OceanBottomListSheetItemBinding
import br.com.useblu.oceands.databinding.OceanBottomListSheetWithIconItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class OceanBottomListSheet(context: Context) : BottomSheetDialog(context) {

    private var title: String? = null
    private var isDismiss: Boolean = true
    private var adapter: OceanBottomListSheetAdapter? = null
    private var adapterWithIcon: OceanBottomListSheetWithIconAdapter? = null
    private var manager: FragmentManager? = null
    private var limit: Int? = null

    private lateinit var binding: OceanBottomListSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.ocean_bottom_list_sheet,
            null,
            false
        )

        title?.let {
            binding.title = it
        }

        adapter?.let {
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }

        adapterWithIcon?.let {
            binding.recyclerView.adapter = adapterWithIcon
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }

        binding.cancelabled = isDismiss

        setCancelable(isDismiss)
        setCanceledOnTouchOutside(isDismiss)
        setContentView(binding.root)

        val view = binding.root.parent as View
        view.background = ColorDrawable(Color.TRANSPARENT)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        behavior.peekHeight = context.resources.displayMetrics.heightPixels
    }

    fun withDismiss(dismiss: Boolean): OceanBottomListSheet {
        this.isDismiss = dismiss
        return this
    }

    fun withTitle(title: String): OceanBottomListSheet {
        this.title = title
        return this
    }

    fun withTitle(title: Int): OceanBottomListSheet {
        this.title = context.getString(title)
        return this
    }

    fun withSearch(manager: FragmentManager, limit: Int): OceanBottomListSheet {
        this.limit = limit
        this.manager = manager
        return this
    }

    fun withSimpleList(
        items: List<String>,
        selectedPosition: Int = -1,
        onItemSelect: (Int) -> Unit,
    ): OceanBottomListSheet {
        adapter = OceanBottomListSheetAdapter(
            items = items,
            selected = selectedPosition,
            onSelect = {
                onItemSelect(it)
                dismiss()
            },
            limit = limit,
            title = title,
            manager = manager
        )
        return this
    }

    fun withCustomList(
        items: List<OceanBottomListSheetUIModel>,
        selectedPosition: Int = -1,
        onItemSelect: (Int) -> Unit
    ): OceanBottomListSheet {
        adapterWithIcon = OceanBottomListSheetWithIconAdapter(
            items = items,
            selected = selectedPosition,
            onSelect = {
                onItemSelect(it)
                dismiss()
            }
        )
        return this
    }

}

data class OceanBottomListSheetUIModel(
    val drawableIcon: Drawable,
    val title: String,
    val description: String
)

class OceanBottomListSheetAdapter(
    private val items: List<String>,
    private val onSelect: (Int) -> Unit,
    private val selected: Int = -1,
    private val limit: Int? = null,
    private val title: String? = null,
    private val manager: FragmentManager? = null
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

    private fun replaceList(items: List<String>) {
        itemsAll.clear()
        itemsAll.addAll(items)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanBottomListSheetViewHolder {
        val itemBinding = OceanBottomListSheetItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OceanBottomListSheetViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: OceanBottomListSheetViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = itemsAll.size

    inner class OceanBottomListSheetViewHolder(
        private val itemBinding: OceanBottomListSheetItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.textOceanParagraph.text = itemsAll[position]
            itemBinding.root.setOnClickListener {
                if (isItemSeeAll(position)) {
                    replaceList(items)
                    showOceanDialog()
                } else {
                    hideOceanDialog()
                    onSelect(position)
                    itemBinding.isSelected = position == selected
                }
            }
        }

        private fun isItemSeeAll(position: Int) = itemsAll[position] == SEE_ALL

        private fun showOceanDialog() {
            oceanSearchDialog = OceanSearchDialog(title, this@OceanBottomListSheetAdapter)
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
        const val SEE_ALL = "Ver Todos"
    }

}

internal class OceanBottomListSheetWithIconAdapter(
    private val items: List<OceanBottomListSheetUIModel>,
    private val onSelect: (Int) -> Unit,
    private val selected: Int = -1
) : RecyclerView.Adapter<OceanBottomListSheetWithIconAdapter.OceanBottomListSheetWithIconViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OceanBottomListSheetWithIconViewHolder {
        val itemBinding = OceanBottomListSheetWithIconItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OceanBottomListSheetWithIconViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: OceanBottomListSheetWithIconViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = items.size

    inner class OceanBottomListSheetWithIconViewHolder(
        private val itemBinding: OceanBottomListSheetWithIconItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindView(position: Int) {
            itemBinding.item = items[position]
            itemBinding.root.setOnClickListener { onSelect(position) }
            itemBinding.isSelected = position == selected
        }

    }

}