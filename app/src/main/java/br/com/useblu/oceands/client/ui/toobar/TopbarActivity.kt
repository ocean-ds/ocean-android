package br.com.useblu.oceands.client.ui.toobar

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.runtime.livedata.observeAsState
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityTopbarBinding
import br.com.useblu.oceands.components.compose.OceanTopBarInverse
import br.com.useblu.oceands.extensions.setTitleColor

class TopbarActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {
    private lateinit var binding: ActivityTopbarBinding
    private lateinit var viewModel: TopbarViewModel

    private val toolbar by lazy {
        binding.topbarDefault.toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_topbar)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this)[TopbarViewModel::class.java]
        binding.viewmodel = viewModel

        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener(this)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initObservers()

        binding.composeView.setContent {
            val shadowState = viewModel.shadowState.observeAsState(false)
            OceanTopBarInverse(
                title = "TopBar Compose",
                onClickIcon = { println("onClick") },
                onClickToolbar = { println("onClickToolbar") },
                visibleShadow = shadowState.value
            )
        }
    }

    private fun initObservers() {
        viewModel.shadowState.observe(this) {
            binding.topbarDefault.visibleShadow = it
            binding.topbarPrimary.visibleShadow = it
            binding.topbarComplementary.visibleShadow = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toggle_size, menu)
        menu?.findItem(R.id.toggle_size_small)?.setTitleColor(Color.RED)
        return true
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toggle_size_small -> {
                println("Click item small")
                true
            }

            R.id.toggle_size_medium -> {
                println("Click item medium")
                true
            }

            R.id.toggle_size_large -> {
                println("Click item large")
                true
            }

            else -> false
        }
    }
}
