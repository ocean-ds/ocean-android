package br.com.useblu.oceands.bindingadapters

import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.useblu.oceands.adapter.OceanBalanceAdapter
import br.com.useblu.oceands.model.OceanHeaderAppModel
import me.relex.circleindicator.CircleIndicator3

@BindingAdapter("header_model", "on_page_change", "circle_indicator")
fun ViewPager2.setViewPager(
    headerModel: OceanHeaderAppModel?,
    onPageChangeCallback: ((Int) -> Unit)?,
    circleIndicatorRes: Int
) {
    headerModel ?: return

    if (adapter == null) {
        this.adapter = OceanBalanceAdapter(headerModel)

        val parent = this.parent as ViewGroup
        val circleIndicator = parent.findViewById<CircleIndicator3>(circleIndicatorRes)
        circleIndicator.setViewPager(this)
        adapter?.registerAdapterDataObserver(circleIndicator.adapterDataObserver)

        this.registerOnPageChangeCallback(ViewPagerCallback(this) {
            onPageChangeCallback?.invoke(it)
        })
    } else {
        (adapter as OceanBalanceAdapter).updateModel(headerModel)
    }

    this.setPreviewNextItem(true)
}

private class ViewPagerCallback(
    private val viewPager: ViewPager2,
    private val onPageChangeCallback: (Int) -> Unit
): ViewPager2.OnPageChangeCallback() {
    private var currentView: View? = null
    private val layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        currentView?.let {
            updatePagerHeightForChild(it)
        }
    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        onPageChangeCallback(position)
        currentView?.viewTreeObserver?.removeOnGlobalLayoutListener(layoutListener)

        val layoutManager = (viewPager[0] as RecyclerView).layoutManager
        layoutManager?.findViewByPosition(position)?.let {
            currentView = it
            updatePagerHeightForChild(it)
            it.viewTreeObserver?.addOnGlobalLayoutListener(layoutListener)
        }
    }

    private fun updatePagerHeightForChild(view: View) {
        val wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
        val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(wMeasureSpec, hMeasureSpec)

        if (viewPager.layoutParams.height != view.measuredHeight) {
            viewPager.layoutParams = viewPager.layoutParams.also {
                it.height = view.measuredHeight
            }
        }
    }
}