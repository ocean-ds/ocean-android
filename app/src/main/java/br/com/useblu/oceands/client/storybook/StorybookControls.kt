package br.com.useblu.oceands.client.storybook

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

/**
 * Área de pré-visualização ("canvas") onde o componente é renderizado isolado, sobre um
 * fundo neutro que evidencia os limites do componente.
 */
@Composable
fun StorybookCanvas(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(OceanColors.interfaceLightPure)
            .padding(OceanSpacing.xs)
    ) {
        content()
    }
}

/**
 * Painel de controles (knobs) que altera os parâmetros do componente em tempo real.
 */
@Composable
fun StorybookControlsPanel(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(OceanSpacing.xs)
    ) {
        OceanText(
            text = "CONTROLS",
            style = OceanTextStyle.caption.copy(color = OceanColors.interfaceDarkUp)
        )
        OceanDivider()
        content()
    }
}

/**
 * Controle de seleção única entre poucas opções (ex.: size, style).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorybookSegmented(
    label: String,
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = OceanSpacing.xxs)
    ) {
        Text(text = label)
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
        ) {
            options.forEachIndexed { index, option ->
                FilterChip(
                    selected = index == selectedIndex,
                    onClick = { onSelect(index) },
                    label = { Text(text = option) }
                )
            }
        }
    }
}

/**
 * Controle booleano (ex.: exibir/ocultar CTA, habilitar/desabilitar).
 */
@Composable
fun StorybookSwitch(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = OceanSpacing.xxs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

/**
 * Controle de texto livre (ex.: title, description).
 */
@Composable
fun StorybookTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = OceanSpacing.xxs)
    )
}
