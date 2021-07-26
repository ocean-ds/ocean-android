package br.com.useblu.oceands.core

import android.graphics.drawable.Drawable
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.useblu.oceands.R
import com.bumptech.glide.Glide
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
    val params = view.layoutParams as ViewGroup.LayoutParams
    params.width = width
    view.layoutParams = params
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

@BindingAdapter("app:ocean_text", "app:ocean_text_format")
fun setFormatType(view: TextView, text: String?, type: Formatter?) {
    if (type != null && !text.isNullOrBlank()) {
        view.text = type.format(text)
    }
}

@BindingAdapter("imageUrl", "placeHolder")
fun loadImage(view: ImageView, url: String?, placeHolder: Drawable) {
    if (url.isNullOrEmpty().not()) {
        Glide.with(view.context).load(url).placeholder(placeHolder).into(view)
    } else {
        view.setImageDrawable(placeHolder)
    }
}

@BindingAdapter("setAdapter")
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

@BindingAdapter("app:ocean_alert_background")
fun setOceanBackground(layout: ConstraintLayout, type: OceanAlertType) {
    when (type) {
        OceanAlertType.Information -> {
            layout.setBackgroundResource(R.drawable.ocean_alert_info_background)
        }
        OceanAlertType.Error -> {
            layout.setBackgroundResource(R.drawable.ocean_alert_error_background)

        }
        OceanAlertType.Success -> {
            layout.setBackgroundResource(R.drawable.ocean_alert_success_background)
        }
        OceanAlertType.Warning -> {
            layout.setBackgroundResource(R.drawable.ocean_alert_warning_background)
        }
    }
}

@BindingAdapter("app:ocean_alert_src")
fun setOceanSrc(imageView: ImageView, type: OceanAlertType) {
    when (type) {
        OceanAlertType.Information -> {
            imageView.setBackgroundResource(R.drawable.icon_information)
        }
        OceanAlertType.Error -> {
            imageView.setBackgroundResource(R.drawable.icon_error)
        }
        OceanAlertType.Success -> {
            imageView.setBackgroundResource(R.drawable.icon_success)
        }
        OceanAlertType.Warning -> {
            imageView.setBackgroundResource(R.drawable.icon_warning)
        }
    }
}

@BindingAdapter("app:ocean_alert_text_color")
fun setOceanAlertTextColor(textView: TextView, type: OceanAlertType) {
    val context = textView.context
    when (type) {
        OceanAlertType.Information -> {
            textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    (R.color.ocean_color_brand_primary_down)
                )
            )
        }
        OceanAlertType.Error -> {
            textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    (R.color.ocean_color_status_negative_pure)
                )
            )

        }
        OceanAlertType.Success -> {
            textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    (R.color.ocean_color_status_positive_deep)
                )
            )
        }
        OceanAlertType.Warning -> {
            textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    (R.color.ocean_color_status_neutral_deep)
                )
            )
        }
    }
}

@BindingAdapter("app:marginStart")
fun setCustomMarginStart(view: View, margin: Int) {
    val params = view.layoutParams as ConstraintLayout.LayoutParams
    params.marginStart = margin.dp
    view.layoutParams = params
}