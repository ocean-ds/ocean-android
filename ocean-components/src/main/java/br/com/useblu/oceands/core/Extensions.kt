package br.com.useblu.oceands.core

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun String.formatterDateBR(): String {
    val inFormat = SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US)
    val outFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = inFormat.parse(this)
    return date?.let { outFormat.format(it) } ?: ""
}

fun Date.oceanFormatDefault(): String = oceanFormat("dd/MM/yyyy")

fun Date.oceanFormat(pattern: String): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

fun Double.oceanFormatWithCurrency() = FormatTypes.FORMAT_VALUE_WITH_SYMBOL.format(this.toString())

fun String.clearSpacing() = replace("\\s+".toRegex(), "").trim()

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

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