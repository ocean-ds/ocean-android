package br.com.useblu.oceands.client.ui.blubalance

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanButtonModel
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.blubalance.OceanBluBalance
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBalanceItemAction
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemInteraction
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemModel
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

class BluBalanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BluBalancePreview()
        }
    }
}

@Preview
@Composable
private fun BluBalancePreview() {
    val bgColor = Color(0xFF2244E8)
    val mainExpandable = OceanBluBalanceItemModel(
        type = OceanBluBalanceItemType.Main(
            title = "Saldo total na Blu",
            value = "R$ 1.500.000,00"
        ),
        interaction = OceanBluBalanceItemInteraction.Expandable(
            items = listOf(
                "Saldo atual" to "R$ 1.000,00",
                "Agenda" to "R$ 10.000,00"
            )
        )
    )

    val knowMoreItem = OceanBluBalanceItemModel(
        type = OceanBluBalanceItemType.Text(
            "Facilite a conciliação de cobranças PagBlu"
        ),
        interaction = OceanBluBalanceItemInteraction.Action(
            type = OceanBalanceItemAction.Button(
                title = "Saiba mais"
            ),
            action = { }
        )
    )

    val acquirersItem = OceanBluBalanceItemModel(
        type = OceanBluBalanceItemType.Main(
            title = "Saldo nas maquininhas",
            value = "R$ 1.000.000,00"
        ),
        interaction = OceanBluBalanceItemInteraction.Action(
            type = OceanBalanceItemAction.Badges(
                icons = listOf(
                    OceanIcons.ACQUIRER_REDE,
                    OceanIcons.ACQUIRER_GETNET,
                    OceanIcons.ACQUIRER_CIELO,
                    OceanIcons.ACQUIRER_PAGBANK,
                    OceanIcons.ACQUIRER_SICOOB
                )
            ),
            action = { }
        )
    )

    var isLoading by remember { mutableStateOf(false) }
    var hideContent by remember { mutableStateOf(false) }

    OceanTheme {
        Column(
            modifier = Modifier
                .background(color = OceanColors.interfaceLightPure),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OceanBluBalance(
                modifier = Modifier
                    .background(
                        color = bgColor
                    ),
                items = listOf(
                    mainExpandable
                ),
                hideContent = hideContent,
                isLoading = isLoading
            )

            OceanBluBalance(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.xs)
                    .borderBackground(
                        color = bgColor,
                        borderRadius = OceanBorderRadius.SM.allCorners
                    ),
                items = listOf(
                    mainExpandable,
                    knowMoreItem
                ),
                hideContent = hideContent,
                isLoading = isLoading
            )

            OceanBluBalance(
                modifier = Modifier
                    .padding(horizontal = OceanSpacing.xs)
                    .borderBackground(
                        color = bgColor,
                        borderRadius = OceanBorderRadius.SM.allCorners
                    ),
                items = listOf(
                    mainExpandable,
                    acquirersItem
                ),
                hideContent = hideContent,
                isLoading = isLoading
            )

            OceanButton(
                button = OceanButtonModel(
                    text = "Toggle Hidden",
                    buttonStyle = OceanButtonStyle.SecondaryLarge,
                    onClick = {
                        hideContent = !hideContent
                    }
                )
            )

            OceanButton(
                button = OceanButtonModel(
                    text = "Toggle Loading",
                    buttonStyle = OceanButtonStyle.SecondaryLarge,
                    onClick = {
                        isLoading = !isLoading
                    }
                )
            )
        }
    }
}
