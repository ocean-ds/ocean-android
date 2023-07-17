package br.com.useblu.oceands.components.compose

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.ui.compose.OceanColors


@Preview
@Composable
fun PreviewOceanStep() {
    Column {
        OceanStep(1, 3)
        OceanStep(2, 3)
        OceanStep(3, 3)
        OceanStep(4, 3)
    }
}

@Composable
fun OceanStep(
    @IntRange(from = 1) currentStep: Int,
    stepCount: Int
) {
    Row(
        modifier = Modifier.height(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1 until currentStep) {
            DoneCircle()
            if (i != stepCount) {
                StepLine(done = true)
            }
        }

        for (i in currentStep .. stepCount) {
            StepCircle(
                currentStep = i == currentStep,
                showLeftLine = i != 1,
                showRightLine = i != stepCount
            )

            if (i != stepCount) {
                StepLine(false)
            }
        }
    }

}

@Composable
fun StepLine(
    done: Boolean
) {
    val color = if (done) {
        OceanColors.complementaryPure()
    } else {
        OceanColors.interfaceLightDown()
    }

    Box(
        modifier = Modifier
            .height(2.dp)
            .width(12.dp)
            .background(color = color)
    )
}

@Composable
fun StepCircle(
    currentStep: Boolean,
    showLeftLine: Boolean,
    showRightLine: Boolean
) {
    val color = if (currentStep) {
        OceanColors.complementaryPure()
    } else {
        OceanColors.interfaceLightDown()
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        if (showLeftLine) {
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(4.dp)
                    .background(color = color)
            )
        } else {
            Spacer(modifier = Modifier.width(4.dp))
        }

        Box(
            modifier = Modifier
                .size(12.dp)
                .border(
                    width = 2.dp,
                    color = color,
                    shape = CircleShape
                )
        )

        if (showRightLine) {
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(4.dp)
                    .background(color = color)
            )
        } else {
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}


@Composable
fun DoneCircle() {
    val color = OceanColors.complementaryPure()

    Box(
        modifier = Modifier
            .background(color = color, shape = CircleShape)
            .size(20.dp),
        contentAlignment = Alignment.Center
    ) {
        OceanIcon(
            token = "checkoutline",
            modifier = Modifier.size(10.dp),
            tint = OceanColors.interfaceLightPure()
        )
    }
}