package br.com.useblu.oceands.client.ui.step

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityStepViewBinding

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
