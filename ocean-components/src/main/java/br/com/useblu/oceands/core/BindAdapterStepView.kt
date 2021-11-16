package br.com.useblu.oceands.core

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.StepView

@BindingAdapter("currentStep", "totalAmountSteps")
fun setupStepView(stepView: StepView, currentStep: Int?, totalAmountSteps: Int?) {
    if (currentStep != null && totalAmountSteps != null) {
        if (stepView.stepCount == 0 && totalAmountSteps > 1) {
            stepView.setStepsNumber(totalAmountSteps)

            val nextStepColor =
                ContextCompat.getColor(stepView.context, R.color.ocean_color_interface_light_down)
            stepView.state
                .nextStepCircleColor(nextStepColor)
                .nextStepCircleEnabled(true)
                .numberCurrentStepEnabled(false)
                .numbersNextStepEnabled(false)
                .selectedCircleRadius(10.dp)
                .doneCircleRadius(10.dp)
                .commit()
        }


        if (currentStep < stepView.stepCount && currentStep >= 0) {
            stepView.go(currentStep, true)
        }
    }
}
