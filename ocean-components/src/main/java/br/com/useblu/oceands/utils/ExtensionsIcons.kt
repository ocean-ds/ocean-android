package br.com.useblu.oceands.utils

fun String.toOceanIcon(): Int {
    return OceanIcons
        .fromToken(
            this.lowercase()
        ).icon
}

