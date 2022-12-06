package br.com.useblu.oceands.client.ui.orderedlistitem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityListItemBinding
import br.com.useblu.oceands.client.databinding.ActivityOrderedListBindingImpl
import br.com.useblu.oceands.client.ui.listitem.ListItemViewModel

class OrderedListItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderedListBindingImpl
    private lateinit var viewModel: OrderedListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ordered_list)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[OrderedListViewModel::class.java]
        binding.viewmodel = viewModel
    }
}
