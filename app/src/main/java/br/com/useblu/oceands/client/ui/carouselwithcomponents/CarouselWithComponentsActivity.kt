package br.com.useblu.oceands.client.ui.carouselwithcomponents

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.components.compose.OceanCarouselWithComponents
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.model.compose.carouselwithcomponents.OceanCarouselComponentItem
import br.com.useblu.oceands.model.compose.carouselwithcomponents.OceanCarouselCycle
import br.com.useblu.oceands.model.compose.carouselwithcomponents.OceanCarouselIndicator

class CarouselWithComponentsActivity : AppCompatActivity() {

    private lateinit var viewModel: CarouselViewWithComponentsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[CarouselViewWithComponentsModel::class.java]

        viewModel.loadData()

        setContent {
            CarouselWithComponentsActivityContent(
                viewModel.entries.observeAsState(initial = emptyList()).value,
                viewModel.entries2.observeAsState(initial = emptyList()).value,
                viewModel.entries3.observeAsState(initial = emptyList()).value,
                viewModel.entries4.observeAsState(initial = emptyList()).value
            )
        }
    }
}

@Preview
@Composable
private fun CarouselWithComponentsActivityContentPreview() {
    CarouselWithComponentsActivityContent(
        emptyList(), emptyList(), emptyList(), emptyList()
    )
}

@Composable
private fun CarouselWithComponentsActivityContent(
    firstEntries: List<OceanCarouselComponentItem>,
    secondEntries: List<OceanCarouselComponentItem>,
    thirdEntries: List<OceanCarouselComponentItem>,
    fourthEntries: List<OceanCarouselComponentItem>,
) {
    OceanTheme {
        Column(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Default", modifier = Modifier.padding(horizontal = 16.dp))
            OceanCarouselWithComponents(
                items = firstEntries
            )

            Text(text = "Without page indicator", modifier = Modifier.padding(horizontal = 16.dp))
            OceanCarouselWithComponents(
                items = secondEntries,
                indicator = OceanCarouselIndicator.NONE
            )

            Text(text = "Auto cycle", modifier = Modifier.padding(horizontal = 16.dp))
            OceanCarouselWithComponents(
                items = thirdEntries,
                cycle = OceanCarouselCycle.Auto(time = 1000)
            )

            Text(text = "Unique item", modifier = Modifier.padding(horizontal = 16.dp))
            OceanCarouselWithComponents(
                items = fourthEntries
            )
        }
    }
}