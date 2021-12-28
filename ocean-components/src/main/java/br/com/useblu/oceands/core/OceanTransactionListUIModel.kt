package br.com.useblu.oceands.core

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
): Parcelable
