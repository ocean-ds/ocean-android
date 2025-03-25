package br.com.useblu.oceands.client.ui.headings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityHeadingsBinding

class HeadingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeadingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_headings)
        binding.lifecycleOwner = this
    }
}
