package br.com.useblu.oceands.client.ui.transactionfooter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.OceanButtonModel
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.OceanTransactionFooter
import br.com.useblu.oceands.components.compose.OceanTransactionFooterItem
import br.com.useblu.oceands.components.compose.OceanTransactionFooterNotice
import br.com.useblu.oceands.components.compose.OceanTransactionFooterTotal
import br.com.useblu.oceands.components.compose.OceanTransactionFooterType
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

class TransactionFooterV2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Transaction Footer 2.0"
        setContent {
            OceanTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = OceanSpacing.lg),
                    verticalArrangement = Arrangement.spacedBy(OceanSpacing.md)
                ) {
                    footerSection("Default", mainItem = order, details = standardDetails)
                    footerSection(
                        "Highlight",
                        type = OceanTransactionFooterType.Highlight,
                        mainItem = order,
                        details = standardDetails
                    )
                    footerSection(
                        "Highlight with notice",
                        type = OceanTransactionFooterType.Highlight,
                        notice = OceanTransactionFooterNotice(
                            description = "A economia será aplicada ao pagamento."
                        ),
                        mainItem = order,
                        details = standardDetails
                    )
                    footerSection(
                        "Notice with title and icon",
                        notice = OceanTransactionFooterNotice(
                            description = "A economia será aplicada ao pagamento.",
                            title = "Economia de R$ 96,39",
                            icon = OceanIcons.CHECK_CIRCLE_OUTLINE
                        ),
                        mainItem = order,
                        details = standardDetails
                    )
                    footerSection("Main item only", mainItem = order)
                    footerSection("Details only", details = standardDetails)
                    footerSection("Four details", details = fourDetails)
                }
            }
        }
    }

    @Composable
    private fun footerSection(
        title: String,
        type: OceanTransactionFooterType = OceanTransactionFooterType.Default,
        notice: OceanTransactionFooterNotice? = null,
        mainItem: OceanTransactionFooterItem? = null,
        details: List<OceanTransactionFooterItem> = emptyList()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
        ) {
            OceanText(
                text = title,
                modifier = Modifier.padding(horizontal = OceanSpacing.xs),
                style = OceanTextStyle.eyebrow
            )
            OceanTransactionFooter(
                total = OceanTransactionFooterTotal("Total", "R$ 623,80"),
                button = OceanButtonModel(
                    text = "Revisar pagamento",
                    onClick = {},
                    buttonStyle = OceanButtonStyle.PrimaryMedium
                ),
                modifier = Modifier.fillMaxWidth(),
                type = type,
                notice = notice,
                mainItem = mainItem,
                details = details
            )
        }
    }

    private companion object {
        val order = OceanTransactionFooterItem("Pedido", "R$ 623,80")
        val standardDetails = listOf(
            OceanTransactionFooterItem("Pague em", "3x de R$ 207,93 sem acréscimo")
        )
        val fourDetails = listOf(
            OceanTransactionFooterItem("Pague em", "3x de R$ 207,93 sem acréscimo"),
            OceanTransactionFooterItem("Custo de antecipação", "R$ 96,39"),
            OceanTransactionFooterItem("Vencimento", "10 de outubro"),
            OceanTransactionFooterItem("Forma de pagamento", "Cartão de crédito")
        )
    }
}
