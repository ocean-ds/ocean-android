package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.utils.OceanIcons
import kotlinx.coroutines.launch

private val sections = mutableListOf<@Composable () -> Unit>(
    {
        Column {
            for (item in 0..15) {
                Text(text = "Texto $item", modifier = Modifier.padding(8.dp))
            }
        }
    },
    {
        Column {
            for (item in 1..30) {
                OceanStep(item, 11)
            }
        }
    },
    {
        Column {
            for (item in 0..30) {
                Text(text = "Texto $item", modifier = Modifier.padding(8.dp))
            }
        }
    },
    {
        Column {
            for (item in 0..10) {
                OceanShortcut(
                    model = OceanShortcutModel(
                        label = "MediumVertical Disabled",
                        icon = OceanIcons.X_OUTLINE,
                        layout = OceanShortcutLayout.MediumVertical,
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                        disabled = true
                    )
                )
            }
        }
    }
)

@Preview
@Composable
private fun OceanScrollableTabPreview() {
    val tabs = listOf(
        OceanTabItemModel("Tab 1", 1),
        OceanTabItemModel("Tab 2", 2),
        OceanTabItemModel("Tab 3", 3),
        OceanTabItemModel("Tab 4")
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    OceanTheme {
        Scaffold(
            topBar = {
                OceanTopBarInverse(title = "Tela preview", onClickIcon = {})
            }
        ) {
            OceanScrollableTab(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                tabs = tabs,
                defaultSelectedTab = selectedTabIndex,
                onSelectedTab = {
                    println("new tab index $it")
                    selectedTabIndex = it
                },
                headerContent = {
                    Column {
                        repeat(12) {
                            OceanText(
                                text = "Testeeee $it",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                },
                content = {
                    sections.forEach {
                        item {
                            it()
                        }
                    }
                },
                footerContent = {
                    OceanButton(
                        text = "Teste",
                        onClick = {},
                        buttonStyle = OceanButtonStyle.PrimaryMedium
                    )
                }
            )
        }
    }
}

/**
 * Tela com seções roladas por uma [OceanTabScrollable] que gruda no topo.
 *
 * Cada item de [content] é uma seção, na mesma ordem de [tabs]: tocar numa aba rola a lista até a
 * seção, e rolar a lista troca a aba selecionada. Depois que [headerContent] sai da tela, a barra
 * fica presa no topo. A barra tem o visual de [OceanTabScrollable] (56dp de altura, linha de baixo
 * em [br.com.useblu.oceands.ui.compose.OceanColors.interfaceLightDown], 32dp de margem lateral).
 *
 * @param modifier [Modifier] aplicado à tela.
 * @param tabs abas, uma por seção de [content]. Ver [OceanTabItemModel].
 * @param defaultSelectedTab índice da aba selecionada ao compor.
 * @param onSelectedTab chamado com o índice da aba selecionada, por toque ou por rolagem.
 * @param headerContent conteúdo opcional acima da barra, que rola junto da lista.
 * @param content seções da lista, um item por aba.
 * @param footerContent conteúdo opcional depois da última seção.
 */
@Composable
fun OceanScrollableTab(
    modifier: Modifier = Modifier,
    tabs: List<OceanTabItemModel>,
    defaultSelectedTab: Int,
    onSelectedTab: (Int) -> Unit,
    headerContent: (@Composable () -> Unit)? = null,
    content: LazyListScope.() -> Unit,
    footerContent: (@Composable () -> Unit)? = null
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val firstVisibleIndex by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex }
    }

    val headerOffset = if (headerContent != null) 2 else 1

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        var currentItemIndex by remember(defaultSelectedTab) { mutableIntStateOf(defaultSelectedTab) }

        val headerIsSticky = remember(lazyListState) {
            derivedStateOf {
                val items = lazyListState.layoutInfo.visibleItemsInfo
                !(items.size >= 2 && items[0].index == 0)
            }
        }

        LaunchedEffect(key1 = firstVisibleIndex) {
            currentItemIndex = (firstVisibleIndex - headerOffset)
                .coerceIn(tabs.indices)
            onSelectedTab(currentItemIndex)
        }

        Box {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .run {
                        if (headerIsSticky.value) {
                            // Altura da barra (item de 56dp do Figma, sem token de tamanho).
                            padding(top = 56.dp)
                        } else this
                    },
                state = lazyListState,
                content = {
                    if (headerContent != null) {
                        item {
                            headerContent()
                        }
                    }

                    item {
                        if (!headerIsSticky.value) {
                            OceanTabScrollable(
                                tabs = tabs,
                                defaultSelectedTab = currentItemIndex,
                                onSelectedTab = {
                                    onSelectedTab(it)
                                    coroutineScope.launch {
                                        lazyListState.animateScrollToItem(it + headerOffset)
                                    }
                                }
                            )
                        }
                    }

                    content()

                    if (footerContent != null) {
                        item {
                            footerContent()
                        }
                    }
                }
            )

            if (headerIsSticky.value) {
                OceanTabScrollable(
                    tabs = tabs,
                    defaultSelectedTab = currentItemIndex,
                    onSelectedTab = {
                        onSelectedTab(it)
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(it + headerOffset)
                        }
                    },
                    modifier = Modifier.align(Alignment.TopStart)
                )
            }
        }
    }
}
