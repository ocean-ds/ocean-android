package br.com.useblu.oceands.bindingadapters

import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.adapter.OceanChipListAdapter
import br.com.useblu.oceands.model.OceanChip

@BindingAdapter("chips")
fun setChipsAdapter(
    recyclerView: RecyclerView,
    chips: List<OceanChip>?
) {
    chips ?: return
    val adapter = OceanChipListAdapter {
        Toast.makeText(recyclerView.context, "Selected items: $it", Toast.LENGTH_SHORT).show()
    }
    recyclerView.adapter = adapter.apply {
        submitList(chips)
    }
}