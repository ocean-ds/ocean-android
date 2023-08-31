package br.com.useblu.oceands.components.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons
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
            title = "Title",
            description = "Description",
            actionTitle = "Call to action"
        )
    }
}

@Composable
fun OceanCardCrossSell(
    title: String,
    description: String,
    actionTitle: String,
    imageUrl: String? = null,
    @DrawableRes imageResource: Int? = null
) {
    Column(
        modifier = Modifier
            .background(
                color = OceanColors.interfaceLightUp,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = OceanColors.interfaceLightDown,
                shape = RoundedCornerShape(8.dp)
            )
    ){
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = OceanTextStyle.heading4
                )
                Text(
                    text = description,
                    style = OceanTextStyle.description
                )
            }

            when {
                imageUrl != null -> {
                    GlideImage(
                        imageModel = { imageUrl },
                        modifier = Modifier.size(80.dp)
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = OceanColors.interfaceLightPure,
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
                .border(
                    width = 1.dp,
                    color = OceanColors.interfaceLightDown,
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )

        ) {
            Text(
                text = actionTitle,
                color = OceanColors.brandPrimaryPure,
                fontFamily = OceanFontFamily.BaseBold,
                fontSize = 14.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )

            OceanIcon(
                iconType = OceanIcons.CHEVRON_RIGHT_SOLID,
                tint = OceanColors.brandPrimaryPure,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .padding(vertical = 14.dp)
                    .size(20.dp)
            )
        }
    }
}