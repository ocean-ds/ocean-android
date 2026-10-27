package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.utils.OceanIcons

data class OceanTextListIconItemModel(
    val title: String,
    val modifier: Modifier = Modifier,
    val description: String = "",
    val caption: String = "",
    val badge: String = "",
    val leadingIconToken: OceanIcons? = null,
    val trailingIconToken: OceanIcons? = null,
    val onClick: (() -> Unit)? = null,
    /**
     * Conteúdo Compose desenhado no lugar de [leadingIconToken], no mesmo quadro de 24dp — slot
     * para um avatar de iniciais, por exemplo. Nasce `null`: o ícone do catálogo segue o padrão.
     */
    val leadingContent: (@Composable () -> Unit)? = null
)
