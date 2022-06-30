package br.com.useblu.oceands.client.ui.footer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityFooterBluBinding

class FooterBluActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFooterBluBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_footer_blu)
    }
}