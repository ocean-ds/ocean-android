package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import br.com.useblu.oceands.ui.compose.OceanColors


private val sections = mutableListOf<@Composable (() -> Unit)>()

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

    MaterialTheme {
        Column(
            Modifier
                .background(color = OceanColors.interfaceLightPure)
                .height(200.dp)
        ) {
            OceanTabScrollableSection(
                tabs = tabs,
                defaultSelectedTab = selectedTabIndex,
                onSelectedTab = { selectedTabIndex = it }
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
) {
    var sectionSelected by remember { mutableStateOf(defaultSelectedTab) }

    OceanTabScrollable(
        modifier = modifier
            .fillMaxWidth(),
        tabs = tabs,
        defaultSelectedTab = defaultSelectedTab,
        onSelectedTab = {
            sectionSelected = it
            onSelectedTab(it)
        }
    )
    LazyColumn {
        items(sections.size) { index ->
            if (index == sectionSelected) {
                sections[index]()
            }
        }
    }
}

@Composable
fun addSection(
    content: @Composable () -> Unit
){
    sections.add(content)
}
