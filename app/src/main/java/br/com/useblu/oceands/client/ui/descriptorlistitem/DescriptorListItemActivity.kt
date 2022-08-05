package br.com.useblu.oceands.client.ui.descriptorlistitem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityDescriptorListBinding

class DescriptorListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptorListBinding
    private lateinit var viewModel: DescriptorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_descriptor_list)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[DescriptorListViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
