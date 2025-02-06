package br.com.useblu.oceands.components.compose.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanGroupCta
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTooltip
import br.com.useblu.oceands.model.chart.OceanChartItem
import br.com.useblu.oceands.model.chart.OceanChartModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle


@Preview
@Composable
private fun OceanChartCardPreview() {
    Column(
        modifier = Modifier
            .background(OceanColors.interfaceLightPure),
    ) {
        OceanChartCard(
            modifier = Modifier,
            title = "Title",
            subtitle = "Subtitle",
            model = donutModel,
            actionTitle = "Call to Action",
            callToAction = {}
        )
    }
}

@Composable
fun OceanChartCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    showProgress: Boolean = false,
    model: OceanChartModel,
    actionTitle: String? = null,
    callToAction: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(horizontal = OceanSpacing.xs)
        ) {
            OceanText(text = title, style = OceanTextStyle.heading4)

            OceanSpacing.StackXXXS()

            if (!subtitle.isNullOrBlank()) {
                OceanText(text = subtitle, style = OceanTextStyle.description)
            }

            OceanSpacing.StackXS()

            OceanDonut(model = model, modifier = Modifier.height(180.dp))

            OceanSpacing.StackXS()

            OceanChardLegend(model)
        }

        if (actionTitle != null) {
            OceanSpacing.StackSM()

            OceanDivider()

            OceanGroupCta(
                title = actionTitle,
                onClick = { callToAction?.invoke() },
                showProgress = showProgress
            )
        }
    }
}


@Composable
fun OceanChardLegend(
    model: OceanChartModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        model.items.forEachIndexed { index, it ->
            if (index != 0) {
                OceanDivider()
            }

            OceanChartLegendItem(
                model = it,
                onClick = {
                    if (model.items.all { it.selected }) {
                        model.onItemSelected(index)
                    } else {
                        if (it.selected) {
                            model.onNothingSelected()
                        } else {
                            model.onItemSelected(index)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun OceanChartLegendItem(
    onClick: () -> Unit,
    model: OceanChartItem
) {
    val alpha = if (model.selected) 1f else 0.4f

    Row(
        modifier = Modifier
            .alpha(alpha)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = colorResource(id = model.color),
                            shape = CircleShape
                        )
                )

                OceanSpacing.StackXXS()

                OceanText(text = model.title, style = OceanTextStyle.description)

                OceanSpacing.StackXXXS()

                if (model.information.isNotBlank()) {
                    OceanTooltip(model.information) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ocean_icon_info_solid),
                            tint = OceanColors.interfaceLightDeep,
                            contentDescription = null
                        )
                    }
                }
            }

            OceanSpacing.StackXXXS()

            OceanText(
                modifier = Modifier.padding(start = 16.dp),
                text = model.subtitle,
                style = OceanTextStyle.caption
            )
        }

        OceanText(text = model.valueFormatted, style = OceanTextStyle.description)
    }
}