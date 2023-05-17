package br.com.useblu.oceands.client.ui.progressbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityProgressBarBinding
import br.com.useblu.oceands.client.databinding.ActivityRadiosBinding

class ProgressBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgressBarBinding
    private lateinit var viewModel: ProgressBarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_progress_bar)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ProgressBarViewModel::class.java]
        binding.viewmodel = viewModel
    }
}