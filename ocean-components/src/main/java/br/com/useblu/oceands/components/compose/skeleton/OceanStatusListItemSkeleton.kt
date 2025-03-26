package br.com.useblu.oceands.components.compose.skeleton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanShimmering
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.borderBackground

@Composable
fun OceanStatusListItemSkeleton(items: Int) {
    OceanShimmering { brush ->
        repeat(items) {
            Column(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .background(OceanColors.interfaceLightPure)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(96.dp)
                            .height(16.dp)
                            .borderBackground(
                                brush = brush,
                                borderRadius = OceanBorderRadius.Tiny.allCorners
                            )
                    )
                }
                Row {
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .fillMaxSize()
                            .borderBackground(
                                brush = brush,
                                borderRadius = OceanBorderRadius.Tiny.allCorners
                            )
                    )
                }
            }
        }
    }
}
