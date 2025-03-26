package br.com.useblu.oceands.client.ui.input

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityInputBinding
import br.com.useblu.oceands.components.compose.input.OceanTextInput
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.stringmask.OceanInputType

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private lateinit var viewModel: InputViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[InputViewModel::class.java]
        binding.viewmodel = viewModel

        initObservers()

        binding.composeView.setContent {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Inputs em Compose", style = OceanTextStyle.heading3)

                OceanTextInputHelper(label = "Input Default", inputType = OceanInputType.DEFAULT)
                OceanTextInputHelper(label = "Input Email", inputType = OceanInputType.Email)
                OceanTextInputHelper(label = "Input Data", inputType = OceanInputType.Date)
                OceanTextInputHelper(label = "Input Billet", inputType = OceanInputType.BankBillet)
                OceanTextInputHelper(label = "Input CNPJ", inputType = OceanInputType.CNPJ)
                OceanTextInputHelper(label = "Input CPF", inputType = OceanInputType.CPF)
                OceanTextInputHelper(label = "Input CPF/CNPJ", inputType = OceanInputType.CpfCnpj)
                OceanTextInputHelper(label = "Input CEP", inputType = OceanInputType.CEP)
                OceanTextInputHelper(label = "Input Phone", inputType = OceanInputType.Phone)
            }
        }
    }

    @Composable
    private fun OceanTextInputHelper(
        label: String,
        inputType: OceanInputType
    ) {
        var text by remember { mutableStateOf("") }

        OceanTextInput(
            value = text,
            label = label,
            placeholder = label,
            onTextChanged = {
                text = it
            },
            oceanInputType = inputType
        )
    }

    private fun initObservers() {
        viewModel.itemSelect.observe(this) {
            Toast.makeText(this, "Item selecionado na posição $it", Toast.LENGTH_SHORT).show()
            println("Item selecionado ${viewModel.selectItem}")
        }
        viewModel.itemSelect2.observe(this) {
            Toast.makeText(this, "Item selecionado na posição $it", Toast.LENGTH_SHORT).show()
            println("Item selecionado ${viewModel.selectItem2}")
        }
        viewModel.tokenValue.observe(this) {
            Toast.makeText(this, "Token value $it", Toast.LENGTH_SHORT).show()
        }
    }
}
