package br.com.useblu.oceands.client.ui.carousel

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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCarouselBinding
import br.com.useblu.oceands.components.compose.OceanCarousel
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.model.OceanCarouselItem

class CarouselActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarouselBinding
    private lateinit var viewModel: CarouselViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_carousel)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[CarouselViewModel::class.java]

        viewModel.loadData()

        setContent {
            CarouselActivityContent(
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
private fun CarouselActivityContentPreview() {
    CarouselActivityContent(
        emptyList(), emptyList(), emptyList(), emptyList()
    )
}

@Composable
private fun CarouselActivityContent(
    firstEntries: List<OceanCarouselItem>,
    secondEntries: List<OceanCarouselItem>,
    thirdEntries: List<OceanCarouselItem>,
    fourEntries: List<OceanCarouselItem>,
) {
    OceanTheme {
        Column(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Default", modifier = Modifier.padding(horizontal = 16.dp))
            OceanCarousel(
                firstEntries
            )

            Text(text = "Without page indicator", modifier = Modifier.padding(horizontal = 16.dp))
            OceanCarousel(
                secondEntries,
                showPageIndicator = false
            )

            Text(text = "Auto cycle", modifier = Modifier.padding(horizontal = 16.dp))
            OceanCarousel(
                thirdEntries,
                autoCycle = true
            )

            Text(text = "One item", modifier = Modifier.padding(horizontal = 16.dp))
            OceanCarousel(
                fourEntries
            )
        }
    }
}