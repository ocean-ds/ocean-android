package br.com.useblu.oceands.client.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import br.com.useblu.oceands.*
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityHomeBinding
import br.com.useblu.oceands.client.ui.alert.AlertActivity
import br.com.useblu.oceands.client.ui.badges.BadgesActivity
import br.com.useblu.oceands.client.ui.balance.BalanceActivity
import br.com.useblu.oceands.client.ui.buttons.ButtonsActivity
import br.com.useblu.oceands.client.ui.cardcontent.CardContentActivity
import br.com.useblu.oceands.client.ui.carousel.CarouselActivity
import br.com.useblu.oceands.client.ui.chips.ChipsActivity
import br.com.useblu.oceands.client.ui.input.InputActivity
import br.com.useblu.oceands.client.ui.listitem.ListItemActivity
import br.com.useblu.oceands.client.ui.optionscard.OptionsCardActivity
import br.com.useblu.oceands.client.ui.radio.RadioActivity
import br.com.useblu.oceands.client.ui.shortcuts.ShortcutsActivity
import br.com.useblu.oceands.client.ui.listsubheader.ListSubheaderActivity
import br.com.useblu.oceands.client.ui.step.StepViewActivity
import br.com.useblu.oceands.client.ui.switchs.SwitchsActivity
import br.com.useblu.oceands.client.ui.tag.TagActivity
import br.com.useblu.oceands.client.ui.textlink.TextLinkActivity
import br.com.useblu.oceands.client.ui.toobar.TopbarActivity
import br.com.useblu.oceands.client.ui.transactionlistitem.TransactionListItemActivity
import br.com.useblu.oceands.client.ui.typography.TypographyActivity

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

    fun onClickButtons(view: View) {
        val intent = Intent(this, ButtonsActivity::class.java)
        startActivity(intent)
    }

    fun onClickInputs(view: View) {
        val intent = Intent(this, InputActivity::class.java)
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

    fun listItems(view: View) {
        val intent = Intent(this, ListItemActivity::class.java)
        startActivity(intent)
    }

    fun listSubheaderClick(view: View) {
        val intent = Intent(this, ListSubheaderActivity::class.java)
        startActivity(intent)
    }

    fun cardContentClick(view: View) {
        val intent = Intent(this, CardContentActivity::class.java)
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

    fun onClickSwitch(view: View) {
        val intent = Intent(this, SwitchsActivity::class.java)
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

    fun onOceanBottomListSheet(view: View) {
        val options = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        OceanBottomListSheet(this)
            .withTitle("Title")
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

    fun onClickTooltip(view: View) {
        val message = getString(R.string.message)
        val tooltip = OceanTooltip(
            context = this
        ).withMessage(message)
            .build()

        tooltip.show(binding.tooltip)
        tooltip.dismissWithDelay(1000)
    }
}
