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

/**
 * Every Figma `State` of the Transaction List Selectable (24320-5708, Platform App) for each
 * `Controller type`: Default, Hover (mouse pointer over the item), Indeterminate (Checkbox only),
 * Selected, Disabled, Disabled Selected, Error and Loading.
 */
@Preview
@Composable
private fun TransactionListSelectableSamples() = SamplesScreen {
    OceanTransactionListController.entries.forEach { controller ->
        SelectableStates(controller)
    }

    SampleSection("Radio · grupo de opção única")
    var option by remember { mutableIntStateOf(0) }
    repeat(3) { index ->
        OceanTransactionListSelectable(
            content = TransactionListSamples.content(),
            amount = TransactionListSamples.amount(),
            controller = OceanTransactionListController.Radio,
            selected = option == index,
            onSelectedChange = { option = index }
        )
    }
}

@Composable
private fun SelectableStates(controller: OceanTransactionListController) {
    val name = controller.name

    SampleSection("$name · Default / Hover (toque para marcar)")
    var interactive by remember { mutableStateOf(false) }
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        controller = controller,
        selected = interactive,
        onSelectedChange = { interactive = it }
    )

    if (controller == OceanTransactionListController.Checkbox) {
        SampleSection("$name · Indeterminate")
        var indeterminate by remember { mutableStateOf(true) }
        var afterIndeterminate by remember { mutableStateOf(false) }
        OceanTransactionListSelectable(
            content = TransactionListSamples.content(),
            amount = TransactionListSamples.amount(),
            controller = controller,
            selected = afterIndeterminate,
            indeterminate = indeterminate,
            onSelectedChange = {
                indeterminate = false
                afterIndeterminate = it
            }
        )
    }

    SampleSection("$name · Selected")
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        controller = controller,
        selected = true,
        onSelectedChange = {}
    )

    SampleSection("$name · Disabled")
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        controller = controller,
        selected = false,
        state = OceanTransactionListState.Disabled,
        onSelectedChange = {}
    )

    SampleSection("$name · Disabled Selected")
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        controller = controller,
        selected = true,
        state = OceanTransactionListState.Disabled,
        onSelectedChange = {}
    )

    SampleSection("$name · Error")
    var error by remember { mutableStateOf(true) }
    var errorSelected by remember { mutableStateOf(false) }
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        controller = controller,
        selected = errorSelected,
        showError = error,
        onSelectedChange = {
            errorSelected = it
            error = false
        }
    )

    SampleSection("$name · Loading")
    OceanTransactionListSelectable(
        content = TransactionListSamples.content(),
        amount = TransactionListSamples.amount(),
        controller = controller,
        selected = false,
        state = OceanTransactionListState.Loading,
        onSelectedChange = {}
    )
}
