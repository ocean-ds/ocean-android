package br.com.useblu.oceands.bindingadapters

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.components.OceanStepView
import br.com.useblu.oceands.R
import br.com.useblu.oceands.extensions.dp

@BindingAdapter("currentStep", "totalAmountSteps")
fun setupStepView(oceanStepView: OceanStepView, currentStep: Int?, totalAmountSteps: Int?) {
    if (currentStep != null && totalAmountSteps != null) {
        if (oceanStepView.stepCount == 0 && totalAmountSteps > 1) {
            oceanStepView.setStepsNumber(totalAmountSteps)

            val nextStepColor =
                ContextCompat.getColor(oceanStepView.context, R.color.ocean_color_interface_light_down)
            oceanStepView.State()
                .nextStepCircleColor(nextStepColor)
                .nextStepCircleEnabled(true)
                .numberCurrentStepEnabled(false)
                .numbersNextStepEnabled(false)
                .selectedCircleRadius(10.dp)
                .doneCircleRadius(10.dp)
                .commit()
        }


        if (currentStep < oceanStepView.stepCount && currentStep >= 0) {
            oceanStepView.go(currentStep, true)
        }
    }
}

@BindingAdapter("withAnimation")
fun setupWithAnimation(oceanStepView: OceanStepView, withAnimation: Boolean?) {
    withAnimation?.let {
        val animationType = if (it)
            OceanStepView.ANIMATION_LINE
        else
            OceanStepView.ANIMATION_NONE
        oceanStepView.State().animationType(animationType).commit()
    }
}
