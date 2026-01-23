package br.com.useblu.oceands.components.compose.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTextNotBlank
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.extensions.compose.height
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
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
    ctaTitle: String,
    onCtaClick: () -> Unit
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
            onCtaClick = onCtaClick
        )
        is OceanBannerKind.Small -> OceanBannerSmall(
            style = style,
            image = kind.image,
            title = title,
            description = description,
            ctaTitle = ctaTitle,
            onCtaClick = onCtaClick
        )
    }
}

@Composable
private fun OceanBannerLarge(
    image: OceanImageProxy,
    style: OceanBannerStyle,
    title: String,
    description: String,
    ctaTitle: String,
    onCtaClick: () -> Unit
) = Column {
    image.View()
    OceanBannerInfoContent(
        style = style,
        title = title,
        description = description,
        ctaTitle = ctaTitle,
        onCtaClick = onCtaClick
    )
}

@Composable
private fun OceanBannerSmall(
    image: OceanImageProxy?,
    style: OceanBannerStyle,
    title: String,
    description: String,
    ctaTitle: String,
    onCtaClick: () -> Unit
) = Row(modifier = Modifier.height(IntrinsicSize.Min)) {
    var contentHeight by remember { mutableIntStateOf(0) }

    OceanBannerInfoContent(
        modifier = Modifier
            .weight(1f)
            .onSizeChanged { contentHeight = it.height },
        style = style,
        title = title,
        description = description,
        ctaTitle = ctaTitle,
        onCtaClick = onCtaClick
    )
    image?.View(
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(max = with(LocalConfiguration.current) { screenWidthDp.dp * 0.25f })
    )
}

@Composable
private fun OceanBannerInfoContent(
    modifier: Modifier = Modifier,
    style: OceanBannerStyle,
    title: String,
    description: String,
    ctaTitle: String,
    onCtaClick: () -> Unit
) = Column(
    modifier = modifier
        .padding(OceanSpacing.xs)
) {
    OceanText(
        text = title,
        style = OceanTextStyle.heading4.copy(
            color = style.getTitleColor()
        )
    )

    OceanSpacing.StackXXXS()

    OceanTextNotBlank(
        text = description,
        style = OceanTextStyle.caption.copy(
            color = style.getDescriptionColor()
        ),
        modifier = Modifier
            .padding(top = OceanSpacing.xxxs)
    )

    OceanSpacing.StackXXSExtra()

    OceanButton(
        text = ctaTitle,
        onClick = onCtaClick,
        buttonStyle = style.getButtonStyle()
    )
}

@Preview(heightDp = 1100)
@Composable
private fun OceanBannerPreview() = OceanTheme {
    Column(
        modifier = Modifier
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
            style = OceanBannerStyle.Neutral,
            kind = OceanBannerKind.Small(),
            title = "Banner Title",
            description = "ahfsaidhfsiuahdfosiadhoasdhfoasduifaosdufhoasdufhsodfhsaoidfhsaoiudhfshiudhfsiuadhaoisdfhu",
            ctaTitle = "Click Here",
            onCtaClick = {}
        )

        OceanBanner(
            modifier = Modifier,
            style = OceanBannerStyle.Warning,
            kind = OceanBannerKind.Small(),
            title = "Banner Title",
            description = "ahfsaidhfsiuahdfosiadhoasdhfoasduifaosdufhoasdufhsodfhsaoidfhsaoiudhfshiudhfsiuadhaoisdfhu",
            ctaTitle = "Click Here",
            onCtaClick = {}
        )
    }
}
