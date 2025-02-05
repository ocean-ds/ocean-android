package br.com.useblu.oceands.components.compose.expandabletextlisticonitem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.expandabletextlisticonitem.model.OceanExpandableTextListIconItemChild
import br.com.useblu.oceands.components.compose.expandabletextlisticonitem.model.OceanExpandableTextListIconItemChildType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons
import br.com.useblu.oceands.utils.SwipeBox

@Preview
@Composable
fun OceanExpandableTextListIconItemPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(OceanColors.interfaceLightPure)
                .verticalScroll(rememberScrollState())
        ) {
            OceanExpandableTextListIconItem(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                title = "Title",
                collapsed = true,
                childsItems = listOf(1, 2, 3).map {
                    OceanExpandableTextListIconItemChild(
                        icon = OceanIcons.PLACEHOLDER_SOLID,
                        title = "Title $it",
                        description = "Description $it",
                        key = it
                    )
                }
            )

            OceanExpandableTextListIconItem(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                title = "Title",
                description = "Description",
                collapsed = true,
                childs = OceanExpandableTextListIconItemChildType.Default(
                    items = listOf(1, 2, 3).map {
                        OceanExpandableTextListIconItemChild(
                            icon = OceanIcons.PLACEHOLDER_SOLID,
                            title = "Title $it",
                            description = "Description $it",
                            key = it
                        )
                    },
                    actionType = OceanExpandableTextListIconItemChildType.Default.ActionType.Custom(
                        icon = OceanIcons.PLACEHOLDER_SOLID
                    )
                )
            )

            OceanExpandableTextListIconItem(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                title = "Title",
                description = "Description",
                collapsed = true,
                childs = OceanExpandableTextListIconItemChildType.WithSwipe(
                    items = listOf(1, 2, 3).map {
                        OceanExpandableTextListIconItemChild(
                            icon = OceanIcons.PLACEHOLDER_SOLID,
                            title = "Title $it",
                            description = "Description $it",
                            key = it
                        )
                    },
                    contentSize = 100.dp,
                    content = { key ->
                        Box(
                            modifier = Modifier
                                .background(OceanColors.highlightPure)
                                .width(100.dp)
                        ) {
                            OceanText(text = "Content $key")
                        }
                    }
                )
            )

            OceanExpandableTextListIconItem(
                icon = OceanIcons.PLACEHOLDER_SOLID,
                title = "Title",
                description = "Description",
                collapsed = false,
                childs = OceanExpandableTextListIconItemChildType.Default(
                    items = listOf(1, 2, 3).map {
                        OceanExpandableTextListIconItemChild(
                            icon = OceanIcons.PLACEHOLDER_SOLID,
                            title = "Title $it",
                            description = "Description $it",
                            key = it
                        )
                    },
                    actionType = OceanExpandableTextListIconItemChildType.Default.ActionType.Edit(
                        options = listOf(1, 2, 3).map {
                            OceanExpandableTextListIconItemChildType.Default.ActionType.Edit.Option(
                                title = "Option $it",
                                color = if (it == 3) OceanColors.statusWarningPure else Color.Unspecified
                            )
                        },
                        onClick = { index -> println("Clicked on option $index") }
                    )
                )
            )
        }
    }
}

@Composable
fun <ChildReferenceKey> OceanExpandableTextListIconItem(
    icon: OceanIcons?,
    title: String,
    description: String?,
    collapsed: Boolean = true,
    childs: OceanExpandableTextListIconItemChildType<ChildReferenceKey>,
    onClick: (OceanExpandableTextListIconItemChild<ChildReferenceKey>) -> Unit = {}
) {
    var isCollapsed by remember { mutableStateOf(collapsed) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HeaderItem(
            modifier = Modifier
                .clickable { isCollapsed = isCollapsed.not() },
            icon = icon,
            title = title,
            description = description,
            collapsed = isCollapsed,
        )
        AnimatedVisibility(
            visible = isCollapsed.not()
        ) {
            Column {
                childs.items.forEach { child ->
                    ChildItem(setup = childs, item = child, onClick = onClick)
                }
            }
        }
    }
}

