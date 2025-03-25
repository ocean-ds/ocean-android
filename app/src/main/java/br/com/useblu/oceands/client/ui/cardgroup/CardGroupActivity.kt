package br.com.useblu.oceands.client.ui.cardgroup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCardGroupBinding

class CardGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardGroupBinding
    private lateinit var viewModel: CardGroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_group)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[CardGroupViewModel::class.java]
        binding.viewmodel = viewModel

        initObservables()

        viewModel.loadData()
    }

    private fun initObservables() {
        viewModel.shouldShowMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}
