package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanTabItemModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

@Preview
@Composable
fun OceanTabPreview() {
    val tabs = listOf(
        OceanTabItemModel("Tab 1", 1),
        OceanTabItemModel("Tab 2", 2),
        OceanTabItemModel("Tab 3", 3)
    )

    var selectedTabIndex by remember { mutableStateOf(0) }

    MaterialTheme {
        Column(
            Modifier
                .background(color = OceanColors.interfaceLightPure)
                .height(200.dp)
        ) {
            OceanTab(
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                onSelectedTab = { selectedTabIndex = it }
            )
        }
    }
}

@Preview
@Composable
private fun OceanTabScrollablePreview() {
    val tabs = listOf(
        OceanTabItemModel("Tab 1", 1),
        OceanTabItemModel("Tab 2", 2, OceanBadgeType.WARNING),
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
            OceanTabScrollable(
                tabs = tabs,
                defaultSelectedTab = selectedTabIndex,
                onSelectedTab = { selectedTabIndex = it }
            )
        }
    }
}

@Composable
fun OceanTab(
    modifier: Modifier = Modifier,
    tabs: List<OceanTabItemModel>,
    selectedTabIndex: Int,
    onSelectedTab: (Int) -> Unit
) {
    var selectedTabIndexInternal by remember(selectedTabIndex) {
        mutableStateOf(selectedTabIndex)
    }

    TabRow(
        selectedTabIndex = selectedTabIndexInternal,
        modifier = modifier
            .background(color = OceanColors.interfaceLightPure)
            .fillMaxWidth(),
        indicator = { tabPositions ->
            if (selectedTabIndexInternal < tabPositions.size) {
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndexInternal]),
                    color = OceanColors.brandPrimaryPure
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            val selected = index == selectedTabIndexInternal
            Tab(
                selected = selected,
                modifier = Modifier
                    .background(color = OceanColors.interfaceLightPure)
                    .height(56.dp),
                onClick = {
                    onSelectedTab(index)
                    selectedTabIndexInternal = index
                },
                text = {
                    TabText(
                        tab,
                        selected,
                        tab.counter,
                        tab.badgeType
                    )
                }
            )
        }
    }
}

@Composable
fun OceanTabScrollable(
    modifier: Modifier = Modifier,
    tabs: List<OceanTabItemModel>,
    defaultSelectedTab: Int,
    onSelectedTab: (Int) -> Unit
) {
    ScrollableTabRow(
        modifier = modifier
            .background(color = OceanColors.interfaceLightPure)
            .fillMaxWidth(),
        edgePadding = TabRowDefaults.ScrollableTabRowEdgeStartPadding,
        selectedTabIndex = defaultSelectedTab,
        indicator = { tabPositions ->
            if (defaultSelectedTab < tabPositions.size) {
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[defaultSelectedTab]),
                    color = OceanColors.brandPrimaryPure
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            val selected = index == defaultSelectedTab
            Tab(
                selected = selected,
                modifier = Modifier
                    .background(color = OceanColors.interfaceLightPure)
                    .height(56.dp),
                onClick = {
                    onSelectedTab(index)
                },
                text = {
                    TabText(
                        tab,
                        selected,
                        tab.counter,
                        tab.badgeType
                    )
                }
            )
        }
    }
}

@Composable
private fun TabText(
    tab: OceanTabItemModel,
    selected: Boolean,
    counter: Int?,
    badgeType: OceanBadgeType = OceanBadgeType.PRIMARY
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = tab.label,
            style = OceanTextStyle.heading4,
            color = if (selected) OceanColors.brandPrimaryPure else OceanColors.interfaceDarkUp
        )

        if (tab.counter != null) {
            OceanSpacing.StackXXS()

            OceanBadge(
                text = counter.toString(),
                type = if (selected) badgeType else OceanBadgeType.DISABLED,
                size = OceanBadgeSize.Small
            )
        }
    }
}
