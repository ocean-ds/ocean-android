package br.com.useblu.oceands.client.ui.shortcuts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityShortcutsBinding

class ShortcutsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShortcutsBinding
    private lateinit var viewModel: ShortcutsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shortcuts)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[ShortcutsViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.loadData()
    }
}