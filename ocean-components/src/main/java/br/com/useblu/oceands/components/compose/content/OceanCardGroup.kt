package br.com.useblu.oceands.components.compose.content

import androidx.annotation.FloatRange
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.CardCta
import br.com.useblu.oceands.components.compose.OceanAlert
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.components.compose.OceanProgressBar
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.OceanTagLayout
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTextListItem
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.components.compose.content.CardGroupType.DEFAULT
import br.com.useblu.oceands.components.compose.content.CardGroupType.INVERTED
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.OceanTextListContentStyle
import br.com.useblu.oceands.model.OceanTextListStyle
import br.com.useblu.oceands.model.compose.AlertStyle
import br.com.useblu.oceands.model.compose.OceanAlertType
import br.com.useblu.oceands.model.compose.OceanTagModel
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanCardGroupPreview() {
    OceanTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = OceanColors.interfaceLightPure)
        ) {
            val alertInfo = OceanAlertType.WithBadges(
                alertType = AlertStyle.StyleInfo(),
                badges = listOf("getnet", "blu", "cielo"),
                label = "Use o saldo disponível na Rede e pague hoje",
                wrapSize = 3
            )

            OceanCardGroup(
                modifier = Modifier.padding(16.dp),
                title = "Crédito disponível",
                tag = OceanTagModel(
                    type = OceanTagType.Highlight,
                    text = "Boletos disponiveis"
                ),
                actionTitle = "Ver todos os boletos",
                actionClick = { },
                badgeText = "9",
                badgeType = OceanBadgeType.WARNING,
                alert = alertInfo
            ) {
                OceanTextListItem(
                    title = "Limite para pagar boletos",
                    description = "R$ 9.000,00",
                    caption = "Pague em até 12 vezes",
                    contentStyle = OceanTextListContentStyle.Inverted,
                    textListStyle = OceanTextListStyle.Icon(icon = OceanIcons.BRAND_MASTERCARD),
                    showDivider = false
                )
            }

            val alertPositive = OceanAlertType.WithBadges(
                alertType = AlertStyle.StylePositive(),
                badges = listOf("getnet", "blu", "cielo"),
                label = "Antecipe seus recebíveis com taxas especiais",
                wrapSize = 3
            )

            OceanCardGroup(
                modifier = Modifier.padding(16.dp),
                title = "Antecipação",
                tag = OceanTagModel(
                    type = OceanTagType.Highlight,
                    text = "Saldo disponível"
                ),
                actionTitle = "Antecipar recebíveis",
                actionClick = { },
                badgeText = "5",
                badgeType = OceanBadgeType.WARNING,
                alert = alertPositive
            ) {
                OceanTextListItem(
                    title = "Valor disponível para antecipação",
                    description = "R$ 15.000,00",
                    caption = "Taxa a partir de 1,99% a.m.",
                    contentStyle = OceanTextListContentStyle.Inverted,
                    textListStyle = OceanTextListStyle.Icon(icon = OceanIcons.BRAND_MASTERCARD),
                    showDivider = false
                )
            }

            val alertWarning = OceanAlertType.WithBadges(
                alertType = AlertStyle.StyleWarning(),
                label = "Você tem pagamentos que vencem em breve",
                wrapSize = 3
            )

            OceanCardGroup(
                modifier = Modifier.padding(16.dp),
                title = "Pagamentos pendentes",
                tag = OceanTagModel(
                    type = OceanTagType.Highlight,
                    text = "Requer atenção"
                ),
                actionTitle = "Gerenciar pagamentos",
                actionClick = { },
                badgeText = "3",
                badgeType = OceanBadgeType.WARNING,
                alert = alertWarning
            ) {
                OceanTextListItem(
                    title = "Total a pagar",
                    description = "R$ 2.500,00",
                    caption = "Vencimento em 3 dias",
                    contentStyle = OceanTextListContentStyle.Inverted,
                    textListStyle = OceanTextListStyle.Icon(icon = OceanIcons.BRAND_MASTERCARD),
                    showDivider = false
                )
            }

            OceanCardGroup(
                modifier = Modifier.padding(16.dp),
                title = "Crédito",
                tag = OceanTagModel(
                    type = OceanTagType.Highlight,
                    text = "Boletos disponiveis"
                ),
                backgroundColor = OceanColors.interfaceLightUp,
                actionTitle = "Ir para boletos",
                actionClick = { },
                badgeText = "9",
                badgeType = OceanBadgeType.WARNING,
                highlightText = "Pague seu boleto da Ortobom Colchões hoje usando seu limite de crédito.",
                highlightTextColor = OceanColors.interfaceLightPure,
                highlightBackgroundColor = OceanColors.brandPrimaryPure
            ) {
                OceanTextListItem(
                    title = "Limite para pagar boletos",
                    description = "R$ 9.000,00",
                    caption = "Pague em até 12 vezes",
                    contentStyle = OceanTextListContentStyle.Inverted,
                    textListStyle = OceanTextListStyle.Icon(icon = OceanIcons.BRAND_MASTERCARD),
                    backgroundColor = OceanColors.interfaceLightUp,
                    showDivider = false
                )
            }

            OceanCardGroup(
                modifier = Modifier.padding(16.dp),
                title = "Crédito",
                actionTitle = "Ir para parcelas atrasadas",
                actionClick = { },
                badgeText = "9",
                badgeType = OceanBadgeType.WARNING,
                highlightText = "Pague as parcelas atrasadas e tenha crédito disponível novamente",
                highlightTextColor = OceanColors.interfaceDarkDeep,
                highlightBackgroundColor = OceanColors.statusWarningDown
            ) {
                OceanTextListItem(
                    title = "Seu parcelamento está atrasado",
                    description = "R$ 4.328,04",
                    caption = "Fique em dia e tenha mais crédito",
                    contentStyle = OceanTextListContentStyle.Inverted,
                    textListStyle = OceanTextListStyle.Icon(icon = OceanIcons.BRAND_MASTERCARD),
                    showDivider = false
                )
            }

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
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    tag: OceanTagModel? = null,
    backgroundColor: Color = OceanColors.interfaceLightPure,
    actionTitle: String = "",
    actionClick: () -> Unit = { },
    showProgress: Boolean = false,
    badgeText: String = "",
    badgeType: OceanBadgeType = OceanBadgeType.WARNING,
    highlightText: String = "",
    highlightTextColor: Color = OceanColors.interfaceLightUp,
    highlightBackgroundColor: Color = OceanColors.brandPrimaryPure,
    alert: OceanAlertType.WithBadges? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        ),
        shape = OceanBorderRadius.SM.allCorners.shape(),
        border = BorderStroke(1.dp, OceanColors.interfaceLightDown),
        onClick = { /* No-op */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = OceanSpacing.xs)
                .padding(horizontal = OceanSpacing.xs)
                .padding(bottom = OceanSpacing.xxxs)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ContentDefault(
                    title = title,
                    subtitle = description
                )

                Spacer(modifier = Modifier.weight(1f))

                if (tag != null && tag.text.isNotEmpty()) {
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
        }

        content()

        alert?.let { type ->
            OceanAlert(
                type = type,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        CardCta(
            showProgress = showProgress,
            actionTitle = actionTitle,
            badgeText = badgeText,
            badgeType = badgeType,
            backgroundColor = if (highlightText.isNotEmpty()) highlightBackgroundColor else backgroundColor,
            actionClick = actionClick
        )

        if (highlightText.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = highlightBackgroundColor,
                        shape = OceanBorderRadius.SM.bottomCorners.shape()
                    )
                    .padding(
                        horizontal = OceanSpacing.xs,
                        vertical = OceanSpacing.xxsExtra
                    )
            ) {
                OceanText(
                    text = highlightText,
                    style = OceanTextStyle.captionBold,
                    color = highlightTextColor
                )
            }
        }
    }
}

