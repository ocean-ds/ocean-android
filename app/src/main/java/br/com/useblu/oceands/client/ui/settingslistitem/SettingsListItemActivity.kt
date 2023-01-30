package br.com.useblu.oceands.client.ui.settingslistitem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivitySettingsListItemBinding

class SettingsListItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsListItemBinding
    private lateinit var viewModel: SettingsListItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings_list_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[SettingsListItemViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
