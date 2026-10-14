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
import br.com.useblu.oceands.components.compose.AmountType
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.OceanButtonModel
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.OceanTransactionFooter
import br.com.useblu.oceands.components.compose.OceanTransactionFooterItem
import br.com.useblu.oceands.components.compose.OceanTransactionFooterNotice
import br.com.useblu.oceands.components.compose.OceanTransactionFooterTotal
import br.com.useblu.oceands.components.compose.OceanTransactionFooterType
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanTagModel
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
                            description = "Texto de apoio do aviso."
                        ),
                        mainItem = order,
                        details = standardDetails
                    )
                    footerSection(
                        "Amount variations",
                        mainItem = amountVariants.first(),
                        details = amountVariants.drop(1)
                    )
                    footerSection(
                        "Notice with title and icon",
                        notice = OceanTransactionFooterNotice(
                            description = "Texto de apoio do aviso.",
                            title = "Desconto aplicado",
                            icon = OceanIcons.CHECK_CIRCLE_SOLID
                        ),
                        mainItem = order,
                        details = standardDetails
                    )
                    footerSection("Main item only", mainItem = order)
                    footerSection("Details only", details = standardDetails)
                    footerSection("Four details", details = fourDetails)
                    footerSection(
                        "Additional info",
                        mainItem = footerItem(
                            "Subtotal",
                            "R$ 1.250,00",
                            info = "sem acréscimo"
                        )
                    )
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
                total = OceanTransactionFooterTotal("Total", "R$ 1.250,00"),
                button = OceanButtonModel(
                    text = "Continuar",
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
        private const val INSTALLMENT_VALUE = "5x de R$ 250,00"

        val order = footerItem("Subtotal", "R$ 1.250,00")
        val amountVariants = listOf(
            footerItem("Taxa", "Grátis", type = AmountType.Strikethrough, strikethroughAmount = "3,99%"),
            footerItem("Ajuste", "R$ 0,00", type = AmountType.StrikethroughNeutral, strikethroughAmount = "R$ 1,00"),
            footerItem("Cashback", "R$ 10,00", type = AmountType.Positive),
            footerItem("Tarifa de ajuste", "R$ 2,00", type = AmountType.Negative),
            footerItem("Taxa anterior", "R$ 2,00", type = AmountType.Inactive),
            footerItem("Desconto", "Grátis", tag = OceanTagModel(OceanTagType.Positive, "Aplicado")),
            footerItem("Parcelamento", INSTALLMENT_VALUE, info = "sem acréscimo"),
            footerItem(
                "Compra",
                "R$ 1.250,00",
                description = "Pagamento em cinco parcelas"
            )
        )
        val standardDetails = listOf(
            footerItem("Parcelamento", INSTALLMENT_VALUE, info = "sem acréscimo")
        )
        val fourDetails = listOf(
            footerItem("Parcelamento", INSTALLMENT_VALUE, info = "sem acréscimo"),
            footerItem("Taxa de serviço", "R$ 12,50"),
            footerItem("Data de entrega", "15 de março"),
            footerItem("Forma de pagamento", "Cartão de crédito")
        )

        private fun footerItem(
            label: String,
            value: String,
            info: String = "",
            type: AmountType = AmountType.Default,
            strikethroughAmount: String = "",
            tag: OceanTagModel? = null,
            description: String = ""
        ) = OceanTransactionFooterItem(
            content = ContentListStyle.Default(title = label, description = description),
            amount = ContentListStyle.Amount(
                amount = value,
                type = type,
                strikethroughAmount = strikethroughAmount,
                tag = tag,
                additionalData = info
            )
        )
    }
}
