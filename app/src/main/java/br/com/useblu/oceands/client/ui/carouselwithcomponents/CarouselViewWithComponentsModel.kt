package br.com.useblu.oceands.client.ui.carouselwithcomponents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.components.compose.content.OceanCardGroup
import br.com.useblu.oceands.model.OceanCarouselItem
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

    var items = listOf(
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 1",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 1 Action",
                    actionClick = {println("OceanCarouselItem 1 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 1 selected")
            }
        ),
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 2",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 2 Action",
                    actionClick = {println("OceanCarouselItem 2 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 2 selected")
            }
        ),
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 3",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 3 Action",
                    actionClick = {println("OceanCarouselItem 3 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 3 selected")
            }
        ),
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 4",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 4 Action",
                    actionClick = {println("OceanCarouselItem 4 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 4 selected")
            }
        ),
    )

    var items2 = listOf(
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 1",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 1 Action",
                    actionClick = {println("OceanCarouselItem 1 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 1 selected")
            }
        ),
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 2",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 2 Action",
                    actionClick = {println("OceanCarouselItem 2 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 2 selected")
            }
        ),
    )

    var items3 = listOf(
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 1",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 1 Action",
                    actionClick = {println("OceanCarouselItem 1 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 1 selected")
            }
        ),
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 2",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 2 Action",
                    actionClick = {println("OceanCarouselItem 2 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 2 selected")
            }
        ),
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 3",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 3 Action",
                    actionClick = {println("OceanCarouselItem 3 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 3 selected")
            }
        )
    )

    var items4 = listOf(
        OceanCarouselComponentItem(
            component = {
                OceanCardGroup(
                    title = "Item 1",
                    subtitle = "Subtitle",
                    caption = "Caption",
                    actionTitle = "Item 1 Action",
                    actionClick = {println("OceanCarouselItem 1 selected")},
                )
            },
            action = {
                println("OceanCarouselItem 1 selected")
            }
        )
    )

    fun loadData() {
        _entries.postValue(items)
        _entries2.postValue(items2)
        _entries3.postValue(items3)
        _entries4.postValue(items4)
    }

}