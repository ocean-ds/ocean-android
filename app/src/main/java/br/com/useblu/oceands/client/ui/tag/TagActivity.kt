package br.com.useblu.oceands.client.ui.tag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTagBinding
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.OceanTagLayout
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.model.OceanTagAppearance
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

class TagActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTagBinding
    private lateinit var viewModel: TagViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tag)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TagViewModel::class.java]
        binding.viewmodel = viewModel
        binding.translucentTags.setContent {
            AppearanceTags()
        }
    }
}

/**
 * Amostra da opção `appearance` do [OceanTag] Default sobre superfície colorida. Cada bloco tem uma linha
 * `propriedade: valor`.
 */
@Composable
private fun AppearanceTags() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(OceanColors.brandPrimaryPure)
            .padding(OceanSpacing.xxs),
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
    ) {
        AppearanceSample(appearance = OceanTagAppearance.Default)
        AppearanceSample(appearance = OceanTagAppearance.Translucent)
    }
}

@Composable
private fun AppearanceSample(appearance: OceanTagAppearance) {
    val samples = listOf(
        Triple("Paid", OceanTagType.Positive, OceanIcons.CHECK_CIRCLE_SOLID),
        Triple("Due soon", OceanTagType.Warning, OceanIcons.EXCLAMATION_CIRCLE_SOLID),
        Triple("Overdue", OceanTagType.Negative, OceanIcons.X_CIRCLE_SOLID),
        Triple("Scheduled", OceanTagType.Complementary, OceanIcons.PLACEHOLDER_SOLID),
        Triple("Processing", OceanTagType.Neutral, OceanIcons.PLACEHOLDER_SOLID),
        Triple("In review", OceanTagType.NeutralPrimary, OceanIcons.PLACEHOLDER_SOLID)
    )

    OceanText(
        text = "appearance: ${appearance.name}",
        style = OceanTextStyle.description,
        color = OceanColors.interfaceLightPure
    )

    samples.forEach { (label, type, icon) ->
        OceanTag(
            style = OceanTagStyle.Default(
                label = label,
                layout = OceanTagLayout.Medium(icon = icon),
                type = type,
                appearance = appearance
            )
        )
    }
}
