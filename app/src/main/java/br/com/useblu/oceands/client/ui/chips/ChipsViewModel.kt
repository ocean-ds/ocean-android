package br.com.useblu.oceands.client.ui.chips

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.useblu.oceands.adapter.Badge
import br.com.useblu.oceands.adapter.OceanChipItem
import br.com.useblu.oceands.adapter.OceanChipItemState
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.ui.chips.model.ChipModel
import br.com.useblu.oceands.core.OceanBadgeType

class ChipsViewModel(application: Application): AndroidViewModel(application) {
    private val allChip = ChipModel(
        id = "all",
        label = "Todos"
    )
    private val toDueChip = ChipModel(
        id = "scheduled",
        label = "A vencer"
    )
    private val overDueChip = ChipModel(
        id = "overdue",
        label = "Vencidos"
    )
    private val unavailableChip = ChipModel(
        id = "unavailable",
        label = "Indisponível"
    )
    private val errorChip = ChipModel(
        id = "error",
        label = "Erro"
    )
    val chips: ArrayList<OceanChipItem> = arrayListOf(
        OceanChipItem(
            label = allChip.label,
            id = allChip.id
        ),
        OceanChipItem(
            label = toDueChip.label,
            id = toDueChip.id
        ),
        OceanChipItem(
            label = overDueChip.label,
            id = overDueChip.id
        ),
        OceanChipItem(
            label = unavailableChip.label,
            id = unavailableChip.id,
            state = OceanChipItemState.DISABLED
        ),
        OceanChipItem(
            label = errorChip.label,
            id = errorChip.id,
            state = OceanChipItemState.ERROR
        )
    )

    val chipsWithIcon: ArrayList<OceanChipItem> = arrayListOf(
        OceanChipItem(
            label = allChip.label,
            id = allChip.id,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        ),
        OceanChipItem(
            label = toDueChip.label,
            id = toDueChip.id,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        ),
        OceanChipItem(
            label = overDueChip.label,
            id = overDueChip.id,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        ),
        OceanChipItem(
            label = unavailableChip.label,
            id = unavailableChip.id,
            state = OceanChipItemState.DISABLED,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        ),
        OceanChipItem(
            label = errorChip.label,
            id = errorChip.id,
            state = OceanChipItemState.ERROR,
            icon = ContextCompat.getDrawable(getApplication<Application>(), R.drawable.icon_information)
        )
    )

    val chipsWithBadge: ArrayList<OceanChipItem> = arrayListOf(
        OceanChipItem(
            label = allChip.label,
            id = allChip.id,
            badge = Badge(100, OceanBadgeType.ALERT)
        ),
        OceanChipItem(
            label = toDueChip.label,
            id = toDueChip.id,
            badge = Badge(50, OceanBadgeType.ALERT)
        ),
        OceanChipItem(
            label = overDueChip.label,
            id = overDueChip.id,
            badge = Badge(0, OceanBadgeType.ALERT)
        ),
        OceanChipItem(
            label = unavailableChip.label,
            id = unavailableChip.id,
            state = OceanChipItemState.DISABLED,
            badge = Badge(9, OceanBadgeType.ALERT)
        ),
        OceanChipItem(
            label = errorChip.label,
            id = errorChip.id,
            state = OceanChipItemState.ERROR,
            badge = Badge(9, OceanBadgeType.ALERT)
        )
    )

    val chipsWithClose: ArrayList<OceanChipItem> = arrayListOf(
        OceanChipItem(
            label = allChip.label,
            id = allChip.id,
            hasClose = true
        ),
        OceanChipItem(
            label = toDueChip.label,
            id = toDueChip.id,
            hasClose = true
        ),
        OceanChipItem(
            label = overDueChip.label,
            id = overDueChip.id,
            hasClose = true
        ),
        OceanChipItem(
            label = unavailableChip.label,
            id = unavailableChip.id,
            state = OceanChipItemState.DISABLED,
            hasClose = true
        ),
        OceanChipItem(
            label = errorChip.label,
            id = errorChip.id,
            state = OceanChipItemState.ERROR,
            hasClose = true
        )
    )

    private val _selectedItem: MutableLiveData<OceanChipItem?> = MutableLiveData()
    val selectedItem: MutableLiveData<OceanChipItem?> get() = _selectedItem
}
