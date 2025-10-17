package br.com.useblu.oceands.components.compose.cardbalance.model

sealed interface OceanCardBalanceItemType {
    val hiddenValue: String

    data class Main(
        val title: String,
        val value: String,
        override val hiddenValue: String = "R$ ••••••"
    ) : OceanCardBalanceItemType

    data class Text(
        val text: String
    ) : OceanCardBalanceItemType {
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
