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
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

private const val ADDITIONAL_DATA = "Additional data"
private const val BLU_ISSUER = BLU_ISSUER
private const val CANCEL_LABEL = CANCEL_LABEL
private const val CANCEL_DATE = CANCEL_DATE

class OceanTransactionListExpandableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TransactionListExpandableSamples()
        }
    }
}

@Composable
private fun TransactionListExpandableSamples() {
    val retainValue = -150.00
    val cancelValue = 150.00

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        OceanSpacing.StackMD()

        OceanTransactionListExpandable(
            parent = OceanTransactionListExpandableItem(
                primaryLabel = "Title",
                secondaryLabel = "Description",
                dimmedLabel = "Caption",
                primaryValue = 0.0,
                time = ADDITIONAL_DATA,
                tagTitle = "Label",
                tagType = OceanTagType.Positive
            ),
            itemsIcon = OceanIcons.LOCK_CLOSED_SOLID,
            items = listOf(
                OceanTransactionListExpandableItem(
                    primaryLabel = "Title",
                    secondaryLabel = "Description",
                    dimmedLabel = "Caption",
                    primaryValue = 0.0,
                    time = ADDITIONAL_DATA,
                    tagTitle = "Label",
                    tagType = OceanTagType.Positive
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = "Title",
                    secondaryLabel = "Description",
                    dimmedLabel = "Caption",
                    primaryValue = 0.0,
                    time = ADDITIONAL_DATA,
                    tagTitle = "Label",
                    tagType = OceanTagType.Positive
                )
            ),
            footerText = "Supporting text that providing context.",
            startExpanded = true
        )

        OceanTransactionListExpandable(
            parent = OceanTransactionListExpandableItem(
                primaryLabel = "Retenções",
                primaryValue = -2260.00
            ),
            itemsIcon = OceanIcons.LOCK_CLOSED_SOLID,
            items = listOf(
                OceanTransactionListExpandableItem(
                    primaryLabel = "Retenção de saldo",
                    secondaryLabel = BLU_ISSUER,
                    primaryValue = retainValue
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = "Retenção de saldo",
                    secondaryLabel = BLU_ISSUER,
                    primaryValue = retainValue
                )
            ),
            footerText = "Fim das retenções de saldo",
            startExpanded = true
        )

        OceanTransactionListExpandable(
            parent = OceanTransactionListExpandableItem(
                primaryLabel = "Cancelamento de retenções",
                primaryValue = 3295.00
            ),
            itemsIcon = OceanIcons.LOCK_OPEN_SOLID,
            items = listOf(
                OceanTransactionListExpandableItem(
                    primaryLabel = CANCEL_LABEL,
                    secondaryLabel = BLU_ISSUER,
                    dimmedLabel = CANCEL_DATE,
                    primaryValue = cancelValue
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = CANCEL_LABEL,
                    secondaryLabel = BLU_ISSUER,
                    dimmedLabel = CANCEL_DATE,
                    primaryValue = cancelValue
                )
            ),
            footerText = "Fim dos cancelamentos das retenções",
            startExpanded = true
        )

        OceanSpacing.StackMD()

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
                    primaryLabel = CANCEL_LABEL,
                    secondaryLabel = BLU_ISSUER,
                    dimmedLabel = CANCEL_DATE,
                    primaryValue = cancelValue
                ),
                OceanTransactionListExpandableItem(
                    primaryLabel = CANCEL_LABEL,
                    secondaryLabel = BLU_ISSUER,
                    dimmedLabel = CANCEL_DATE,
                    primaryValue = cancelValue
                )
            ),
            footerText = "Fim dos cancelamentos das retenções",
            startExpanded = true
        )

        OceanSpacing.StackMD()
    }
}

@Preview
@Composable
private fun TransactionListExpandableSamplesPreview() {
    TransactionListExpandableSamples()
}
