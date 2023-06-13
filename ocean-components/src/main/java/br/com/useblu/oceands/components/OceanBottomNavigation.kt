package br.com.useblu.oceands.components

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import br.com.useblu.oceands.databinding.BottomNavigationActiveBackgroundBinding
import br.com.useblu.oceands.databinding.BottomNavigationMenuItemBinding
import br.com.useblu.oceands.extensions.dp
import br.com.useblu.oceands.model.OceanBottomNavigationMenuItem

class OceanBottomNavigation @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var linearLayout: LinearLayout
    private lateinit var background: BottomNavigationActiveBackgroundBinding
    private var currentActiveIndex = -1

    private val menuItems = arrayListOf<Pair<OceanBottomNavigationMenuItem, BottomNavigationMenuItemBinding>>()

    init {
        initViews()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed && currentActiveIndex > -1) {
            val xPosition = menuItems[currentActiveIndex].second.root.x
            val width = menuItems[currentActiveIndex].second.root.width
            setupBackground(xPosition, width)
        }
    }

    private fun initViews() {
        linearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
        }

        background = BottomNavigationActiveBackgroundBinding.inflate(LayoutInflater.from(context))

        this.addView(background.root)
        this.addView(linearLayout)
    }

    fun setSelectedIndex(index: Int) {
        if (currentActiveIndex > -1) {
            val xPosition = menuItems[index].second.root.x.toInt()
            updateBackgroundPosition(xPosition)
        }

        currentActiveIndex = index

        menuItems.forEachIndexed { menuIndex, menu ->
            menu.second.isSelected = menuIndex == index
        }
    }

    private fun setupBackground(xPosition: Float, width: Int) {
        background.root.x = xPosition + this.paddingStart
        background.root.layoutParams = background.root.layoutParams.apply {
            this.width = width
        }
    }

    private fun updateBackgroundPosition(xPosition: Int) {
        val xPositionXWithPadding = xPosition + this.paddingStart
        val currentX = background.root.x
        val deltaX = xPositionXWithPadding - currentX

        val animation = ValueAnimator.ofFloat(0f, 1f)
        animation.addUpdateListener { valueAnimator ->
            val fraction = valueAnimator.animatedFraction
            background.root.x = currentX + (deltaX * fraction)
        }
        animation.start()
    }

    fun addMenuItem(item: OceanBottomNavigationMenuItem) {
        val layoutInflater = LayoutInflater.from(context)
        val menuLayout = BottomNavigationMenuItemBinding.inflate(layoutInflater)

        menuLayout.apply {
            model = item

            val width = 0
            val height = LinearLayout.LayoutParams.MATCH_PARENT
            val weight = 1f

            val layoutParams = LinearLayout.LayoutParams(width, height, weight)
            layoutParams.marginStart = 2.dp
            layoutParams.marginEnd = 2.dp

            root.layoutParams = layoutParams

            root.setOnClickListener { _ ->
                val currentIndex = menuItems.indexOfFirst { it.first == item }

                if (currentIndex != currentActiveIndex) {
                    setSelectedIndex(currentIndex)
                    item.onClickListener()
                }
            }
        }

        linearLayout.addView(menuLayout.root)
        menuItems.add(item to menuLayout)
    }
}