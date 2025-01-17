package br.com.useblu.oceands.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun String.isValidDate(pattern: String): Boolean {
    val format = SimpleDateFormat(
        pattern,
        Locale.getDefault()
    ).apply {
        isLenient = false
    }

    return runCatching { format.parse(this) }.isSuccess
}

fun String.toDate(pattern: String = "dd/MM/yyyy") = runCatching {
    SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
}.getOrNull()