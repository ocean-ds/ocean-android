package br.com.useblu.oceands

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.IntDef
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.ViewCompat
import br.com.useblu.oceands.core.dp
import java.util.*
import kotlin.math.max
import kotlin.math.min

class OceanStepView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.sv_stepViewStyle
) : View(context, attrs, defStyleAttr) {

    interface OnStepClickListener {
        fun onStepClick(step: Int)
    }

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(ANIMATION_LINE, ANIMATION_CIRCLE, ANIMATION_ALL, ANIMATION_NONE)
    annotation class AnimationType

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(DISPLAY_MODE_WITH_TEXT, DISPLAY_MODE_NO_TEXT)
    annotation class DisplayMode

    private var onStepClickListener: OnStepClickListener? = null

    @DisplayMode
    private var displayMode = DISPLAY_MODE_WITH_TEXT
    private val steps: MutableList<String> = ArrayList()

    private var stepsNumber = 0
    var currentStep = START_STEP
        private set
    private var nextAnimatedStep = 0
    private var state = IDLE
    private val paddingOffset = 10

    @AnimationType
    private var animationType = 0

    @ColorInt
    private var selectedCircleColor = 0

    @Dimension
    private var selectedCircleRadius = 0

    @ColorInt
    private var selectedTextColor = 0

    @ColorInt
    private var doneCircleColor = 0

    @Dimension
    private var doneCircleRadius = 0

    @ColorInt
    private var doneTextColor = 0

    @ColorInt
    private var nextTextColor = 0

    @Dimension
    private var stepPadding = 0

    @ColorInt
    private var nextStepLineColor = 0

    @ColorInt
    private var doneStepLineColor = 0

    @Dimension
    private var stepLineWidth = 0

    @Dimension(unit = Dimension.SP)
    private var textSize = 0f

    @Dimension
    private var textPadding = 0
    private var selectedStepNumberColor = 0

    @Dimension(unit = Dimension.SP)
    private var stepNumberTextSize = 0f

    @ColorInt
    private var doneStepMarkColor = 0
    private var animationDuration = 0
    private var nextStepCircleEnabled = false

    @ColorInt
    private var nextStepCircleColor = 0
    private var numberCurrentStepEnabled = false
    private var numbersNextStepEnabled = false
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint: TextPaint
    private var animator: ValueAnimator? = null
    private var circlesX: IntArray = IntArray(0)
    private var startLinesX: IntArray = IntArray(0)
    private var endLinesX: IntArray = IntArray(0)
    private var constraints: FloatArray = FloatArray(0)
    private var circlesY = 0
    private var textY = 0
    private var animatedFraction = 0f
    private var done = false
    private lateinit var textLayouts: Array<StaticLayout?>
    private val bounds = Rect()
    private fun applyStyles(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val ta = context.obtainStyledAttributes(
            attrs,
            R.styleable.StepView,
            defStyleAttr,
            R.style.StepView
        )
        selectedCircleColor = ta.getColor(R.styleable.StepView_sv_selectedCircleColor, 0)
        selectedCircleRadius =
            ta.getDimensionPixelSize(R.styleable.StepView_sv_selectedCircleRadius, 0)
        selectedTextColor = ta.getColor(R.styleable.StepView_sv_selectedTextColor, 0)
        selectedStepNumberColor = ta.getColor(R.styleable.StepView_sv_selectedStepNumberColor, 0)
        doneStepMarkColor = ta.getColor(R.styleable.StepView_sv_doneStepMarkColor, 0)
        doneCircleColor = ta.getColor(R.styleable.StepView_sv_doneCircleColor, 0)
        doneCircleRadius = ta.getDimensionPixelSize(R.styleable.StepView_sv_doneCircleRadius, 0)
        doneTextColor = ta.getColor(R.styleable.StepView_sv_doneTextColor, 0)
        nextTextColor = ta.getColor(R.styleable.StepView_sv_nextTextColor, 0)
        stepPadding = ta.getDimensionPixelSize(R.styleable.StepView_sv_stepPadding, 0)
        nextStepLineColor = ta.getColor(R.styleable.StepView_sv_nextStepLineColor, 0)
        doneStepLineColor = ta.getColor(R.styleable.StepView_sv_doneStepLineColor, 0)
        stepLineWidth = ta.getDimensionPixelSize(R.styleable.StepView_sv_stepLineWidth, 0)
        textPadding = ta.getDimensionPixelSize(R.styleable.StepView_sv_textPadding, 0)
        stepNumberTextSize = ta.getDimension(R.styleable.StepView_sv_stepNumberTextSize, 0f)
        textSize = ta.getDimension(R.styleable.StepView_sv_textSize, 0f)
        animationDuration = ta.getInteger(R.styleable.StepView_sv_animationDuration, 0)
        animationType = ta.getInteger(R.styleable.StepView_sv_animationType, 0)
        stepsNumber = ta.getInteger(R.styleable.StepView_sv_stepsNumber, 0)
        nextStepCircleEnabled = ta.getBoolean(R.styleable.StepView_sv_nextStepCircleEnabled, false)
        numberCurrentStepEnabled =
            ta.getBoolean(R.styleable.StepView_sv_numberCurrentStepEnabled, true)
        nextStepCircleColor = ta.getColor(R.styleable.StepView_sv_nextStepCircleColor, 0)
        val descriptions = ta.getTextArray(R.styleable.StepView_sv_steps)
        displayMode = if (descriptions != null) {
            for (description in descriptions) {
                steps.add(description.toString())
            }
            DISPLAY_MODE_WITH_TEXT
        } else {
            DISPLAY_MODE_NO_TEXT
        }
        val background = ta.getDrawable(R.styleable.StepView_sv_background)
        background?.let { setBackgroundDrawable(it) }
        val fontId = ta.getResourceId(R.styleable.StepView_sv_typeface, 0)
        if (fontId != 0) {
            val typeface = ResourcesCompat.getFont(context, fontId)
            setTypeface(typeface)
        }
        textPaint.textSize = textSize
        ta.recycle()
    }

    private fun setTypeface(typeface: Typeface?) {
        if (typeface != null) {
            textPaint.typeface = typeface
            paint.typeface = typeface
        }
    }

    private fun drawEditMode() {
        if (isInEditMode) {
            if (displayMode == DISPLAY_MODE_WITH_TEXT) {
                if (steps.isEmpty()) {
                    steps.add("Step 1")
                    steps.add("Step 2")
                    steps.add("Step 3")
                }
                setSteps(steps)
            } else {
                if (stepsNumber == 0) {
                    stepsNumber = 4
                }
                setStepsNumber(stepsNumber)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val superResult = super.onTouchEvent(event)
        if (onStepClickListener != null && isEnabled) {
            val action = event.actionMasked
            if (action == MotionEvent.ACTION_UP) {
                val x = event.x
                val y = event.y
                val step = getStepByPointer(x, y)
                onStepClickListener!!.onStepClick(step)
            }
        }
        return superResult
    }

    private fun getStepByPointer(x: Float, y: Float): Int {
        val count = stepCount
        for (i in constraints.indices) {
            val constraint = constraints[i]
            if (x <= constraint) {
                return i
            }
        }
        return count - 1
    }

    fun setOnStepClickListener(listener: OnStepClickListener?) {
        isClickable = listener != null
        onStepClickListener = listener
    }

    fun setSteps(steps: List<String>?) {
        stepsNumber = 0
        displayMode = DISPLAY_MODE_WITH_TEXT
        this.steps.clear()
        this.steps.addAll(steps!!)
        requestLayout()
        go(START_STEP, false)
    }

    fun setStepsNumber(number: Int) {
        steps.clear()
        displayMode = DISPLAY_MODE_NO_TEXT
        stepsNumber = number
        requestLayout()
        go(START_STEP, false)
    }

    fun getState(): State {
        return State()
    }

    fun go(step: Int, animate: Boolean) {
        if (step in START_STEP until stepCount) {
            if (animate && animationType != ANIMATION_NONE && startLinesX != null) {
                if (Math.abs(step - currentStep) > 1) {
                    endAnimation()
                    currentStep = step
                    invalidate()
                } else {
                    nextAnimatedStep = step
                    state = ANIMATE_STEP_TRANSITION
                    animate(step)
                    invalidate()
                }
            } else {
                currentStep = step
                invalidate()
            }
        }
    }

    fun done(isDone: Boolean) {
        done = isDone
        invalidate()
    }

    private fun endAnimation() {
        if (animator != null && animator!!.isRunning) {
            animator!!.end()
        }
    }

    private fun animate(step: Int) {
        endAnimation()
        animator = getAnimator(step)
        if (animator == null) {
            return
        }
        animator!!.addUpdateListener { valueAnimator ->
            animatedFraction = valueAnimator.animatedFraction
            invalidate()
        }
        animator!!.addListener(object : AnimatorListener() {
            override fun onAnimationEnd(animator: Animator) {
                state = IDLE
                currentStep = step
                invalidate()
            }
        })
        animator!!.duration = animationDuration.toLong()
        animator!!.start()
    }

    private fun getAnimator(step: Int): ValueAnimator? {
        var animator: ValueAnimator? = null
        val i: Int
        if (step > currentStep) {
            when (animationType) {
                ANIMATION_LINE -> {
                    i = step - 1
                    animator = ValueAnimator.ofInt(startLinesX[i], endLinesX[i])
                }
                ANIMATION_CIRCLE -> {
                    animator = ValueAnimator.ofInt(0, selectedCircleRadius)
                }
                ANIMATION_ALL -> {
                    i = step - 1
                    animator = ValueAnimator.ofInt(
                        0,
                        (endLinesX[i] - startLinesX[i] + selectedCircleRadius) / 2
                    )
                }
            }
        } else if (step < currentStep) {
            when (animationType) {
                ANIMATION_LINE -> {
                    i = step
                    animator = ValueAnimator.ofInt(endLinesX[i], startLinesX[i])
                }
                ANIMATION_CIRCLE -> {
                    animator = ValueAnimator.ofInt(0, selectedCircleRadius)
                }
                ANIMATION_ALL -> {
                    i = step
                    animator = ValueAnimator.ofInt(
                        0,
                        (endLinesX[i] - startLinesX[i] + selectedCircleRadius) / 2
                    )
                }
            }
        }
        return animator
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (animator != null && animator!!.isRunning) {
            animator!!.cancel()
        }
    }

    val stepCount: Int
        get() = if (displayMode == DISPLAY_MODE_WITH_TEXT) steps.size else stepsNumber

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureWidth(widthMeasureSpec)
        if (stepCount == 0) {
            setMeasuredDimension(width, 0)
            return
        }
        if (width == 0) {
            setMeasuredDimension(width, 0)
            return
        }
        measureConstraints(width)
        val height = measureHeight(heightMeasureSpec)
        setMeasuredDimension(width, height)
        measureAttributes()
    }

    private fun measureWidth(widthMeasureSpec: Int): Int {
        return MeasureSpec.getSize(widthMeasureSpec)
    }

    private fun measureConstraints(width: Int) {
        constraints = FloatArray(stepCount)
        constraints[0] = (width / stepCount).toFloat()
        for (i in 1 until constraints.size) {
            constraints[i] = constraints[0] * (i + 1)
        }
    }

    private fun measureHeight(heightMeasureSpec: Int): Int {
        val specSize = MeasureSpec.getSize(heightMeasureSpec)
        val specMode = MeasureSpec.getMode(heightMeasureSpec)
        var desiredSize = (paddingTop
                + paddingBottom
                + max(
            selectedCircleRadius,
            doneCircleRadius
        ) * 2 + if (displayMode == DISPLAY_MODE_WITH_TEXT) textPadding else 0)
        if (steps.isNotEmpty()) {
            desiredSize += measureStepsHeight()
        }
        return when (specMode) {
            MeasureSpec.UNSPECIFIED -> desiredSize
            MeasureSpec.AT_MOST -> min(desiredSize, specSize)
            else  -> specSize
        }
    }

    private fun measureStepsHeight(): Int {
        textLayouts = arrayOfNulls(steps.size)
        textPaint.textSize = textSize
        var max = 0
        for (i in steps.indices) {
            val text = steps[i]
            val alignment =
                if (isRtl) Layout.Alignment.ALIGN_OPPOSITE else Layout.Alignment.ALIGN_NORMAL
            textLayouts!![i] = StaticLayout(
                text,
                textPaint,
                measuredWidth / steps.size,
                alignment,
                1f,
                0f,
                true
            )
            val height = textLayouts!![i]!!.height
            max = Math.max(height, max)
        }
        return max
    }

    @get:TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private val isRtl: Boolean
        get() = ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL

    private fun measureAttributes() {
        circlesY = circleY
        if (displayMode == DISPLAY_MODE_NO_TEXT) {
            circlesY += paddingTop
        }
        circlesX = circlePositions
        if (displayMode == DISPLAY_MODE_NO_TEXT) {
            paint.textSize = stepNumberTextSize
        } else {
            paint.textSize = stepNumberTextSize
            paint.textSize = textSize
            textY = circlesY + selectedCircleRadius + textPadding
        }
        measureLines()
    }

    private val circleY: Int
        get() {
            val availableHeight = measuredHeight - paddingTop - paddingBottom
            if (displayMode == DISPLAY_MODE_NO_TEXT) {
                return availableHeight / 2
            }
            val maxItemHeight =
                maxTextHeight + Math.max(selectedCircleRadius, doneCircleRadius) + textPadding
            val additionalPadding = (availableHeight - maxItemHeight) / 2
            return paddingTop + additionalPadding + selectedCircleRadius
        }
    private val maxTextHeight: Int
        get() {
            var max = 0
            if (textLayouts.isEmpty()) {
                return max
            }
            for (tl in textLayouts) {
                max = Math.max(tl!!.height, max)
            }
            return max
        }
    private val circlePositions: IntArray
        get() {
            val stepsCount = stepCount
            val result = IntArray(stepsCount)
            if (result.isEmpty()) {
                return result
            }
            result[0] = startCirclePosition
            if (result.size == 1) {
                return result
            }
            result[stepsCount - 1] = endCirclePosition
            if (result.size < 3) {
                return result
            }
            val spaceLeft =
                if (isRtl) (result[0] - result[stepsCount - 1]).toFloat() else result[stepsCount - 1] - result[0].toFloat()
            val margin = (spaceLeft / (stepsCount - 1)).toInt()
            if (isRtl) {
                for (i in 1 until stepsCount - 1) {
                    result[i] = result[i - 1] - margin
                }
            } else {
                for (i in 1 until stepsCount - 1) {
                    result[i] = result[i - 1] + margin
                }
            }
            return result
        }
    private val startCirclePosition: Int
        get() {
            val result: Int = if (displayMode == DISPLAY_MODE_WITH_TEXT) {
                if (isRtl) {
                    measuredWidth - paddingRight -
                            max(getMaxLineWidth(textLayouts[0]) / 2, selectedCircleRadius)
                } else {
                    paddingLeft + max(
                        getMaxLineWidth(textLayouts[0]) / 2,
                        selectedCircleRadius
                    )
                }
            } else {
                if (isRtl) {
                    measuredWidth - paddingRight - selectedCircleRadius
                } else {
                    paddingLeft + selectedCircleRadius
                }
            }
            return result
        }

    private fun getMaxLineWidth(layout: StaticLayout?): Int {
        val lineCount = layout!!.lineCount
        var max = 0
        for (i in 0 until lineCount) {
            max = max(layout.getLineWidth(i), max.toFloat()).toInt()
        }
        return max
    }

    private val endCirclePosition: Int
        get() {
            val result: Int = if (displayMode == DISPLAY_MODE_WITH_TEXT) {
                if (isRtl) {
                    paddingLeft +
                            max(
                                getMaxLineWidth(last(textLayouts)) / 2,
                                selectedCircleRadius
                            )
                } else {
                    measuredWidth - paddingRight -
                            max(
                                getMaxLineWidth(last(textLayouts)) / 2,
                                selectedCircleRadius
                            )
                }
            } else {
                if (isRtl) {
                    paddingLeft + selectedCircleRadius
                } else {
                    measuredWidth - paddingRight - selectedCircleRadius
                }
            }
            return result
        }

    private fun <T> last(array: Array<T>?): T {
        return array!![array.size - 1]
    }

    private fun measureLines() {
        startLinesX = IntArray(stepCount - 1)
        endLinesX = IntArray(stepCount - 1)
        val padding = stepPadding + selectedCircleRadius
        for (i in 1 until stepCount) {
            if (isRtl) {
                startLinesX[i - 1] = circlesX[i - 1] - padding
                endLinesX[i - 1] = circlesX[i] + padding
            } else {
                startLinesX[i - 1] = circlesX[i - 1] + padding
                endLinesX[i - 1] = circlesX[i] - padding
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (height == 0) return
        val stepSize = stepCount
        if (stepSize == 0) {
            return
        }
        for (i in 0 until stepSize) {
            drawStep(canvas, i, circlesX[i], circlesY)
        }
        for (i in startLinesX.indices) {
            if (state == ANIMATE_STEP_TRANSITION && i == nextAnimatedStep - 1 && nextAnimatedStep > currentStep && (animationType == ANIMATION_LINE || animationType == ANIMATION_ALL)) {
                val animatedX =
                    (startLinesX[i] + animatedFraction * (endLinesX[i] - startLinesX[i])).toInt()
                drawLine(canvas, startLinesX[i] - paddingOffset, animatedX, circlesY, true)
                drawLine(canvas, animatedX, endLinesX[i], circlesY, false)
            } else if (state == ANIMATE_STEP_TRANSITION && i == nextAnimatedStep && nextAnimatedStep < currentStep && (animationType == ANIMATION_LINE || animationType == ANIMATION_ALL)) {
                val animatedX =
                    (endLinesX[i] - animatedFraction * (endLinesX[i] - startLinesX[i])).toInt()
                drawLine(canvas, startLinesX[i], animatedX, circlesY, true)
                drawLine(canvas, animatedX, endLinesX[i], circlesY, false)
            } else if (i < currentStep) {
                drawLine(canvas, startLinesX[i], endLinesX[i], circlesY, true)
            } else {
                drawLine(canvas, startLinesX[i], endLinesX[i], circlesY, false)
            }
        }
    }

    private fun drawStep(canvas: Canvas, step: Int, circleCenterX: Int, circleCenterY: Int) {
        val text = if (displayMode == DISPLAY_MODE_WITH_TEXT) steps[step] else ""
        val isSelected = step == currentStep
        val isDone = if (done) step <= currentStep else step < currentStep
        val number = (step + 1).toString()
        val strokeWidth = 2.dp.toFloat()
        if (isSelected && !isDone) {
            paint.color = selectedCircleColor
            val radius: Int
            if (state == ANIMATE_STEP_TRANSITION
                && (animationType == ANIMATION_CIRCLE || animationType == ANIMATION_ALL)
                && nextAnimatedStep < currentStep
            ) {
                radius = if (!nextStepCircleEnabled || nextStepCircleColor == 0) {
                    (selectedCircleRadius - selectedCircleRadius * animatedFraction).toInt()
                } else {
                    selectedCircleRadius
                }
                if (nextStepCircleEnabled && nextStepCircleColor != 0) {
                    paint.color = ColorUtils.blendARGB(
                        selectedCircleColor,
                        nextStepCircleColor,
                        animatedFraction
                    )
                }
            } else {
                radius = selectedCircleRadius
            }
            canvas.drawCircle(
                circleCenterX.toFloat(),
                circleCenterY.toFloat(),
                (radius - paddingOffset).toFloat(),
                paint
            )
            paint.color = Color.WHITE
            canvas.drawCircle(
                circleCenterX.toFloat(),
                circleCenterY.toFloat(),
                radius - (strokeWidth + paddingOffset),
                paint
            )
            if (numberCurrentStepEnabled) {
                paint.color = selectedStepNumberColor
                paint.textSize = stepNumberTextSize
                paint.style = Paint.Style.FILL_AND_STROKE
                paint.strokeWidth = 0f
                drawNumber(canvas, number, circleCenterX, paint)
            }
            textPaint.textSize = textSize
            textPaint.color = selectedTextColor
            drawText(canvas, text, textY, step)
        } else if (isDone) {
            paint.color = doneCircleColor
            paint.style = Paint.Style.FILL
            canvas.drawCircle(
                circleCenterX.toFloat(),
                circleCenterY.toFloat(),
                doneCircleRadius.toFloat(),
                paint
            )
            drawCheckMark(canvas, circleCenterX, circleCenterY)
            if (state == ANIMATE_STEP_TRANSITION && step == nextAnimatedStep && nextAnimatedStep < currentStep) {
                paint.color = selectedTextColor
                val alpha = max(Color.alpha(doneTextColor), (animatedFraction * 255).toInt())
                paint.alpha = alpha
            } else {
                paint.color = doneTextColor
            }
            textPaint.textSize = textSize
            textPaint.color = doneTextColor
            drawText(canvas, text, textY, step)
        } else {
            if (state == ANIMATE_STEP_TRANSITION && step == nextAnimatedStep && nextAnimatedStep > currentStep) {
                if (animationType == ANIMATION_CIRCLE || animationType == ANIMATION_ALL) {
                    if (nextStepCircleEnabled && nextStepCircleColor != 0) {
                        paint.color = ColorUtils.blendARGB(
                            nextStepCircleColor,
                            selectedCircleColor,
                            animatedFraction
                        )
                        canvas.drawCircle(
                            circleCenterX.toFloat(),
                            circleCenterY.toFloat(),
                            selectedCircleRadius.toFloat(),
                            paint
                        )
                    } else {
                        val animatedRadius = (selectedCircleRadius * animatedFraction).toInt()
                        paint.color = selectedCircleColor
                        canvas.drawCircle(
                            circleCenterX.toFloat(),
                            circleCenterY.toFloat(),
                            animatedRadius.toFloat(),
                            paint
                        )
                    }
                }
                if (numberCurrentStepEnabled) {
                    if (animationType != ANIMATION_NONE) {
                        if (animationType == ANIMATION_CIRCLE || animationType == ANIMATION_ALL) {
                            paint.color = selectedStepNumberColor
                            val alpha = (animatedFraction * 255).toInt()
                            paint.alpha = alpha
                            paint.textSize = stepNumberTextSize * animatedFraction
                        } else {
                            paint.textSize = stepNumberTextSize
                            paint.color = nextTextColor
                        }
                    } else {
                        paint.textSize = stepNumberTextSize
                        paint.color = nextTextColor
                    }
                    drawNumber(canvas, number, circleCenterX, paint)
                }
                textPaint.textSize = textSize
                textPaint.color = nextTextColor
                val alpha = max(
                    Color.alpha(nextTextColor).toFloat(),
                    animatedFraction * 255
                ).toInt()
                textPaint.alpha = alpha
                drawText(canvas, text, textY, step)
            } else {
                if (nextStepCircleEnabled && nextStepCircleColor != 0) {
                    paint.color = nextStepCircleColor
                    canvas.drawCircle(
                        circleCenterX.toFloat(),
                        circleCenterY.toFloat(),
                        (selectedCircleRadius - paddingOffset).toFloat(),
                        paint
                    )
                    paint.color = Color.WHITE
                    canvas.drawCircle(
                        circleCenterX.toFloat(),
                        circleCenterY.toFloat(),
                        selectedCircleRadius - (strokeWidth + paddingOffset),
                        paint
                    )
                }
                if (numbersNextStepEnabled) {
                    paint.color = nextTextColor
                    paint.textSize = stepNumberTextSize
                    drawNumber(canvas, number, circleCenterX, paint)
                }
                textPaint.textSize = textSize
                textPaint.color = nextTextColor
                drawText(canvas, text, textY, step)
            }
        }
    }

    private fun drawNumber(canvas: Canvas, number: String, circleCenterX: Int, paint: Paint) {
        paint.getTextBounds(number, 0, number.length, bounds)
        val y = circlesY + bounds.height() / 2f - bounds.bottom
        canvas.drawText(number, circleCenterX.toFloat(), y, paint)
    }

    private fun drawText(canvas: Canvas, text: String, y: Int, step: Int) {
        if (text.isEmpty()) {
            return
        }
        val layout = textLayouts!![step]
        canvas.save()
        canvas.translate(circlesX[step].toFloat(), y.toFloat())
        layout!!.draw(canvas)
        canvas.restore()
    }

    private fun drawCheckMark(canvas: Canvas, circleCenterX: Int, circleCenterY: Int) {
        paint.color = doneStepMarkColor
        val width = stepNumberTextSize * 0.1f
        paint.strokeWidth = width
        val bounds = Rect(
            (circleCenterX - width * 4.5).toInt(),
            (circleCenterY - width * 3.5).toInt(),
            (circleCenterX + width * 4.5).toInt(),
            (circleCenterY + width * 3.5).toInt()
        )
        canvas.drawLine(
            bounds.left + 0.5f * width,
            bounds.bottom - 3.25f * width,
            bounds.left + 3.25f * width,
            bounds.bottom - 0.75f * width, paint
        )
        canvas.drawLine(
            bounds.left + 2.75f * width,
            bounds.bottom - 0.75f * width,
            bounds.right - 0.375f * width,
            bounds.top + 0.75f * width, paint
        )
    }

    private fun drawLine(canvas: Canvas, startX: Int, endX: Int, centerY: Int, highlight: Boolean) {
        if (highlight) {
            paint.color = doneStepLineColor
            paint.strokeWidth = stepLineWidth.toFloat()
            canvas.drawLine(
                startX.toFloat(),
                centerY.toFloat(),
                (endX + paddingOffset).toFloat(),
                centerY.toFloat(),
                paint
            )
        } else {
            paint.color = nextStepLineColor
            paint.strokeWidth = stepLineWidth.toFloat()
            canvas.drawLine(
                (startX - paddingOffset).toFloat(),
                centerY.toFloat(),
                (endX + paddingOffset).toFloat(),
                centerY.toFloat(),
                paint
            )
        }
    }

    inner class State {
        private var steps: List<String>? = null
        private var stepsNumber = 0

        @AnimationType
        private var animationType = this@OceanStepView.animationType

        @ColorInt
        private var selectedCircleColor = this@OceanStepView.selectedCircleColor

        @Dimension
        private var selectedCircleRadius = this@OceanStepView.selectedCircleRadius

        @ColorInt
        private var selectedTextColor = this@OceanStepView.selectedTextColor

        @ColorInt
        private var doneCircleColor = this@OceanStepView.doneCircleColor

        @Dimension
        private var doneCircleRadius = this@OceanStepView.doneCircleRadius

        @ColorInt
        private var doneTextColor = this@OceanStepView.doneTextColor

        @ColorInt
        private var nextTextColor = this@OceanStepView.nextTextColor

        @Dimension
        private var stepPadding = this@OceanStepView.stepPadding

        @ColorInt
        private var nextStepLineColor = this@OceanStepView.nextStepLineColor

        @ColorInt
        private var doneStepLineColor = this@OceanStepView.doneStepLineColor

        @Dimension
        private var stepLineWidth = this@OceanStepView.stepLineWidth

        @Dimension(unit = Dimension.SP)
        private var textSize = this@OceanStepView.textSize

        @Dimension
        private var textPadding = this@OceanStepView.textPadding

        @ColorInt
        private var selectedStepNumberColor = this@OceanStepView.selectedStepNumberColor

        @Dimension(unit = Dimension.SP)
        private var stepNumberTextSize = this@OceanStepView.stepNumberTextSize

        @ColorInt
        private var doneStepMarkColor = this@OceanStepView.doneStepMarkColor
        private var animationDuration = this@OceanStepView.animationDuration
        private var nextStepCircleEnabled = this@OceanStepView.nextStepCircleEnabled

        @ColorInt
        private var nextStepCircleColor = this@OceanStepView.nextStepCircleColor
        private var numberCurrentStepEnabled = this@OceanStepView.numberCurrentStepEnabled
        private var numbersNextStepEnabled = this@OceanStepView.numbersNextStepEnabled
        private var typeface = paint.typeface
        fun animationType(@AnimationType animationType: Int): State {
            this.animationType = animationType
            return this
        }

        fun selectedCircleColor(@ColorInt selectedCircleColor: Int): State {
            this.selectedCircleColor = selectedCircleColor
            return this
        }

        fun selectedCircleRadius(@Dimension selectedCircleRadius: Int): State {
            this.selectedCircleRadius = selectedCircleRadius
            return this
        }

        fun selectedTextColor(@ColorInt selectedTextColor: Int): State {
            this.selectedTextColor = selectedTextColor
            return this
        }

        fun doneCircleColor(@ColorInt doneCircleColor: Int): State {
            this.doneCircleColor = doneCircleColor
            return this
        }

        fun doneCircleRadius(@Dimension doneCircleRadius: Int): State {
            this.doneCircleRadius = doneCircleRadius
            return this
        }

        fun doneTextColor(@ColorInt doneTextColor: Int): State {
            this.doneTextColor = doneTextColor
            return this
        }

        fun nextTextColor(@ColorInt nextTextColor: Int): State {
            this.nextTextColor = nextTextColor
            return this
        }

        fun stepPadding(@Dimension stepPadding: Int): State {
            this.stepPadding = stepPadding
            return this
        }

        fun nextStepLineColor(@ColorInt nextStepLineColor: Int): State {
            this.nextStepLineColor = nextStepLineColor
            return this
        }

        fun doneStepLineColor(@ColorInt doneStepLineColor: Int): State {
            this.doneStepLineColor = doneStepLineColor
            return this
        }

        fun stepLineWidth(@Dimension stepLineWidth: Int): State {
            this.stepLineWidth = stepLineWidth
            return this
        }

        fun textSize(@Dimension(unit = Dimension.SP) textSize: Int): State {
            this.textSize = textSize.toFloat()
            return this
        }

        fun textPadding(@Dimension textPadding: Int): State {
            this.textPadding = textPadding
            return this
        }

        fun selectedStepNumberColor(@ColorInt selectedStepNumberColor: Int): State {
            this.selectedStepNumberColor = selectedStepNumberColor
            return this
        }

        fun stepNumberTextSize(@Dimension(unit = Dimension.SP) stepNumberTextSize: Int): State {
            this.stepNumberTextSize = stepNumberTextSize.toFloat()
            return this
        }

        fun doneStepMarkColor(@ColorInt doneStepMarkColor: Int): State {
            this.doneStepMarkColor = doneStepMarkColor
            return this
        }

        fun animationDuration(animationDuration: Int): State {
            this.animationDuration = animationDuration
            return this
        }

        fun steps(steps: List<String>?): State {
            this.steps = steps
            return this
        }

        fun stepsNumber(stepsNumber: Int): State {
            this.stepsNumber = stepsNumber
            return this
        }

        fun typeface(typeface: Typeface): State {
            this.typeface = typeface
            return this
        }

        fun nextStepCircleEnabled(enabled: Boolean): State {
            this.nextStepCircleEnabled = enabled
            return this
        }

        fun nextStepCircleColor(@ColorInt color: Int): State {
            this.nextStepCircleColor = color
            return this
        }

        fun numberCurrentStepEnabled(enabled: Boolean): State {
            this.numberCurrentStepEnabled = enabled
            return this
        }

        fun numbersNextStepEnabled(enabled: Boolean): State {
            this.numbersNextStepEnabled = enabled
            return this
        }

        fun commit() {
            this@OceanStepView.animationType = animationType
            this@OceanStepView.selectedTextColor = selectedTextColor
            this@OceanStepView.selectedCircleRadius = selectedCircleRadius
            this@OceanStepView.selectedCircleColor = selectedCircleColor
            this@OceanStepView.doneCircleColor = doneCircleColor
            this@OceanStepView.doneCircleRadius = doneCircleRadius
            this@OceanStepView.doneTextColor = doneTextColor
            this@OceanStepView.nextTextColor = nextTextColor
            this@OceanStepView.stepPadding = stepPadding
            this@OceanStepView.nextStepLineColor = nextStepLineColor
            this@OceanStepView.doneStepLineColor = doneStepLineColor
            this@OceanStepView.stepLineWidth = stepLineWidth
            this@OceanStepView.textSize = textSize
            this@OceanStepView.textPadding = textPadding
            this@OceanStepView.selectedStepNumberColor = selectedStepNumberColor
            this@OceanStepView.stepNumberTextSize = stepNumberTextSize
            this@OceanStepView.doneStepMarkColor = doneStepMarkColor
            this@OceanStepView.animationDuration = animationDuration
            setTypeface(typeface)
            this@OceanStepView.nextStepCircleEnabled = nextStepCircleEnabled
            this@OceanStepView.nextStepCircleColor = nextStepCircleColor
            this@OceanStepView.numberCurrentStepEnabled = numberCurrentStepEnabled
            this@OceanStepView.numbersNextStepEnabled = numbersNextStepEnabled
            if (steps != null && this@OceanStepView.steps != steps) {
                setSteps(steps)
            } else if (stepsNumber != 0 && stepsNumber != this@OceanStepView.stepsNumber) {
                setStepsNumber(stepsNumber)
            } else {
                this@OceanStepView.invalidate()
            }
        }
    }

    companion object {
        const val ANIMATION_LINE = 0
        const val ANIMATION_CIRCLE = 1
        const val ANIMATION_ALL = 2
        const val ANIMATION_NONE = 3
        const val DISPLAY_MODE_WITH_TEXT = 0
        const val DISPLAY_MODE_NO_TEXT = 1
        private const val ANIMATE_STEP_TRANSITION = 0
        private const val IDLE = 1
        private const val START_STEP = 0
    }

    init {
        paint.textAlign = Paint.Align.CENTER
        textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        textPaint.textAlign = Paint.Align.CENTER
        applyStyles(context, attrs, defStyleAttr)
        drawEditMode()
    }
}
