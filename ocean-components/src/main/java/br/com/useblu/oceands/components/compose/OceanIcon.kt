package br.com.useblu.oceands.components.compose

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.com.useblu.oceands.utils.toOceanIcon

@Composable
fun OceanIcon(
    token: String,
    modifier: Modifier = Modifier,
    fallbackIcon: String? = null
) {
    token.toOceanIcon()?.let {
        Icon(
            painter = painterResource(id = it),
            contentDescription = null,
            modifier = modifier,
        )
    } ?: fallbackIcon?.toOceanIcon()?.let {
        Icon(
            painter = painterResource(id = it),
            contentDescription = null,
            modifier = modifier,
        )
    }
}