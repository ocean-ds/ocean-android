package br.com.useblu.oceands.client.ui.transactionlist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListController
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListSelectable
import br.com.useblu.oceands.components.compose.transactionlist.OceanTransactionListState

class TransactionListSelectableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TransactionListSelectableSamples() }
    }
}

@Preview
@Composable
private fun TransactionListSelectableSamples() = SamplesScreen {
    SampleSection("Checkbox")
    var first by remember { mutableStateOf(false) }
    var second by remember { mutableStateOf(true) }
    var indeterminate by remember { mutableStateOf(true) }
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        selected = first,
        onSelectedChange = { first = it }
    )
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        selected = second,
        onSelectedChange = { second = it }
    )
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        selected = !indeterminate,
        indeterminate = indeterminate,
        onSelectedChange = { indeterminate = false }
    )

    SampleSection("Radio")
    var option by remember { mutableIntStateOf(0) }
    repeat(2) { index ->
        OceanTransactionListSelectable(
            content = TransactionListSamples.content(),
            amount = TransactionListSamples.amount(),
            controller = OceanTransactionListController.Radio,
            selected = option == index,
            onSelectedChange = { option = index }
        )
    }

    SampleSection("Disabled, error and loading")
    OceanTransactionListController.entries.forEach { controller ->
        OceanTransactionListSelectable(
            content = TransactionListSamples.content(),
            amount = TransactionListSamples.amount(),
            controller = controller,
            selected = false,
            state = OceanTransactionListState.Disabled,
            onSelectedChange = {}
        )
        OceanTransactionListSelectable(
            content = TransactionListSamples.content(),
            amount = TransactionListSamples.amount(),
            controller = controller,
            selected = true,
            state = OceanTransactionListState.Disabled,
            onSelectedChange = {}
        )
        OceanTransactionListSelectable(
            content = TransactionListSamples.content(),
            amount = TransactionListSamples.amount(),
            controller = controller,
            selected = false,
            showError = true,
            onSelectedChange = {}
        )
    }
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        selected = false,
        state = OceanTransactionListState.Loading,
        onSelectedChange = {}
    )
}
