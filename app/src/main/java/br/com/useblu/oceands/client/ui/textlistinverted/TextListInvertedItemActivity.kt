package br.com.useblu.oceands.client.ui.textlistinverted

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTextListInvertedItemBinding

class TextListInvertedItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextListInvertedItemBinding
    private lateinit var viewModel: TextListInvertedItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_list_inverted_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TextListInvertedItemViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
