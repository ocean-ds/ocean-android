package br.com.useblu.oceands.client.ui.carouselwithcomponents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.components.compose.content.OceanCardGroup
import br.com.useblu.oceands.model.compose.carouselwithcomponents.OceanCarouselComponentItem

class CarouselViewWithComponentsModel : ViewModel() {

    private val _entries = MutableLiveData<List<OceanCarouselComponentItem>>()
    val entries: LiveData<List<OceanCarouselComponentItem>> = _entries

    private val _entries2 = MutableLiveData<List<OceanCarouselComponentItem>>()
    val entries2: LiveData<List<OceanCarouselComponentItem>> = _entries2

    private val _entries3 = MutableLiveData<List<OceanCarouselComponentItem>>()
    val entries3: LiveData<List<OceanCarouselComponentItem>> = _entries3

    private val _entries4 = MutableLiveData<List<OceanCarouselComponentItem>>()
    val entries4: LiveData<List<OceanCarouselComponentItem>> = _entries4

    private var items = generateCards(count = 5)
    private var items2 = generateCards(count = 4)
    private var items3 = generateCards(count = 3)
    private var items4 = generateCards(count = 1)

    fun loadData() {
        _entries.postValue(items)
        _entries2.postValue(items2)
        _entries3.postValue(items3)
        _entries4.postValue(items4)
    }

    private fun generateCards(count: Int): List<OceanCarouselComponentItem> {
        return (1..count).map { createCardExample(it) }
    }

    private fun createCardExample(index: Int): OceanCarouselComponentItem {
        return OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item $index",
                    subtitle = "Subtitle $index",
                    caption = "Caption $index",
                    actionTitle = "Item $index Action",
                    actionClick = {println("OceanCarouselItem $index selected")},
                )
            },
            action = {
                println("OceanCarouselItem $index selected")
            }
        )
    }
}