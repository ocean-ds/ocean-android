package br.com.useblu.oceands.client.storybook

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

/**
 * Descreve uma "story" do Storybook: um componente do Ocean DS renderizado de forma
 * isolada com um painel de controles interativos para validar suas variantes.
 *
 * Para adicionar um novo componente ao Storybook, crie a story (ver [bannerStory] como
 * referência) e registre-a em [StorybookCatalog.stories].
 */
data class StorybookStory(
    val name: String,
    val group: String,
    val content: @Composable () -> Unit
)

/**
 * Catálogo central de stories. Cada componente do Ocean DS que entrar no Storybook
 * deve ser registrado aqui.
 */
object StorybookCatalog {
    val stories: List<StorybookStory> = listOf(
        bannerStory
    )
}

/**
 * Raiz do Storybook: alterna entre a lista de componentes e a visualização isolada
 * de uma story.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorybookApp() {
    var selected by remember { mutableStateOf<StorybookStory?>(null) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val current = selected
            if (current == null) {
                StorybookHeader(title = "Storybook", onBack = null)
                StorybookList(
                    stories = StorybookCatalog.stories,
                    onSelect = { selected = it }
                )
            } else {
                StorybookHeader(title = current.name, onBack = { selected = null })
                current.content()
            }
        }
    }
}

@Composable
private fun StorybookHeader(
    title: String,
    onBack: (() -> Unit)?
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (onBack != null) {
            TextButton(onClick = onBack) {
                Text(text = "← Voltar")
            }
        }
        OceanText(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = OceanSpacing.xs,
                    end = OceanSpacing.xs,
                    bottom = OceanSpacing.xxs
                ),
            style = OceanTextStyle.heading3
        )
        OceanDivider()
    }
}

@Composable
private fun StorybookList(
    stories: List<StorybookStory>,
    onSelect: (StorybookStory) -> Unit
) {
    val grouped = stories.groupBy { it.group }.toSortedMap()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        grouped.forEach { (group, groupStories) ->
            item {
                Text(
                    text = group.uppercase(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = OceanSpacing.xs,
                            end = OceanSpacing.xs,
                            top = OceanSpacing.xs,
                            bottom = OceanSpacing.xxs
                        ),
                    style = OceanTextStyle.caption.copy(color = OceanColors.interfaceDarkUp)
                )
            }
            items(groupStories.sortedBy { it.name }) { story ->
                OceanText(
                    text = story.name,
                    modifier = Modifier
                        .clickable { onSelect(story) }
                        .fillMaxWidth()
                        .padding(OceanSpacing.xs),
                    style = OceanTextStyle.subtitle1,
                    color = OceanColors.interfaceDarkDown
                )
                OceanDivider()
            }
        }
    }
}