@Composable
private fun HeaderItem(
    modifier: Modifier = Modifier,
    icon: OceanIcons?,
    title: String,
    description: String?,
    collapsed: Boolean
) {
    Row(
        modifier = modifier
            .padding(horizontal = OceanSpacing.xxs)
            .padding(vertical = OceanSpacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            OceanIcon(
                modifier = Modifier
                    .padding(end = OceanSpacing.xxs),
                iconType = it
            )
        }

        Column(
            modifier = Modifier
                .height(40.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OceanText(
                text = title,
                style = OceanTextStyle.paragraph,
                color = OceanColors.interfaceDarkDeep
            )
            description?.let {
                OceanText(
                    text = it,
                    style = OceanTextStyle.description
                )
            }
        }

        Spacer(
            modifier = Modifier
                .weight(1f)
                .padding(end = OceanSpacing.xxs)
        )

        OceanIcon(
            modifier = Modifier
                .size(20.dp),
            iconType = if (collapsed) OceanIcons.CHEVRON_DOWN_SOLID else OceanIcons.CHEVRON_UP_SOLID
        )
    }
}

@Composable
private fun <ChildReferenceKey>ChildItem(
    setup: OceanExpandableTextListIconItemChildType<ChildReferenceKey>,
    item: OceanExpandableTextListIconItemChild<ChildReferenceKey>,
    onClick: (OceanExpandableTextListIconItemChild<ChildReferenceKey>) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClick(item) }
    ) {
        when (setup) {
            is OceanExpandableTextListIconItemChildType.Default -> DefaultChildItem(
                setup = setup,
                item = item
            )

            is OceanExpandableTextListIconItemChildType.WithSwipe -> WithSwipeChildItem(
                setup = setup,
                item = item
            )
        }
    }
}

@Composable
private fun <ChildReferenceKey>DefaultChildItem(
    setup: OceanExpandableTextListIconItemChildType.Default<ChildReferenceKey>,
    item: OceanExpandableTextListIconItemChild<ChildReferenceKey>,
) {
    Row(
        modifier = Modifier
            .padding(start = OceanSpacing.sm, end = OceanSpacing.xxs)
            .padding(vertical = OceanSpacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.icon?.let {
            OceanIcon(
                modifier = Modifier
                    .padding(end = OceanSpacing.xxs),
                iconType = it

            )
        }

        Column(
            modifier = Modifier
                .height(40.dp),
        ) {
            OceanText(
                text = item.title,
                style = OceanTextStyle.paragraph,
            )

            item.description?.let {
                OceanText(
                    text = it,
                    style = OceanTextStyle.description
                )
            }
        }

        Spacer(
            modifier = Modifier
                .weight(1f)
                .padding(end = OceanSpacing.xxs)
        )

        when (setup.actionType) {
            is OceanExpandableTextListIconItemChildType.Default.ActionType.Edit -> {
                var expanded by remember { mutableStateOf(false) }
                Column {
                    IconButton(
                        onClick = { expanded = true }
                    ) {
                        OceanIcon(
                            iconType = OceanIcons.DOTS_VERTICAL_SOLID
                        )
                    }

                    DropdownMenu(
                        modifier = Modifier
                            .background(OceanColors.interfaceLightPure),
                        expanded = expanded,
                        onDismissRequest = { expanded = false}
                    ) {
                        setup.actionType.options.forEachIndexed { index, option ->
                            DropdownMenuItem(
                                text = {
                                    OceanText(
                                        text = option.title,
                                        style = OceanTextStyle.description,
                                        color = option.color ?: OceanColors.interfaceDarkDown
                                    )
                                },
                                onClick = {
                                    expanded = false
                                    setup.actionType.onClick(index)
                                } ,
                            )
                        }
                    }
                }
            }
            is OceanExpandableTextListIconItemChildType.Default.ActionType.Custom -> {
                setup.actionType.icon?.let {
                    OceanIcon(
                        iconType = it
                    )
                }
            }
        }
    }
}

@Composable
private fun <ChildReferenceKey>WithSwipeChildItem(
    setup: OceanExpandableTextListIconItemChildType.WithSwipe<ChildReferenceKey>,
    item: OceanExpandableTextListIconItemChild<ChildReferenceKey>
) {
    SwipeBox(
        content = {
            Row(
                modifier = Modifier
                    .padding(start = OceanSpacing.sm, end = OceanSpacing.xxs)
                    .padding(vertical = OceanSpacing.xs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item.icon?.let {
                    OceanIcon(
                        modifier = Modifier
                            .padding(end = OceanSpacing.xxs),
                        iconType = it
                    )
                }

                Column(
                    modifier = Modifier
                        .height(40.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    OceanText(
                        text = item.title,
                        style = OceanTextStyle.paragraph
                    )

                    item.description?.let {
                        OceanText(
                            text = it,
                            style = OceanTextStyle.description
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = OceanSpacing.xxs)
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_swipe),
                    contentDescription = null,
                )
            }
        },
        draggedLeftContent = {
            Box { setup.content(item.key) }
        },
        draggedContentSize = setup.contentSize
    )
}

@Composable
fun <ChildReferenceKey> OceanExpandableTextListIconItem(
    icon: OceanIcons?,
    title: String,
    description: String? = null,
    collapsed: Boolean = true,
    childsItems: List<OceanExpandableTextListIconItemChild<ChildReferenceKey>>,
) {
    OceanExpandableTextListIconItem(
        icon = icon,
        title = title,
        description = description,
        collapsed = collapsed,
        childs = OceanExpandableTextListIconItemChildType.Default(
            items = childsItems
        )
    )
}



