package br.com.useblu.oceands.client.ui.shortcuts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCarouselBinding
import br.com.useblu.oceands.client.databinding.ActivityShortcutsBinding
import br.com.useblu.oceands.client.ui.carousel.CarouselViewModel

class ShortcutsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShortcutsBinding
    private lateinit var viewModel: ShortcutsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shortcuts)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[ShortcutsViewModel::class.java]
        binding.viewmodel = viewModel

        initObservers()

        viewModel.loadData()
    }

    private fun initObservers() {
        viewModel.itemSelected.observe(this, {
            Toast.makeText(this, "Item \"${it.label}\" selecionado", Toast.LENGTH_SHORT).show()
        })

        viewModel.itemSelected2.observe(this, {
            Toast.makeText(this, "Item \"${it.label}\" selecionado", Toast.LENGTH_SHORT).show()
        })
    }

}