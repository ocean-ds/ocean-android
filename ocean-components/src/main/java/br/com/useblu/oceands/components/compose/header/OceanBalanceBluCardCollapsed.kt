package br.com.useblu.oceands.components.compose.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.shimmeringBrush
import br.com.useblu.oceands.model.compose.OceanBalanceBluModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
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
        isContentHidden = isContentHidden,
        isLoading = false,
        onClickToggleHideContent = { isContentHidden = !isContentHidden }
    )
}
@Composable
fun OceanBalanceBluCardCollapsed(
    modifier: Modifier = Modifier,
    model: OceanBalanceBluModel,
    isContentHidden: Boolean,
    isLoading: Boolean,
    shimmeringBrush: Brush = shimmeringBrush(),
    onClickToggleHideContent: () -> Unit
) {
    Row(
        modifier = modifier
            .background(color = Color(0xFF2244E8))
            .height(58.dp)
            .padding(horizontal =  OceanSpacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BalanceCardMainValues(onClickToggleHideContent, isContentHidden, model, isLoading, shimmeringBrush)

        Box(modifier = Modifier
            .size(40.dp)
            .clickable { model.onClickExpandScroll() }
        ) {
            OceanIcon(
                iconType = OceanIcons.CHEVRON_DOWN_OUTLINE,
                tint = OceanColors.brandPrimaryUp,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}