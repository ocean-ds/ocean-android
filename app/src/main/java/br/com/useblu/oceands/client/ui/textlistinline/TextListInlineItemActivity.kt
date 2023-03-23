package br.com.useblu.oceands.client.ui.textlistinline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTextListInlineItemBinding

class TextListInlineItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextListInlineItemBinding
    private lateinit var viewModel: TextListInlineItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_list_inline_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TextListInlineItemViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
