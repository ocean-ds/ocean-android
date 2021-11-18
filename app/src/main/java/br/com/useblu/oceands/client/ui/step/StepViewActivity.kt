package br.com.useblu.oceands.client.ui.step

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityShortcutsBinding
import br.com.useblu.oceands.client.databinding.ActivityStepViewBinding
import br.com.useblu.oceands.client.ui.shortcuts.ShortcutsViewModel

class StepViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStepViewBinding
    private lateinit var viewModel: StepViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_step_view)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[StepViewViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.loadData()
    }

}
