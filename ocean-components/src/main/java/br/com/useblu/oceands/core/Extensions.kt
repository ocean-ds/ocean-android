package br.com.useblu.oceands.core

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
