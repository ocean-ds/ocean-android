package br.com.useblu.oceands.model

import android.graphics.drawable.Drawable

data class OceanTransactionListUIModel(
    val icon: Drawable? = null,
    val highlightedLabel: String? = null,
    val primaryLabel: String? = null,
    val secondaryLabel: String? = null,
    val dimmedLabel: String? = null,
    val value: String? = null,
    val valueIsHighlighted: Boolean = false,
    val valueWithSignal: Boolean = true,
    val tagTitle: String? = null,
    val time: String? = null
)