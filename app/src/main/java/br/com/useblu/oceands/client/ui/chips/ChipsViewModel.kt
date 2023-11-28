package br.com.useblu.oceands.client.ui.chips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
import br.com.useblu.oceands.utils.OceanIcons
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChipsViewModel : ViewModel() {

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

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

    private var singleChoiceFilterOptions = listOf(
        FilterOptionsItem("Teste 1"),
        FilterOptionsItem("Teste 2", isSelected = true)
    )

    private var multipleChoiceFilterOptions = listOf(
        FilterOptionsItem("Teste 1"),
        FilterOptionsItem("Teste 2"),
        FilterOptionsItem("Teste 3")
    )

    private var multipleChoiceBadge: Badge? = null

    val chipsWithoutIcon: List<OceanChip> get() = listOf(
            OceanBasicChip(
                label = allChip.label,
                id = allChip.id,
                onClick = {
                    println("OceanChipItem 1 $it")
                }
            ),
            OceanFilterChip(
                label = "Filtro",
                id = "999",
                filterOptions = OceanChipFilterOptions.SingleChoice(
                    title = "Status do Boleto",
                    optionsItems = singleChoiceFilterOptions,
                    onSelectItem = {
                        singleChoiceFilterOptions = singleChoiceFilterOptions.mapIndexed { index, filterOptionsItem ->
                            filterOptionsItem.copy(isSelected = index == it)
                        }
                        loadData()
                    }
                )
            ),
            OceanFilterChip(
                label = "Filtro Teste",
                id = "9999",
                badge = multipleChoiceBadge,
                state = if (multipleChoiceFilterOptions.any { it.isSelected }) OceanChipItemState.DEFAULT else OceanChipItemState.INACTIVE_HOVER,
                filterOptions = OceanChipFilterOptions.MultipleChoice(
                    title = "Status do Pagamento",
                    optionsItems = multipleChoiceFilterOptions,
                    primaryButtonLabel = "Salvar",
                    secondaryButtonLabel = "Limpar",
                    onPrimaryButtonClick = {
                        multipleChoiceFilterOptions = multipleChoiceFilterOptions.mapIndexed { index, filterOptionsItem ->
                            filterOptionsItem.copy(isSelected = it.contains(index))
                        }
                        multipleChoiceBadge = if (it.isNotEmpty()) {
                            Badge(
                                type = OceanBadgeType.PRIMARY_INVERTED,
                                text = it.size
                            )
                        } else null
                        loadData()
                    },
                    onSecondaryButtonClick = {
                        multipleChoiceFilterOptions = multipleChoiceFilterOptions.map { filterOptionsItem ->
                            filterOptionsItem.copy(isSelected = false)
                        }
                        multipleChoiceBadge = null
                        loadData()
                    }
                )
            ),
            OceanBasicChip(
                label = toDueChip.label,
                id = toDueChip.id,
                onClick = {
                    println("OceanChipItem 2 $it")
                }
            ),
            OceanBasicChip(
                label = overDueChip.label,
                id = overDueChip.id,
                onClick = {
                    println("OceanChipItem 3 $it")
                }
            ),
            OceanBasicChip(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED_ACTIVE,
                onClick = {
                    println("OceanChipItem 4 $it")
                }
            ),
            OceanBasicChip(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.INACTIVE_HOVER,
                onClick = {
                    println("OceanChipItem 5 $it")
                }
            )
        )

    val chipsWithIcon: List<OceanChip> = listOf(
            OceanBasicChip(
                label = allChip.label,
                id = allChip.id,
                icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE,
                onClick = {
                    println("OceanChipItem 1 $it")
                }
            ),
            OceanFilterChip(
                label = "Filtro",
                id = "999",
                filterOptions = OceanChipFilterOptions.SingleChoice(
                    title = "Status do Boleto",
                    optionsItems = singleChoiceFilterOptions,
                    onSelectItem = {
                        _toastText.postValue("Item selecionado: $it")
                    }
                ),
            ),
            OceanFilterChip(
                label = "Filtro 2",
                id = "999",
                filterOptions = OceanChipFilterOptions.MultipleChoice(
                    optionsItems = multipleChoiceFilterOptions,
                    primaryButtonLabel = "Adicionar",
                    secondaryButtonLabel = "Cancelar",
                    onPrimaryButtonClick = {
                        _toastText.postValue("Items selecionados: $it")
                    },
                    onSecondaryButtonClick = {},
                    title = "Status do Pagamento"
                )
            ),
            OceanFilterChip(
                label = "Filtro 3",
                id = "9999",
                filterOptions = OceanChipFilterOptions.MultipleChoice(
                    title = "Status do Pagamento",
                    optionsItems = multipleChoiceFilterOptions,
                    primaryButtonLabel = "Salvar",
                    secondaryButtonLabel = "Cancelar",
                    onPrimaryButtonClick = {
                        _toastText.postValue("Items selecionados: $it")
                    },
                    onSecondaryButtonClick = {}
                )
            ),
            OceanBasicChip(
                label = toDueChip.label,
                id = toDueChip.id,
                icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE,
                onClick = {
                    println("OceanChipItem 2 $it")
                }
            ),
            OceanBasicChip(
                label = overDueChip.label,
                id = overDueChip.id,
                icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE,
                onClick = {
                    println("OceanChipItem 3 $it")
                }
            ),
            OceanBasicChip(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED_ACTIVE,
                icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE,
                onClick = {
                    println("OceanChipItem 4 $it")
                }
            ),
            OceanBasicChip(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.INACTIVE_HOVER,
                icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE,
                onClick = {
                    println("OceanChipItem 5 $it")
                },
                hasFilterAll = true
            )
        )

    val chipsWithBadge: List<OceanChip> = listOf(
            OceanBasicChip(
                label = allChip.label,
                id = allChip.id,
                badge = Badge(100, OceanBadgeType.PRIMARY_INVERTED),
                onClick = {
                    println("OceanChipItem 1 $it")
                }
            ),
            OceanBasicChip(
                label = toDueChip.label,
                id = toDueChip.id,
                badge = Badge(50, OceanBadgeType.PRIMARY_INVERTED),
                onClick = {
                    println("OceanChipItem 2 $it")
                }
            ),
            OceanBasicChip(
                label = overDueChip.label,
                id = overDueChip.id,
                badge = Badge(10, OceanBadgeType.PRIMARY),
                onClick = {
                    println("OceanChipItem 3 $it")
                }
            ),
            OceanBasicChip(
                label = unavailableChip.label,
                id = unavailableChip.id,
                state = OceanChipItemState.DISABLED_ACTIVE,
                badge = Badge(9, OceanBadgeType.PRIMARY_INVERTED),
                onClick = {
                    println("OceanChipItem 4 $it")
                }
            ),
            OceanBasicChip(
                label = errorChip.label,
                id = errorChip.id,
                state = OceanChipItemState.INACTIVE_HOVER,
                badge = Badge(9, OceanBadgeType.PRIMARY_INVERTED),
                onClick = {
                    println("OceanChipItem 5 $it")
                },
                hasFilterAll = true
            )
        )

    private val _chips: MutableLiveData<List<OceanChip>> = MutableLiveData()
    val chips: LiveData<List<OceanChip>> = _chips

    fun loadData() {
        viewModelScope.launch {
            delay(100)
            _chips.postValue(chipsWithoutIcon)
        }
    }
}
