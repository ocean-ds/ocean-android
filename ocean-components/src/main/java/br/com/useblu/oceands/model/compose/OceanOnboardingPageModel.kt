package br.com.useblu.oceands.model.compose

import androidx.annotation.DrawableRes

data class OceanOnboardingPageModel(
    val title: String,
    val subtitle: String,
    val caption: String = "",
    val link: Pair<String, () -> Unit>? = null,
    @DrawableRes val image: Int
)
