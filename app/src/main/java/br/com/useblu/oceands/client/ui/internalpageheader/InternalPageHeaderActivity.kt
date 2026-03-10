package br.com.useblu.oceands.client.ui.internalpageheader

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.internalpageheader.OceanInternalPageHeader
import br.com.useblu.oceands.components.compose.internalpageheader.model.OceanInternalPageHeaderStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

class InternalPageHeaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OceanTheme {
                InternalPageHeaderDemo()
            }
        }
    }
}

@Composable
private fun InternalPageHeaderDemo() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(OceanColors.interfaceLightPure)
    ) {
        SectionLabel("Default - Title + Subtitle")
        OceanInternalPageHeader(
            title = "Title",
            subtitle = "Subtitle"
        )

        SectionLabel("Default - Title Only")
        OceanInternalPageHeader(
            title = "Title Only"
        )

        SectionLabel("Default - Subtitle Only")
        OceanInternalPageHeader(
            subtitle = "Subtitle Only"
        )

        SectionLabel("WithSteps - Step 1 of 3, Title + Subtitle")
        OceanInternalPageHeader(
            title = "Title",
            subtitle = "Subtitle",
            style = OceanInternalPageHeaderStyle.WithSteps(
                currentStep = 1,
                stepCount = 3
            )
        )

        SectionLabel("WithSteps - Step 2 of 3, Title Only")
        OceanInternalPageHeader(
            title = "Title",
            style = OceanInternalPageHeaderStyle.WithSteps(
                currentStep = 2,
                stepCount = 3
            )
        )

        SectionLabel("WithSteps - Step 3 of 3, Complete")
        OceanInternalPageHeader(
            title = "Final Step",
            subtitle = "You have completed all steps",
            style = OceanInternalPageHeaderStyle.WithSteps(
                currentStep = 3,
                stepCount = 3
            )
        )

        Spacer(modifier = Modifier.height(OceanSpacing.xs))
    }
}

@Composable
private fun SectionLabel(text: String) {
    OceanText(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .background(OceanColors.interfaceLightDown.copy(alpha = 0.3f))
            .padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xxs),
        style = OceanTextStyle.caption,
        color = OceanColors.interfaceDarkUp
    )
}

@Preview
@Composable
private fun InternalPageHeaderDemoPreview() {
    OceanTheme {
        InternalPageHeaderDemo()
    }
}
