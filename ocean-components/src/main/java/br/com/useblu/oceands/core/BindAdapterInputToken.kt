package br.com.useblu.oceands.core

import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import br.com.useblu.oceands.R
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter(
    "app:ocean_current_position",
    "app:ocean_value",
    "app:ocean_error",
)
fun setOceanNextFocus(
    inputText: TextInputEditText,
    currentPosition: Int,
    value: MutableLiveData<String>?,
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
                error?.postValue("")
                when (currentPosition) {
                    2 -> inputOne.requestFocus()
                    3 -> inputTwo.requestFocus()
                    4 -> inputThree.requestFocus()
                }
            }
        }
    )

    inputText.addTextChangedListener(
        inputTextWatcher {
            if (inputText.text.toString().isNotBlank()) {
                when (currentPosition) {
                    1 -> inputTwo.requestFocus()
                    2 -> inputThree.requestFocus()
                    3 -> inputFour.requestFocus()
                }
            }
        }
    )

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

private fun inputTextWatcher(
    action: () -> Unit
) = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable?) {
        action.invoke()
    }
}
