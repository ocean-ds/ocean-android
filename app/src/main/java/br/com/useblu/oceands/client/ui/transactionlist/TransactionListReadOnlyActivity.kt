package br.com.useblu.oceands.client.ui.transactionlist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.AmountType
import br.com.useblu.oceands.components.compose.ContentListSize
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.ContentListType
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListReadOnly
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListState
import br.com.useblu.oceands.components.compose.transactionlist.TransactionListIconColor
import br.com.useblu.oceands.ui.compose.OceanColors

class TransactionListReadOnlyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TransactionListReadOnlySamples() }
    }
}

@Preview
@Composable
private fun TransactionListReadOnlySamples() = SamplesScreen {
    SampleSection("States")
    OceanTransactionListState.entries.forEach { state ->
        OceanTransactionListReadOnly(
            content = TransactionListSamples.content(),
            amount = TransactionListSamples.amount(),
            icon = TransactionListSamples.icon,
            state = state
        )
    }

    SampleSection("Icon colors (Default, OnColor, Highlight, disabled)")
    TransactionListIconColor.entries.forEach { color ->
        OceanTransactionListReadOnly(
            content = ContentListStyle.Inverted(title = "iconColor", description = color.name),
            amount = TransactionListSamples.amount(),
            icon = TransactionListSamples.icon,
            iconColor = color
        )
    }
    OceanTransactionListReadOnly(
        content = ContentListStyle.Inverted(title = "iconColor", description = "Highlight · Disabled"),
        amount = TransactionListSamples.amount(),
        icon = TransactionListSamples.icon,
        iconColor = TransactionListIconColor.Highlight,
        state = OceanTransactionListState.Disabled
    )
    OceanTransactionListReadOnly(
        modifier = Modifier.background(OceanColors.statusWarningUp),
        content = ContentListStyle.Inverted(title = "iconColor", description = "OnColor em Status/Warning/Up"),
        amount = TransactionListSamples.amount(),
        icon = TransactionListSamples.icon,
        iconColor = TransactionListIconColor.OnColor,
        showDivider = false
    )
    OceanTransactionListReadOnly(
        modifier = Modifier.background(OceanColors.statusNegativeUp),
        content = ContentListStyle.Inverted(title = "iconColor", description = "OnColor em Status/Negative/Up"),
        amount = TransactionListSamples.amount(),
        icon = TransactionListSamples.icon,
        iconColor = TransactionListIconColor.OnColor,
        showDivider = false
    )

    SampleSection("Sizes")
    OceanTransactionListReadOnly(
        content = TransactionListSamples.content(ContentListSize.Sm),
        amount = TransactionListSamples.amount(ContentListSize.Md),
        icon = TransactionListSamples.icon
    )
    OceanTransactionListReadOnly(
        content = TransactionListSamples.content(ContentListSize.Md),
        amount = TransactionListSamples.amount(ContentListSize.Sm),
        icon = TransactionListSamples.icon
    )
    OceanTransactionListReadOnly(
        content = TransactionListSamples.content(ContentListSize.Sm),
        amount = TransactionListSamples.amount(ContentListSize.Sm),
        icon = TransactionListSamples.icon
    )

    SampleSection("Amount types")
    OceanTransactionListReadOnly(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(type = AmountType.Positive)
    )
    OceanTransactionListReadOnly(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(type = AmountType.Negative)
    )
    OceanTransactionListReadOnly(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(type = AmountType.Inactive)
    )
    OceanTransactionListReadOnly(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(
            type = AmountType.Strikethrough,
            amount = "Grátis",
            strikethrough = "3,99%"
        )
    )
    OceanTransactionListReadOnly(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(
            type = AmountType.StrikethroughNeutral,
            amount = "R$ 90,00",
            strikethrough = "R$ 100,00"
        )
    )

    SampleSection("Content types")
    ContentListType.entries.forEach { type ->
        OceanTransactionListReadOnly(
            content = ContentListStyle.Inverted(
                title = type.name,
                description = "Description",
                caption = "Caption",
                type = type
            ),
            amount = TransactionListSamples.amount()
        )
    }
    OceanTransactionListReadOnly(
        content = ContentListStyle.Strikethrough(
            title = "Strikethrough",
            description = "Strikethrough",
            newValue = "Description",
            caption = "Caption",
            size = ContentListSize.Md
        ),
        amount = TransactionListSamples.amount()
    )
}
