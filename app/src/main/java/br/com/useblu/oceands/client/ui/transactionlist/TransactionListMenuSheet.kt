package br.com.useblu.oceands.client.ui.transactionlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.OceanBottomSheet
import br.com.useblu.oceands.components.compose.OceanBottomSheetModel
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

internal val TransactionMenuOptions = listOf("Ver detalhes", "Compartilhar comprovante", "Cancelar")

/**
 * Options of the Transaction List Action Menu on the app: the screen opens the Ocean Compose
 * bottom sheet when the kebab is tapped (MR-615, operator decision 08/10).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TransactionListMenuSheet(
    onOptionClick: (String) -> Unit,
    onDismiss: () -> Unit
) {
    OceanBottomSheet(
        model = OceanBottomSheetModel(
            title = "Opções da transação",
            buttons = emptyList(),
            customContent = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TransactionMenuOptions.forEach { option ->
                        OceanText(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onOptionClick(option) }
                                .padding(vertical = OceanSpacing.xxsExtra),
                            text = option,
                            style = OceanTextStyle.paragraph,
                            color = if (option == "Cancelar") {
                                OceanColors.statusNegativePure
                            } else {
                                OceanColors.interfaceDarkDeep
                            }
                        )
                    }
                }
            },
            onDismiss = { onDismiss() }
        )
    )
}
