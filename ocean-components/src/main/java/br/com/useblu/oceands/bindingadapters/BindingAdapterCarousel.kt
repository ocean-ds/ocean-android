package br.com.useblu.oceands.bindingadapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import br.com.useblu.oceands.R
import br.com.useblu.oceands.adapter.OceanBalanceAdapter
import br.com.useblu.oceands.databinding.ItemCarouselOceanBinding
import br.com.useblu.oceands.extensions.dp
import br.com.useblu.oceands.model.OceanBalanceModel
import br.com.useblu.oceands.model.OceanCarouselItem
import me.relex.circleindicator.CircleIndicator2
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage

@BindingAdapter("entries")
fun setAdapterCarousel(carousel: ImageCarousel, entries: List<OceanCarouselItem>?) {
    carousel.carouselListener = object : CarouselListener {
        override fun onCreateViewHolder(
            layoutInflater: LayoutInflater,
            parent: ViewGroup
        ): ViewBinding {
            return ItemCarouselOceanBinding.inflate(layoutInflater, parent, false)
        }

        override fun onBindViewHolder(
            binding: ViewBinding,
            item: CarouselItem,
            position: Int
        ) {
            val currentBinding = binding as ItemCarouselOceanBinding
            currentBinding.imageView.apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImage(item, R.drawable.carousel_default_placeholder)
                setOnClickListener {
                    entries?.get(position)?.action?.invoke()
                }
            }
        }
    }

    val items = entries?.map {
        CarouselItem(it.url)
    } ?: emptyList()

    carousel.setData(items)
}

@BindingAdapter("setIndicator", "autoCycleCarousel")
fun setCircleIndicator(
    circleIndicator: CircleIndicator2,
    setIndicator: Boolean,
    autoCycleCarousel: Boolean
) {
    if (setIndicator) {
        val constraintLayout = (circleIndicator.parent as ConstraintLayout)
        val carouselId = if (autoCycleCarousel) R.id.carousel_view2 else R.id.carousel_view
        val imageCarousel = constraintLayout.findViewById<ImageCarousel>(carouselId)
        imageCarousel.setIndicator(circleIndicator)
    }
}

@BindingAdapter("setViewPager")
fun ViewPager2.setViewPager(model: OceanBalanceModel?) {
    model ?: return

    this.offscreenPageLimit = 1
    this.clipToPadding = false
    this.adapter = OceanBalanceAdapter(listOf(model, model))

    val nextItemVisiblePx = 24.dp
    val currentItemHorizontalMarginPx = 32.dp
    val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

    val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
        page.translationX = -pageTranslationX * position
    }

    this.addItemDecoration(object: RecyclerView.ItemDecoration() {
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

    this.setPageTransformer(pageTransformer)

    this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        private var currentView: View? = null
        private val layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            currentView?.let {
                updatePagerHeightForChild(it)
            }
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            currentView?.viewTreeObserver?.removeOnGlobalLayoutListener(layoutListener)

            val layoutManager = (this@setViewPager.get(0) as RecyclerView).layoutManager
            val view = layoutManager?.findViewByPosition(position)
            currentView = view
            view?.viewTreeObserver?.addOnGlobalLayoutListener(layoutListener)
        }

        private fun updatePagerHeightForChild(view: View) {
            val wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
            val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view.measure(wMeasureSpec, hMeasureSpec)

            if (this@setViewPager.layoutParams.height != view.measuredHeight) {
                // ParentViewGroup is, for example, LinearLayout
                // ... or whatever the parent of the ViewPager2 is
                this@setViewPager.layoutParams = (this@setViewPager.layoutParams as ViewGroup.LayoutParams)
                    .also { lp -> lp.height = view.measuredHeight }
            }
        }
    })
}