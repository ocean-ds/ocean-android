package br.com.useblu.oceands.client.ui.groupcta

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityGroupCtaBinding

class GroupCTAActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupCtaBinding
    private lateinit var viewModel: GroupCTAViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_cta)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[GroupCTAViewModel::class.java]
        binding.viewmodel = viewModel

        initObservables()

        viewModel.loadData()
    }

    private fun initObservables() {
        viewModel.shouldShowMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}
