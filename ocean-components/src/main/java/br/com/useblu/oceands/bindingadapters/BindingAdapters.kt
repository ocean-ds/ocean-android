package br.com.useblu.oceands.bindingadapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.text.InputType
import android.text.method.LinkMovementMethod
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import br.com.useblu.oceands.R
import br.com.useblu.oceands.extensions.dp
import br.com.useblu.oceands.model.OceanAlertType
import br.com.useblu.oceands.utils.Formatter
import br.com.useblu.oceands.utils.toOceanIcon
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("ocean_visible_or_invisible")
fun setVisibleOrInvisible(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("ocean_visible_or_gone")
fun setVisibleOrGone(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.GONE
}

@BindingAdapter("clickable_effect")
fun setClickableEffect(view: View, value: Boolean?) {
    if (value == true) {
        val outValue = TypedValue()
        view.context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        view.setBackgroundResource(outValue.resourceId)
    }
}

@BindingAdapter("ocean_text_from_html")
fun setTextFromHtml(view: TextView, text: String?) {
    view.text = HtmlCompat.fromHtml(text ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
    view.movementMethod = LinkMovementMethod.getInstance()
}

@BindingAdapter("ocean_layout_width")
fun setLayoutWidth(view: View, width: Int) {
    val params = view.layoutParams as ViewGroup.LayoutParams
    params.width = width
    view.layoutParams = params
}

@BindingAdapter("layout_height")
fun setLayoutHeight(view: View, height: Int) {
    val layoutParams: ViewGroup.LayoutParams = view.layoutParams
    layoutParams.height = height
    view.layoutParams = layoutParams
}

@BindingAdapter("android:layout_marginStart")
fun setLayoutMarginStart(view: View, dimen: Float) {
    val layoutParams = view.layoutParams as MarginLayoutParams
    layoutParams.marginStart = dimen.toInt()
    view.layoutParams = layoutParams
}

@BindingAdapter("onLongClickPressed")
fun setOnLongClickPressed(view: View, longClick: (() -> Unit)?) {
    view.setOnLongClickListener {
        longClick?.invoke()
        false
    }
}

@BindingAdapter("ocean_drawable_padding")
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

@BindingAdapter("ocean_background")
fun setOceanBackground(input: TextInputEditText, error: Boolean) {
    input.setBackgroundResource(R.drawable.ocean_input_text_field_states)

    if (error) input.setBackgroundResource(R.drawable.ocean_input_text_field_error)

    input.setOnFocusChangeListener { _, hasFocus ->
        when {
            error -> input.setBackgroundResource(R.drawable.ocean_input_text_field_error)
            hasFocus -> input.setBackgroundResource(R.drawable.ocean_input_text_field_focused)
            !input.text.isNullOrBlank() -> input.setBackgroundResource(R.drawable.ocean_input_text_field_activated)
            else -> input.setBackgroundResource(R.drawable.ocean_input_text_field_inactive)
        }
    }
}

@BindingAdapter("ocean_inputType")
fun setOceanInputType(inputText: TextInputEditText, inputType: Int) {
    val typeFace = inputText.typeface
    inputText.inputType = inputType

    if (inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
        val textInputLayout = inputText.parent.parent as TextInputLayout
        textInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        textInputLayout.setEndIconDrawable(R.drawable.ocean_selector_eye)
        textInputLayout.setEndIconTintList(
            AppCompatResources.getColorStateList(
                textInputLayout.context,
                R.color.ocean_color_interface_dark_up
            )
        )
        inputText.typeface = typeFace
    }
}

@BindingAdapter("ocean_text", "ocean_text_format")
fun setFormatType(view: TextView, text: String?, type: Formatter?) {
    if (type != null && !text.isNullOrBlank()) {
        view.text = type.format(text)
        val valueToDouble = text.toDoubleOrNull()
        if (valueToDouble != null && valueToDouble < 0) {
            view.setTextColor(
                ContextCompat.getColor(
                    view.context,
                    R.color.ocean_color_status_negative_pure
                )
            )
        } else {
            view.setTextColor(
                ContextCompat.getColor(
                    view.context,
                    R.color.ocean_color_interface_dark_deep
                )
            )
        }
    }
}

@BindingAdapter("ocean_text", "ocean_text_format", "change_color")
fun setFormatType(view: TextView, text: String?, type: Formatter?, changeColor: Boolean?) {
    if (type != null && !text.isNullOrBlank()) {
        view.text = type.format(text)
        val valueToDouble = text.toDoubleOrNull()
        if (valueToDouble != null && valueToDouble < 0) {
            if (changeColor == false) return

            view.setTextColor(
                ContextCompat.getColor(
                    view.context,
                    R.color.ocean_color_status_negative_pure
                )
            )
        } else {
            if (changeColor == false) return

            view.setTextColor(
                ContextCompat.getColor(
                    view.context,
                    R.color.ocean_color_interface_dark_deep
                )
            )
        }
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter(
    "ocean_text_value",
    "ocean_text_format_value",
    "highlighted_text",
    "show_signal"
)
fun setFormatTypeCurrency(
    view: TextView,
    text: String?,
    type: Formatter?,
    highlightedText: Boolean?,
    hideSignal: Boolean?
) {
    if (type != null && !text.isNullOrBlank()) {
        view.text = type.format(text)

        text.toDoubleOrNull()?.let { value ->
            when {
                value > 0 -> {
                    if (highlightedText == true)
                        view.setTextColor(
                            ContextCompat.getColor(
                                view.context,
                                R.color.ocean_color_status_positive_deep
                            )
                        )
                    if (hideSignal == true)
                        view.text = "+ ${view.text}"
                }

                value < 0 -> {
                    if (hideSignal == false)
                        view.text = view.text.toString().replace("- ", "")
                }

                else -> {
                    if (hideSignal == true)
                        view.text = "- ${view.text}"
                }
            }
        }
    }
}

@BindingAdapter("ocean_icon")
fun loadIcon(view: ImageView, token: String?) {
    if (!token.isNullOrBlank()) {
        val isUrl = token.contains("http")
        if (isUrl) {
            val placeHolder =
                AppCompatResources.getDrawable(view.context, R.drawable.circle_place_holder)
            Glide.with(view.context).load(token).placeholder(placeHolder).into(view)
        } else {
            val context = view.context
            val icon = AppCompatResources.getDrawable(
                context,
                token.toOceanIcon()
            )
            view.setImageDrawable(icon)
        }
    }
}

@BindingAdapter("is_active", "active_icon", "inactive_icon")
fun ImageView.loadBottomNavIcon(isActive: Boolean, activeToken: String?, inactiveToken: String?) {
    val activeResource = activeToken?.toOceanIcon()
    val inactiveResource = inactiveToken?.toOceanIcon()

    val finalIcon = if (isActive) {
        activeResource ?: inactiveResource
    } else {
        inactiveResource ?: activeResource
    }

    if (finalIcon != null) {
        val icon = AppCompatResources.getDrawable(context, finalIcon)
        setImageDrawable(icon)
    }
}

@BindingAdapter("ocean_tint")
fun setTintColor(view: ImageView, colorResourceId: Int?) {
    colorResourceId?.let {
        view.setColorFilter(ContextCompat.getColor(view.context, it))
    }
}

@BindingAdapter("tint")
fun setTint(view: ImageView, tint: Int?) {
    tint?.let {
        view.setColorFilter(it)
    }
}

@BindingAdapter("ocean_alert_background")
fun setOceanBackground(layout: LinearLayout, type: OceanAlertType?) {
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

        else -> {
            layout.setBackgroundResource(R.drawable.ocean_alert_warning_background)
        }
    }
}

@BindingAdapter("ocean_alert_src", "ocean_alert_icon", "ocean_alert_icon_token")
fun setOceanSrc(imageView: ImageView, type: OceanAlertType?, icon: Drawable?, token: String?) {
    if (token != null) {
        loadIconByToken(imageView, token, type)
    } else if (icon != null) {
        imageView.setImageDrawable(icon)
    } else {
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

            else -> {
                imageView.setBackgroundResource(R.drawable.icon_warning)
            }
        }
    }
}

private fun loadIconByToken(
    imageView: ImageView,
    token: String,
    type: OceanAlertType?
) {
    val icon = AppCompatResources.getDrawable(
        imageView.context,
        token.toOceanIcon()
    )
    imageView.setColorFilter(ContextCompat.getColor(imageView.context, getColor(type)))
    imageView.setImageDrawable(icon)
}

fun getColor(type: OceanAlertType?) = when (type) {
    OceanAlertType.Information -> R.color.ocean_color_brand_primary_down
    OceanAlertType.Error -> R.color.ocean_color_status_negative_pure
    OceanAlertType.Success -> R.color.ocean_color_status_positive_pure
    OceanAlertType.Warning -> R.color.ocean_color_status_warning_pure
    else -> R.color.ocean_color_interface_dark_down
}

@BindingAdapter("ocean_alert_text_color")
fun setOceanAlertTextColor(textView: TextView, type: OceanAlertType?) {
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
                    (R.color.ocean_color_status_warning_pure)
                )
            )
        }

        else -> {
            textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    (R.color.ocean_color_status_warning_pure)
                )
            )
        }
    }
}

@BindingAdapter("marginStart")
fun setCustomMarginStart(view: View, margin: Int) {
    val params = view.layoutParams as ConstraintLayout.LayoutParams
    params.marginStart = margin.dp
    view.layoutParams = params
}

@BindingAdapter("customMinHeight")
fun setCustomMinHeight(constraintLayout: ConstraintLayout, minHeight: Float) {
    constraintLayout.minHeight = minHeight.toInt()
}

@BindingAdapter("ocean_background")
fun setBackground(view: View, background: Drawable?) {
    if (background != null) {
        view.background = background
    } else {
        view.setBackgroundResource(0)
    }
}

