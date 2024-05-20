package br.com.useblu.oceands.client.ui.chips

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityChipsBinding
import br.com.useblu.oceands.components.OceanBottomSheet
import br.com.useblu.oceands.components.compose.OceanChip
import br.com.useblu.oceands.model.Badge
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanBasicChip
import br.com.useblu.oceands.model.OceanChipItemState
import br.com.useblu.oceands.utils.OceanIcons

class ChipsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChipsBinding
    private lateinit var viewModel: ChipsViewModel

    private val composeChipModels by lazy {
        listOf(
            OceanBasicChip(
                id = "1",
                label = "Basic Chip",
                icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE,
                state = OceanChipItemState.HOVER_INACTIVE,
                badge = Badge(5, OceanBadgeType.PRIMARY),
                onClick = {
                    OceanBottomSheet(this)
                        .withTitle("Title")
                        .withMessage("Message")
                        .withImage("https://portal-cicloentrada.blu.com.br/assets/icons/coin_trail-cc541831a7fbf4d215f3910fb241b14701f5ab0f79d574ad3a6e12379b7e871e.png")
                        .withOrientationButtons(OceanBottomSheet.Orientation.Vertical)
                        .withDismiss(true)
                        .withActionPositive(R.string.all_button_confirm) {}
                        .show()
                }
            ),
            viewModel.chipsWithoutIcon[2]
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chips)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ChipsViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.loadData()

        viewModel.toastText.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        binding.composeView.setContent {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                composeChipModels.forEach { chip ->
                    OceanChip(model = chip)
                }
            }
        }
    }
}
