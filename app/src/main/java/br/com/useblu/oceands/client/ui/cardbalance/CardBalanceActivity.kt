package br.com.useblu.oceands.client.ui.cardbalance

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.balance.cardbalance.OceanCardBalancePreview
import br.com.useblu.oceands.components.compose.balance.cardbalance.OceanCardBalancePreviewAnticipation
import br.com.useblu.oceands.components.compose.balance.cardbalance.OceanCardBalancePreviewLocked
import br.com.useblu.oceands.ui.compose.OceanSpacing

class CardBalanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                Modifier.verticalScroll(rememberScrollState())
            ) {
                OceanSpacing.StackMD()

                OceanCardBalancePreview()

                OceanSpacing.StackMD()

                OceanCardBalancePreviewAnticipation()

                OceanSpacing.StackMD()

                OceanCardBalancePreviewLocked()
            }
        }
    }
}
