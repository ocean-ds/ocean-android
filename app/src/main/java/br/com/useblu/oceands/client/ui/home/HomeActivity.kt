package br.com.useblu.oceands.client.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import br.com.useblu.oceands.OceanBottomSheet
import br.com.useblu.oceands.OceanSnackBar
import br.com.useblu.oceands.OceanToast
import br.com.useblu.oceands.OceanTooltip
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityHomeBinding
import br.com.useblu.oceands.client.ui.alert.AlertActivity
import br.com.useblu.oceands.client.ui.buttons.ButtonsActivity
import br.com.useblu.oceands.client.ui.input.InputActivity
import br.com.useblu.oceands.client.ui.toobar.TopbarActivity
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

    fun topBarClick(view: View) {
        val intent = Intent(this, TopbarActivity::class.java)
        startActivity(intent)
    }


    fun onClickBottomSheet(view: View) {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_primary)
            .withOrientationButtons(OceanBottomSheet.Orientation.Vertical)
            .withDismiss(true)
            .withActionPositive(R.string.all_button_confirm) {
            }
            .withActionNegative(R.string.all_button_cancel) {
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