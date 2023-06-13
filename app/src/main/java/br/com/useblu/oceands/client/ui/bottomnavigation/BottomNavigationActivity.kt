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

        binding.navBottom.apply {
            addMenuItem(
                OceanBottomNavigationMenuItem(
                    title = "Home",
                    activeIcon = "homesolid",
                    inactiveIcon = "homeoutline",
                    onClickListener = {}
                )
            )

            addMenuItem(
                OceanBottomNavigationMenuItem(
                    title = "Pagar",
                    activeIcon = "pagblusolid",
                    inactiveIcon = "pagbluoutline",
                    onClickListener = {}
                )
            )

            addMenuItem(
                OceanBottomNavigationMenuItem(
                    title = "Cobrar",
                    activeIcon = "chargesolid",
                    inactiveIcon = "chargeoutline",
                    onClickListener = {}
                )
            )

            addMenuItem(
                OceanBottomNavigationMenuItem(
                    title = "Transferir",
                    activeIcon = "switchhorizontalsolid",
                    inactiveIcon = "switchhorizontaloutline",
                    onClickListener = {}
                )
            )

            addMenuItem(
                OceanBottomNavigationMenuItem(
                    title = "Relatórios",
                    activeIcon = "reportsolid",
                    inactiveIcon = "reportoutline",
                    onClickListener = {}
                )
            )

            setSelectedIndex(0)
        }
    }
}