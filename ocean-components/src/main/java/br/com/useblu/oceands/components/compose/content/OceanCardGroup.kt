package br.com.useblu.oceands.components.compose.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.CardCta
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing

@Composable
fun OceanCardGroup(
    headerTitle: String,
    modifier: Modifier = Modifier,
    headerSubtitle: String? = null,
    badgeText: String? = null,
    badgeType: OceanBadgeType = OceanBadgeType.WARNING,
    footerActionTitle: String,
    actionFooterClick: () -> Unit,
    showProgress: Boolean = false,
    highlight: Boolean = false,
    label: String? = null
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = headerTitle,
                )

                if (headerSubtitle != null) {
                    OceanSpacing.StackXXS()

                    Text(
                        text = headerSubtitle,
                    )
                }
            }

            if (badgeText != null) {
                OceanBadge(
                    text = badgeText,
                    type = badgeType,
                    size = OceanBadgeSize.Small
                )
            }
        }

        Divider(color = OceanColors.interfaceLightDown)

        CardCta(
            showProgress = showProgress,
            actionTitle = footerActionTitle,
            actionClick = actionFooterClick
        )
    }
}