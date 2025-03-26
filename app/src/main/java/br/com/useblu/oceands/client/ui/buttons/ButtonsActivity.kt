package br.com.useblu.oceands.client.ui.buttons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityButtonsBinding
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.utils.OceanIcons
import com.savvyapps.togglebuttonlayout.Toggle
import com.savvyapps.togglebuttonlayout.ToggleButtonLayout

class ButtonsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityButtonsBinding
    private lateinit var viewModel: ButtonsViewModel

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_buttons
        )
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ButtonsViewModel::class.java]
        binding.viewmodel = viewModel

        this.initToggleButtons()

        this.initToggleListeners()

        binding.composeView.setContent {
            val iconState = viewModel.isIconEnabled.observeAsState(false)
            val buttonState = viewModel.buttonState.observeAsState("Default")
            val blockState = viewModel.isWidthStateBlocked.observeAsState(false)
            val buttonType = viewModel.buttonType.observeAsState("Primary").value
            val buttonSize = viewModel.buttonSize.observeAsState("Large").value

            val modifier = if (blockState.value) {
                Modifier.fillMaxWidth()
            } else {
                Modifier.wrapContentWidth()
            }

            val buttonStyle = when (buttonType) {
                "Primary" -> {
                    when (buttonSize) {
                        "Large" -> OceanButtonStyle.PrimaryLarge
                        "Medium" -> OceanButtonStyle.PrimaryMedium
                        "Small" -> OceanButtonStyle.PrimarySmall
                        else -> OceanButtonStyle.PrimaryMedium
                    }
                }
                "Critical" -> {
                    when (buttonSize) {
                        "Large" -> OceanButtonStyle.PrimaryCriticalLarge
                        "Medium" -> OceanButtonStyle.PrimaryCriticalMedium
                        "Small" -> OceanButtonStyle.PrimaryCriticalSmall
                        else -> OceanButtonStyle.PrimaryCriticalMedium
                    }
                }
                "Secondary" -> {
                    when (buttonSize) {
                        "Large" -> OceanButtonStyle.SecondaryLarge
                        "Medium" -> OceanButtonStyle.SecondaryMedium
                        "Small" -> OceanButtonStyle.SecondarySmall
                        else -> OceanButtonStyle.SecondaryMedium
                    }
                }
                "Secondary Critical" -> {
                    when (buttonSize) {
                        "Large" -> OceanButtonStyle.SecondaryCriticalLarge
                        "Medium" -> OceanButtonStyle.SecondaryCriticalMedium
                        "Small" -> OceanButtonStyle.SecondaryCriticalSmall
                        else -> OceanButtonStyle.SecondaryCriticalMedium
                    }
                }
                "Inverse" -> {
                    when (buttonSize) {
                        "Large" -> OceanButtonStyle.PrimaryInverseLarge
                        "Medium" -> OceanButtonStyle.PrimaryInverseMedium
                        "Small" -> OceanButtonStyle.PrimaryInverseSmall
                        else -> OceanButtonStyle.PrimaryInverseMedium
                    }
                }
                "Text" -> {
                    when (buttonSize) {
                        "Large" -> OceanButtonStyle.TertiaryLarge
                        "Medium" -> OceanButtonStyle.TertiaryMedium
                        "Small" -> OceanButtonStyle.TertiarySmall
                        else -> OceanButtonStyle.TertiaryMedium
                    }
                }
                "Text Critical" -> {
                    when (buttonSize) {
                        "Large" -> OceanButtonStyle.TertiaryCriticalLarge
                        "Medium" -> OceanButtonStyle.TertiaryCriticalMedium
                        "Small" -> OceanButtonStyle.TertiaryCriticalSmall
                        else -> OceanButtonStyle.TertiaryCriticalMedium
                    }
                }
                else -> {
                    OceanButtonStyle.PrimaryMedium
                }
            }

            OceanButton(
                text = "Compose $buttonType Button",
                icon = if (iconState.value) OceanIcons.PLUS_CIRCLE_OUTLINE else null,
                showProgress = buttonState.value == "Loading",
                disabled = buttonState.value == "Disabled",
                buttonStyle = buttonStyle,
                modifier = modifier,
                onClick = {}
            )
        }
    }

    private fun initToggleButtons() {
        binding.toggleType.setToggled(R.id.toggle_type_primary, true)
        binding.toggleIconState.setToggled(R.id.toggle_icon_none, true)
        binding.toggleBlockedState.setToggled(R.id.toggle_width_default, true)
        binding.toggleState.setToggled(R.id.toggle_state_default, true)
        binding.toggleSize.setToggled(R.id.toggle_size_medium, true)

        viewModel.setButtonType("Primary")
        viewModel.setIconEnabled(false)
        viewModel.setWidthStateBlocked(false)
        viewModel.setState("Default")
        viewModel.setButtonSize("Medium")
    }

    private fun initToggleListeners() {
        binding.toggleType.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setButtonType(toggle.title.toString())
            }

        binding.toggleIconState.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setIconEnabled(toggle.id == R.id.toggle_icon_visible && toggle.isSelected)
            }

        binding.toggleBlockedState.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setWidthStateBlocked(toggle.id == R.id.toggle_width_blocked && toggle.isSelected)
            }

        binding.toggleState.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setState(toggle.title.toString())
            }

        binding.toggleSize.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setButtonSize(toggle.title.toString())
            }
    }
}
