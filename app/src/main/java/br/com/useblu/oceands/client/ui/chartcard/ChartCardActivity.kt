package br.com.useblu.oceands.client.ui.chartcard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityChartCardBinding
import br.com.useblu.oceands.components.compose.chart.OceanChartCard
import br.com.useblu.oceands.model.chart.OceanChartModel

class ChartCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChartCardBinding
    private lateinit var viewModel: ChartCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chart_card)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ChartCardViewModel::class.java]
        binding.viewmodel = viewModel

        binding.composeView.setContent {
            val model = viewModel.oceanDonutLiveData.observeAsState(OceanChartModel()).value

            OceanChartCard(
                title = "ChartCard em Compose",
                subtitle = "Subtitle",
                model = model,
                actionTitle = "Call to Action",
                callToAction = viewModel::click
            )
        }
    }
}
