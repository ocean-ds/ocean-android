package br.com.useblu.oceands.client.ui.optionscard

import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.client.R

class OptionsCardViewModel : ViewModel() {

    fun onOptionsCardClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.card_options_one -> {
                    if (checked) {
                        println("Item 1 selecionado")
                    }
                }
                R.id.card_options_two -> {
                    if (checked) {
                        println("Item 2 selecionado")
                    }
                }
                R.id.card_options_thee -> {
                    if (checked) {
                        println("Item 3 selecionado")
                    }
                }
                R.id.card_options_for -> {
                    if (checked) {
                        println("Item 4 selecionado")
                    }
                }
            }
        }
    }

    fun onOptionsCardClickedSM(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.card_options_one_sm -> {
                    if (checked) {
                        println("Item 1 selecionado")
                    }
                }
                R.id.card_options_two_sm -> {
                    if (checked) {
                        println("Item 2 selecionado")
                    }
                }
                R.id.card_options_thee_sm -> {
                    if (checked) {
                        println("Item 3 selecionado")
                    }
                }
                R.id.card_options_for_sm -> {
                    if (checked) {
                        println("Item 4 selecionado")
                    }
                }
            }
        }
    }
}