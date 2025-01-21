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

    private val _entries4 = MutableLiveData<List<OceanCarouselItem>>()
    val entries4: LiveData<List<OceanCarouselItem>> = _entries4

    var items = listOf(
        OceanCarouselItem(
            url = "https://portal-cicloentrada.blu.com.br/assets/banners/banner_fornecedores_natal_2024-d8cc0b848ce20406eae96f2514fcd5a53e0d7f7996cd20586da151adabb77be5.png",
            action = {
                println("OceanCarouselItem 1 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://portal-cicloentrada.blu.com.br/assets/banners/banner_fornecedores_natal_2024-d8cc0b848ce20406eae96f2514fcd5a53e0d7f7996cd20586da151adabb77be5.png",
            action = {
                println("OceanCarouselItem 2 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://portal-cicloentrada.blu.com.br/assets/banners/banner_fornecedores_natal_2024-d8cc0b848ce20406eae96f2514fcd5a53e0d7f7996cd20586da151adabb77be5.png",
            action = {
                println("OceanCarouselItem 3 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://portal-cicloentrada.blu.com.br/assets/banners/banner_fornecedores_natal_2024-d8cc0b848ce20406eae96f2514fcd5a53e0d7f7996cd20586da151adabb77be5.png",
            action = {
                println("OceanCarouselItem 4 selected")
            }
        ),
    )

    var items2 = listOf(
        OceanCarouselItem(
            url = "https://portal-cicloentrada.blu.com.br/assets/banners/banner_fornecedores_natal_2024-d8cc0b848ce20406eae96f2514fcd5a53e0d7f7996cd20586da151adabb77be5.png",
            action = {
                println("OceanCarouselItem 1 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://portal-cicloentrada.blu.com.br/assets/banners/banner_fornecedores_natal_2024-d8cc0b848ce20406eae96f2514fcd5a53e0d7f7996cd20586da151adabb77be5.png",
            action = {
                println("OceanCarouselItem 2 selected")
            }
        ),
    )

    var items3 = listOf(
        OceanCarouselItem(
            url = "https://portal-cicloentrada.blu.com.br/assets/banners/banner_fornecedores_natal_2024-d8cc0b848ce20406eae96f2514fcd5a53e0d7f7996cd20586da151adabb77be5.png",
            action = {
                println("OceanCarouselItem 1 selected")
            }
        ),
        OceanCarouselItem(
            url = "https://portal-cicloentrada.blu.com.br/assets/banners/banner_fornecedores_natal_2024-d8cc0b848ce20406eae96f2514fcd5a53e0d7f7996cd20586da151adabb77be5.png",
            action = {
                println("OceanCarouselItem 2 selected")
            }
        )
    )

    var items4 = listOf(
        OceanCarouselItem(
            url = "https://portal-cicloentrada.blu.com.br/assets/banners/banner_fornecedores_natal_2024-d8cc0b848ce20406eae96f2514fcd5a53e0d7f7996cd20586da151adabb77be5.png",
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