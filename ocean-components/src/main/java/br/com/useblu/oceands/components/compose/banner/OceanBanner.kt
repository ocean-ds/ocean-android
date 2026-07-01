package br.com.useblu.oceands.components.compose.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTextNotBlank
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.image.OceanImageProxy

@Composable
fun OceanBanner(
    modifier: Modifier,
    style: OceanBannerStyle,
    kind: OceanBannerKind,
    title: String,
    description: String = "",
    ctaTitle: String = "",
    onCtaClick: () -> Unit = { /* No-op */ },
    ctaIsEnabled: Boolean = true,
    secondaryCtaTitle: String = "",
    onSecondaryCtaClick: () -> Unit = { /* No-op */ },
    secondaryCtaIsEnabled: Boolean = true
) = Box(
    modifier = modifier
        .borderBackground(
            color = style.getBackgroundColor(),
            borderRadius = OceanBorderRadius.SM.allCorners
        )
) {
    when (kind) {
        is OceanBannerKind.Large -> OceanBannerLarge(
            style = style,
            image = kind.image,
            title = title,
            description = description,
            ctaTitle = ctaTitle,
            onCtaClick = onCtaClick,
            ctaIsEnabled = ctaIsEnabled,
            secondaryCtaTitle = secondaryCtaTitle,
            onSecondaryCtaClick = onSecondaryCtaClick,
            secondaryCtaIsEnabled = secondaryCtaIsEnabled
        )
        is OceanBannerKind.Small -> OceanBannerSmall(
            style = style,
            image = kind.image,
            title = title,
            description = description,
            ctaTitle = ctaTitle,
            onCtaClick = onCtaClick,
            ctaIsEnabled = ctaIsEnabled,
            secondaryCtaTitle = secondaryCtaTitle,
            onSecondaryCtaClick = onSecondaryCtaClick,
            secondaryCtaIsEnabled = secondaryCtaIsEnabled
        )
    }
}

@Composable
private fun OceanBannerLarge(
    image: OceanImageProxy?,
    style: OceanBannerStyle,
    title: String,
    description: String,
    ctaTitle: String,
    onCtaClick: () -> Unit,
    ctaIsEnabled: Boolean,
    secondaryCtaTitle: String,
    onSecondaryCtaClick: () -> Unit,
    secondaryCtaIsEnabled: Boolean
) = Column {
    image?.View()
    OceanBannerInfoContent(
        style = style,
        title = title,
        description = description,
        ctaTitle = ctaTitle,
        onCtaClick = onCtaClick,
        ctaIsEnabled = ctaIsEnabled,
        secondaryCtaTitle = secondaryCtaTitle,
        onSecondaryCtaClick = onSecondaryCtaClick,
        secondaryCtaIsEnabled = secondaryCtaIsEnabled,
        textToButtonSpacing = OceanSpacing.sm
    )
}

@Composable
private fun OceanBannerSmall(
    image: OceanImageProxy?,
    style: OceanBannerStyle,
    title: String,
    description: String,
    ctaTitle: String,
    onCtaClick: () -> Unit,
    ctaIsEnabled: Boolean,
    secondaryCtaTitle: String,
    onSecondaryCtaClick: () -> Unit,
    secondaryCtaIsEnabled: Boolean
) = Row {
    var contentHeight by remember { mutableIntStateOf(0) }
    val density = androidx.compose.ui.platform.LocalDensity.current
    val heightDp = with(density) { contentHeight.toDp() }

    OceanBannerInfoContent(
        modifier = Modifier
            .weight(1f)
            .onSizeChanged { contentHeight = it.height },
        style = style,
        title = title,
        description = description,
        ctaTitle = ctaTitle,
        onCtaClick = onCtaClick,
        ctaIsEnabled = ctaIsEnabled,
        secondaryCtaTitle = secondaryCtaTitle,
        onSecondaryCtaClick = onSecondaryCtaClick,
        secondaryCtaIsEnabled = secondaryCtaIsEnabled,
        textToButtonSpacing = OceanSpacing.xxsExtra
    )

    image?.View(
        modifier = Modifier
            .height(heightDp)
            .width(82.dp)
    )
}

