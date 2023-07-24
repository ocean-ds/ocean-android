package br.com.useblu.oceands.ui.stringmask

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.concrete.canarinho.formatador.Formatador
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily


sealed interface OceanInputType {
    interface StaticStringMask {
        fun getMask(currentValue: String): String
    }

    fun getKeyboardType(): KeyboardType {
        return KeyboardType.Text
    }

    fun getPrefixComposable(): @Composable (() -> Unit)? = null

    fun getVisualTransformation(): VisualTransformation

    object DEFAULT: OceanInputType {
        override fun getVisualTransformation() = VisualTransformation.None
    }

    data class Currency(
        val currencySymbol: Boolean = true
    ): OceanInputType {

        override fun getPrefixComposable(): @Composable (() -> Unit) {
            return {
                Text(
                    text = "R$",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = OceanColors.interfaceDarkDeep,
                        fontFamily = OceanFontFamily.BaseRegular
                    ),
                    modifier = Modifier.padding(end = 2.dp)
                )
            }
        }
        override fun getVisualTransformation(): VisualTransformation {
            return VisualTransformation.None
        }
    }

     object BankBillet: OceanInputType, StaticStringMask {
        private val BOLETO_NORMAL = "#####.##### #####.###### #####.###### # ##############"
        private val BOLETO_TRIBUTO = "############ ############ ############ ############"

        override fun getMask(currentValue: String): String {
            return if (ehTributo(currentValue))
                BOLETO_TRIBUTO
            else BOLETO_NORMAL
        }

         override fun getVisualTransformation(): VisualTransformation {
             return StaticMaskVisualTransformation(this)
         }

        private fun ehTributo(e: String): Boolean {
            return e[0] == '8'
        }
    }

    object CEP: OceanInputType, StaticStringMask {
        private val CEP_DIGITOS = "#####-###"

        override fun getMask(currentValue: String): String {
            return CEP_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object Phone: OceanInputType, StaticStringMask {
        private val TELEFONE_OITO_DIGITOS = "(##) ####-####"
        private val TELEFONE_NOVE_DIGITOS = "(##) #####-####"

        private fun ehNoveDigitos(e: String): Boolean {
            return Formatador.Padroes.PADRAO_SOMENTE_NUMEROS.matcher(e).replaceAll("").length > 10
        }

        override fun getMask(currentValue: String): String {
            return if (ehNoveDigitos(currentValue)) TELEFONE_NOVE_DIGITOS else TELEFONE_OITO_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object CPF: OceanInputType, StaticStringMask {
        private val CPF_DIGITOS = "###.###.###-##"

        override fun getMask(currentValue: String): String {
            return CPF_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object CNPJ: OceanInputType, StaticStringMask {
        private val CNPJ_DIGITOS = "##.###.###/####-##"

        override fun getMask(currentValue: String): String {
            return CNPJ_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }

    object CpfCnpj: OceanInputType, StaticStringMask {
        private val CPF_DIGITOS = "###.###.###-##"
        private val CNPJ_DIGITOS = "##.###.###/####-##"

        override fun getMask(currentValue: String): String {
            return if (currentValue.length > 11) CNPJ_DIGITOS else CPF_DIGITOS
        }

        override fun getVisualTransformation(): VisualTransformation {
            return StaticMaskVisualTransformation(this)
        }
    }
}

