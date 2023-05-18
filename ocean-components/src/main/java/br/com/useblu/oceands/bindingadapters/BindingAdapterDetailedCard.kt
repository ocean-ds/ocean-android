package br.com.useblu.oceands.bindingadapters

import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.useblu.oceands.R
import br.com.useblu.oceands.adapter.OceanDetailedAdapter
import br.com.useblu.oceands.model.OceanDetailedItem
import me.relex.circleindicator.CircleIndicator3

@BindingAdapter("items")
fun setDetailedCardAdapter(
    page: ViewPager2,
    options: List<OceanDetailedItem>?
) {
    options?.let { items ->
        val adapter =
            OceanDetailedAdapter(
                items = items
            )

        page.adapter = adapter
        page.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val frameLayout = (page.parent as FrameLayout)
        val indicator: CircleIndicator3 = frameLayout.findViewById(R.id.indicator)
        indicator.setViewPager(page)

        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)
    }
}
