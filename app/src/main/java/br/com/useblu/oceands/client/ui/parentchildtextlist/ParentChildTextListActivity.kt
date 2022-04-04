package br.com.useblu.oceands.client.ui.parentchildtextlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityParentChildTextListBinding

class ParentChildTextListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentChildTextListBinding
    private lateinit var viewModel: ParentChildTextListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_child_text_list)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ParentChildTextListViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.loadData(ContextCompat.getDrawable(this,R.drawable.icon_generic_primary))

    }
}