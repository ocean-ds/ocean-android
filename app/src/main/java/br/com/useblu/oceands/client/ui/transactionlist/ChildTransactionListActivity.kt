package br.com.useblu.oceands.client.ui.transactionlist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.ContentListSize
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.transactionlist.OceanChildTransactionListAction
import br.com.useblu.oceands.components.compose.transactionlist.OceanChildTransactionListReadOnly
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListPosition
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListState
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

/** Child Transaction List Action (Figma 24323-3663). */
class ChildTransactionListActionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ChildTransactionListActionSamples() }
    }
}

/** Child Transaction List Read Only (Figma 26758-376). */
class ChildTransactionListReadOnlyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ChildTransactionListReadOnlySamples() }
    }
}

@Preview
@Composable
private fun ChildTransactionListActionSamples() = SamplesScreen {
    var taps by remember { mutableIntStateOf(0) }
    OceanText(
        modifier = Modifier.padding(OceanSpacing.xs),
        text = "Toques: $taps",
        style = OceanTextStyle.paragraph
    )

    OceanTransactionListState.entries.forEach { state ->
        SampleSection("Position: standalone/first/middle/last · $state")
        OceanTransactionListPosition.entries.forEach { position ->
            OceanChildTransactionListAction(
                content = TransactionListSamples.content(ContentListSize.Sm),
                amount = TransactionListSamples.amount(ContentListSize.Sm),
                icon = TransactionListSamples.childIcon,
                position = position,
                state = state,
                onClick = { taps++ }
            )
        }
    }
}

@Preview
@Composable
private fun ChildTransactionListReadOnlySamples() = SamplesScreen {
    OceanTransactionListState.entries.forEach { state ->
        SampleSection("Position: standalone/first/middle/last · $state")
        OceanTransactionListPosition.entries.forEach { position ->
            OceanChildTransactionListReadOnly(
                content = TransactionListSamples.content(ContentListSize.Sm),
                amount = TransactionListSamples.amount(ContentListSize.Sm),
                icon = TransactionListSamples.childIcon,
                position = position,
                state = state
            )
        }
    }
}
