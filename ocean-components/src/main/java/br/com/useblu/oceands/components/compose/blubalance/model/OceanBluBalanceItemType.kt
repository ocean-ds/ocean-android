package br.com.useblu.oceands.components.compose.blubalance.model

sealed interface OceanBluBalanceItemType {
    val hiddenValue: String

    data class Main(
        val title: String,
        val value: String,
        override val hiddenValue: String = "R$ ••••••"
    ) : OceanBluBalanceItemType

    data class Text(
        val text: String
    ) : OceanBluBalanceItemType {
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
