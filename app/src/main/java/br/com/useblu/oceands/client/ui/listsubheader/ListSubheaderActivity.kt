package br.com.useblu.oceands.client.ui.listsubheader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivitySubheaderBinding

class ListSubheaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubheaderBinding
    private lateinit var viewModelList: ListSubheaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subheader)
        binding.lifecycleOwner = this

        viewModelList = ViewModelProvider(this)[ListSubheaderViewModel::class.java]
        binding.viewmodel = viewModelList
    }
}
