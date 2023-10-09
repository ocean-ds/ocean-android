package br.com.useblu.oceands.components.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Preview
@Composable
fun OceanCardCrossSellPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(16.dp)
    ) {
        OceanCardCrossSell(
            title = "Boleto Mais Seguro",
            description = "Menos atraso e inadimplência para vendas feitas com boleto",
            actionTitle = "Saiba mais"
        )

        Spacer(modifier = Modifier.size(16.dp))

        OceanCardCrossSell(
            title = "Boleto Mais Seguro",
            description = "Menos atraso e inadimplência para vendas feitas com boleto",
            actionTitle = "Saiba mais",
            showProgress = true
        )
    }
}

@Composable
fun OceanCardCrossSell(
    title: String,
    description: String,
    actionTitle: String,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    @DrawableRes imageResource: Int? = null,
    actionIcon: OceanIcons = OceanIcons.CHEVRON_RIGHT_SOLID,
    titleColor: Color = Color.Unspecified,
    descriptionColor: Color = Color.Unspecified,
    backgroundColor: Color = OceanColors.interfaceLightUp,
    showProgress: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = OceanColors.interfaceLightDown,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                enabled = !showProgress && onClick != null,
                onClick = { onClick?.invoke() }
            )
    ){
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = OceanTextStyle.heading4,
                    color = titleColor
                )

                OceanSpacing.StackXXXS()

                Text(
                    text = description,
                    style = OceanTextStyle.description,
                    color = descriptionColor
                )
            }

            when {
                imageUrl != null -> {
                    GlideImage(
                        imageModel = { imageUrl },
                        modifier = Modifier.size(80.dp),
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center
                        )
                    )
                }
                imageResource != null -> {
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                }
                else -> Spacer(modifier = Modifier.height(80.dp))
            }
        }

        CardCta(showProgress, actionTitle, actionIcon)
    }
}
