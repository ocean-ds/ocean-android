package br.com.useblu.oceands.utils

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.SystemClock
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.animation.doOnEnd
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import java.util.LinkedList
import java.util.Queue

abstract class SwipeHelper(private val context: Context, private val recyclerView: RecyclerView) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private var buttons: MutableList<UnderlayButton> = ArrayList()
    private val gestureDetector: GestureDetector by lazy {
        GestureDetector(
            context,
            gestureListener
        )
    }
    private var swipedPos = -1
    private var swipeThreshold = 0.5f
    private val buttonsBuffer: MutableMap<Int, MutableList<UnderlayButton>> = HashMap()
    private val recoverQueue: Queue<Int> = LinkedList()
    private val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(this)
    private val gestureListener: SimpleOnGestureListener = object : SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            for (button in buttons) {
                if (button.onClick(e.x, e.y)) break
            }
            return true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private val onTouchListener = OnTouchListener { _, e ->
        if (swipedPos < 0) return@OnTouchListener false
        val point = Point(
            e.rawX.toInt(),
            e.rawY.toInt()
        )
        val swipedViewHolder = recyclerView.findViewHolderForAdapterPosition(swipedPos)
        swipedViewHolder?.let {
            val swipedItem = it.itemView
            val rect = Rect()
            swipedItem.getGlobalVisibleRect(rect)
            if (e.action == MotionEvent.ACTION_DOWN || e.action == MotionEvent.ACTION_UP || e.action == MotionEvent.ACTION_MOVE) {
                if (rect.top < point.y && rect.bottom > point.y) gestureDetector.onTouchEvent(e) else {
                    if (recoverQueue.contains(swipedPos).not()) {
                        recoverQueue.add(swipedPos)
                    }
                    swipedPos = -1
                    recoverSwipedItem()
                }
            }
        }
        false
    }

    init {
        recyclerView.setOnTouchListener(onTouchListener)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.bindingAdapterPosition
        if (swipedPos != pos && recoverQueue.contains(swipedPos).not()) recoverQueue.add(swipedPos)
        swipedPos = pos
        if (buttonsBuffer.containsKey(swipedPos)) buttons =
            buttonsBuffer[swipedPos]!! else buttons.clear()
        buttonsBuffer.clear()
        swipeThreshold = 0.5f * buttons.size * BUTTON_WIDTH
        recoverSwipedItem()
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return swipeThreshold
    }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return 0.1f * defaultValue
    }

    override fun getSwipeVelocityThreshold(defaultValue: Float): Float {
        return 5.0f * defaultValue
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val pos = viewHolder.bindingAdapterPosition
        var translationX = dX
        val itemView = viewHolder.itemView
        if (pos < 0) {
            swipedPos = pos
            return
        }
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX < 0) {
                var buffer: MutableList<UnderlayButton> = ArrayList()
                if (!buttonsBuffer.containsKey(pos)) {
                    instantiateUnderlayButton(viewHolder, buffer)
                    buttonsBuffer[pos] = buffer
                } else {
                    buffer = buttonsBuffer[pos]!!
                }
                translationX = dX * buffer.size * BUTTON_WIDTH / itemView.width
                drawButtons(c, itemView, buffer, pos, translationX)
            }
        }
        super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            translationX,
            dY,
            actionState,
            isCurrentlyActive
        )
    }

    fun animateItem(pos: Int) {
        val childView = recyclerView.getChildAt(pos) ?: return
        val distance = childView.width
        val x = childView.width / 2F
        val viewLocation = IntArray(2)
        val parentLocation = IntArray(2)
        childView.getLocationInWindow(viewLocation)
        recyclerView.getLocationInWindow(parentLocation)
        val y = (viewLocation[1] - parentLocation[1]) + childView.height / 2F
        val downTime = SystemClock.uptimeMillis()
        recyclerView.dispatchTouchEvent(
            MotionEvent.obtain(
                downTime,
                downTime,
                MotionEvent.ACTION_DOWN,
                x,
                y,
                0
            )
        )
        ValueAnimator.ofInt(0, distance).apply {
            duration = 600
            addUpdateListener {
                val dX = it.animatedValue as Int
                val mX = x - dX

                recyclerView.dispatchTouchEvent(
                    MotionEvent.obtain(
                        downTime,
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_MOVE,
                        mX,
                        y,
                        0
                    )
                )
            }
            doOnEnd {
                recyclerView.dispatchTouchEvent(
                    MotionEvent.obtain(
                        downTime,
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_CANCEL,
                        x,
                        y,
                        0
                    )
                )
            }
        }.start()
    }

    fun hideOptions(pos: Int) {
        swipedPos = pos
        val swipedViewHolder = recyclerView.findViewHolderForAdapterPosition(swipedPos)
        swipedViewHolder?.let {
            if (recoverQueue.contains(swipedPos).not()) {
                recoverQueue.add(swipedPos)
            }
            swipedPos = -1
            recoverSwipedItem()
        }
    }

    @Synchronized
    private fun recoverSwipedItem() {
        while (!recoverQueue.isEmpty()) {
            val pos = recoverQueue.poll()
            if (pos > -1) {
                recyclerView.adapter!!.notifyItemChanged(pos)
            }
        }
    }

    private fun drawButtons(
        c: Canvas,
        itemView: View,
        buffer: List<UnderlayButton>,
        pos: Int,
        dX: Float
    ) {
        var right = itemView.right.toFloat()
        val dButtonWidth = -1 * dX / buffer.size
        for (button in buffer) {
            val left = right - dButtonWidth
            button.onDraw(
                c,
                RectF(
                    left,
                    itemView.top.toFloat(),
                    right,
                    itemView.bottom.toFloat()
                ),
                pos
            )
            right = left
        }
    }

    abstract fun instantiateUnderlayButton(
        viewHolder: RecyclerView.ViewHolder?,
        underlayButtons: MutableList<UnderlayButton>?
    )

    class UnderlayButton(
        private val context: Context,
        private val textId: Int,
        private val imageResId: Int?,
        private val backgroundColor: Int,
        private val textColor: Int,
        private val clickListener: UnderlayButtonClickListener
    ) {
        private var pos = 0
        private var clickRegion: RectF? = null
        fun onClick(x: Float, y: Float): Boolean {
            if (clickRegion != null && clickRegion!!.contains(x, y)) {
                clickListener.onClick(pos)
                return true
            }
            return false
        }

        fun onDraw(canvas: Canvas, rect: RectF, pos: Int) {
            val p = Paint()
            // Draw background
            p.color = context.resources.getColor(backgroundColor)
            canvas.drawRect(rect, p)

            // Draw Text
            val textPaint = TextPaint()
            textPaint.typeface = ResourcesCompat.getFont(context, R.font.font_family_base_regular)
            textPaint.textSize = 16 * context.resources.displayMetrics.density
            textPaint.color = context.resources.getColor(textColor)

            val sl = StaticLayout(
                context.resources.getText(textId),
                textPaint,
                rect.width().toInt(),
                Layout.Alignment.ALIGN_CENTER,
                1f,
                1f,
                false
            )
            imageResId?.let {
                val drawable = AppCompatResources.getDrawable(context, it)
                val top = (rect.top + rect.height() * 0.2f).toInt()
                val bottom = (rect.bottom - rect.height() * 0.5f).toInt()
                val height = bottom - top
                val paddingHorizontal = (BUTTON_WIDTH / 2) - height / 2
                drawable?.setBounds(
                    (rect.left + paddingHorizontal).toInt(),
                    top,
                    (rect.right - paddingHorizontal).toInt(),
                    bottom
                )
                drawable?.draw(canvas)
            }
            canvas.save()
            if (rect.width() > BUTTON_WIDTH * 0.8) {
                canvas.translate(rect.left, rect.top + rect.height() * 0.6f)
                sl.draw(canvas)
            }
            canvas.restore()
            clickRegion = rect
            this.pos = pos
        }
    }

    interface UnderlayButtonClickListener {
        fun onClick(pos: Int)
    }

    companion object {
        const val BUTTON_WIDTH = 200
    }
}
