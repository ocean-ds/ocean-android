package br.com.useblu.oceands.client.ui.unorderedlistitem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityUnorderedListBinding

class UnorderedListItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUnorderedListBinding
    private lateinit var viewModel: UnorderedListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_unordered_list)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[UnorderedListViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
