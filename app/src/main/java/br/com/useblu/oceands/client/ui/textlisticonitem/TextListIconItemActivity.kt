package br.com.useblu.oceands.client.ui.textlisticonitem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTextListIconItemBinding

class TextListIconItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextListIconItemBinding
    private lateinit var viewModel: TextListIconItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_list_icon_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TextListIconItemViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
