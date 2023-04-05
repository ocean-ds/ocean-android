package br.com.useblu.oceands.client.ui.chips

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.ui.chips.model.ChipModel
import br.com.useblu.oceands.model.Badge
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChipItem
import br.com.useblu.oceands.model.OceanChipItemState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChipsViewModel(application: Application) : AndroidViewModel(application) {
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
    val chipsWithoutIcon = listOf(
            OceanBasicChipItem(
                label = allChip.label,
                id = allChip.id,
                action = {
                    println("OceanChipItem 1 Selected")
                }
            ),
            OceanBasicChipItem(
                label = toDueChip.label,
                id = toDueChip.id,
                action = {
                    println("OceanChipItem 2 Selected")
                }
            ),
            OceanBasicChipItem(
                label = overDueChip.label,
                id = overDueChip.id,
                action = {
                    println("OceanChipItem 3 Selected")
                }
            ),
            OceanBasicChipItem(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED,
                action = {
                    println("OceanChipItem 4 Selected")
                }
            ),
            OceanBasicChipItem(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.HOVER,
                action = {
                    println("OceanChipItem 5 Selected")
                }
            )
        )

    val chipsWithIcon = listOf(
            OceanBasicChipItem(
                label = allChip.label,
                id = allChip.id,
                icon = ContextCompat.getDrawable(
                    getApplication<Application>(),
                    R.drawable.icon_information
                ),
                action = {
                    println("OceanChipItem 1 Selected")
                }
            ),
            OceanBasicChipItem(
                label = toDueChip.label,
                id = toDueChip.id,
                icon = ContextCompat.getDrawable(
                    getApplication<Application>(),
                    R.drawable.icon_information
                ),
                action = {
                    println("OceanChipItem 2 Selected")
                }
            ),
            OceanBasicChipItem(
                label = overDueChip.label,
                id = overDueChip.id,
                icon = ContextCompat.getDrawable(
                    getApplication<Application>(),
                    R.drawable.icon_information
                ),
                action = {
                    println("OceanChipItem 3 Selected")
                }
            ),
            OceanBasicChipItem(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED,
                icon = ContextCompat.getDrawable(
                    getApplication<Application>(),
                    R.drawable.icon_information
                ),
                action = {
                    println("OceanChipItem 4 Selected")
                }
            ),
            OceanBasicChipItem(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.HOVER,
                icon = ContextCompat.getDrawable(
                    getApplication<Application>(),
                    R.drawable.icon_information
                ),
                action = {
                    println("OceanChipItem 5 Selected")
                }
            )
        )

    val chipsWithBadge = listOf(
            OceanBasicChipItem(
                label = allChip.label,
                id = allChip.id,
                badge = Badge(100, OceanBadgeType.HIGHLIGHT),
                action = {
                    println("OceanChipItem 1 Selected")
                }
            ),
            OceanBasicChipItem(
                label = toDueChip.label,
                id = toDueChip.id,
                badge = Badge(50, OceanBadgeType.HIGHLIGHT),
                action = {
                    println("OceanChipItem 2 Selected")
                }
            ),
            OceanBasicChipItem(
                label = overDueChip.label,
                id = overDueChip.id,
                badge = Badge(10, OceanBadgeType.PRIMARY),
                action = {
                    println("OceanChipItem 3 Selected")
                }
            ),
            OceanBasicChipItem(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED,
                badge = Badge(9, OceanBadgeType.WARNING),
                action = {
                    println("OceanChipItem 4 Selected")
                }
            ),
            OceanBasicChipItem(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.HOVER,
                badge = Badge(9, OceanBadgeType.WARNING),
                action = {
                    println("OceanChipItem 5 Selected")
                }
            )
        )

    private val _chips: MutableLiveData<List<OceanBasicChipItem>> = MutableLiveData()
    val chips: LiveData<List<OceanBasicChipItem>> = _chips

    fun loadData() {
        viewModelScope.launch {
            delay(3000)
            _chips.postValue(chipsWithoutIcon)
        }
    }
}
