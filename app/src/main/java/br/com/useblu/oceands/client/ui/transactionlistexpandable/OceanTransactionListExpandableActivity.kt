package br.com.useblu.oceands.client.ui.transactionlistexpandable

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.list.OceanTransactionListExpandable
import br.com.useblu.oceands.components.compose.list.OceanTransactionListExpandableItem
import br.com.useblu.oceands.components.compose.list.OceanTransactionListExpandablePreview
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

class OceanTransactionListExpandableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                OceanSpacing.StackMD()

                OceanTransactionListExpandablePreview()

                OceanSpacing.StackMD()

                CustomItemsIconSample()

                OceanSpacing.StackMD()
            }
        }
    }
}

@Composable
private fun CustomItemsIconSample() {
    OceanText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = OceanSpacing.xs),
        text = "Custom itemsIconSize + itemsIconTint",
        style = OceanTextStyle.heading5,
        color = OceanColors.interfaceDarkDeep
    )

    OceanSpacing.StackXXS()

    OceanText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = OceanSpacing.xs),
        text = "Ícone 16dp · tint interfaceLightDown",
        style = OceanTextStyle.caption,
        color = OceanColors.interfaceDarkDown
    )

    OceanSpacing.StackXS()

    OceanTransactionListExpandable(
        parent = OceanTransactionListExpandableItem(
            primaryLabel = "Cancelamento de retenções",
            primaryValue = 3295.00
        ),
        itemsIcon = OceanIcons.LOCK_OPEN_SOLID,
        itemsIconSize = 16.dp,
        itemsIconTint = OceanColors.interfaceLightDown,
        items = listOf(
            OceanTransactionListExpandableItem(
                primaryLabel = "Cancelamento de retenção",
                secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                dimmedLabel = "Retenção lançada em 14/01/2026",
                primaryValue = 150.00
            ),
            OceanTransactionListExpandableItem(
                primaryLabel = "Cancelamento de retenção",
                secondaryLabel = "Boleto de Blu Instituição de Pagamentos LTDA",
                dimmedLabel = "Retenção lançada em 14/01/2026",
                primaryValue = 150.00
            )
        ),
        footerText = "Fim dos cancelamentos das retenções",
        startExpanded = true
    )
}

@Preview
@Composable
private fun Preview() {
    OceanTransactionListExpandablePreview()
}

@Preview
@Composable
private fun CustomItemsIconPreview() {
    CustomItemsIconSample()
}
