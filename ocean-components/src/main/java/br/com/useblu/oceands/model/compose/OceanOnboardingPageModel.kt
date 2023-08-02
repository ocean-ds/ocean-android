package br.com.useblu.oceands.model.compose

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class OceanOnboardingPageModel(
    val title: String,
    val subtitle: String,
    @DrawableRes val image: Int
)
