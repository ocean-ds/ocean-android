package br.com.useblu.oceands.components.compose.balance.model

sealed interface OceanBalanceItemType {
    val hiddenValue: String

    data class Main(
        val title: String,
        val value: String,
        override val hiddenValue: String = "R$ ••••••"
    ) : OceanBalanceItemType

    data class Text(
        val text: String
    ) : OceanBalanceItemType {
        override val hiddenValue: String
            get() = text

        fun getValue(hideContent: Boolean): String {
            return if (hideContent) {
                hiddenValue
            } else {
                text
            }
        }
    }
}
