package br.com.useblu.oceands.core

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.viewbinding.ViewBinding
import br.com.useblu.oceands.R
import br.com.useblu.oceands.databinding.ItemCarouselOceanBinding
import me.relex.circleindicator.CircleIndicator2
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage

@BindingAdapter("entries", "itemSelected")
fun setAdapterCarousel(carousel: ImageCarousel, entries: List<String>?, itemSelected: (Int) -> Unit) {
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
                    itemSelected.invoke(position)
                }
            }
        }
    }

    carousel.setData(entries?.map { CarouselItem(it) } ?: emptyList())
}

@BindingAdapter("setIndicator", "autoCycleCarousel")
fun setCircleIndicator(circleIndicator: CircleIndicator2, setIndicator: Boolean, autoCycleCarousel: Boolean) {
    if (setIndicator) {
        val constraintLayout = (circleIndicator.parent as ConstraintLayout)
        val carouselId = if (autoCycleCarousel) R.id.carousel_view2 else R.id.carousel_view
        val imageCarousel = constraintLayout.findViewById<ImageCarousel>(carouselId)
        imageCarousel.setIndicator(circleIndicator)
    }
}