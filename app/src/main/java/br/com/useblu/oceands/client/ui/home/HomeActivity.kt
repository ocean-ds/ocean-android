package br.com.useblu.oceands.client.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import br.com.useblu.oceands.adapter.OceanBottomListSheetAdapter
import br.com.useblu.oceands.adapter.OceanUnorderedListAdapter
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.ui.accordion.AccordionActivity
import br.com.useblu.oceands.client.ui.alert.AlertActivity
import br.com.useblu.oceands.client.ui.badges.BadgesActivity
import br.com.useblu.oceands.client.ui.balance.BalanceActivity
import br.com.useblu.oceands.client.ui.bottomnavigation.BottomNavigationActivity
import br.com.useblu.oceands.client.ui.buttons.ButtonsActivity
import br.com.useblu.oceands.client.ui.cardgroup.CardGroupActivity
import br.com.useblu.oceands.client.ui.carditem.CardItemActivity
import br.com.useblu.oceands.client.ui.carousel.CarouselActivity
import br.com.useblu.oceands.client.ui.chartcard.ChartCardActivity
import br.com.useblu.oceands.client.ui.checkbox.CheckBoxActivity
import br.com.useblu.oceands.client.ui.chips.ChipsActivity
import br.com.useblu.oceands.client.ui.crosscellcard.CardCrossSellActivity
import br.com.useblu.oceands.client.ui.descriptorlistitem.DescriptorListActivity
import br.com.useblu.oceands.client.ui.detailed.DetailedCardActivity
import br.com.useblu.oceands.client.ui.donut.DonutActivity
import br.com.useblu.oceands.client.ui.footer.FooterBluActivity
import br.com.useblu.oceands.client.ui.groupcta.GroupCTAActivity
import br.com.useblu.oceands.client.ui.headerapp.HeaderAppActivity
import br.com.useblu.oceands.client.ui.informativecard.InformativeCardActivity
import br.com.useblu.oceands.client.ui.input.InputActivity
import br.com.useblu.oceands.client.ui.listsubheader.ListSubheaderActivity
import br.com.useblu.oceands.client.ui.optionscard.OptionsCardActivity
import br.com.useblu.oceands.client.ui.orderedlistitem.OrderedListItemActivity
import br.com.useblu.oceands.client.ui.progressbar.ProgressBarActivity
import br.com.useblu.oceands.client.ui.radio.RadioActivity
import br.com.useblu.oceands.client.ui.settingslistitem.SettingsListItemActivity
import br.com.useblu.oceands.client.ui.shortcuts.ShortcutsActivity
import br.com.useblu.oceands.client.ui.statuslistitem.StatusListItemActivity
import br.com.useblu.oceands.client.ui.step.StepViewActivity
import br.com.useblu.oceands.client.ui.switchs.SwitchsActivity
import br.com.useblu.oceands.client.ui.tab.TabActivity
import br.com.useblu.oceands.client.ui.tag.TagActivity
import br.com.useblu.oceands.client.ui.textlink.TextLinkActivity
import br.com.useblu.oceands.client.ui.textlistexpandable.TextListExpandableActivity
import br.com.useblu.oceands.client.ui.textlisticonitem.TextListIconItemActivity
import br.com.useblu.oceands.client.ui.textlistinline.TextListInlineItemActivity
import br.com.useblu.oceands.client.ui.textlistinverted.TextListInvertedItemActivity
import br.com.useblu.oceands.client.ui.textlistitem.TextListItemActivity
import br.com.useblu.oceands.client.ui.toobar.TopbarActivity
import br.com.useblu.oceands.client.ui.transactionfooter.TransactionFooterActivity
import br.com.useblu.oceands.client.ui.transactionlistitem.TransactionListItemActivity
import br.com.useblu.oceands.client.ui.typography.TypographyActivity
import br.com.useblu.oceands.client.ui.unorderedlistitem.UnorderedListItemActivity
import br.com.useblu.oceands.components.OceanBottomListSheet
import br.com.useblu.oceands.components.OceanBottomListSheetUIModel
import br.com.useblu.oceands.components.OceanBottomSheet
import br.com.useblu.oceands.components.OceanBottomSheetCompose
import br.com.useblu.oceands.components.OceanDatePickerFullscreen
import br.com.useblu.oceands.components.OceanSnackBar
import br.com.useblu.oceands.components.OceanToast
import br.com.useblu.oceands.components.OceanTooltip
import br.com.useblu.oceands.components.compose.BottomSheetButtonsOrientation
import br.com.useblu.oceands.components.compose.OceanBottomSheet
import br.com.useblu.oceands.components.compose.OceanBottomSheetModel
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.model.OceanUnorderedListItem
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class HomeActivity : AppCompatActivity() {

    private lateinit var view: View

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            view = LocalView.current

            val showBottomSheet = remember {
                mutableStateOf(false)
            }

            if (showBottomSheet.value) {
                OceanBottomSheet(
                    model = OceanBottomSheetModel(
                        title = "Title",
                        message = "Message",
                        subMessage = "SubMessage",
                        imageUrl = "https://portal-cicloentrada.blu.com.br/assets/icons/coin_trail-cc541831a7fbf4d215f3910fb241b14701f5ab0f79d574ad3a6e12379b7e871e.png",
                        code = 2000,
                        actionPositive = "Aceitar" to {},
                        actionNegative = "Cancelar" to {},
                        buttonsOrientation = BottomSheetButtonsOrientation.Vertical
                    ),
                    showBottomSheet = showBottomSheet
                )
            }

            LazyColumn {
                textAction(text = "Accordion", onClick = { onClickAccordion() })
                textAction(text = "Alerts", onClick = { onClickAlert() })
                textAction(text = "Badges", onClick = { onClickBadges() })
                textAction(text = "Balance", onClick = { onClickBalance() })
                textAction(text = "Buttons", onClick = { onClickButtons() })
                textAction(text = "Bottom Navigation", onClick = { onClickBottomNavigation() })
                textAction(text = "BottomSheet with Image", onClick = { onClickBottomSheetImage() })
                textAction(text = "BottomSheet Vertical", onClick = { onClickBottomSheetVertical() })
                textAction(text = "BottomSheet Vertical with Code", onClick = { onClickBottomSheetImageVerticalWithCode() })
                textAction(text = "BottomSheet Horizontal", onClick = { onClickBottomSheetHorizontal() })
                textAction(text = "BottomSheet Critical", onClick = { onClickBottomSheetCritical() })
                textAction(text = "BottomSheet Compose Content", onClick = { onClickBottomSheetWithCompose() })
                textAction(text = "BottomSheet 100% Compose", onClick = { showBottomSheet.value = true })
                textAction(text = "BottomSheetList", onClick = { onOceanBottomListSheet() })
                textAction(text = "BottomSheetList (body Icon)", onClick = { onOceanBottomListSheetWithBodyIcon() })
                textAction(text = "BottomSheetList (button)", onClick = { onOceanBottomListSheetButton() })
                textAction(text = "BottomSheetList (button with caption)", onClick = { onOceanBottomListSheetButtonWithCaption() })
                textAction(text = "BottomSheetList (icon)", onClick = { onOceanBottomListSheetIcon() })
                textAction(text = "BottomSheetList (icon + subtitle)", onClick = { onOceanBottomListSheetIcon() })
                textAction(text = "BottomSheetList (Generic List)", onClick = { onOceanBottomListSheetWithGenericList() })
                textAction(text = "BottomSheetList (search)", onClick = { onOceanBottomListSheetWithSearch() })
                textAction(text = "Card Cross Cell", onClick = { cardCrossCellClick() })
                textAction(text = "Card Group", onClick = { cardContentClick() })
                textAction(text = "Card Item", onClick = { onClickCardItem() })
                textAction(text = "Carousel", onClick = { carousel() })
                textAction(text = "Chart Card", onClick = { chartCardClick() })
                textAction(text = "Checkbox", onClick = { checkbox() })
                textAction(text = "Chips", onClick = { onClickChips() })
                textAction(text = "DatePicker Fullscreen", onClick = { onOceanDatePickerFullScreen() })
                textAction(text = "Descriptor List Item", onClick = { descriptorList() })
                textAction(text = "Detailed Card", onClick = { detailedCardClick() })
                textAction(text = "Donut", onClick = { donutView() })
                textAction(text = "Footer Blu", onClick = { onFooterBlu() })
                textAction(text = "Group CTA", onClick = { onClickCta() })
                textAction(text = "Header App", onClick = { onClickHeaderApp() })
                textAction(text = "Informative Card", onClick = { informativeCardClick() })
                textAction(text = "Input", onClick = { onClickInputs() })
                textAction(text = "Options Card", onClick = { clickOptionsCard() })
                textAction(text = "Progress Bar", onClick = { clickProgressBar() })
                textAction(text = "Radio", onClick = { onClickRadio() })
                textAction(text = "Shortcuts", onClick = { shortcuts() })
                textAction(text = "SnackBar", onClick = { onClickSnackBar(view) })
                textAction(text = "SnackBar + Action", onClick = { onClickSnackBarAction(view) })
                textAction(text = "Status List Item", onClick = { statusListItem() })
                textAction(text = "Step", onClick = { stepview() })
                textAction(text = "Switch", onClick = { onClickSwitch() })
                textAction(text = "Tab", onClick = { onClickTab() })
                textAction(text = "Tag", onClick = { onClickTags() })
                textAction(text = "Text Link", onClick = { onClickTextLink() })
                textAction(text = "Text List Expandable", onClick = { listItemsExpandable() })
                textAction(text = "Text List Icon Item", onClick = { textListIconItem() })
                textAction(text = "Text List Item", onClick = { textListItem() })
                textAction(text = "Text List Inverted Item", onClick = { textListInvertedItem() })
                textAction(text = "Text List Inline Item", onClick = { textListInlineItem() })
                textAction(text = "Text List Settings", onClick = { listItemsSettings() })
                textAction(text = "List Ordered", onClick = { listOrderedClick() })
                textAction(text = "List Subheader", onClick = { listSubheaderClick() })
                textAction(text = "Toast", onClick = { onClickToast() })
                textAction(text = "Tooltip", onClick = { onClickTooltip(view) })
                textAction(text = "TopBar", onClick = { topBarClick() })
                textAction(text = "Transaction List", onClick = { transactionListClick() })
                textAction(text = "Transaction Footer", onClick = { transactionFooter() })
                textAction(text = "Typography", onClick = { onClickTypography() })
                textAction(text = "Unordered List Item", onClick = { onClickUnorderedListItem() })
            }
        }
    }

    private fun LazyListScope.textAction(
        text: String,
        onClick: () -> Unit
    ) {
        item {
            Text(
                text = text,
                modifier = Modifier
                    .clickable { onClick() }
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 24.sp,
                color = OceanColors.interfaceDarkDown
            )
            Divider()
        }
    }

    @Composable
    private fun Divider() {
        Divider(
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 12.dp),
            color = Color(0xFFE7E7E7)
        )
    }

    private fun onClickTypography() {
        val intent = Intent(this, TypographyActivity::class.java)
        startActivity(intent)
    }

    private fun onClickBottomNavigation() {
        val intent = Intent(this, BottomNavigationActivity::class.java)
        startActivity(intent)
    }

    private fun onClickUnorderedListItem() {
        val intent = Intent(this, UnorderedListItemActivity::class.java)
        startActivity(intent)
    }

    private fun onClickButtons() {
        val intent = Intent(this, ButtonsActivity::class.java)
        startActivity(intent)
    }

    private fun onClickCta() {
        val intent = Intent(this, GroupCTAActivity::class.java)
        startActivity(intent)
    }

    private fun onClickHeaderApp() {
        val intent = Intent(this, HeaderAppActivity::class.java)
        startActivity(intent)
    }

    private fun onClickInputs() {
        val intent = Intent(this, InputActivity::class.java)
        startActivity(intent)
    }

    private fun onClickAccordion() {
        val intent = Intent(this, AccordionActivity::class.java)
        startActivity(intent)
    }

    private fun onClickAlert() {
        val intent = Intent(this, AlertActivity::class.java)
        startActivity(intent)
    }

    private fun onClickBadges() {
        val intent = Intent(this, BadgesActivity::class.java)
        startActivity(intent)
    }

    private fun onClickChips() {
        val intent = Intent(this, ChipsActivity::class.java)
        startActivity(intent)
    }

    private fun onClickBalance() {
        val intent = Intent(this, BalanceActivity::class.java)
        startActivity(intent)
    }

    private fun topBarClick() {
        val intent = Intent(this, TopbarActivity::class.java)
        startActivity(intent)
    }

    private fun transactionListClick() {
        val intent = Intent(this, TransactionListItemActivity::class.java)
        startActivity(intent)
    }

    private fun listItemsExpandable() {
        val intent = Intent(this, TextListExpandableActivity::class.java)
        startActivity(intent)
    }

    private fun listItemsSettings() {
        val intent = Intent(this, SettingsListItemActivity::class.java)
        startActivity(intent)
    }

    private fun listSubheaderClick() {
        val intent = Intent(this, ListSubheaderActivity::class.java)
        startActivity(intent)
    }

    private fun listOrderedClick() {
        val intent = Intent(this, OrderedListItemActivity::class.java)
        startActivity(intent)
    }

    private fun cardContentClick() {
        val intent = Intent(this, CardGroupActivity::class.java)
        startActivity(intent)
    }

    private fun cardCrossCellClick() {
        val intent = Intent(this, CardCrossSellActivity::class.java)
        startActivity(intent)
    }

    private fun informativeCardClick() {
        val intent = Intent(this, InformativeCardActivity::class.java)
        startActivity(intent)
    }

    private fun carousel() {
        val intent = Intent(this, CarouselActivity::class.java)
        startActivity(intent)
    }

    private fun shortcuts() {
        val intent = Intent(this, ShortcutsActivity::class.java)
        startActivity(intent)
    }

    private fun stepview() {
        val intent = Intent(this, StepViewActivity::class.java)
        startActivity(intent)
    }

    private fun statusListItem() {
        val intent = Intent(this, StatusListItemActivity::class.java)
        startActivity(intent)
    }

    private fun textListIconItem() {
        val intent = Intent(this, TextListIconItemActivity::class.java)
        startActivity(intent)
    }

    private fun textListItem() {
        val intent = Intent(this, TextListItemActivity::class.java)
        startActivity(intent)
    }

    private fun textListInvertedItem() {
        val intent = Intent(this, TextListInvertedItemActivity::class.java)
        startActivity(intent)
    }

    private fun textListInlineItem() {
        val intent = Intent(this, TextListInlineItemActivity::class.java)
        startActivity(intent)
    }

    private fun onClickSwitch() {
        val intent = Intent(this, SwitchsActivity::class.java)
        startActivity(intent)
    }

    private fun onClickTab() {
        val intent = Intent(this, TabActivity::class.java)
        startActivity(intent)
    }

    private fun onClickTags() {
        val intent = Intent(this, TagActivity::class.java)
        startActivity(intent)
    }

    private fun onClickRadio() {
        val intent = Intent(this, RadioActivity::class.java)
        startActivity(intent)
    }

    private fun onClickTextLink() {
        val intent = Intent(this, TextLinkActivity::class.java)
        startActivity(intent)
    }

    private fun clickOptionsCard() {
        val intent = Intent(this, OptionsCardActivity::class.java)
        startActivity(intent)
    }

    private fun clickProgressBar() {
        val intent = Intent(this, ProgressBarActivity::class.java)
        startActivity(intent)
    }

    private fun checkbox() {
        val intent = Intent(this, CheckBoxActivity::class.java)
        startActivity(intent)
    }

    private fun transactionFooter() {
        val intent = Intent(this, TransactionFooterActivity::class.java)
        startActivity(intent)
    }

    private fun descriptorList() {
        val intent = Intent(this, DescriptorListActivity::class.java)
        startActivity(intent)
    }

    private fun donutView() {
        val intent = Intent(this, DonutActivity::class.java)
        startActivity(intent)
    }

    private fun detailedCardClick() {
        val intent = Intent(this, DetailedCardActivity::class.java)
        startActivity(intent)
    }

    private fun chartCardClick() {
        val intent = Intent(this, ChartCardActivity::class.java)
        startActivity(intent)
    }

    private fun onClickBottomSheetImage() {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withImage("https://portal-cicloentrada.blu.com.br/assets/icons/coin_trail-cc541831a7fbf4d215f3910fb241b14701f5ab0f79d574ad3a6e12379b7e871e.png")
            .withOrientationButtons(OceanBottomSheet.Orientation.Vertical)
            .withDismiss(true)
            .withActionPositive(R.string.all_button_confirm) {}
            .withActionNegative(R.string.all_button_cancel) {}
            .show()
    }

    private fun onClickBottomSheetVertical() {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_black)
            .withOrientationButtons(OceanBottomSheet.Orientation.Vertical)
            .withDismiss(true)
            .withActionPositive(R.string.all_button_confirm) {}
            .withActionNegative(R.string.all_button_cancel) {}
            .show()
    }

    private fun onClickBottomSheetImageVerticalWithCode() {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message a huge text to test the bottom sheet behavior on deal with multiple lines")
            .withIcon(R.drawable.icon_blu_plus)
            .withCode("9999")
            .withOrientationButtons(OceanBottomSheet.Orientation.Vertical)
            .withDismiss(true)
            .withActionPositive(R.string.all_button_confirm) {}
            .withActionNegative(R.string.all_button_cancel) {}
            .show()
    }

    private fun onClickBottomSheetHorizontal() {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_black)
            .withOrientationButtons(OceanBottomSheet.Orientation.Horizontal)
            .withDismiss(true)
            .withActionPositive(R.string.all_button_confirm) {}
            .withActionNegative(R.string.all_button_cancel) {}
            .show()
    }

    private fun onClickBottomSheetCritical() {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_black)
            .withOrientationButtons(OceanBottomSheet.Orientation.Horizontal)
            .withDismiss(true)
            .withCritical(true)
            .withActionPositive(R.string.all_button_confirm) {}
            .withActionNegative(R.string.all_button_cancel) {}
            .show()
    }

    private fun onClickBottomSheetWithCompose() {
        OceanBottomSheetCompose()
            .withComposeContent {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OceanIcon(
                        iconType = OceanIcons.BARCODE_OUTLINE,
                        modifier = Modifier.size(40.dp)
                    )
                    OceanSpacing.StackXS()
                    Text(
                        text = "Texto de teste",
                        style = OceanTextStyle.heading3
                    )
                }
            }
            .withDismiss(true)
            .withCritical(true)
            .withOrientationButtons(OceanBottomSheetCompose.Orientation.Vertical)
            .withCode("300")
            .withActionPositive("Confirm") {
            }
            .withActionNegative("Cancel") {
            }
            .show(supportFragmentManager, "BottomSheetCompose")
    }

    private fun onOceanBottomListSheet() {
        val options = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        OceanBottomListSheet(this)
            .withTitle("Title")
            .withSimpleList(
                items = options,
                selectedPosition = 1,
                onItemSelect = {
                    Toast.makeText(
                        this,
                        "O Item selecionado foi \"${options[it]}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ).show()
    }

    private fun onOceanBottomListSheetWithBodyIcon() {
        val options = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        OceanBottomListSheet(this)
            .withBodyIcon(ContextCompat.getDrawable(this, R.drawable.ocean_icon_retailer_outline))
            .withTitle("Title")
            .withSimpleList(
                items = options,
                selectedPosition = 1,
                onItemSelect = {
                    Toast.makeText(
                        this,
                        "O Item selecionado foi \"${options[it]}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ).show()
    }

    private fun onOceanBottomListSheetWithGenericList() {

        val bottomSheet = OceanBottomListSheet(this)

        val adapter = OceanUnorderedListAdapter(
            items = listOf(
                OceanUnorderedListItem(
                    title = "Item 1",
                    icon = "chevronrightsolid",
                    needsRoundBackgroundIcon = true,
                    needLeadingSpacer = true,
                    needTrailingSpacer = true,
                    iconColor = R.color.ocean_color_status_negative_pure,
                ),
                OceanUnorderedListItem(
                    title = "Item 2",
                    icon = "chevronrightsolid",
                    needsRoundBackgroundIcon = true,
                    needLeadingSpacer = true,
                    needTrailingSpacer = true,
                    iconColor = R.color.ocean_color_status_warning_pure,
                    roundBackgroundColor = R.color.ocean_color_status_warning_up
                )
            )
        )

        bottomSheet.apply {
            withTitle("Title")
            withCustomList(adapter)
        }.show()
    }

    private fun onOceanBottomListSheetButton() {
        val showLoading = MutableLiveData(false)
        val options = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        OceanBottomListSheet(this)
            .withTitle("Title")
            .withSimpleList(
                items = options,
                selectedPosition = 1,
                onItemSelect = {
                    Toast.makeText(
                        this,
                        "O Item selecionado foi \"${options[it]}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
            .withFooterButton(
                text = getString(R.string.all_button_confirm),
                icon = ContextCompat.getDrawable(this, R.drawable.ocean_icon_retailer_outline),
                click = { showToast(showLoading) },
                loading = showLoading,
                useSecondaryStyle = true
            ).show()
    }

    private fun onOceanBottomListSheetButtonWithCaption() {
        val showLoading = MutableLiveData(false)
        val options = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")


        val bottomSheet = OceanBottomListSheet(this)
        val adapter = OceanBottomListSheetAdapter(
            oceanBottomSheet = bottomSheet,
            items = options,
            selected = 1,
            onSelect = {
                Toast.makeText(
                    this,
                    "O Item selecionado foi \"${options[it]}\"",
                    Toast.LENGTH_SHORT
                ).show()
                bottomSheet.dismiss()
            },
            title = "Title",
        )

        bottomSheet
            .withTitle("Title")
            .withCustomList(adapter)
            .withFooterButton(
                text = getString(R.string.all_button_confirm),
                click = { showToast(showLoading) },
                loading = showLoading
            )
            .withCaption("Lembre-se de salvar o documento em um local onde consiga encontrar com facilidade.")
            .show()
    }

    private fun showToast(loading: MutableLiveData<Boolean>) {
        loading.postValue(true)
        Toast.makeText(
            this,
            "Footer button clicked",
            Toast.LENGTH_SHORT
        ).show()
        lifecycleScope.launch {
            delay(6000)
            loading.postValue(false)
        }
    }


    private fun onOceanBottomListSheetIcon() {
        val drawableIcon = ContextCompat.getDrawable(this, R.drawable.icon_generic_primary)!!
        val options = listOf(
            OceanBottomListSheetUIModel(drawableIcon, "Title 1", "description 1"),
            OceanBottomListSheetUIModel(drawableIcon, "Title 2", "description 2"),
            OceanBottomListSheetUIModel(drawableIcon, "Title 3", "description 3"),
        )
        OceanBottomListSheet(this)
            .withTitle("Title")
            .withCustomList(
                items = options,
                onItemSelect = {
                    Toast.makeText(
                        this,
                        "O Item selecionado foi \"${options[it].title}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ).show()
    }

    private fun onOceanBottomListSheetWithSearch() {
        val options = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        OceanBottomListSheet(this)
            .withTitle("Title")
            .withHint("Hint Sample")
            .withSearch(
                manager = supportFragmentManager,
                limit = 3,
            )
            .withSimpleList(
                items = options,
                onItemSelect = {
                    Toast.makeText(
                        this,
                        "O Item selecionado foi \"${options[it]}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ).show()
    }

    private fun onOceanDatePickerFullScreen() {
        val calendarMinDate = Calendar.getInstance()
        calendarMinDate.time = Date()

        val calendarMaxDate = Calendar.getInstance()
        calendarMaxDate.time = Date()
        calendarMaxDate.add(Calendar.MONTH, 5)

        val calendarDefaultSelected = Calendar.getInstance()
        calendarDefaultSelected.time = Date()
        calendarDefaultSelected.add(Calendar.MONTH, 1)

        val disableDay1 = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2022)
            set(Calendar.MONTH, Calendar.JUNE)
            set(Calendar.DAY_OF_MONTH, 28)
        }

        val disabledDay2 = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2022)
            set(Calendar.MONTH, Calendar.JULY)
            set(Calendar.DAY_OF_MONTH, 12)
        }

        val disabledDay3 = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2022)
            set(Calendar.MONTH, Calendar.AUGUST)
            set(Calendar.DAY_OF_MONTH, 13)
        }

        OceanDatePickerFullscreen(supportFragmentManager)
            .withTitle("Teste de título 2")
            .withMinDate(calendarMinDate)
            .withMaxDate(calendarMaxDate)
            .withDefaultSelect(calendarDefaultSelected)
            .withDisabledDays(arrayOf(disableDay1, disabledDay2, disabledDay3))
            .withOnConfirm { calendar ->
                OceanToast(this)
                    .withType(OceanToast.OceanToastType.Warning)
                    .withMessage(calendar.time.toString())
                    .show()
            }
            .show()

    }

    private fun onClickToast() {
        OceanToast(this)
            .withType(OceanToast.OceanToastType.Warning)
            .withMessage(R.string.message)
            .show()
    }

    private fun onClickSnackBar(view: View) {
        OceanSnackBar(
            view,
            getString(R.string.lorem_ipsum),
            OceanSnackBar.OceanSnackBarType.Error
        ).show()
    }

    private fun onClickSnackBarAction(view: View) {
        OceanSnackBar(
            container = view,
            message = getString(R.string.lorem_ipsum),
            type = OceanSnackBar.OceanSnackBarType.Success,
            actionText = getString(R.string.help),
            actionClick = { println("ok") }
        ).show()
    }

    private fun onClickTooltip(view: View) {
        val message = getString(R.string.message)
        val tooltip = OceanTooltip(context = this)
            .withMessage(message)
            .withClick {
                Toast.makeText(this, "Select Tooltip", Toast.LENGTH_LONG).show()
            }
            .withAutoDismissDuration(1000)
            .build()

        tooltip.showAlignTop(view)
    }

    private fun onFooterBlu() {
        val intent = Intent(this, FooterBluActivity::class.java)
        startActivity(intent)
    }

    private fun onClickCardItem() {
        val intent = Intent(this, CardItemActivity::class.java)
        startActivity(intent)
    }
}
