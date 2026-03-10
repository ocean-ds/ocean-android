package br.com.useblu.oceands.components.compose.internalpageheader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanStep
import br.com.useblu.oceands.components.compose.OceanTextNotBlank
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.internalpageheader.model.OceanInternalPageHeaderStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

@Composable
fun OceanInternalPageHeader(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    style: OceanInternalPageHeaderStyle = OceanInternalPageHeaderStyle.Default
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = OceanSpacing.xs)
            .padding(bottom = OceanSpacing.xs),
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
    ) {
        when (style) {
            is OceanInternalPageHeaderStyle.Default -> {
                if (title.isNotBlank() || subtitle.isNotBlank()) {
                    TitleSubtitleContent(
                        title = title,
                        subtitle = subtitle
                    )
                }
            }
            is OceanInternalPageHeaderStyle.WithSteps -> {
                OceanStep(
                    currentStep = style.currentStep,
                    stepCount = style.stepCount
                )
                if (title.isNotBlank() || subtitle.isNotBlank()) {
                    TitleSubtitleContent(
                        title = title,
                        subtitle = subtitle
                    )
                }
            }
        }
    }
}

@Composable
private fun TitleSubtitleContent(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        OceanTextNotBlank(
            text = title,
            style = OceanTextStyle.heading3,
            color = OceanColors.interfaceDarkDeep
        )
        OceanTextNotBlank(
            text = subtitle,
            style = OceanTextStyle.description,
            color = OceanColors.interfaceDarkDown
        )
    }
}

@Preview
@Composable
private fun OceanInternalPageHeaderPreview() = OceanTheme {
    Column(
        modifier = Modifier.background(color = OceanColors.interfaceLightPure)
    ) {
        OceanInternalPageHeader(
            title = "Title",
            subtitle = "Subtitle"
        )
        OceanDivider()
        OceanInternalPageHeader(
            title = "Title Only"
        )
        OceanDivider()
        OceanInternalPageHeader(
            subtitle = "Subtitle Only"
        )
        OceanDivider()
        OceanInternalPageHeader(
            title = "Title",
            subtitle = "Subtitle",
            style = OceanInternalPageHeaderStyle.WithSteps(
                currentStep = 1,
                stepCount = 3
            )
        )
        OceanDivider()
        OceanInternalPageHeader(
            title = "Title",
            style = OceanInternalPageHeaderStyle.WithSteps(
                currentStep = 2,
                stepCount = 3
            )
        )
    }
}
