package br.com.useblu.oceands.client.ui.listitem

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityListItemBinding

class ListItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListItemBinding
    private lateinit var viewModel: ListItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_item)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[ListItemViewModel::class.java]
        binding.viewmodel = viewModel
        initObservers()
    }

    private fun initObservers() {
        viewModel.clickedLink.observe(this) {
            Toast.makeText(this, "Clicked item id: $it", Toast.LENGTH_SHORT).show()
        }

        viewModel.longClickPressed.observe(this) {
            Toast.makeText(this, "Long click pressed", Toast.LENGTH_SHORT).show()
        }
    }
}
