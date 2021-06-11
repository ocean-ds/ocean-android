package br.com.useblu.oceands.core

import br.com.concrete.canarinho.formatador.Formatador

class FormatTypes {

    companion object {

        @JvmField
        val FORMAT_BANK_BILLET = object : Formatter {
            override fun format(text: String): String {
                return format(Formatador.BOLETO, text)
            }
        }

        @JvmField
        val FORMAT_CPF_CNPJ = object : Formatter {
            override fun format(text: String): String {
                var cpfCnpj = text

                if (Formatador.CPF_CNPJ.estaFormatado(cpfCnpj)) {
                    cpfCnpj = Formatador.CPF_CNPJ.desformata(cpfCnpj)
                }

                if (cpfCnpj.length in 12..13) {
                    while (cpfCnpj.length < 14) {
                        cpfCnpj = "0$cpfCnpj"
                    }
                }

                return "CPF/CNPJ: ${format(Formatador.CPF_CNPJ, cpfCnpj)}"
            }
        }

        @JvmField
        val FORMAT_CPF_OR_CNPJ = object : Formatter {
            override fun format(text: String): String {
                var cpfCnpj = text

                if (Formatador.CPF_CNPJ.estaFormatado(cpfCnpj)) {
                    cpfCnpj = Formatador.CPF_CNPJ.desformata(cpfCnpj)
                }

                val cpfCnpjFormatted = format(Formatador.CPF_CNPJ, cpfCnpj)
                return if (cpfCnpj.length >= 12)
                    "CNPJ: $cpfCnpjFormatted"
                else
                    "CPF: $cpfCnpjFormatted"
            }
        }

        @JvmField
        val FORMAT_DATA = object : Formatter {
            override fun format(text: String): String {
                return text.formatterDateBR()
            }
        }

        @JvmField
        val FORMAT_VALUE = object : Formatter {
            override fun format(text: String): String {
                return format(Formatador.VALOR, text)
            }
        }

        @JvmField
        val FORMAT_VALUE_WITH_SYMBOL = object : Formatter {
            override fun format(text: String): String {
                return format(Formatador.VALOR_COM_SIMBOLO, text)
            }
        }

        @JvmField
        val FORMAT_PHONE = object : Formatter {
            override fun format(text: String): String {
                return format(Formatador.TELEFONE, text)
            }
        }

        @JvmField
        val FORMAT_CEP = object : Formatter {
            override fun format(text: String): String {
                return format(Formatador.CEP, text)
            }
        }

        private fun format(formatador: Formatador, text: String): String {
            return if (formatador.podeSerFormatado(text)) formatador.formata(text) else text
        }

    }
}

interface Formatter {
    fun format(text: String): String
}