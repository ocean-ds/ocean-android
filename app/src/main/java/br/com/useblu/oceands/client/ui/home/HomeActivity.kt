package br.com.useblu.oceands.client.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityMainBinding
import br.com.useblu.oceands.client.ui.buttons.ButtonsActivity
import br.com.useblu.oceands.client.ui.typography.TypographyActivity

class HomeActivity : AppCompatActivity() {
    val titles = arrayOf<String>("Typography", "Buttons")

    private lateinit var binding: ActivityMainBinding

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun onClickTypography(view: View) {
        val typographyIntent = Intent(HomeActivity@ this, TypographyActivity::class.java)
        startActivity(typographyIntent)
    }

    fun onClickButtons(view: View) {
        val buttonsIntent = Intent(HomeActivity@ this, ButtonsActivity::class.java)
        startActivity(buttonsIntent)
    }
}