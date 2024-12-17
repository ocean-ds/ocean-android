package br.com.useblu.oceands.components.compose.content

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.CardCta
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.components.compose.OceanProgressBar
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.OceanTagLayout
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.content.CardGroupType.DEFAULT
import br.com.useblu.oceands.components.compose.content.CardGroupType.INVERTED
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.compose.OceanTagModel
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle


@Preview
@Composable
fun OceanCardGroupPreview() {
    OceanTheme {
        Column(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure)
        ) {
            OceanCardGroup(
                title = "Header",
                subtitle = "Subtitle",
                badgeText = "110",
                actionClick = {},
                actionTitle = "Call to Action",
                label = "Recomendado",
                showProgress = false,
                progressBar = 0.5f,
                modifier = Modifier.padding(16.dp),
                tag = OceanTagModel(
                    type = OceanTagType.Warning,
                    text = "Warning tag example"
                )
            )

            OceanCardGroup(
                title = "Header",
                subtitle = "Tudo certo! Você não possui boletos pendentes para aprovação",
                badgeText = "110",
                actionClick = {},
                actionTitle = "Call to Action",
                showProgress = false,
                progressBar = 0.75f,
                modifier = Modifier.padding(16.dp),
                tag = OceanTagModel(
                    type = OceanTagType.Highlight,
                    text = "Highlight tag example"
                )
            )

            OceanCardGroup(
                title = "Header",
                subtitle = "Tudo certo! Você não possui boletos pendentes para aprovação",
                badgeText = "110",
                actionClick = {},
                actionTitle = "Call to Action",
                showProgress = true,
                modifier = Modifier.padding(16.dp)
            )

            OceanCardGroup(
                title = "Sua economia atual",
                subtitle = "R$ 10.000,00",
                caption = "Valor total que você deixou de pagar, em taxa de antecipação.",
                type = INVERTED,
                actionClick = {},
                actionTitle = "Call to Action",
                showProgress = false,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun OceanCardGroup(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    caption: String? = null,
    badgeText: String? = null,
    badgeType: OceanBadgeType = OceanBadgeType.WARNING,
    actionTitle: String,
    actionClick: () -> Unit,
    @FloatRange(0.0, 1.0) progressBar: Float? = null,
    showProgress: Boolean = false,
    label: String? = null,
    type: CardGroupType = DEFAULT,
    tag: OceanTagModel? = null
) {
    Box(modifier = modifier) {
        val paddingTop = if (label != null) 9.dp else 0.dp
        Column(
            modifier = Modifier
                .padding(top = paddingTop)
                .border(
                    width = 1.dp,
                    color = if (label.isNullOrBlank()) OceanColors.interfaceLightDown
                    else OceanColors.brandPrimaryUp,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = OceanColors.interfaceLightPure,
                        shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)
                    )
                    .padding(horizontal = OceanSpacing.xs)
                    .padding(top = OceanSpacing.xs)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    when (type) {
                        DEFAULT -> {
                            ContentDefault(title, subtitle)
                        }

                        INVERTED -> {
                            ContentInverted(title, subtitle)
                        }
                    }

                    if (caption != null) {
                        OceanSpacing.StackXXS()

                        OceanText(
                            text = caption,
                            style = OceanTextStyle.captionBold,
                            color = OceanColors.interfaceDarkDown
                        )
                    }

                    tag?.let { tag ->
                        OceanTag(
                            modifier = Modifier
                                .padding(top = OceanSpacing.xxs),
                            style = OceanTagStyle.Default(
                                label = tag.text,
                                layout = OceanTagLayout.Medium(),
                                type = tag.type
                            )
                        )
                    }
                }

                if (badgeText != null) {
                    OceanSpacing.StackXS()

                    OceanBadge(
                        text = badgeText,
                        type = badgeType,
                        size = OceanBadgeSize.Medium
                    )
                }
            }

            if (progressBar != null) {
                OceanSpacing.StackXXS()
                OceanSpacing.StackXXXS()
                OceanProgressBar(
                    progress = progressBar,
                    modifier = Modifier
                        .padding(horizontal = OceanSpacing.xs)
                )
            }

            OceanSpacing.StackXS()

            CardCta(
                showProgress = showProgress,
                actionTitle = actionTitle,
                actionClick = actionClick
            )
        }

        if (label != null) {
            Box(
                modifier = Modifier
                    .padding(start = OceanSpacing.xs)
                    .height(18.dp)
                    .background(
                        color = OceanColors.brandPrimaryDown,
                        shape = RoundedCornerShape(40.dp)
                    )
                    .padding(horizontal = 6.dp)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = label,
                    fontSize = OceanFontSize.xxxs,
                    color = OceanColors.interfaceLightPure,
                    fontFamily = OceanFontFamily.BaseBold,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun ContentDefault(
    title: String,
    subtitle: String?
) {
    Text(
        text = title,
        fontFamily = OceanFontFamily.BaseExtraBold,
        fontSize = OceanFontSize.xs,
        color = OceanColors.interfaceDarkDeep
    )

    if (subtitle != null) {
        OceanSpacing.StackXXS()

        Text(
            text = subtitle,
            fontFamily = OceanFontFamily.BaseRegular,
            fontSize = OceanFontSize.xxs,
            color = OceanColors.interfaceDarkDown
        )
    }
}

@Composable
private fun ContentInverted(
    title: String,
    subtitle: String?
) {
    OceanText(
        text = title,
        style = OceanTextStyle.description,
        color = OceanColors.interfaceDarkDown
    )

    if (subtitle != null) {
        OceanSpacing.StackXXS()

        OceanText(
            text = subtitle,
            style = OceanTextStyle.heading4,
            color = OceanColors.interfaceDarkDeep
        )
    }
}


enum class CardGroupType {
    DEFAULT,
    INVERTED
}

