package br.com.useblu.oceands.client.ui.cardoption

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanCardOption
import br.com.useblu.oceands.components.compose.OceanLink
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.OceanTopBarInverse
import br.com.useblu.oceands.model.OceanOptionCardItem
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanSpacing.xs
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

class CardOptionActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OceanTheme {
                CardOptionDemo(this)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardOptionDemo(activity: CardOptionActivity) {
    val cardOptions = listOf(
        OceanOptionCardItem(
            data = Any(),
            icon = OceanIcons.RETAILER_OUTLINE.token,
            title = "Seu Negócio",
            subTitle = "Taxas e tarifas para mairo com duas linhas aaaa",
            disabled = false,
            recommend = false
        ),
        OceanOptionCardItem(
            data = Any(),
            icon = OceanIcons.ACADEMIC_CAP_OUTLINE.token,
            title = "PagBlu com prazo",
            subTitle = "Pague em parcelas com mairo com duas linhas aaaa",
            disabled = false,
            recommend = false
        ),
        OceanOptionCardItem(
            data = Any(),
            icon = OceanIcons.ACADEMIC_CAP_SOLID.token,
            title = "Transferência",
            subTitle = "Envie dinheiro para qualquer mairo com duas linhas aaaa",
            disabled = false,
            recommend = false
        ),
        OceanOptionCardItem(
            data = Any(),
            icon = OceanIcons.BRIEFCASE_OUTLINE.token,
            title = "Investimentos",
            subTitle = "Aplique seu dinheiro e mairo com duas linhas aaaa",
            disabled = false,
            recommend = false
        )
    )

    Scaffold(
        topBar = {
            OceanTopBarInverse(
                title = "Card Option Demo",
                onClickIcon = { activity.onBackPressedDispatcher.onBackPressed() },
                onClickToolbar = {}
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(OceanColors.interfaceLightDown)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(OceanColors.interfaceLightPure)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = OceanSpacing.xs),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxsExtra)
        ) {
            cardOptions.forEach { item ->
                OceanCardOption(
                    item = item,
                    showBackgroundIcon = true,
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.size(OceanSpacing.md - OceanSpacing.xxs * 2))
            OceanLink(
                text = "Saiba mais sobre as opções",
                onClick = {}
            )
            OceanText(
                text = "Entre em contato conosco.",
                style = OceanTextStyle.caption,
                color = OceanColors.interfaceDarkDown
            )
        }
    }
}
