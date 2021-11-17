package br.com.useblu.oceands.core

import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import br.com.useblu.oceands.R
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter(
    "app:ocean_input_token_focus",
    "app:ocean_input_token_error",
)
fun setOceanInputTokenFocus(
    inputText: TextInputEditText,
    currentPosition: Int,
    error: MutableLiveData<String>?
) {
    val components = bindingComponents(inputText)

    inputText.addTextChangedListener(
        inputTextWatcher {
            if (inputText.text.isNullOrBlank()) {
                backSpaceFocus(error, currentPosition, components)
            } else {
                nextFocus(currentPosition, components)
            }
        }
    )
}

@BindingAdapter(
    "app:ocean_input_token_value"
)
fun setOceanInputTokenValue(
    inputText: TextInputEditText,
    value: MutableLiveData<String>?,
) {
    val components = bindingComponents(inputText)
    val lastComponent = components.last()

    lastComponent.addTextChangedListener(
        inputTextWatcher {
            if (lastComponent.text.toString().isNotBlank()) {
                value?.postValue(
                    components.fold(
                        "",
                        { token, textInputEditText ->
                            token + textInputEditText.text.toString()
                        }
                    )
                )
            }
        }
    )
}

@BindingAdapter(
    "app:ocean_input_token_autocompleted"
)
fun setOceanInputTokenAutocompleted(
    inputText: TextInputEditText,
    autocomplete: MutableLiveData<String>?
) {
    val components = bindingComponents(inputText)
    val value = autocomplete?.value ?: ""

    if (value.isNotBlank() && value.length == 4) {
        tokenAutoComplete(value, components)
    } else {
        inputText.addTextChangedListener(
            inputTextWatcher {
                tokenAutoComplete(
                    inputText.text.toString(),
                    components
                )
            }
        )
    }
}

fun bindingComponents(inputText: TextInputEditText): List<TextInputEditText> {
    val linearLayout = inputText.parent.parent.parent as LinearLayout
    return listOf(
        linearLayout.findViewById(R.id.inputOne),
        linearLayout.findViewById(R.id.inputTwo),
        linearLayout.findViewById(R.id.inputThree),
        linearLayout.findViewById(R.id.inputFour)
    )
}

private fun nextFocus(
    currentPosition: Int,
    components: List<TextInputEditText>,
) {
    if ((components.size - 1) == currentPosition) return
    if (components.last().text.toString().isNotBlank()) {
        components.last().requestFocus()
        return
    }
    components[currentPosition + 1].requestFocus()
}

private fun backSpaceFocus(
    error: MutableLiveData<String>?,
    currentPosition: Int,
    components: List<TextInputEditText>,
) {
    if (currentPosition == 0) return
    error?.postValue("")
    components[currentPosition - 1].requestFocus()
}

private fun tokenAutoComplete(
    valueInput: String,
    components: List<TextInputEditText>
) {
    if (valueInput.isNotBlank() && valueInput.length == 4) {
        components.forEachIndexed { index, textInputEditText ->
            textInputEditText.setText(valueInput[index].toString())
            textInputEditText.setSelection(1)
        }
    }
}

private fun inputTextWatcher(
    action: () -> Unit
) = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable?) {
        action.invoke()
    }
}
