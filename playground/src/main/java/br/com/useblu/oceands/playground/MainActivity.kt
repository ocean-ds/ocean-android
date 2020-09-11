package br.com.useblu.oceands.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.playground.databinding.MainActivityBinding
import br.com.useblu.oceands.playground.ui.main.HomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: HomeViewModel

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.viewmodel = viewModel

        initObservers()
    }

    private fun initObservers() {
        viewModel.onClickButton()
        //binding.textValue.text = "Soluções de negócios inovadoras e que beneficiam toda a cadeia, do varejo à indústria Soluções de negócios inovadoras e que beneficiam toda a cadeia, do varejo à indústria.";
        binding.editSpacingDefault.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    viewModel.onNewDefault(p0.toString().toFloat())
                } catch (e: Exception) {

                }
            }
        })

        binding.editSpacingMedium.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    viewModel.onNewMedium(p0.toString().toFloat())
                } catch (e: Exception) {

                }
            }
        })

        binding.editSpacingLarge.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    viewModel.onNewLarge(p0.toString().toFloat())
                } catch (e: Exception) {

                }
            }
        })

        viewModel.showNextState.observe(this, Observer {
            Toast.makeText(this, "Sample Click", Toast.LENGTH_LONG).show()

            //binding.textValue.setLineSpacing(0.0f,1.5f)
        })
    }
}
