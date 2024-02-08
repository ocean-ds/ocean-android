package br.com.useblu.oceands.client.ui.carousel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.OceanCarouselItem

class CarouselViewModel : ViewModel() {

    private val _entries = MutableLiveData<List<OceanCarouselItem>>()
    val entries: LiveData<List<OceanCarouselItem>> = _entries

    private val _entries2 = MutableLiveData<List<OceanCarouselItem>>()
    val entries2: LiveData<List<OceanCarouselItem>> = _entries2

    private val _entries3 = MutableLiveData<List<OceanCarouselItem>>()
    val entries3: LiveData<List<OceanCarouselItem>> = _entries3

    var items = listOf(
        OceanCarouselItem(
            url = "https://cdn.pixabay.com/photo/2015/12/15/09/02/banner-1093898_960_720.jpg",
            action = {
                println("OceanCarouselItem 1 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://cdn.pixabay.com/photo/2016/04/20/07/20/logo-1340526_960_720.png",
            action = {
                println("OceanCarouselItem 2 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://cdn.pixabay.com/photo/2016/04/20/07/20/logo-1340526_960_720.png",
            action = {
                println("OceanCarouselItem 3 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://png.pngtree.com/background/20210710/original/pngtree-low-polygon-geometric-banner-background-picture-image_977922.jpg",
            action = {
                println("OceanCarouselItem 4 selected")
            }
        ),
    )

    var items2 = listOf(
        OceanCarouselItem(
            url = "https://as1.ftcdn.net/v2/jpg/02/82/97/30/500_F_282973093_6VkMlmUG3XCXGNXF0RQRHsYvuzV5kBch.jpg",
            action = {
                println("OceanCarouselItem 1 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://as2.ftcdn.net/v2/jpg/02/40/73/83/500_F_240738367_rfTqBgkm2lYpg1Wy6NmYUWsAGyuRuUxc.jpg",
            action = {
                println("OceanCarouselItem 2 selected")
            }
        ),
    )

    var items3 = listOf(
        OceanCarouselItem(
            url = "https://as1.ftcdn.net/v2/jpg/00/82/76/32/500_F_82763224_Pf0grqhdk1YJCuri0jkDPFscV7sqvVxA.jpg",
            action = {
                println("OceanCarouselItem 1 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://as1.ftcdn.net/v2/jpg/01/93/33/48/500_F_193334887_JvSbuaPkyQK62yJ3nIlwb6gijcy4wda5.jpg",
            action = {
                println("OceanCarouselItem 2 selected")
            }
        )
    )

    fun loadData() {
        _entries.postValue(items)
        _entries2.postValue(items2)
        _entries3.postValue(items3)
    }

}