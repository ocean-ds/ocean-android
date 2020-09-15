package br.com.useblu.oceands.client.ui.paragraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityHeadingsBinding
import br.com.useblu.oceands.client.databinding.ActivityParagraphBinding

class ParagraphActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParagraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_paragraph)
        binding.lifecycleOwner = this
    }
}