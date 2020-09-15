package br.com.useblu.oceands.client.ui.caption

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityCaptionBinding

class CaptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCaptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_caption)
        binding.lifecycleOwner = this
    }
}