package br.com.useblu.oceands

import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:ocean_visible_or_invisible")
fun setVisibleOrInvisible(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("app:ocean_visible_or_gone")
fun setVisibleOrGone(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.GONE
}

@BindingAdapter("app:ocean_text_from_html")
fun setTextFromHtml(view: TextView, text: String?) {
    view.text = HtmlCompat.fromHtml(text ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@BindingAdapter("app:ocean_layout_width")
fun setLayoutWidth(view: View, width: Int) {
    val params = view?.layoutParams as ViewGroup.LayoutParams
    params.width = width
    view?.layoutParams = params
}

@BindingAdapter("app:ocean_drawable_padding")
fun setOceanDrawablePadding(button: Button, @DimenRes dimenId: Int) {
    var dimensionPixelSize = 0

    if (dimenId != 0) {
        dimensionPixelSize = button.context.resources.getDimensionPixelSize(dimenId)
    }

    if (button is MaterialButton) {
        button.iconPadding = dimensionPixelSize
    } else {
        button.compoundDrawablePadding = dimensionPixelSize
    }
}

@BindingAdapter("app:ocean_background")
fun setOceanBackground(input: TextInputEditText, erro: Boolean) {
    input.setBackgroundResource(R.drawable.ocean_input_text_field_states)

    if (erro) input.setBackgroundResource(R.drawable.ocean_input_text_field_error)

    input.setOnFocusChangeListener { _, hasFocus ->
        when {
            erro -> input.setBackgroundResource(R.drawable.ocean_input_text_field_error)
            hasFocus -> input.setBackgroundResource(R.drawable.ocean_input_text_field_focused)
            !input.text.isNullOrBlank() -> input.setBackgroundResource(R.drawable.ocean_input_text_field_activated)
            else -> input.setBackgroundResource(R.drawable.ocean_input_text_field_inactive)
        }
    }
}

@BindingAdapter("app:ocean_inputType")
fun setOceanInputType(inputText: TextInputEditText, inputType: Int) {
    val typeFace = inputText.typeface
    inputText.inputType = inputType

    if (inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
        val textInputLayout = inputText.parent.parent as TextInputLayout
        textInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        textInputLayout.setEndIconDrawable(R.drawable.ocean_selector_eye)
        inputText.typeface = typeFace
    }
}
