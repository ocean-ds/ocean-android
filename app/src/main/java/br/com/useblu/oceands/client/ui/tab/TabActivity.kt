package br.com.useblu.oceands.client.ui.tab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivitySwitchsBinding
import br.com.useblu.oceands.client.databinding.ActivityTabBinding
import br.com.useblu.oceands.client.ui.switchs.SwitchsViewModel

class TabActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabBinding
    private lateinit var viewModel: TabViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tab)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[TabViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.loadDetails()

        viewModel.showToast.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

}
