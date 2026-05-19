package br.com.useblu.oceands.model.compose

import androidx.compose.ui.Alignment
import br.com.useblu.oceands.components.compose.OceanTagStyle

/**
 * Configuration for the Highlight Corner Tag displayed by [OceanCardListItem].
 *
 * Wraps an [OceanTagStyle] (typically with [OceanTagLayout.Corner]) plus the
 * card alignment for the tag overlay. Defaults to top-end per Figma spec
 * (PRD Pagnet#16178, Jira MR-490).
 */
data class OceanCardCornerTag(
    val tag: OceanTagStyle,
    val alignment: Alignment = Alignment.TopEnd
)
