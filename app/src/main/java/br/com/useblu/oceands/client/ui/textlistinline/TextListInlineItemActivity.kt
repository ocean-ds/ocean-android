package br.com.useblu.oceands.client.ui.textlistinline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTextListInlineItemBinding
import br.com.useblu.oceands.components.compose.OceanInlineTextListItemPreview

class TextListInlineItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextListInlineItemBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_list_inline_item)
        binding.lifecycleOwner = this

        binding.composeView.setContent {
            OceanInlineTextListItemPreview()
        }
    }
}
