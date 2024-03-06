package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons
import kotlinx.coroutines.launch


private val sections = mutableListOf<@Composable (() -> Unit)>(
    {
        Column {
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
            Text(text = "asdasdsa")
        }
    },
    {
        Column {
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
            OceanStep(1, 3)
        }
    },
    {
        Column {
            OceanShortcut(model = OceanShortcutModel(
                label = "MediumVertical Disabled",
                icon = OceanIcons.ACADEMIC_CAP_SOLID,
                layout = OceanShortcutLayout.MediumVertical,
                description = "Lorem ipsum dolor sit amet, consectetur.",
                disabled = true
            ))
            OceanShortcut(model = OceanShortcutModel(
                label = "MediumVertical Disabled",
                icon = OceanIcons.ACADEMIC_CAP_SOLID,
                layout = OceanShortcutLayout.MediumVertical,
                description = "Lorem ipsum dolor sit amet, consectetur.",
                disabled = true
            ))
            OceanShortcut(model = OceanShortcutModel(
                label = "MediumVertical Disabled",
                icon = OceanIcons.ACADEMIC_CAP_SOLID,
                layout = OceanShortcutLayout.MediumVertical,
                description = "Lorem ipsum dolor sit amet, consectetur.",
                disabled = true
            ))
            OceanShortcut(model = OceanShortcutModel(
                label = "MediumVertical Disabled",
                icon = OceanIcons.ACADEMIC_CAP_SOLID,
                layout = OceanShortcutLayout.MediumVertical,
                description = "Lorem ipsum dolor sit amet, consectetur.",
                disabled = true
            ))
        }
    }
)

@Preview
@Composable
fun OceanTabScrollableSectionPreview() {
    val tabs = listOf(
        OceanTabItemModel("Tab 1", 1),
        OceanTabItemModel("Tab 2", 2),
        OceanTabItemModel("Tab 3", 3),
        OceanTabItemModel("Tab 4", 4),
        OceanTabItemModel("Tab 5", 5),
    )

    var selectedTabIndex by remember { mutableStateOf(0) }

    OceanTheme {
        Column(
            Modifier
                .background(color = OceanColors.interfaceLightPure),
            verticalArrangement = Arrangement.Top
        ) {
            OceanTabScrollableSection(
                modifier = Modifier.fillMaxSize(),
                tabs = tabs,
                defaultSelectedTab = selectedTabIndex,
                onSelectedTab = { selectedTabIndex = it },
                sections = {
                    item {
                        sections[0]()
                    }

                    item {
                        sections[1]()
                    }

                    item {
                        sections[2]()
                    }
                }
            )
        }
    }
}

@Composable
fun OceanTabScrollableSection(
    modifier: Modifier = Modifier,
    tabs: List<OceanTabItemModel>,
    defaultSelectedTab: Int,
    onSelectedTab: (Int) -> Unit,
    sections: LazyListScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        OceanTabScrollable(
            tabs = tabs,
            currentSelectedTab = defaultSelectedTab,
            onSelectedTab = {
                onSelectedTab(it)
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(it)
                }
            }
        )

        LaunchedEffect(key1 = lazyListState.firstVisibleItemIndex) {
            onSelectedTab(lazyListState.firstVisibleItemIndex)
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = lazyListState,
            content = sections
        )
    }
}