@Composable
private fun OceanBannerInfoContent(
    modifier: Modifier = Modifier,
    style: OceanBannerStyle,
    title: String,
    description: String,
    ctaTitle: String,
    onCtaClick: () -> Unit,
    ctaIsEnabled: Boolean,
    secondaryCtaTitle: String = "",
    onSecondaryCtaClick: () -> Unit = { /* No-op */ },
    secondaryCtaIsEnabled: Boolean = true,
    textToButtonSpacing: Dp
) = Column(
    modifier = modifier
        .padding(OceanSpacing.xs)
) {
    OceanText(
        text = title,
        style = OceanTextStyle.heading4.copy(
            color = style.getTitleColor(),
            lineHeight = OceanFontSize.xs * 1.24f
        )
    )

    OceanSpacing.StackXXXS()

    OceanTextNotBlank(
        text = description,
        style = OceanTextStyle.description.copy(
            color = style.getDescriptionColor()
        )
    )

    if (ctaTitle.isNotEmpty() || secondaryCtaTitle.isNotEmpty()) {
        Spacer(modifier = Modifier.height(textToButtonSpacing))
        Row(
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
        ) {
            if (ctaTitle.isNotEmpty()) {
                val isEmphasys = style is OceanBannerStyle.Emphasys || style is OceanBannerStyle.Brand
                if (isEmphasys) {
                    // Primary branco sólido — interfaceLightPure + brandPrimaryPure text
                    Button(
                        onClick = onCtaClick,
                        enabled = ctaIsEnabled,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OceanColors.interfaceLightPure,
                            contentColor = OceanColors.brandPrimaryPure,
                            disabledContainerColor = OceanColors.interfaceLightDown,
                            disabledContentColor = OceanColors.interfaceDarkUp
                        ),
                        contentPadding = PaddingValues(horizontal = OceanSpacing.xs, vertical = 0.dp),
                        shape = OceanBorderRadius.Circle.allCorners.shape(),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        OceanText(
                            text = ctaTitle,
                            fontSize = OceanFontSize.xs,
                            fontFamily = OceanFontFamily.BaseBold,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    OceanButton(
                        text = ctaTitle,
                        onClick = onCtaClick,
                        buttonStyle = style.getButtonStyle(),
                        disabled = !ctaIsEnabled
                    )
                }
            }
            if (secondaryCtaTitle.isNotEmpty()) {
                val isEmphasys = style is OceanBannerStyle.Emphasys || style is OceanBannerStyle.Brand
                if (isEmphasys) {
                    // Tertiary com colorInterfaceLightPure — escopo exclusivo do Emphasys/Brand
                    Button(
                        onClick = onSecondaryCtaClick,
                        enabled = secondaryCtaIsEnabled,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = OceanColors.interfaceLightPure
                        ),
                        contentPadding = PaddingValues(0.dp),
                        shape = OceanBorderRadius.Circle.allCorners.shape(),
                        elevation = ButtonDefaults.buttonElevation(0.dp)
                    ) {
                        OceanText(
                            text = secondaryCtaTitle,
                            fontSize = OceanFontSize.xxs,
                            fontFamily = OceanFontFamily.BaseBold,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    OceanButton(
                        text = secondaryCtaTitle,
                        onClick = onSecondaryCtaClick,
                        buttonStyle = style.getSecondaryButtonStyle(),
                        disabled = !secondaryCtaIsEnabled
                    )
                }
            }
        }
    }
}

@Preview(heightDp = 1800)
@Composable
fun OceanBannerPreview() {
    OceanTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(color = OceanColors.interfaceLightPure)
                .padding(OceanSpacing.xxs),
            verticalArrangement = Arrangement.spacedBy(OceanSpacing.xs)
        ) {
            OceanBanner(
                modifier = Modifier,
                style = OceanBannerStyle.Neutral,
                kind = OceanBannerKind.Large(
                    image = OceanImageProxy.Resource(
                        resId = R.drawable.image_placeholder
                    )
                ),
                title = "Banner Title",
                description = "This is a description for the banner component.",
                ctaTitle = "Click Here",
                onCtaClick = {}
            )

            OceanBanner(
                modifier = Modifier.wrapContentSize(),
                style = OceanBannerStyle.Brand,
                kind = OceanBannerKind.Small(
                    image = OceanImageProxy.Resource(
                        resId = R.drawable.image_blocked,
                        contentScale = ContentScale.FillHeight,
                        alignment = Alignment.Center
                    )
                ),
                title = "Banner Title",
                description = "ahfsaidhfsiuahdfosiadhoasdhfoasduifaosdufhoasdufhsodfhsaoidfhsaoiudhfshiudhfsiuadhaoisdfhu",
                ctaTitle = "Click Here",
                onCtaClick = {}
            )

            OceanBanner(
                modifier = Modifier.wrapContentSize(),
                style = OceanBannerStyle.Neutral,
                kind = OceanBannerKind.Small(
                    image = OceanImageProxy.Resource(
                        resId = R.drawable.image_placeholder,
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )
                ),
                title = "Banner Title",
                description = "ahfsaidhfsiuahdfosiadhoasdhfoasduifaosdufhoasdufhsodfhsaoidfhsaoiudhfshiudhfsiuadhaoisdfhu",
                ctaTitle = "Click Here",
                onCtaClick = {}
            )

            OceanBanner(
                modifier = Modifier,
                style = OceanBannerStyle.Custom(
                    backgroundColor = OceanColors.statusNegativeUp,
                    titleColor = OceanColors.interfaceDarkDeep,
                    descriptionColor = OceanColors.interfaceDarkDown,
                    customButtonStyle = OceanButtonStyle.PrimaryMedium
                ),
                kind = OceanBannerKind.Small(
                    image = OceanImageProxy.URL(
                        url = "https://portal-cob.blu.com.br/assets/credblu/image_hero_bill_overdue_negative-b5bb0660469f00e54ae453279e54f24800fb953f9d4664d704f89f3ebadd9203.webp"
                    )
                ),
                title = "Recebíveis de fora da Blu podem ser usados pra pagar este boleto",
                description = "Para evitar isso, faça um Pix para a sua conta Blu ou pague em qualquer banco copiando o código de barras."
            )

            OceanBanner(
                modifier = Modifier,
                style = OceanBannerStyle.Warning,
                kind = OceanBannerKind.Small(),
                title = "Banner Title",
                description = "ahfsaidhfsiuahdfosiadhoasdhfoasduifaosdufhoasdufhsodfhsaoidfhsaoiudhfshiudhfsiuadhaoisdfhu",
                ctaTitle = "Click Here",
                onCtaClick = {},
                ctaIsEnabled = false
            )

            // Negative style
            OceanBanner(
                modifier = Modifier,
                style = OceanBannerStyle.Negative,
                kind = OceanBannerKind.Small(),
                title = "Atenção: pagamento recusado",
                description = "Seu pagamento foi recusado. Por favor, tente novamente ou use outro método.",
                ctaTitle = "Tentar novamente",
                onCtaClick = {}
            )

            // Emphasys style
            OceanBanner(
                modifier = Modifier,
                style = OceanBannerStyle.Emphasys,
                kind = OceanBannerKind.Small(),
                title = "Oferta especial para você",
                description = "Aproveite condições exclusivas disponíveis apenas hoje.",
                ctaTitle = "Saiba mais",
                onCtaClick = {}
            )

            // Large without image
            OceanBanner(
                modifier = Modifier,
                style = OceanBannerStyle.Neutral,
                kind = OceanBannerKind.Large(image = null),
                title = "Banner sem imagem",
                description = "OceanBannerKind.Large agora suporta imagem opcional.",
                ctaTitle = "Ação principal",
                onCtaClick = {}
            )

            // Two CTAs
            OceanBanner(
                modifier = Modifier,
                style = OceanBannerStyle.Negative,
                kind = OceanBannerKind.Large(image = null),
                title = "Saldo insuficiente",
                description = "Você não tem saldo suficiente para concluir esta operação.",
                ctaTitle = "Adicionar saldo",
                onCtaClick = {},
                secondaryCtaTitle = "Cancelar",
                onSecondaryCtaClick = {}
            )
        }
    }
}
