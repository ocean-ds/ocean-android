package br.com.useblu.oceands.client.ui.chips

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.ui.chips.model.ChipModel
import br.com.useblu.oceands.model.Badge
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanChipItem
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
            OceanChipItem(
                label = allChip.label,
                id = allChip.id,
                action = {
                    println("OceanChipItem 1 Selected")
                }
            ),
            OceanChipItem(
                label = toDueChip.label,
                id = toDueChip.id,
                action = {
                    println("OceanChipItem 2 Selected")
                }
            ),
            OceanChipItem(
                label = overDueChip.label,
                id = overDueChip.id,
                action = {
                    println("OceanChipItem 3 Selected")
                }
            ),
            OceanChipItem(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED,
                action = {
                    println("OceanChipItem 4 Selected")
                }
            ),
            OceanChipItem(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.ERROR,
                action = {
                    println("OceanChipItem 5 Selected")
                }
            )
        )

    val chipsWithIcon = listOf(
            OceanChipItem(
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
            OceanChipItem(
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
            OceanChipItem(
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
            OceanChipItem(
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
            OceanChipItem(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.ERROR,
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
            OceanChipItem(
                label = allChip.label,
                id = allChip.id,
                badge = Badge(100, OceanBadgeType.ALERT),
                action = {
                    println("OceanChipItem 1 Selected")
                }
            ),
            OceanChipItem(
                label = toDueChip.label,
                id = toDueChip.id,
                badge = Badge(50, OceanBadgeType.ALERT),
                action = {
                    println("OceanChipItem 2 Selected")
                }
            ),
            OceanChipItem(
                label = overDueChip.label,
                id = overDueChip.id,
                badge = Badge(0, OceanBadgeType.ALERT),
                action = {
                    println("OceanChipItem 3 Selected")
                }
            ),
            OceanChipItem(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED,
                badge = Badge(9, OceanBadgeType.ALERT),
                action = {
                    println("OceanChipItem 4 Selected")
                }
            ),
            OceanChipItem(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.ERROR,
                badge = Badge(9, OceanBadgeType.ALERT),
                action = {
                    println("OceanChipItem 5 Selected")
                }
            )
        )

    val chipsWithClose = listOf(
            OceanChipItem(
                label = allChip.label,
                id = allChip.id,
                hasClose = true,
                action = {
                    println("OceanChipItem 1 Selected")
                }
            ),
            OceanChipItem(
                label = toDueChip.label,
                id = toDueChip.id,
                hasClose = true,
                action = {
                    println("OceanChipItem 2 Selected")
                }
            ),
            OceanChipItem(
                label = overDueChip.label,
                id = overDueChip.id,
                hasClose = true,
                action = {
                    println("OceanChipItem 3 Selected")
                }
            ),
            OceanChipItem(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED,
                hasClose = true,
                action = {
                    println("OceanChipItem 4 Selected")
                }
            ),
            OceanChipItem(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.ERROR,
                hasClose = true,
                action = {
                    println("OceanChipItem 5 Selected")
                }
            )
        )

    val chipsArrayList = arrayListOf(
        OceanChipItem(
            label = allChip.label,
            id = allChip.id,
            action = {
                println("OceanChipItem 1 Selected")
            }
        ),
        OceanChipItem(
            label = toDueChip.label,
            id = toDueChip.id,
            action = {
                println("OceanChipItem 2 Selected")
            }
        ),
        OceanChipItem(
            label = overDueChip.label,
            id = overDueChip.id,
            action = {
                println("OceanChipItem 3 Selected")
            }
        ),
        OceanChipItem(
            label = unavailableChip.label,
            id = unavailableChip.id,
            state = OceanChipItemState.DISABLED,
            action = {
                println("OceanChipItem 4 Selected")
            }
        ),
        OceanChipItem(
            label = errorChip.label,
            id = errorChip.id,
            state = OceanChipItemState.ERROR,
            action = {
                println("OceanChipItem 5 Selected")
            }
        )
    )

    private val _chips: MutableLiveData<ArrayList<OceanChipItem>> = MutableLiveData(ArrayList())
    val chips: MutableLiveData<ArrayList<OceanChipItem>> get() = _chips

    fun loadData() {
        viewModelScope.launch {
            delay(3000)
            _chips.postValue(chipsArrayList)
        }
    }
}
