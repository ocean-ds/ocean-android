package br.com.useblu.oceands.bindingadapters

import android.graphics.Rect
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.useblu.oceands.extensions.dp

@BindingAdapter("previewNextItem")
fun ViewPager2.setPreviewNextItem(enabled: Boolean) {
    if (!enabled) return

    val pager = this
    val nextItemVisiblePx = 24.dp
    val currentItemHorizontalMarginPx = 32.dp
    val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

    val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
        page.translationX = -pageTranslationX * position
    }

    if (pager.itemDecorationCount == 0) {
        pager.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildAdapterPosition(view)
                val totalItems = parent.adapter?.itemCount ?: 0

                when (position) {
                    0 -> {
                        outRect.left = 16.dp
                        outRect.right = currentItemHorizontalMarginPx
                    }

                    totalItems - 1 -> {
                        outRect.left = currentItemHorizontalMarginPx
                        outRect.right = 16.dp
                    }

                    else -> {
                        outRect.left = currentItemHorizontalMarginPx
                        outRect.right = currentItemHorizontalMarginPx
                    }
                }
            }
        })
    }

    pager.offscreenPageLimit = 1
    pager.clipToPadding = false
    pager.setPageTransformer(pageTransformer)
}
