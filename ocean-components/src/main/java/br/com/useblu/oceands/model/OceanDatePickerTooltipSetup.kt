package br.com.useblu.oceands.model

data class OceanDatePickerTooltipSetup(
    val message: String,
    private val autoDismissMs: Long? = null
) {
    fun getAutoDismissDuration(): Long {
        return autoDismissMs ?: -1
    }
}
