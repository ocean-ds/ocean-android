package br.com.useblu.oceands.client.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import br.com.useblu.oceands.adapter.OceanBottomListSheetAdapter
import br.com.useblu.oceands.adapter.OceanUnorderedListAdapter
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityHomeBinding
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
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.model.OceanUnorderedListItem
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun onClickTypography(view: View) {
        val intent = Intent(this, TypographyActivity::class.java)
        startActivity(intent)
    }

    fun onClickBottomNavigation(view: View) {
        val intent = Intent(this, BottomNavigationActivity::class.java)
        startActivity(intent)
    }

    fun onClickUnorderedListItem(view: View) {
        val intent = Intent(this, UnorderedListItemActivity::class.java)
        startActivity(intent)
    }

    fun onClickButtons(view: View) {
        val intent = Intent(this, ButtonsActivity::class.java)
        startActivity(intent)
    }

    fun onClickCta(view: View) {
        val intent = Intent(this, GroupCTAActivity::class.java)
        startActivity(intent)
    }

    fun onClickHeaderApp(view: View) {
        val intent = Intent(this, HeaderAppActivity::class.java)
        startActivity(intent)
    }

    fun onClickInputs(view: View) {
        val intent = Intent(this, InputActivity::class.java)
        startActivity(intent)
    }

    fun onClickAccordion(view: View) {
        val intent = Intent(this, AccordionActivity::class.java)
        startActivity(intent)
    }

    fun onClickAlert(view: View) {
        val intent = Intent(this, AlertActivity::class.java)
        startActivity(intent)
    }

    fun onClickBadges(view: View) {
        val intent = Intent(this, BadgesActivity::class.java)
        startActivity(intent)
    }

    fun onClickChips(view: View) {
        val intent = Intent(this, ChipsActivity::class.java)
        startActivity(intent)
    }

    fun onClickBalance(view: View) {
        val intent = Intent(this, BalanceActivity::class.java)
        startActivity(intent)
    }

    fun topBarClick(view: View) {
        val intent = Intent(this, TopbarActivity::class.java)
        startActivity(intent)
    }

    fun transactionListClick(view: View) {
        val intent = Intent(this, TransactionListItemActivity::class.java)
        startActivity(intent)
    }

    fun listItemsExpandable(view: View) {
        val intent = Intent(this, TextListExpandableActivity::class.java)
        startActivity(intent)
    }

    fun listItemsSettings(view: View) {
        val intent = Intent(this, SettingsListItemActivity::class.java)
        startActivity(intent)
    }

    fun listSubheaderClick(view: View) {
        val intent = Intent(this, ListSubheaderActivity::class.java)
        startActivity(intent)
    }

    fun listOrderedClick(view: View) {
        val intent = Intent(this, OrderedListItemActivity::class.java)
        startActivity(intent)
    }

    fun cardContentClick(view: View) {
        val intent = Intent(this, CardGroupActivity::class.java)
        startActivity(intent)
    }

    fun cardCrossCellClick(view: View) {
        val intent = Intent(this, CardCrossSellActivity::class.java)
        startActivity(intent)
    }

    fun informativeCardClick(view: View) {
        val intent = Intent(this, InformativeCardActivity::class.java)
        startActivity(intent)
    }

    fun carousel(view: View) {
        val intent = Intent(this, CarouselActivity::class.java)
        startActivity(intent)
    }

    fun shortcuts(view: View) {
        val intent = Intent(this, ShortcutsActivity::class.java)
        startActivity(intent)
    }

    fun stepview(view: View) {
        val intent = Intent(this, StepViewActivity::class.java)
        startActivity(intent)
    }

    fun statusListItem(view: View) {
        val intent = Intent(this, StatusListItemActivity::class.java)
        startActivity(intent)
    }

    fun textListIconItem(view: View) {
        val intent = Intent(this, TextListIconItemActivity::class.java)
        startActivity(intent)
    }

    fun textListItem(view: View) {
        val intent = Intent(this, TextListItemActivity::class.java)
        startActivity(intent)
    }

    fun textListInvertedItem(view: View) {
        val intent = Intent(this, TextListInvertedItemActivity::class.java)
        startActivity(intent)
    }

    fun textListInlineItem(view: View) {
        val intent = Intent(this, TextListInlineItemActivity::class.java)
        startActivity(intent)
    }

    fun onClickSwitch(view: View) {
        val intent = Intent(this, SwitchsActivity::class.java)
        startActivity(intent)
    }

    fun onClickTab(view: View) {
        val intent = Intent(this, TabActivity::class.java)
        startActivity(intent)
    }

    fun onClickTags(view: View) {
        val intent = Intent(this, TagActivity::class.java)
        startActivity(intent)
    }

    fun onClickRadio(view: View) {
        val intent = Intent(this, RadioActivity::class.java)
        startActivity(intent)
    }

    fun onClickTextLink(view: View) {
        val intent = Intent(this, TextLinkActivity::class.java)
        startActivity(intent)
    }

    fun clickOptionsCard(view: View) {
        val intent = Intent(this, OptionsCardActivity::class.java)
        startActivity(intent)
    }

    fun clickProgressBar(view: View) {
        val intent = Intent(this, ProgressBarActivity::class.java)
        startActivity(intent)
    }

    fun checkbox(view: View) {
        val intent = Intent(this, CheckBoxActivity::class.java)
        startActivity(intent)
    }

    fun transactionFooter(view: View) {
        val intent = Intent(this, TransactionFooterActivity::class.java)
        startActivity(intent)
    }

    fun descriptorList(view: View) {
        val intent = Intent(this, DescriptorListActivity::class.java)
        startActivity(intent)
    }

    fun donutView(view: View) {
        val intent = Intent(this, DonutActivity::class.java)
        startActivity(intent)
    }

    fun detailedCardClick(view: View) {
        val intent = Intent(this, DetailedCardActivity::class.java)
        startActivity(intent)
    }

    fun chartCardClick(view: View) {
        val intent = Intent(this, ChartCardActivity::class.java)
        startActivity(intent)
    }

    fun onClickBottomSheetVertical(view: View) {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_black)
            .withOrientationButtons(OceanBottomSheet.Orientation.Vertical)
            .withDismiss(true)
            .withActionPositive(R.string.all_button_confirm) {
            }
            .withActionNegative(R.string.all_button_cancel) {
            }
            .show()
    }

    fun onClickBottomSheetHorizontal(view: View) {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_black)
            .withOrientationButtons(OceanBottomSheet.Orientation.Horizontal)
            .withDismiss(true)
            .withActionPositive(R.string.all_button_confirm) {
            }
            .withActionNegative(R.string.all_button_cancel) {
            }
            .show()
    }

    fun onClickBottomSheetCritical(view: View) {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_black)
            .withOrientationButtons(OceanBottomSheet.Orientation.Horizontal)
            .withDismiss(true)
            .withCritical(true)
            .withActionPositive(R.string.all_button_confirm) {
            }
            .withActionNegative(R.string.all_button_cancel) {
            }
            .show()
    }
    
    fun onClickBottomSheetWithCompose(view: View) {
        OceanBottomSheetCompose()
            .withComposeContent { 
                OceanIcon(token = "barcodeoutline", modifier = Modifier.size(40.dp))
                OceanSpacing.StackXS()
                Text(text = "Texto de teste", style = OceanTextStyle.heading3)
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

    fun onOceanBottomListSheet(view: View) {
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

    fun onOceanBottomListSheetWithBodyIcon(view: View) {
        val options = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        OceanBottomListSheet(this)
            .withBodyIcon(getDrawable(R.drawable.ocean_icon_retailer_outline))
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

    fun onOceanBottomListSheetWithGenericList(view: View) {

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

    fun onOceanBottomListSheetButton(view: View) {
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
                icon = getDrawable(R.drawable.ocean_icon_retailer_outline),
                click = { showToast(showLoading) },
                loading = showLoading,
                useSecondaryStyle = true
            ).show()
    }

    fun onOceanBottomListSheetButtonWithCaption(view: View) {
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
        GlobalScope.launch {
            delay(6000)
            loading.postValue(false)
        }
    }


    fun onOceanBottomListSheetIcon(view: View) {
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

    fun onOceanBottomListSheetWithSearch(view: View) {
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

    fun onOceanDatePickerFullScreen(view: View) {
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

    fun onClickToast(view: View) {
        OceanToast(this)
            .withType(OceanToast.OceanToastType.Warning)
            .withMessage(R.string.message)
            .show()
    }

    fun onClickSnackBar(view: View) {
        OceanSnackBar(
            binding.container,
            getString(R.string.lorem_ipsum),
            OceanSnackBar.OceanSnackBarType.Error
        ).show()
    }

    fun onClickSnackBarAction(view: View) {
        OceanSnackBar(
            binding.container,
            getString(R.string.lorem_ipsum),
            OceanSnackBar.OceanSnackBarType.Success,
            getString(R.string.help),
            View.OnClickListener { println("ok") }
        ).show()
    }

    fun onClickTooltip(view: View) {
        val message = getString(R.string.message)
        val tooltip = OceanTooltip(context = this)
            .withMessage(message)
            .withClick {
                Toast.makeText(this, "Select Tooltip", Toast.LENGTH_LONG).show()
            }
            .withAutoDismissDuration(1000)
            .build()

        tooltip.showAlignTop(binding.tooltip)
    }

    fun onFooterBlu(view: View) {
        val intent = Intent(this, FooterBluActivity::class.java)
        startActivity(intent)
    }

    fun onClickCardItem(view: View) {
        val intent = Intent(this, CardItemActivity::class.java)
        startActivity(intent)
    }
}
