package br.com.useblu.oceands.components.compose.pinpad.password

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.useblu.oceands.components.compose.pinpad.OceanPinPad
import br.com.useblu.oceands.components.compose.pinpad.password.models.OceanPasswordPinPadHandler
import br.com.useblu.oceands.components.compose.pinpad.password.models.OceanPasswordPinPadType
import br.com.useblu.oceands.ui.compose.OceanColors

@Composable
fun OceanPasswordPinPad(
    type: OceanPasswordPinPadType,
    inputColor: Color = OceanColors.interfaceDarkDeep
) {
    OceanPinPad(
        handler = OceanPasswordPinPadHandler(
            type = type,
            inputColor = inputColor
        )
    )
}

@Preview
@Composable
fun OceanPasswordPinPadPreview() {
    OceanPasswordPinPad(
        type = OceanPasswordPinPadType.FixedSize(6)
    )
}
