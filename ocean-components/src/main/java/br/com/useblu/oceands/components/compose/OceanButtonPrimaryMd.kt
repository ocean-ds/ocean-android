
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.FontFamilyBaseBold
import br.com.useblu.oceands.ui.OceanColors
import br.com.useblu.oceands.ui.OceanFontSize
import br.com.useblu.oceands.ui.OceanSpacing
import br.com.useblu.oceands.ui.primaryDefaultButtonColors

@Preview
@Composable
private fun PreviewButton() {
    val icon = ContextCompat.getDrawable(
        LocalContext.current,
        R.drawable.icon_chevron_down
    )

    OceanButtonPrimaryMd(
        text = "Entrar",
        showProgress = true,
        icon = icon,
        disabled = false,
        modifier = Modifier,
        onClick = {
            println("Botão clicado")
        }
    )
}

@Composable
fun OceanButtonPrimaryMd(
    text: String,
    showProgress: Boolean = false,
    icon: Drawable? = null,
    disabled: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        colors = primaryDefaultButtonColors(),
        modifier = modifier
            .height(48.dp),
        shape = RoundedCornerShape(40.dp),
        enabled = !disabled
    ) {
        if (!showProgress) {
            Row {
                icon?.let {
                    Icon(
                        bitmap = it.toBitmap().asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )

                    OceanSpacing.StackXXS()
                }

                Text(
                    text = text,
                    fontSize = OceanFontSize.XS(),
                    fontFamily = FontFamilyBaseBold,
                )
            }
        } else {
            CircularProgressIndicator(
                color = OceanColors.InterfaceLightUp(),
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        }
    }
}