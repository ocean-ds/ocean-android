package br.com.useblu.oceands.model.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import br.com.useblu.oceands.components.compose.OceanLinkIcon
import br.com.useblu.oceands.components.compose.OceanLinkType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

sealed interface OceanAlertType {
    class Default(
        val alertType: AlertStyle = AlertStyle.StyleInfo(),
        val description: String
    ) : OceanAlertType

    data class WithAction(
        val alertType: AlertStyle = AlertStyle.StyleInfo(),
        val description: String,
        val actionTitle: String,
        val action: () -> Unit
    ) : OceanAlertType

    class EntitledShort(
        val alertType: AlertStyle = AlertStyle.StyleInfo(),
        val title: String,
        val description: String,
        val button: Pair<String, () -> Unit>? = null,
        val tooltip: String = ""
    ) : OceanAlertType

    class EntitledLong(
        val alertType: AlertStyle = AlertStyle.StyleInfo(),
        val title: String,
        val description: String
    ) : OceanAlertType

    class Labeled(
        val alertType: AlertStyle = AlertStyle.StyleInfo(),
        val title: String? = null,
        val description: String,
        val link: String,
        val linkType: OceanLinkType = OceanLinkType.PRIMARY,
        val linkIcon: OceanLinkIcon = OceanLinkIcon.DEFAULT,
        val onClick: () -> Unit = {}
    ) : OceanAlertType

    class Bookmarked(
        val alertType: AlertStyle = AlertStyle.StyleInfo(),
        val title: String,
        val description: String
    ) : OceanAlertType
}

sealed interface AlertStyle {

    val titleColor: @Composable () -> Color
    val titleStyle: @Composable () -> TextStyle
    val descriptionStyle: @Composable () -> TextStyle
    val descriptionColor: @Composable () -> Color
    val alertBackgroundColor: @Composable () -> Color
    val oceanIcon: OceanIcons?
    val iconTint: @Composable () -> Color

    class StyleInfo(
        override val titleColor: @Composable () -> Color = { OceanColors.brandPrimaryDown },
        override val titleStyle: @Composable () -> TextStyle = { OceanTextStyle.heading5 },
        override val descriptionStyle: @Composable () -> TextStyle = { OceanTextStyle.caption },
        override val descriptionColor: @Composable () -> Color = { OceanColors.interfaceDarkDown },
        override val alertBackgroundColor: @Composable () -> Color = { OceanColors.interfaceLightUp },
        override val oceanIcon: OceanIcons? = OceanIcons.INFO_OUTLINE,
        override val iconTint: @Composable () -> Color = { OceanColors.brandPrimaryDown }
    ) : AlertStyle

    class StyleWarning(
        override val titleColor: @Composable () -> Color = { OceanColors.statusWarningDeep },
        override val titleStyle: @Composable () -> TextStyle = { OceanTextStyle.heading5 },
        override val descriptionStyle: @Composable () -> TextStyle = { OceanTextStyle.caption },
        override val descriptionColor: @Composable () -> Color = { OceanColors.interfaceDarkDown },
        override val alertBackgroundColor: @Composable () -> Color = { OceanColors.statusWarningUp },
        override val oceanIcon: OceanIcons? = OceanIcons.EXCLAMATION_CIRCLE_OUTLINE,
        override val iconTint: @Composable () -> Color = { OceanColors.statusWarningDeep }
    ) : AlertStyle

    class StylePositive(
        override val titleColor: @Composable () -> Color = { OceanColors.statusPositiveDeep },
        override val titleStyle: @Composable () -> TextStyle = { OceanTextStyle.heading5 },
        override val descriptionStyle: @Composable () -> TextStyle = { OceanTextStyle.caption },
        override val descriptionColor: @Composable () -> Color = { OceanColors.interfaceDarkDown },
        override val alertBackgroundColor: @Composable () -> Color = { OceanColors.statusPositiveUp },
        override val oceanIcon: OceanIcons? = OceanIcons.CHECK_CIRCLE_OUTLINE,
        override val iconTint: @Composable () -> Color = { OceanColors.statusPositiveDeep }
    ) : AlertStyle

    class StyleNegative(
        override val titleColor: @Composable () -> Color = { OceanColors.statusNegativePure },
        override val titleStyle: @Composable () -> TextStyle = { OceanTextStyle.heading5 },
        override val descriptionStyle: @Composable () -> TextStyle = { OceanTextStyle.caption },
        override val descriptionColor: @Composable () -> Color = { OceanColors.interfaceDarkDown },
        override val alertBackgroundColor: @Composable () -> Color = { OceanColors.statusNegativeUp },
        override val oceanIcon: OceanIcons? = OceanIcons.X_CIRCLE_OUTLINE,
        override val iconTint: @Composable () -> Color = { OceanColors.statusNegativePure }
    ) : AlertStyle
}
