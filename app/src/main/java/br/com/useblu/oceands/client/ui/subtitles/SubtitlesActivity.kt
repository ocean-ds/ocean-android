package br.com.useblu.oceands.client.ui.subtitles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivitySubtitlesBinding

class SubtitlesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubtitlesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subtitles)
        binding.lifecycleOwner = this
    }
}