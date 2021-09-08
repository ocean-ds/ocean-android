package br.com.useblu.oceands.client.ui.badges

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityAlertBinding
import br.com.useblu.oceands.client.databinding.ActivityBadgesBinding
import br.com.useblu.oceands.client.ui.alert.AlertViewModel

class BadgesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBadgesBinding
    private lateinit var viewModel: BadgesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_badges)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[BadgesViewModel::class.java]
        binding.viewmodel = viewModel
    }

}