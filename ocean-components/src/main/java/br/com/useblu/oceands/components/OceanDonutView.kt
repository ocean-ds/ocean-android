package br.com.useblu.oceands.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import br.com.useblu.oceands.R
import br.com.useblu.oceands.extensions.dp
import br.com.useblu.oceands.model.OceanDonutItem

class OceanDonutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var sectionItems: List<OceanDonutItem> = emptyList()

    private val paintSize = 32.dp.toFloat()

    private val minViewSize = 180.dp

    private val chartPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = paintSize
    }

    private val textPaint = Paint().apply {
        textAlign = Paint.Align.CENTER
        isAntiAlias = true
        typeface = ResourcesCompat.getFont(context, R.font.font_family_base_medium)
    }

    private val drawRect = RectF()

    fun setSectionItems(items: Collection<OceanDonutItem>) {
        sectionItems = items.toList()

        invalidate()
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        val result = when (specMode) {
            MeasureSpec.AT_MOST -> {
                desiredSize.coerceAtMost(specSize)
            }
            MeasureSpec.EXACTLY -> {
                specSize
            }
            MeasureSpec.UNSPECIFIED -> {
                desiredSize
            }
            else -> {
                desiredSize
            }
        }

        return result
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        val desiredSize = if (desiredHeight != 0 && desiredWidth != 0){
            desiredHeight.coerceAtMost(desiredWidth)
        } else if (desiredHeight != 0) {
            desiredHeight
        } else {
            desiredWidth
        }

        val finalWidth = measureDimension(desiredSize, widthMeasureSpec)
        val finalHeight = measureDimension(desiredSize, heightMeasureSpec)

        if (finalWidth != 0 && finalHeight != 0) {
            setMeasuredDimension(finalWidth, finalHeight)
        } else if (finalWidth != 0) {
            setMeasuredDimension(finalWidth, finalWidth)
        } else if (finalHeight != 0) {
            setMeasuredDimension(finalHeight, finalHeight)
        } else {
            setMeasuredDimension(minViewSize, minViewSize)
        }
    }

    override fun onDraw(canvas: Canvas) {
        val height = bottom - top
        val width = right - left

        val size = height.coerceAtMost(width)

        drawRect.apply {
            left = paintSize / 2
            top = paintSize / 2
            right = size - paintSize / 2
            bottom = size - paintSize / 2
        }

        if (sectionItems.isNotEmpty()) {
            drawSectionItems(canvas)
        } else {
            drawEmptyChart(canvas)
        }

        val textX = drawRect.centerX()
        val textY = drawRect.centerY()

        textPaint.color = ContextCompat.getColor(context, R.color.ocean_color_interface_dark_deep)
        textPaint.textSize = 20.dp.toFloat()
        canvas.drawText("0", textX, textY, textPaint)

        textPaint.color = ContextCompat.getColor(context, R.color.ocean_color_interface_dark_down)
        textPaint.textSize = 12.dp.toFloat()
        canvas.drawText("Label", textX, textY + 50, textPaint)
    }

    private fun drawEmptyChart(canvas: Canvas) {
        chartPaint.color = ContextCompat.getColor(context, R.color.ocean_color_interface_light_deep)

        canvas.drawArc(
            drawRect,
            0f,
            360f,
            false,
            chartPaint
        )
    }

    private fun drawSectionItems(canvas: Canvas) {
        val totalValue = sectionItems.sumOf { it.value }
        var startAngle = -90f

        sectionItems.forEach {
            chartPaint.color = ContextCompat.getColor(context, it.color)

            val itemPercent = it.value / totalValue

            val sweepAngle = (itemPercent * 360f).toFloat()

            canvas.drawArc(
                drawRect,
                startAngle,
                sweepAngle,
                false,
                chartPaint
            )

            startAngle += sweepAngle
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}
