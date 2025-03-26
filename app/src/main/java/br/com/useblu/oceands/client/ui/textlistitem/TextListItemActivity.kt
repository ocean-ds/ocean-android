package br.com.useblu.oceands.client.ui.textlistitem

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTextListItemBinding

class TextListItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextListItemBinding
    private lateinit var viewModel: TextListItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_list_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TextListItemViewModel::class.java]
        binding.viewmodel = viewModel
        initObservers()
    }

    private fun initObservers() {
        viewModel.touchItem.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}
