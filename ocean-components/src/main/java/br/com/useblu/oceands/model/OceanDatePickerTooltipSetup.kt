package br.com.useblu.oceands.model

data class OceanDatePickerTooltipSetup(
    val message: String,
    private val autoDismissMs: Long? = null,
    val showOnTap: Boolean = true,
    val showOnOpen: Boolean = false
) {
    fun getAutoDismissDuration(): Long {
        return autoDismissMs ?: -1
    }
}
