package br.com.useblu.oceands.client.ui.accordion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityAccordionBinding

class AccordionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccordionBinding
    private lateinit var viewModel: AccordionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_accordion)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[AccordionViewModel::class.java]
        binding.viewmodel = viewModel
    }
}