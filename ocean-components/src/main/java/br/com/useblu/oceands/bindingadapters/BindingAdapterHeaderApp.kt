package br.com.useblu.oceands.bindingadapters

import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.annotation.IdRes
import androidx.core.view.children
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.useblu.oceands.R
import br.com.useblu.oceands.adapter.OceanBalanceAdapter
import br.com.useblu.oceands.extensions.animateHeight
import br.com.useblu.oceands.model.OceanHeaderAppModel
import com.github.florent37.expansionpanel.ExpansionLayout
import me.relex.circleindicator.CircleIndicator3


@BindingAdapter("header_model", "on_page_change", "circle_indicator")
fun ViewPager2.setViewPager(
    headerModel: OceanHeaderAppModel?,
    onPageChangeCallback: ((Int) -> Unit)?,
    @IdRes circleIndicatorRes: Int
) {
    headerModel ?: return

    if (adapter == null) {
        this.adapter = OceanBalanceAdapter(headerModel)

        val parent = this.parent as ViewGroup
        val circleIndicator = parent.findViewById<CircleIndicator3>(circleIndicatorRes)
        circleIndicator.setViewPager(this)
        adapter?.registerAdapterDataObserver(circleIndicator.adapterDataObserver)

        this.registerOnPageChangeCallback(ViewPagerCallback(this) {
            this.children.elementAt(0).findViewById<ExpansionLayout>(R.id.expansion_layout)?.apply {
                if (isExpanded) toggle(true)
            }

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
    private val layoutManager = (viewPager[0] as RecyclerView).layoutManager
    private val layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        currentView?.let {
            updatePagerHeightForChild(it)
        }
    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        onPageChangeCallback(position)
        currentView?.viewTreeObserver?.removeOnGlobalLayoutListener(layoutListener)

        layoutManager?.findViewByPosition(position)?.let {
            currentView = it
            if (position == 0) {
                it.viewTreeObserver?.addOnGlobalLayoutListener(layoutListener)
            } else {
                updatePagerHeightChangePage(it)
            }
        }
    }

    private fun updatePagerHeightChangePage(view: View) {
        val wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
        val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(wMeasureSpec, hMeasureSpec)

        if (viewPager.layoutParams.height != view.measuredHeight) {
            viewPager.animateHeight(view.measuredHeight, 600)
        }
    }

    private fun updatePagerHeightForChild(view: View) {
        val wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
        val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(wMeasureSpec, hMeasureSpec)

        if (viewPager.layoutParams.height != view.measuredHeight) {
            viewPager.layoutParams.also {
                it.height = view.measuredHeight
            }
        }
    }
}