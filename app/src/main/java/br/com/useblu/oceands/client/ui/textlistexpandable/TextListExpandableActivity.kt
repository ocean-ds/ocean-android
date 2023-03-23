package br.com.useblu.oceands.client.ui.textlistexpandable

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTextListExpandableBinding

class TextListExpandableActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextListExpandableBinding
    private lateinit var viewModel: TextListExpandableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_list_expandable)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[TextListExpandableViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.loadData(ContextCompat.getDrawable(this, R.drawable.icon_generic_primary))
        initObservers()
    }

    private fun initObservers() {

        viewModel.showMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}