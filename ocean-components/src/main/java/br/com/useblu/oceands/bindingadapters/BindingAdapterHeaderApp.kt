package br.com.useblu.oceands.bindingadapters

import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.useblu.oceands.adapter.OceanBalanceAdapter
import br.com.useblu.oceands.model.OceanBalanceBluModel
import br.com.useblu.oceands.model.OceanBalanceOthersModel
import me.relex.circleindicator.CircleIndicator3

@BindingAdapter("blu_model", "others_model", "circle_indicator")
fun ViewPager2.setViewPager(
    bluModel: OceanBalanceBluModel?,
    othersModel: OceanBalanceOthersModel?,
    circleIndicatorRes: Int
) {
    bluModel ?: return
    othersModel ?: return

    this.offscreenPageLimit = 1
    this.clipToPadding = false

    if (adapter == null) {
        this.adapter = OceanBalanceAdapter(bluModel, othersModel)

        val parent = this.parent as ViewGroup
        val circleIndicator = parent.findViewById<CircleIndicator3>(circleIndicatorRes)
        circleIndicator.setViewPager(this)
        adapter?.registerAdapterDataObserver(circleIndicator.adapterDataObserver)

        this.registerOnPageChangeCallback(ViewPagerCallback(this))
    } else {
        (adapter as OceanBalanceAdapter).updateModel(bluModel, othersModel)
    }

    this.setPreviewNextItem(true)
}

private class ViewPagerCallback(private val viewPager: ViewPager2): ViewPager2.OnPageChangeCallback() {
    private var currentView: View? = null
    private val layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        currentView?.let {
            updatePagerHeightForChild(it)
        }
    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
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
            viewPager.layoutParams = (viewPager.layoutParams as ViewGroup.LayoutParams)
                .also { lp -> lp.height = view.measuredHeight }
        }
    }
}