package br.com.useblu.oceands.client.ui.chips

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.useblu.oceands.adapter.Badge
import br.com.useblu.oceands.adapter.OceanChipItem
import br.com.useblu.oceands.adapter.OceanChipItemState
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.ui.chips.model.Chips
import br.com.useblu.oceands.core.OceanBadgeType

class ChipsViewModel(application: Application): AndroidViewModel(application) {
    val chips: ArrayList<OceanChipItem> = arrayListOf(
        OceanChipItem(
            label = Chips.ALL.label,
            id = Chips.ALL.ordinal
        ),
        OceanChipItem(
            label = Chips.TO_DUE.label,
            id = Chips.TO_DUE.ordinal
        ),
        OceanChipItem(
            label = Chips.OVER_DUE.label,
            id = Chips.OVER_DUE.ordinal
        ),
        OceanChipItem(
            label = Chips.UNAVAILABLE.label,
            id = Chips.UNAVAILABLE.ordinal,
            state = OceanChipItemState.DISABLED
        ),
        OceanChipItem(
            label = Chips.ERROR.label,
            id = Chips.ERROR.ordinal,
            state = OceanChipItemState.ERROR
        )
    )

    val chipsWithIcon: ArrayList<OceanChipItem> = arrayListOf(
        OceanChipItem(
            label = Chips.ALL.label,
            id = Chips.ALL.ordinal,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        ),
        OceanChipItem(
            label = Chips.TO_DUE.label,
            id = Chips.TO_DUE.ordinal,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        ),
        OceanChipItem(
            label = Chips.OVER_DUE.label,
            id = Chips.OVER_DUE.ordinal,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        ),
        OceanChipItem(
            label = Chips.UNAVAILABLE.label,
            id = Chips.UNAVAILABLE.ordinal,
            state = OceanChipItemState.DISABLED,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        ),
        OceanChipItem(
            label = Chips.ERROR.label,
            id = Chips.ERROR.ordinal,
            state = OceanChipItemState.ERROR,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        )
    )

    val chipsWithBadge: ArrayList<OceanChipItem> = arrayListOf(
        OceanChipItem(
            label = Chips.ALL.label,
            id = Chips.ALL.ordinal,
            badge = Badge(9, OceanBadgeType.ALERT)
        ),
        OceanChipItem(
            label = Chips.TO_DUE.label,
            id = Chips.TO_DUE.ordinal,
            badge = Badge(9, OceanBadgeType.ALERT)
        ),
        OceanChipItem(
            label = Chips.OVER_DUE.label,
            id = Chips.OVER_DUE.ordinal,
            badge = Badge(9, OceanBadgeType.ALERT)
        ),
        OceanChipItem(
            label = Chips.UNAVAILABLE.label,
            id = Chips.UNAVAILABLE.ordinal,
            state = OceanChipItemState.DISABLED,
            badge = Badge(9, OceanBadgeType.ALERT)
        ),
        OceanChipItem(
            label = Chips.ERROR.label,
            id = Chips.ERROR.ordinal,
            state = OceanChipItemState.ERROR,
            badge = Badge(9, OceanBadgeType.ALERT)
        )
    )

    val chipsWithClose: ArrayList<OceanChipItem> = arrayListOf(
        OceanChipItem(
            label = Chips.ALL.label,
            id = Chips.ALL.ordinal,
            hasClose = true
        ),
        OceanChipItem(
            label = Chips.TO_DUE.label,
            id = Chips.TO_DUE.ordinal,
            hasClose = true
        ),
        OceanChipItem(
            label = Chips.OVER_DUE.label,
            id = Chips.OVER_DUE.ordinal,
            hasClose = true
        ),
        OceanChipItem(
            label = Chips.UNAVAILABLE.label,
            id = Chips.UNAVAILABLE.ordinal,
            state = OceanChipItemState.DISABLED,
            hasClose = true
        ),
        OceanChipItem(
            label = Chips.ERROR.label,
            id = Chips.ERROR.ordinal,
            state = OceanChipItemState.ERROR,
            hasClose = true
        )
    )

    private val _selectedItem: MutableLiveData<OceanChipItem?> = MutableLiveData()
    val selectedItem: MutableLiveData<OceanChipItem?> get() = _selectedItem
}
