package br.com.useblu.oceands.components.compose.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.shimmeringBrush
import br.com.useblu.oceands.model.compose.OceanBalanceBluModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.utils.FormatTypes
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanBalanceBluCardCollapsedPreview() {
    val model = OceanBalanceBluModel(
        firstLabel = "First Label",
        firstValue = "First Value",
        secondLabel = "Second Label",
        secondValue = "Second Value",
        thirdLabel = "Third Label",
        thirdValue = "Third Value",
        buttonCta = "Extrato",
        buttonDescription = "Confira tudo o que entrou e saiu da sua Conta Digital Blu"
    )

    var isContentHidden by remember { mutableStateOf(false) }

    OceanBalanceBluCardCollapsed(
        model = model,
        isLoading = false,
        isContentHidden = isContentHidden,
        onClickExpand = {},
        onClickHideContent = { isContentHidden = !isContentHidden }
    )
}
@Composable
fun OceanBalanceBluCardCollapsed(
    modifier: Modifier = Modifier,
    model: OceanBalanceBluModel,
    isContentHidden: Boolean,
    isLoading: Boolean,
    shimmeringBrush: Brush = shimmeringBrush(),
    onClickExpand: () -> Unit,
    onClickHideContent: () -> Unit
) {
    Row(
        modifier = modifier
            .background(color = Color(0xFF2244E8))
            .height(58.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .height(42.dp)
                .width(28.dp)
                .clickable {
                    onClickHideContent()
                }
        ) {
            val icon = if (isContentHidden) {
                OceanIcons.EYE_OFF_OUTLINE
            } else OceanIcons.EYE_OUTLINE
            OceanIcon(
                iconType = icon,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(20.dp),
                tint = OceanColors.brandPrimaryUp
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = model.firstLabel,
                fontSize = 12.sp,
                modifier = Modifier.height(18.dp),
                color = OceanColors.brandPrimaryUp
            )

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .width(72.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(shimmeringBrush)
                        .padding(vertical = 8.dp)
                )
            } else {
                val text = if (isContentHidden) {
                    FormatTypes.FORMAT_VALUE_WITH_SYMBOL_HIDDEN
                } else {
                    FormatTypes.FORMAT_VALUE_WITH_SYMBOL
                }.format(model.firstValue)

                Text(
                    text = text,
                    fontSize = 20.sp,
                    modifier = Modifier.height(30.dp),
                    color = OceanColors.interfaceLightPure,
                    fontFamily = OceanFontFamily.BaseBold
                )
            }
        }


        Box(modifier = Modifier
            .size(40.dp)
            .clickable { onClickExpand() }
        ) {
            OceanIcon(
                iconType = OceanIcons.CHEVRON_DOWN_OUTLINE,
                tint = OceanColors.brandPrimaryUp,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterEnd)
            )
        }

        Spacer(modifier = Modifier.size(16.dp))
    }
}