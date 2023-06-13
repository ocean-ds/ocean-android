package br.com.useblu.oceands.client.ui.bottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.client.databinding.ActivityBottomNavigationBinding
import br.com.useblu.oceands.model.OceanBottomNavigationMenuItem

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuItems = listOf(
            OceanBottomNavigationMenuItem(
                title = "Home",
                activeIcon = "homesolid",
                inactiveIcon = "homeoutline",
                onClickListener = {}
            ),
            OceanBottomNavigationMenuItem(
                title = "Pagar",
                activeIcon = "pagblusolid",
                inactiveIcon = "pagbluoutline",
                onClickListener = {}
            ),
            OceanBottomNavigationMenuItem(
                title = "Cobrar",
                activeIcon = "chargesolid",
                inactiveIcon = "chargeoutline",
                onClickListener = {}
            ),
            OceanBottomNavigationMenuItem(
                title = "Transferir",
                activeIcon = "switchhorizontalsolid",
                inactiveIcon = "switchhorizontaloutline",
                onClickListener = {}
            ),
            OceanBottomNavigationMenuItem(
                title = "Relatórios",
                activeIcon = "reportsolid",
                inactiveIcon = "reportoutline",
                onClickListener = {}
            )
        )

        binding.navBottom.apply {
            menuItems.forEach {
                addMenuItem(it)
            }

            setSelectedIndex(0)
        }
    }
}