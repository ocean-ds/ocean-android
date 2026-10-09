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
import br.com.useblu.oceands.components.compose.OceanTransactionFooterTotal
import br.com.useblu.oceands.components.compose.OceanTransactionFooterType
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanTagModel
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

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
                    footerSection("Default", items = defaultItems)
                    footerSection(
                        "Highlight",
                        type = OceanTransactionFooterType.Highlight,
                        items = defaultItems
                    )
                    footerSection(
                        "With notice",
                        notice = "Seu pagamento será processado após a confirmação.",
                        items = defaultItems
                    )
                    footerSection(
                        "Rich rows",
                        items = listOf(
                            OceanTransactionFooterItem(
                                content = ContentListStyle.Default(
                                    title = "Taxa",
                                    description = "Antecipação",
                                    caption = "Hoje"
                                ),
                                amount = ContentListStyle.Amount(
                                    amount = "Grátis",
                                    type = AmountType.Strikethrough,
                                    strikethroughAmount = "R$ 10,00",
                                    tag = OceanTagModel(OceanTagType.Positive, "Grátis")
                                )
                            ),
                            OceanTransactionFooterItem(
                                content = ContentListStyle.Default(
                                    title = "Desconto",
                                    description = "Benefício aplicado"
                                ),
                                amount = ContentListStyle.Amount(amount = "R$ 10,00")
                            )
                        )
                    )
                    footerSection(
                        "Many rows",
                        items = (1..7).map { index ->
                            OceanTransactionFooterItem(
                                content = ContentListStyle.Default(title = "Linha $index"),
                                amount = ContentListStyle.Amount(amount = "R$ $index,00")
                            )
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun footerSection(
        title: String,
        type: OceanTransactionFooterType = OceanTransactionFooterType.Default,
        notice: String? = null,
        items: List<OceanTransactionFooterItem>
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
                type = type,
                notice = notice,
                items = items,
                total = OceanTransactionFooterTotal("Total", "R$ 90,00"),
                button = OceanButtonModel(
                    text = "Continuar",
                    onClick = {},
                    buttonStyle = OceanButtonStyle.PrimaryMedium
                )
            )
        }
    }

    private val defaultItems = listOf(
        OceanTransactionFooterItem(
            content = ContentListStyle.Default(title = "Compra", description = "Loja"),
            amount = ContentListStyle.Amount(amount = "R$ 100,00")
        ),
        OceanTransactionFooterItem(
            content = ContentListStyle.Default(title = "Desconto"),
            amount = ContentListStyle.Amount(amount = "R$ 10,00", type = AmountType.Positive)
        )
    )
}
