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
    val linearLayout = inputText.parent.parent.parent as LinearLayout
    val inputOne = linearLayout.findViewById<TextInputEditText>(R.id.inputOne)
    val inputTwo = linearLayout.findViewById<TextInputEditText>(R.id.inputTwo)
    val inputThree = linearLayout.findViewById<TextInputEditText>(R.id.inputThree)
    val inputFour = linearLayout.findViewById<TextInputEditText>(R.id.inputFour)

    inputText.addTextChangedListener(
        inputTextWatcher {
            if (inputText.text.isNullOrBlank()) {
                backSpaceFocus(error, currentPosition, inputOne, inputTwo, inputThree)
            } else {
                nextFocus(currentPosition, inputTwo, inputThree, inputFour)
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
    val linearLayout = inputText.parent.parent.parent as LinearLayout
    val inputOne = linearLayout.findViewById<TextInputEditText>(R.id.inputOne)
    val inputTwo = linearLayout.findViewById<TextInputEditText>(R.id.inputTwo)
    val inputThree = linearLayout.findViewById<TextInputEditText>(R.id.inputThree)
    val inputFour = linearLayout.findViewById<TextInputEditText>(R.id.inputFour)

    inputFour.addTextChangedListener(
        inputTextWatcher {
            if (inputFour.text.toString().isNotBlank()) {
                value?.postValue(
                    inputOne.text.toString()
                            + inputTwo.text.toString()
                            + inputThree.text.toString()
                            + inputFour.text.toString()
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
    val linearLayout = inputText.parent.parent.parent as LinearLayout
    val inputOne = linearLayout.findViewById<TextInputEditText>(R.id.inputOne)
    val inputTwo = linearLayout.findViewById<TextInputEditText>(R.id.inputTwo)
    val inputThree = linearLayout.findViewById<TextInputEditText>(R.id.inputThree)
    val inputFour = linearLayout.findViewById<TextInputEditText>(R.id.inputFour)

    val value = autocomplete?.value ?: ""
    if (value.isNotBlank() && value.length == 4) {
        tokenAutoComplete(value, inputOne, inputTwo, inputThree, inputFour)
    } else {
        inputText.addTextChangedListener(
            inputTextWatcher {
                tokenAutoComplete(
                    inputText.text.toString(),
                    inputOne,
                    inputTwo,
                    inputThree,
                    inputFour
                )
            }
        )
    }
}

private fun nextFocus(
    currentPosition: Int,
    inputTwo: TextInputEditText,
    inputThree: TextInputEditText,
    inputFour: TextInputEditText
) {
    when (currentPosition) {
        1 -> if (inputFour.text.toString().isNotBlank()) inputFour.requestFocus()
        else inputTwo.requestFocus()
        2 -> inputThree.requestFocus()
        3 -> inputFour.requestFocus()
    }
}

private fun backSpaceFocus(
    error: MutableLiveData<String>?,
    currentPosition: Int,
    inputOne: TextInputEditText,
    inputTwo: TextInputEditText,
    inputThree: TextInputEditText
) {
    error?.postValue("")
    when (currentPosition) {
        2 -> inputOne.requestFocus()
        3 -> inputTwo.requestFocus()
        4 -> inputThree.requestFocus()
    }
}

private fun tokenAutoComplete(
    valueInput: String,
    inputOne: TextInputEditText,
    inputTwo: TextInputEditText,
    inputThree: TextInputEditText,
    inputFour: TextInputEditText
) {
    if (valueInput.isNotBlank() && valueInput.length == 4) {
        val lastPosition = 1

        inputOne.setText(valueInput[0].toString())
        inputOne.setSelection(lastPosition)

        inputTwo.setText(valueInput[1].toString())
        inputTwo.setSelection(lastPosition)

        inputThree.setText(valueInput[2].toString())
        inputThree.setSelection(lastPosition)

        inputFour.setText(valueInput[3].toString())
        inputFour.setSelection(lastPosition)

        inputOne.clearFocus()
        inputTwo.clearFocus()
        inputThree.clearFocus()
        inputFour.requestFocus()
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
