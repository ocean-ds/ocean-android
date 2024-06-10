package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanSnackBarPreview() {
    Row(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            OceanSnackBar(
                type = OceanSnackBarType.Information,
                text = "Text 1"
            )

            OceanSnackBar(
                type = OceanSnackBarType.Positive,
                text = "Text 2"
            )

            OceanSnackBar(
                type = OceanSnackBarType.Warning,
                text = "Text 3"
            )

            OceanSnackBar(
                type = OceanSnackBarType.Negative,
                text = "Text 4"
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            OceanSnackBar(
                type = OceanSnackBarType.Information,
                text = "Text 1",
                action = {
                    println("Action")
                },
                actionText = "Action"
            )

            OceanSnackBar(
                type = OceanSnackBarType.Positive,
                action = {
                    println("Action")
                },
                actionText = "Action",
                text = "Text 2"
            )

            OceanSnackBar(
                type = OceanSnackBarType.Warning,
                action = {
                    println("Action")
                },
                actionText = "Action",
                text = "Text 3"
            )

            OceanSnackBar(
                type = OceanSnackBarType.Negative,
                action = {
                    println("Action")
                },
                actionText = "Action",
                text = "Text 4"
            )
        }
    }
}

@Composable
fun OceanSnackBar(
    type: OceanSnackBarType,
    text: String,
    action: (() -> Unit)? = null,
    actionText: String? = null
) {
    val actionComposable: @Composable (() -> Unit)? = if (action != null && actionText != null) {
        {
            TextButton(onClick = { action() }) {
                Text(
                    text = actionText,
                    fontFamily = OceanFontFamily.BaseBold,
                    fontSize = OceanFontSize.xxs,
                    color = type.getColor()
                )
            }
        }
    } else null

    Snackbar(
        modifier = Modifier,
        action = actionComposable
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OceanIcon(
                iconType = type.getIcon(),
                tint = type.getColor()
            )

            OceanSpacing.StackXS()

            Text(
                text = text,
                fontFamily = OceanFontFamily.BaseRegular,
                fontSize = OceanFontSize.xxs,
                color = OceanColors.interfaceLightPure
            )
        }
    }
}


sealed interface OceanSnackBarType {
    fun getIcon(): OceanIcons {
        return when (this) {
            is Information -> OceanIcons.INFO_OUTLINE
            is Positive -> OceanIcons.CHECK_CIRCLE_OUTLINE
            is Warning -> OceanIcons.EXCLAMATION_CIRCLE_OUTLINE
            is Negative -> OceanIcons.X_CIRCLE_OUTLINE
        }
    }

    @Composable
    fun getColor(): Color {
        return when (this) {
            is Information -> OceanColors.brandPrimaryUp
            is Positive -> OceanColors.statusPositivePure
            is Warning -> OceanColors.statusWarningPure
            is Negative -> OceanColors.statusNegativePure
        }
    }
    object Information : OceanSnackBarType
    object Positive : OceanSnackBarType
    object Warning : OceanSnackBarType
    object Negative : OceanSnackBarType
}
