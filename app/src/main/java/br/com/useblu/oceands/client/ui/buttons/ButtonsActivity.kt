package br.com.useblu.oceands.client.ui.buttons

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityButtonsBinding
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
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_buttons
        )
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ButtonsViewModel::class.java]
        binding.viewmodel = viewModel

        binding.toggleType.onToggledListener = { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
            viewModel.setButtonType(toggle.title.toString())
        }

        binding.toggleSize.onToggledListener = { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
            viewModel.setButtonSize(toggle.title.toString())
        }

        binding.toggleBlockedState.onToggledListener = { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
            viewModel.setWidthStateBlocked(toggle.id == R.id.toggle_width_blocked && toggle.isSelected)
        }

        viewModel.buttonType.observeForever { t ->
            Log.d("", t)
        }

        viewModel.buttonSize.observeForever { t ->
            Log.d("", t)
        }
    }

}

