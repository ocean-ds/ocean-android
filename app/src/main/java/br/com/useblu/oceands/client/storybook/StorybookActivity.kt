package br.com.useblu.oceands.client.storybook

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.components.compose.OceanTheme

/**
 * Ponto de entrada do Storybook do Ocean DS Android.
 *
 * Catálogo interativo de componentes para testar e validar visualmente cada componente
 * em isolamento, com controles (knobs) que alteram seus parâmetros em tempo real.
 */
class StorybookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OceanTheme {
                StorybookApp()
            }
        }
    }
}
