package br.com.useblu.oceands.client.ui.chartcard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityChartCardBinding

class ChartCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChartCardBinding
    private lateinit var viewModel: ChartCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chart_card)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ChartCardViewModel::class.java]
        binding.viewmodel = viewModel
    }
}