package br.com.useblu.oceands.components.compose

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Immutable
sealed interface OceanDropdownBehavior {
    data object Dropdown : OceanDropdownBehavior
    data class BottomSheet(
        val title: String
    ) : OceanDropdownBehavior
}

@Preview
@Composable
fun OceanDropDownMenuPreview() {
    OceanTheme {
        Column(
            modifier = Modifier
                .background(color = OceanColors.interfaceLightPure)
                .padding(16.dp),
        ) {
            val context = LocalContext.current
            val items = List(5) { "Item ${it + 1}" }

            OceanDropDownMenu(
                label = "Dropdown behavior",
                hint = "Hint",
                errorText = "",
                defaultOption = "Default Option",
                options = items,
            ) { position ->
                position?.let {
                    Toast
                        .makeText(context, "${items[it]} selected", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            OceanSpacing.StackSM()

            OceanDropDownMenu(
                label = "BottomSheet behavior",
                hint = "Hint",
                defaultOption = "Default Option",
                behavior = OceanDropdownBehavior.BottomSheet("Escolha uma opção"),
                options = items,
            ) { position ->
                position?.let {
                    Toast
                        .makeText(context, "${items[it]} selected", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            OceanSpacing.StackSM()

            OceanDropDownMenu(
                label = "Label",
                hint = "Hint",
                errorText = "Error",
                defaultOption = "Default Option",
                behavior = OceanDropdownBehavior.BottomSheet("Escolha uma opção"),
                options = items,
            ) { position ->
                position?.let {
                    Toast
                        .makeText(context, "${items[it]} selected", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}

@Composable
fun OceanDropDownMenu(
    modifier: Modifier = Modifier,
    label: String,
    hint: String = "",
    defaultOption: String,
    options: List<String>,
    errorText: String = "",
    behavior: OceanDropdownBehavior = OceanDropdownBehavior.Dropdown,
    onItemSelected: (Int?) -> Unit
) {
    when (behavior) {
        is OceanDropdownBehavior.Dropdown -> OceanDropdown(
            modifier = modifier,
            label = label,
            hint = hint,
            defaultOption = defaultOption,
            options = options,
            errorText = errorText,
            onItemSelected = onItemSelected
        )

        is OceanDropdownBehavior.BottomSheet -> OceanBottomSheetDropdown(
            modifier = modifier,
            title = behavior.title,
            label = label,
            hint = hint,
            defaultOption = defaultOption,
            options = options,
            errorText = errorText,
            onItemSelected = onItemSelected
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OceanDropdown(
    modifier: Modifier = Modifier,
    label: String,
    hint: String,
    defaultOption: String,
    options: List<String>,
    errorText: String = "",
    onItemSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var itemSelected by remember { mutableStateOf(defaultOption) }

    Column(
        modifier.background(color = OceanColors.interfaceLightPure),
    ) {
        OceanText(
            text = label,
            style = OceanTextStyle.description
        )
        ExposedDropdownMenuBox(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .wrapContentSize()
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = if(errorText.isBlank()) OceanColors.interfaceLightDown
                                else OceanColors.statusNegativePure
                    ),
                    shape = RoundedCornerShape(
                        topStart = 8.dp, topEnd = 8.dp,
                        bottomStart = if (expanded) 0.dp else 8.dp,
                        bottomEnd = if (expanded) 0.dp else 8.dp
                    ),
                ),
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            Row(
                modifier = Modifier
                    .menuAnchor()
                    .height(56.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OceanText(
                    modifier = Modifier.weight(9f),
                    text = itemSelected.ifBlank { defaultOption },
                    style = TextStyle(
                        color = OceanColors.interfaceDarkDeep,
                        fontFamily = OceanFontFamily.BaseRegular,
                        fontSize = OceanFontSize.xs
                    ),
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(
                        id = if (expanded) OceanIcons.CHEVRON_UP_OUTLINE.icon
                        else OceanIcons.CHEVRON_DOWN_OUTLINE.icon
                    ),
                    contentDescription = ""
                )
            }

            DropdownMenu(
                modifier = Modifier
                    .background(color = OceanColors.interfaceLightPure)
                    .exposedDropdownSize()
                    .height(200.dp),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        contentPadding = PaddingValues(16.dp),
                        text = {
                            OceanText(
                                text = item,
                                style = OceanTextStyle.paragraph
                            )
                        },
                        onClick = {
                            expanded = false
                            itemSelected = item
                            onItemSelected(index)
                        }
                    )
                }
            }
        }

        if (errorText.isNotBlank()) {
            OceanText(
                text = errorText,
                style = OceanTextStyle.error
            )
        }
        if(errorText.isBlank() && hint.isNotBlank()){
            OceanText(
                text = hint,
                style = OceanTextStyle.hint
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OceanBottomSheetDropdown(
    modifier: Modifier = Modifier,
    title: String,
    label: String,
    hint: String,
    defaultOption: String,
    options: List<String>,
    errorText: String = "",
    onItemSelected: (Int) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var itemSelected by remember { mutableStateOf(defaultOption) }
    var lastSelectedIndex by remember { mutableIntStateOf(-1) }

    if (showBottomSheet) {
        OceanBottomSheet(
            modifier = Modifier.padding(horizontal = 2.dp),
            model = OceanBottomSheetModel(
                customContent = bottomSheetContent(
                    title = title,
                    options = options,
                    lastSelectedIndex = lastSelectedIndex,
                    onItemSelected = { position, item ->
                        itemSelected = item
                        lastSelectedIndex = position
                        onItemSelected(position)
                        showBottomSheet = false
                    }
                ),
            ),

            onDismiss = { showBottomSheet = false }
        )
    }

    Column(
        modifier.background(color = OceanColors.interfaceLightPure),
    ) {
        OceanText(
            text = label,
            style = OceanTextStyle.description
        )
        Row (
            modifier = Modifier
                .padding(vertical = 8.dp)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = if(errorText.isBlank()) OceanColors.interfaceLightDown
                                else OceanColors.statusNegativePure
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                .height(56.dp)
                .fillMaxWidth()
                .clickable { showBottomSheet = true },
            verticalAlignment = Alignment.CenterVertically

        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                OceanText(
                    modifier = Modifier.weight(9f),
                    text = itemSelected.ifBlank { defaultOption },
                    style = TextStyle(
                        color = OceanColors.interfaceDarkDeep,
                        fontFamily = OceanFontFamily.BaseRegular,
                        fontSize = OceanFontSize.xs
                    ),
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(
                        id = if (showBottomSheet) OceanIcons.CHEVRON_UP_OUTLINE.icon
                        else OceanIcons.CHEVRON_DOWN_OUTLINE.icon
                    ),
                    contentDescription = ""
                )
            }
        }
        if (errorText.isNotBlank()) {
            OceanText(
                text = errorText,
                style = OceanTextStyle.error
            )
        }
        if(errorText.isBlank() && hint.isNotBlank()){
            OceanText(
                text = hint,
                style = OceanTextStyle.hint
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun bottomSheetContent(
    title: String,
    options: List<String>,
    lastSelectedIndex: Int,
    onItemSelected: (Int, String) -> Unit
): @Composable (SheetState) -> Unit = {
    var itemSelected by remember(lastSelectedIndex) { mutableIntStateOf(lastSelectedIndex) }

    OceanText(
        text = title,
        style = OceanTextStyle.heading3
    )
    OceanSpacing.StackSM()

    Column(
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        options.forEachIndexed { index, item ->
            if (itemSelected == index) {
                Row(
                    modifier = Modifier
                        .background(
                            color = OceanColors.interfaceLightUp,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .height(40.dp)
                        .fillMaxWidth()
                        .clickable {
                            itemSelected = index
                            onItemSelected(index, item)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OceanText(
                        text = item,
                        style = TextStyle(
                            color = OceanColors.brandPrimaryPure,
                            fontFamily = OceanFontFamily.BaseRegular,
                            fontSize = OceanFontSize.xs
                        )
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .background(color = OceanColors.interfaceLightPure)
                        .height(40.dp)
                        .fillMaxWidth()
                        .clickable {
                            itemSelected = index
                            onItemSelected(index, item)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OceanText(
                        text = item,
                        style = OceanTextStyle.paragraph
                    )
                }
            }
        }
    }
}