@Composable
@Deprecated("Use the version with content lambda instead")
fun OceanCardGroup(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String = "",
    caption: String = "",
    badgeText: String = "",
    badgeType: OceanBadgeType = OceanBadgeType.WARNING,
    actionTitle: String,
    actionClick: () -> Unit,
    @FloatRange(0.0, 1.0) progressBar: Float? = null,
    showProgress: Boolean = false,
    label: String = "",
    type: CardGroupType = DEFAULT,
    tag: OceanTagModel? = null
) {
    Box(modifier = modifier) {
        val paddingTop = if (label.isNotBlank()) 9.dp else 0.dp
        Column(
            modifier = Modifier
                .padding(top = paddingTop)
                .border(
                    width = 1.dp,
                    color = if (label.isBlank()) OceanColors.interfaceLightDown
                    else OceanColors.brandPrimaryUp,
                    shape = OceanBorderRadius.SM.allCorners.shape()
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .borderBackground(
                        color = OceanColors.interfaceLightPure,
                        borderRadius = OceanBorderRadius.SM.topCorners
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

                    if (caption.isNotBlank()) {
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

                if (badgeText.isNotBlank()) {
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

        if (label.isNotBlank()) {
            Box(
                modifier = Modifier
                    .padding(start = OceanSpacing.xs)
                    .height(18.dp)
                    .borderBackground(
                        color = OceanColors.brandPrimaryDown,
                        borderRadius = OceanBorderRadius.Circle.allCorners
                    )
                    .padding(horizontal = 6.dp)
                    .align(Alignment.TopStart)
            ) {
                OceanText(
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
    subtitle: String
) {
    OceanText(
        text = title,
        fontFamily = OceanFontFamily.BaseExtraBold,
        fontSize = OceanFontSize.xs,
        color = OceanColors.interfaceDarkDeep
    )

    if (subtitle.isNotBlank()) {
        OceanSpacing.StackXXS()

        OceanText(
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
    subtitle: String
) {
    OceanText(
        text = title,
        style = OceanTextStyle.description,
        color = OceanColors.interfaceDarkDown
    )

    if (subtitle.isNotBlank()) {
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
