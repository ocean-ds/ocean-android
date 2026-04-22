package br.com.useblu.oceands.client.ui.textlistreadonly

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.OceanTextNotBlank
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.textlistreadonly.OceanTextListReadOnlyDefaultPreview
import br.com.useblu.oceands.components.compose.textlistreadonly.OceanTextListReadOnlyInvertedPreview
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

class TextListReadOnlyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanTheme {
                Scaffold { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(OceanColors.interfaceLightPure)
                            .padding(padding)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
                    ) {
                        OceanTextNotBlank(
                            text = "Inverted variants",
                            modifier = Modifier.padding(
                                start = OceanSpacing.xs,
                                top = OceanSpacing.xs
                            ),
                            style = OceanTextStyle.heading4,
                            color = OceanColors.interfaceDarkDeep
                        )
                        OceanTextListReadOnlyInvertedPreview()

                        OceanTextNotBlank(
                            text = "States (Default / Disabled / Loading)",
                            modifier = Modifier.padding(start = OceanSpacing.xs),
                            style = OceanTextStyle.heading4,
                            color = OceanColors.interfaceDarkDeep
                        )
                        OceanTextListReadOnlyDefaultPreview()
                    }
                }
            }
        }
    }
}
