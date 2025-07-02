package br.com.useblu.oceands.client.ui.tokeninput

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.tokeninput.OceanTokenAlphaValidator
import br.com.useblu.oceands.components.compose.tokeninput.OceanTokenInput
import br.com.useblu.oceands.components.compose.tokeninput.OceanTokenNumericValidator
import br.com.useblu.oceands.components.compose.tokeninput.OceanTokenSecurityMask
import br.com.useblu.oceands.components.compose.tokeninput.OceanTokenUppercaseMask
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing

class TokenInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .background(color = OceanColors.interfaceLightPure)
                    .padding(OceanSpacing.xs),
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
            ) {
                var alphanumeric by remember { mutableStateOf("") }
                // Default alphanumeric
                OceanTokenInput(
                    label = "Código de verificação",
                    value = alphanumeric,
                    onValueChange = { alphanumeric = it }
                )

                var numeric by remember { mutableStateOf("") }
                // Numeric only
                OceanTokenInput(
                    label = "PIN numérico",
                    value = numeric,
                    validator = OceanTokenNumericValidator,
                    tokenCount = 6,
                    onValueChange = { numeric = it }
                )

                var secure by remember { mutableStateOf("") }
                // Masked input
                OceanTokenInput(
                    label = "Senha",
                    value = secure,
                    mask = OceanTokenSecurityMask,
                    tokenCount = 8,
                    onValueChange = { secure = it }
                )

                var uppercased by remember { mutableStateOf("") }
                // Letters only (uppercase)
                OceanTokenInput(
                    label = "Código alfabético",
                    value = uppercased,
                    mask = OceanTokenUppercaseMask,
                    validator = OceanTokenAlphaValidator,
                    onValueChange = { uppercased = it }
                )
            }
        }
    }
}
