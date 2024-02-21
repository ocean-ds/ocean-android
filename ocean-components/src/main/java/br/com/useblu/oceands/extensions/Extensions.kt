package br.com.useblu.oceands.extensions

import android.content.Context
import android.content.res.Resources
import android.text.Spanned
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.Transformation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import br.com.useblu.oceands.utils.FormatTypes
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.truncate

fun String.formatterDateBR(): String {
    val inFormat = SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US)
    val outFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = inFormat.parse(this)
    return date?.let { outFormat.format(it) } ?: ""
}

fun Date.oceanFormatDefault(): String = oceanFormat("dd/MM/yyyy")

fun Date.oceanFormat(pattern: String): String {
    val format = SimpleDateFormat(pattern, Locale.getDefault()).format(this)

    val monthRegex = "[A-Z][a-z]{3,8}".toRegex()

    return monthRegex.replace(format) {
        it.value.first().uppercase() + it.value.substring(1).lowercase()
    }.replace(".", "")
}

fun Double.oceanFormatWithCurrency(): String {
    val final = BigDecimal(this).setScale(2, RoundingMode.HALF_EVEN)
    return FormatTypes.FORMAT_VALUE_WITH_SYMBOL.format(final.toString())
}

fun String.clearSpacing() = replace("\\s+".toRegex(), "").trim()

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.dpFloat: Float
    get() = this * Resources.getSystem().displayMetrics.density

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Context.getSupportFragmentManager() = when (this) {
    is Fragment -> this.requireActivity().supportFragmentManager
    else -> (this as AppCompatActivity).supportFragmentManager
}

fun MenuItem.setTitleColor(color: Int) {
    val hexColor = Integer.toHexString(color).uppercase(Locale.ROOT).substring(2)
    val html = "<font color='#$hexColor'>$title</font>"
    this.title = html.parseAsHtml()
}

fun String.parseAsHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun View.animateFadeIn() {
    this.clearAnimation()
    AlphaAnimation(0.0f, 1.0f).run {
        interpolator = DecelerateInterpolator() //add this
        duration = 150
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                this@animateFadeIn.visibility = View.VISIBLE
            }
        })
        this@animateFadeIn.animation = this
        this@animateFadeIn.animate()
    }
}

fun View.animateFadeOut() {
    this.clearAnimation()
    AlphaAnimation(1.0f, 0.0f).run {
        interpolator = DecelerateInterpolator() //add this
        duration = 150
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                this@animateFadeOut.visibility = View.GONE
            }
        })
        this@animateFadeOut.animation = this
        this@animateFadeOut.animate()
    }
}
fun View.animateHeight(targetHeight: Int, durationMs: Int = 2000) {
    ShowAnim(this, targetHeight, durationMs).startAnimation()
}

class ShowAnim(
    private val view: View,
    private val targetHeight: Int,
    private val durationMs: Int = 2000
): Animation() {

    init {
        duration = this.durationMs.toLong()
    }
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val currentHeight = view.layoutParams.height
        val delta = targetHeight - currentHeight
        val targetHeight = currentHeight + (delta * interpolatedTime)
        view.layoutParams.height = (targetHeight).toInt()
        view.requestLayout()
    }

    override fun willChangeBounds() = true

    fun startAnimation() {
        view.startAnimation(this)
    }
}
