package br.com.useblu.oceands.client.ui.transactionlistexpandable

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.list.OceanTransactionListExpandablePreview
import br.com.useblu.oceands.ui.compose.OceanSpacing

class OceanTransactionListExpandableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {
                OceanSpacing.StackMD()

                OceanTransactionListExpandablePreview()
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    OceanTransactionListExpandablePreview()
}
