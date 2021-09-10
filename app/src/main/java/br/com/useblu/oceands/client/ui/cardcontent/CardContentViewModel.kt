package br.com.useblu.oceands.client.ui.cardcontent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.core.OceanTransactionListUIModel

class CardContentViewModel: ViewModel() {

    private val _items = MutableLiveData<List<OceanTransactionListUIModel>>()
    val items: LiveData<List<OceanTransactionListUIModel>> get() = _items

    private val _items2 = MutableLiveData<List<OceanTransactionListUIModel>>()
    val items2: LiveData<List<OceanTransactionListUIModel>> get() = _items2

    fun loadData() {
        _items.postValue(mockItems())
        _items2.postValue(mockItems2())
    }

    private fun mockItems() = listOf(
        OceanTransactionListUIModel(
            primaryLabel = "Label 1",
            secondaryLabel = "Label 2",
            value = "1000.00",
            valueIsHighlighted = true,
            valueWithSignal = true,
            tagTitle = "Pendente"
        ),
        OceanTransactionListUIModel(
            primaryLabel = "Label 1",
            secondaryLabel = "Label 2",
            value = "-1000.00",
            valueIsHighlighted = true,
            valueWithSignal = true,
            tagTitle = "Pendente"
        ),
        OceanTransactionListUIModel(
            primaryLabel = "Label 1",
            secondaryLabel = "Label 2",
            value = "130.00",
            valueIsHighlighted = true,
            valueWithSignal = true,
            tagTitle = "Pendente"
        )
    )

    private fun mockItems2() = listOf(
        OceanTransactionListUIModel(
            primaryLabel = "Label 1",
            value = "130.00",
        ),
        OceanTransactionListUIModel(
            primaryLabel = "Label 1",
            value = "100.00",
        ),
        OceanTransactionListUIModel(
            primaryLabel = "Label 1",
            value = "5.00",
        )
    )

}