package br.com.useblu.oceands.components

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
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
    private var currentSelectedItem: BottomNavigationMenuItemBinding? = null
    private var hasUserInteracted = false

    private val menuItems = arrayListOf<Pair<OceanBottomNavigationMenuItem, BottomNavigationMenuItemBinding>>()

    init {
        initViews()
    }

    private fun initViews() {
        linearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
        }

        background = BottomNavigationActiveBackgroundBinding.inflate(LayoutInflater.from(context))
        background.root.visibility = View.GONE

        this.addView(background.root)
        this.addView(linearLayout)
    }

    private val selectedViewLayoutObserver = ViewTreeObserver.OnGlobalLayoutListener {
        selectedViewLayoutChanged()
    }

    private fun selectedViewLayoutChanged() {
        val selectedItemView = currentSelectedItem ?: return

        val width = selectedItemView.root.width
        updateBackgroundWidth(width)
        if (!hasUserInteracted) {
            val xPosition = selectedItemView.root.x.toInt()
            setBackgroundPosition(xPosition)
        }
    }

    private fun addViewLayoutChangesObserver(view: BottomNavigationMenuItemBinding) {
        view.root.viewTreeObserver.addOnGlobalLayoutListener(selectedViewLayoutObserver)
    }

    private fun removeViewLayoutChangesObserver(view: BottomNavigationMenuItemBinding) {
        view.root.viewTreeObserver.removeOnGlobalLayoutListener(selectedViewLayoutObserver)
    }

    fun setSelectedIndex(index: Int) {
        setSelectedItem(menuItems[index].second)
    }

    private fun setSelectedItem(newSelectedItem: BottomNavigationMenuItemBinding) {
        currentSelectedItem?.let {
            val xPosition = newSelectedItem.root.x.toInt()
            animateBackgroundPosition(xPosition)
            removeViewLayoutChangesObserver(it)
        }

        currentSelectedItem = newSelectedItem
        addViewLayoutChangesObserver(newSelectedItem)

        menuItems.forEach {
            it.second.isSelected = false
        }

        newSelectedItem.isSelected = true
    }

    private fun updateBackgroundWidth(width: Int) {
        background.root.layoutParams = background.root.layoutParams.apply {
            this.width = width
        }
    }

    private fun setBackgroundPosition(xPosition: Int) {
        val xPositionXWithPadding = xPosition + this.paddingStart
        background.root.x = xPositionXWithPadding.toFloat()
    }

    private fun animateBackgroundPosition(xPosition: Int) {
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

            root.setOnClickListener {
                if (this != currentSelectedItem) {
                    hasUserInteracted = true
                    setSelectedItem(this)
                    item.onClickListener()
                }
            }
        }

        linearLayout.addView(menuLayout.root)
        menuItems.add(item to menuLayout)

        hasUserInteracted = false
        background.root.visibility = View.VISIBLE

        if (menuItems.size == 1) {
            setSelectedIndex(0)
        }
    }

    fun clearMenuItems() {
        currentSelectedItem?.let {
            removeViewLayoutChangesObserver(it)
        }
        hasUserInteracted = false
        background.root.visibility = View.GONE
        currentSelectedItem = null
        menuItems.clear()
        linearLayout.removeAllViews()
    }
}