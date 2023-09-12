package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
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
        OceanTabItemModel("Oportunidades", 1),
        OceanTabItemModel("Tab 2", 2)
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

@Composable
fun OceanTab(
    tabs: List<OceanTabItemModel>,
    selectedTabIndex: Int,
    onSelectedTab: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier
            .fillMaxWidth(),
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = OceanColors.brandPrimaryPure
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            val selected = index == selectedTabIndex
            Tab(
                selected = selected,
                modifier = Modifier
                    .background(color = OceanColors.interfaceLightPure)
                    .height(56.dp),
                onClick = { onSelectedTab(index) },
                text = {
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
                                text = tab.counter.toString(),
                                type = if (selected) OceanBadgeType.PRIMARY else OceanBadgeType.DISABLED,
                                size = OceanBadgeSize.Small
                            )
                        }
                    }
                }
            )
        }
    }
}