package br.com.useblu.oceands.client.ui.descriptorlistitem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityDescriptorListBinding
import br.com.useblu.oceands.components.compose.list.OceanDescriptorList

class DescriptorListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptorListBinding
    private lateinit var viewModel: DescriptorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_descriptor_list)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[DescriptorListViewModel::class.java]
        binding.viewmodel = viewModel

        binding.composeView.setContent {
            Column {
                Text(text = "Componente em Compose", modifier = Modifier.padding(vertical = 16.dp))

                OceanDescriptorList(
                    items = viewModel.entries
                )
            }
        }
    }
}
