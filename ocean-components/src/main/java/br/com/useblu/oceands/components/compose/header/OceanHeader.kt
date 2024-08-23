package br.com.useblu.oceands.components.compose.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.header.model.OceanHeaderStyle
import br.com.useblu.oceands.model.compose.OceanBalanceBluModel
import br.com.useblu.oceands.model.compose.OceanBalanceOthersModel
import br.com.useblu.oceands.model.compose.OceanHeaderAppModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing


private val modelPreview = OceanHeaderAppModel(
    clientName = "Fabricante 3 - Fluxo dia atual teste 2",
    formattedCnpj = "32.677.554/0001-14",
    balanceBluModel = OceanBalanceBluModel(
        firstLabel = "First Label",
        firstValue = "-35,63",
        secondLabel = "Second Label",
        secondValue = "10,00",
        thirdLabel = "Third Label",
        thirdValue = "50,00",
        buttonCta = "Extrato",
        buttonDescription = "Confira tudo o que entrou e saiu da sua Conta Digital Blu",
        onClickButton = {
            println("Click blu")
        }
    ),
    balanceOthersModel = OceanBalanceOthersModel(
        title = "Saldo em Outras maquininhas",
        description = "Receba na Blu as vendas feitas nas suas outras maquininhas",
        buttonCta = "Extrato",
        buttonCtaCollapsed = "Extrato",
        onClickButton = {
            println("Click others")
        }
    ),
    isLoading = false,
    isHeaderCollapsed = false
)

@Composable
@Preview
fun OceanHeaderMinimalPreview() {
    val modelPreview = remember {
        mutableStateOf(modelPreview)
    }

    OceanHeader(
        headerModel = modelPreview.value,
        style = OceanHeaderStyle.Minimal
    )
}

@Composable
@Preview
fun OceanHeaderSmallPreview() {
    var modelPreview by remember {
        mutableStateOf(
            modelPreview.copy(isHeaderCollapsed = true)
        )
    }

    LaunchedEffect(key1 = true) {
        modelPreview = modelPreview.copy(
            balanceBluModel = modelPreview.balanceBluModel.copy(
                onClickExpandScroll = {
                    modelPreview = modelPreview.copy(
                        isHeaderCollapsed = false
                    )
                }
            )
        )
    }

    OceanTheme {
        Column {
            OceanHeader(
                headerModel = modelPreview,
                style = OceanHeaderStyle.Small
            )

            OceanSpacing.StackXS()

            Button(
                onClick = {
                    modelPreview =
                        modelPreview.copy(
                            isHeaderCollapsed = !modelPreview.isHeaderCollapsed
                        )
                }
            ) {
                Text(text = "Toggle collapsed")
            }
        }
    }
}

@Composable
@Preview
fun OceanHeaderDefaultPreview() {
    val modelPreview = remember {
        mutableStateOf(
            modelPreview.copy(isHeaderCollapsed = false)
        )
    }

    OceanHeader(
        headerModel = modelPreview.value,
        style = OceanHeaderStyle.Small
    )
}


@Composable
fun OceanHeader(
    modifier: Modifier = Modifier,
    headerModel: OceanHeaderAppModel,
    style: OceanHeaderStyle = OceanHeaderStyle.Small,
) {
    Column(
        modifier = modifier
            .background(color = OceanColors.brandPrimaryPure),
    ) {
        MinimalHeader(model = headerModel)

        if (style is OceanHeaderStyle.Small) {
            SmallHeader(model = headerModel)
        }
    }
}

@Composable
private fun SmallHeader(
    model: OceanHeaderAppModel
) {
    var isContentHidden by remember { mutableStateOf(false) }

    when {
        model.hideBalance -> return
        model.isHeaderCollapsed -> {
            OceanBalanceBluCardCollapsed(
                model = model.balanceBluModel,
                isContentHidden = isContentHidden,
                isLoading = model.isLoading,
                onClickToggleHideContent = {
                    isContentHidden = !isContentHidden
                },
            )
        }

        else -> {
            Column(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.xs)
                    .padding(top = OceanSpacing.xxxs, bottom = OceanSpacing.xs)
            ) {
                OceanBalanceBluCard(
                    model = model.balanceBluModel,
                    isLoading = model.isLoading,
                    isCurrentPage = true,
                    isContentHidden = isContentHidden,
                    onClickToggleHideContent = {
                        isContentHidden = !isContentHidden
                    },
                )
            }
        }
    }
}