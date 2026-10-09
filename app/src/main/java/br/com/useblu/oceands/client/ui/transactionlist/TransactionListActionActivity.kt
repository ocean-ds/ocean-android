package br.com.useblu.oceands.client.ui.transactionlist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.ContentListSize
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListAction
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListActionType
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListState
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

class TransactionListActionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TransactionListActionSamples() }
    }
}

@Preview
@Composable
private fun TransactionListActionSamples() = SamplesScreen {
    var taps by remember { mutableIntStateOf(0) }

    OceanText(
        modifier = Modifier.padding(OceanSpacing.xs),
        text = "Toques: $taps",
        style = OceanTextStyle.paragraph
    )

    SampleSection("States (press for the highlight)")
    OceanTransactionListState.entries.forEach { state ->
        OceanTransactionListAction(
            content = TransactionListSamples.content(),
            amount = TransactionListSamples.amount(),
            icon = TransactionListSamples.icon,
            state = state,
            onClick = { taps++ }
        )
    }

    SampleSection("Type Menu (kebab abre o bottom sheet)")
    var openMenu by remember { mutableStateOf<Int?>(null) }
    var lastOption by remember { mutableStateOf("") }
    OceanTransactionListState.entries.forEachIndexed { index, state ->
        OceanTransactionListAction(
            content = TransactionListSamples.content(),
            amount = TransactionListSamples.amount(),
            icon = TransactionListSamples.icon,
            state = state,
            type = OceanTransactionListActionType.Menu,
            menuActive = openMenu == index,
            onMenuClick = { openMenu = index },
            onClick = { taps++ }
        )
    }
    if (lastOption.isNotBlank()) {
        OceanText(
            modifier = Modifier.padding(OceanSpacing.xs),
            text = "Última opção: $lastOption",
            style = OceanTextStyle.description
        )
    }
    if (openMenu != null) {
        TransactionListMenuSheet(
            onOptionClick = {
                lastOption = it
                openMenu = null
            },
            onDismiss = { openMenu = null }
        )
    }

    SampleSection("Sizes")
    OceanTransactionListAction(
        content = TransactionListSamples.content(ContentListSize.Sm),
        amount = TransactionListSamples.amount(ContentListSize.Sm),
        icon = TransactionListSamples.icon,
        onClick = { taps++ }
    )
    OceanTransactionListAction(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        showDivider = false,
        onClick = { taps++ }
    )
}
