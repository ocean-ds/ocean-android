package br.com.useblu.oceands.client.ui.chartbar

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.chart.OceanChartBar
import br.com.useblu.oceands.model.chart.OceanChartBarModel
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

class ChartBarActivity : AppCompatActivity() {
    private lateinit var viewModel: ChartBarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ChartBarViewModel::class.java]

        setContent {
            val model = viewModel.chartLiveData.observeAsState(OceanChartBarModel()).value

            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                OceanText(text = "Title", style = OceanTextStyle.heading4)

                OceanSpacing.StackXXXS()

                OceanText(text = "Subtitle", style = OceanTextStyle.description)

                OceanSpacing.StackXS()

                OceanChartBar(
                    model = model,
                    modifier = Modifier.height(180.dp)
                )
            }
        }
    }
}