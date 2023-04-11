package br.com.useblu.oceands.client.ui.chips

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.useblu.oceands.client.ui.chips.model.ChipModel
import br.com.useblu.oceands.model.Badge
import br.com.useblu.oceands.model.FilterOptionsItem
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChip
import br.com.useblu.oceands.model.OceanChipFilterOptions
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.model.OceanFilterChip
import br.com.useblu.oceands.model.SingleChoice
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
    val chipsWithoutIcon: List<OceanChip> = listOf(
            OceanBasicChip(
                label = allChip.label,
                id = allChip.id,
                action = {
                    println("OceanChipItem 1 Selected")
                }
            ),
            OceanBasicChip(
                label = toDueChip.label,
                id = toDueChip.id,
                action = {
                    println("OceanChipItem 2 Selected")
                }
            ),
            OceanBasicChip(
                label = overDueChip.label,
                id = overDueChip.id,
                action = {
                    println("OceanChipItem 3 Selected")
                }
            ),
            OceanBasicChip(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED,
                action = {
                    println("OceanChipItem 4 Selected")
                }
            ),
            OceanBasicChip(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.HOVER,
                action = {
                    println("OceanChipItem 5 Selected")
                }
            )
        )

    val chipsWithIcon: List<OceanChip> = listOf(
            OceanBasicChip(
                label = allChip.label,
                id = allChip.id,
                icon = "informationcircleoutline",
                action = {
                    println("OceanChipItem 1 Selected")
                }
            ),
            OceanFilterChip(
                label = "Filtro",
                id = "999",
                filterOptions = SingleChoice(
                    items = listOf(FilterOptionsItem("Teste 1"), FilterOptionsItem("Teste 2", isSelected = true))
                )
            ),
            OceanBasicChip(
                label = toDueChip.label,
                id = toDueChip.id,
                icon = "informationcircleoutline",
                action = {
                    println("OceanChipItem 2 Selected")
                }
            ),
            OceanBasicChip(
                label = overDueChip.label,
                id = overDueChip.id,
                icon = "informationcircleoutline",
                action = {
                    println("OceanChipItem 3 Selected")
                }
            ),
            OceanBasicChip(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED,
                icon = "informationcircleoutline",
                action = {
                    println("OceanChipItem 4 Selected")
                }
            ),
            OceanBasicChip(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.HOVER,
                icon = "informationcircleoutline",
                action = {
                    println("OceanChipItem 5 Selected")
                }
            )
        )

    val chipsWithBadge: List<OceanChip> = listOf(
            OceanBasicChip(
                label = allChip.label,
                id = allChip.id,
                badge = Badge(100, OceanBadgeType.HIGHLIGHT),
                action = {
                    println("OceanChipItem 1 Selected")
                }
            ),
            OceanBasicChip(
                label = toDueChip.label,
                id = toDueChip.id,
                badge = Badge(50, OceanBadgeType.HIGHLIGHT),
                action = {
                    println("OceanChipItem 2 Selected")
                }
            ),
            OceanBasicChip(
                label = overDueChip.label,
                id = overDueChip.id,
                badge = Badge(10, OceanBadgeType.PRIMARY),
                action = {
                    println("OceanChipItem 3 Selected")
                }
            ),
            OceanBasicChip(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED,
                badge = Badge(9, OceanBadgeType.WARNING),
                action = {
                    println("OceanChipItem 4 Selected")
                }
            ),
            OceanBasicChip(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.HOVER,
                badge = Badge(9, OceanBadgeType.WARNING),
                action = {
                    println("OceanChipItem 5 Selected")
                }
            )
        )

    private val _chips: MutableLiveData<List<OceanChip>> = MutableLiveData()
    val chips: LiveData<List<OceanChip>> = _chips

    fun loadData() {
        viewModelScope.launch {
            delay(3000)
            _chips.postValue(chipsWithoutIcon)
        }
    }
}
