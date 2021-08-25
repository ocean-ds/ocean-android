package br.com.useblu.oceands.client.ui.carousel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarouselViewModel: ViewModel() {

    private val _entries = MutableLiveData<List<String>>()
    val entries: LiveData<List<String>> get() = _entries

    private val _entries2 = MutableLiveData<List<String>>()
    val entries2: LiveData<List<String>> get() = _entries2

    private val _entries3 = MutableLiveData<List<String>>()
    val entries3: LiveData<List<String>> get() = _entries3

    val itemSelected = MutableLiveData<String>()
    val itemSelected2 = MutableLiveData<String>()
    val itemSelected3 = MutableLiveData<String>()

    var items = listOf(
        "https://cdn.pixabay.com/photo/2015/12/15/09/02/banner-1093898_960_720.jpg",
        "https://cdn.pixabay.com/photo/2016/04/20/07/20/logo-1340526_960_720.png",
        "https://cdn.pixabay.com/photo/2016/04/20/07/20/logo-1340526_960_720.png",
        "https://png.pngtree.com/background/20210710/original/pngtree-low-polygon-geometric-banner-background-picture-image_977922.jpg"
    )

    var items2 = listOf(
        "https://as1.ftcdn.net/v2/jpg/02/82/97/30/500_F_282973093_6VkMlmUG3XCXGNXF0RQRHsYvuzV5kBch.jpg",
        "https://as2.ftcdn.net/v2/jpg/02/40/73/83/500_F_240738367_rfTqBgkm2lYpg1Wy6NmYUWsAGyuRuUxc.jpg"
    )

    var items3 = listOf(
        "https://as1.ftcdn.net/v2/jpg/00/82/76/32/500_F_82763224_Pf0grqhdk1YJCuri0jkDPFscV7sqvVxA.jpg",
        "https://as1.ftcdn.net/v2/jpg/01/93/33/48/500_F_193334887_JvSbuaPkyQK62yJ3nIlwb6gijcy4wda5.jpg"
    )

    fun loadData() {
        _entries.postValue(items)
        _entries2.postValue(items2)
        _entries3.postValue(items3)
    }

    fun itemSelect(position: Int) {
        itemSelected.postValue(items[position])
    }

    fun itemSelect2(position: Int) {
        itemSelected2.postValue(items[position])
    }

    fun itemSelect3(position: Int) {
        itemSelected3.postValue(items[position])
    }

}