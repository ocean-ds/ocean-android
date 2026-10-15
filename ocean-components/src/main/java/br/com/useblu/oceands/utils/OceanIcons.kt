package br.com.useblu.oceands.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import br.com.useblu.oceands.R
import java.io.Serializable

@Immutable
class OceanIcons(
    val token: String,
    @DrawableRes val icon: Int,
    val name: String = token
) : Serializable {
    override fun equals(other: Any?): Boolean = other is OceanIcons && other.token == token
    override fun hashCode(): Int = token.hashCode()
    override fun toString(): String = name

    companion object {
        private const val serialVersionUID = 1L
        val APPAREL_OUTLINE = OceanIcons(
            token = "appareloutline",
            icon = R.drawable.ocean_icon_apparel_outline,
            name = "APPAREL_OUTLINE"
        )
        val APPLIANCES_OUTLINE = OceanIcons(
            token = "appliancesoutline",
            icon = R.drawable.ocean_icon_appliances_outline,
            name = "APPLIANCES_OUTLINE"
        )
        val ARROW_RIGHT_UP_OUTLINE = OceanIcons(
            token = "arrowrightupoutline",
            icon = R.drawable.ocean_icon_arrow_right_up_outline,
            name = "ARROW_RIGHT_UP_OUTLINE"
        )
        val BACKGROUND_OUTLINE = OceanIcons(
            token = "backgroundoutline",
            icon = R.drawable.ocean_icon_background_outline,
            name = "BACKGROUND_OUTLINE"
        )
        val ACADEMIC_CAP_OUTLINE = OceanIcons(
            token = "academiccapoutline",
            icon = R.drawable.ocean_icon_academic_cap_outline,
            name = "ACADEMIC_CAP_OUTLINE"
        )
        val ACADEMIC_CAP_SOLID = OceanIcons(
            token = "academiccapsolid",
            icon = R.drawable.ocean_icon_academic_cap_solid,
            name = "ACADEMIC_CAP_SOLID"
        )
        val ADJUSTMENTS_OUTLINE = OceanIcons(
            token = "adjustmentsoutline",
            icon = R.drawable.ocean_icon_adjustments_outline,
            name = "ADJUSTMENTS_OUTLINE"
        )
        val ADJUSTMENTS_OUTLINE_HORIZONTAL = OceanIcons(
            token = "adjustmentsoutlinehorizontal",
            icon = R.drawable.ocean_icon_adjustments_outline_horizontal,
            name = "ADJUSTMENTS_OUTLINE_HORIZONTAL"
        )
        val ADJUSTMENTS_SOLID = OceanIcons(
            token = "adjustmentssolid",
            icon = R.drawable.ocean_icon_adjustments_solid,
            name = "ADJUSTMENTS_SOLID"
        )
        val ANNOTATION_OUTLINE = OceanIcons(
            token = "annotationoutline",
            icon = R.drawable.ocean_icon_annotation_outline,
            name = "ANNOTATION_OUTLINE"
        )
        val ANNOTATION_SOLID = OceanIcons(
            token = "annotationsolid",
            icon = R.drawable.ocean_icon_annotation_solid,
            name = "ANNOTATION_SOLID"
        )
        val ARCHIVE_OUTLINE = OceanIcons(
            token = "archiveoutline",
            icon = R.drawable.ocean_icon_archive_outline,
            name = "ARCHIVE_OUTLINE"
        )
        val ARCHIVE_SOLID = OceanIcons(
            token = "archivesolid",
            icon = R.drawable.ocean_icon_archive_solid,
            name = "ARCHIVE_SOLID"
        )
        val ARROW_CIRCLE_DOWN_OUTLINE = OceanIcons(
            token = "arrowcircledownoutline",
            icon = R.drawable.ocean_icon_arrow_circle_down_outline,
            name = "ARROW_CIRCLE_DOWN_OUTLINE"
        )
        val ARROW_CIRCLE_DOWN_SOLID = OceanIcons(
            token = "arrowcircledownsolid",
            icon = R.drawable.ocean_icon_arrow_circle_down_solid,
            name = "ARROW_CIRCLE_DOWN_SOLID"
        )
        val ARROW_CIRCLE_LEFT_OUTLINE = OceanIcons(
            token = "arrowcircleleftoutline",
            icon = R.drawable.ocean_icon_arrow_circle_left_outline,
            name = "ARROW_CIRCLE_LEFT_OUTLINE"
        )
        val ARROW_CIRCLE_LEFT_SOLID = OceanIcons(
            token = "arrowcircleleftsolid",
            icon = R.drawable.ocean_icon_arrow_circle_left_solid,
            name = "ARROW_CIRCLE_LEFT_SOLID"
        )
        val ARROW_CIRCLE_RIGHT_OUTLINE = OceanIcons(
            token = "arrowcirclerightoutline",
            icon = R.drawable.ocean_icon_arrow_circle_right_outline,
            name = "ARROW_CIRCLE_RIGHT_OUTLINE"
        )
        val ARROW_CIRCLE_RIGHT_SOLID = OceanIcons(
            token = "arrowcirclerightsolid",
            icon = R.drawable.ocean_icon_arrow_circle_right_solid,
            name = "ARROW_CIRCLE_RIGHT_SOLID"
        )
        val ARROW_CIRCLE_UP_OUTLINE = OceanIcons(
            token = "arrowcircleupoutline",
            icon = R.drawable.ocean_icon_arrow_circle_up_outline,
            name = "ARROW_CIRCLE_UP_OUTLINE"
        )
        val ARROW_CIRCLE_UP_SOLID = OceanIcons(
            token = "arrowcircleupsolid",
            icon = R.drawable.ocean_icon_arrow_circle_up_solid,
            name = "ARROW_CIRCLE_UP_SOLID"
        )
        val ARROW_DOWN_OUTLINE = OceanIcons(
            token = "arrowdownoutline",
            icon = R.drawable.ocean_icon_arrow_down_outline,
            name = "ARROW_DOWN_OUTLINE"
        )
        val ARROW_DOWN_SOLID = OceanIcons(
            token = "arrowdownsolid",
            icon = R.drawable.ocean_icon_arrow_down_solid,
            name = "ARROW_DOWN_SOLID"
        )
        val ARROW_LEFT_OUTLINE = OceanIcons(
            token = "arrowleftoutline",
            icon = R.drawable.ocean_icon_arrow_left_outline,
            name = "ARROW_LEFT_OUTLINE"
        )
        val ARROW_LEFT_SOLID = OceanIcons(
            token = "arrowleftsolid",
            icon = R.drawable.ocean_icon_arrow_left_solid,
            name = "ARROW_LEFT_SOLID"
        )
        val ARROW_NARROW_DOWN_OUTLINE = OceanIcons(
            token = "arrownarrowdownoutline",
            icon = R.drawable.ocean_icon_arrow_narrow_down_outline,
            name = "ARROW_NARROW_DOWN_OUTLINE"
        )
        val ARROW_NARROW_DOWN_SOLID = OceanIcons(
            token = "arrownarrowdownsolid",
            icon = R.drawable.ocean_icon_arrow_narrow_down_solid,
            name = "ARROW_NARROW_DOWN_SOLID"
        )
        val ARROW_NARROW_LEFT_OUTLINE = OceanIcons(
            token = "arrownarrowleftoutline",
            icon = R.drawable.ocean_icon_arrow_narrow_left_outline,
            name = "ARROW_NARROW_LEFT_OUTLINE"
        )
        val ARROW_NARROW_LEFT_SOLID = OceanIcons(
            token = "arrownarrowleftsolid",
            icon = R.drawable.ocean_icon_arrow_narrow_left_solid,
            name = "ARROW_NARROW_LEFT_SOLID"
        )
        val ARROW_NARROW_RIGHT_OUTLINE = OceanIcons(
            token = "arrownarrowrightoutline",
            icon = R.drawable.ocean_icon_arrow_narrow_right_outline,
            name = "ARROW_NARROW_RIGHT_OUTLINE"
        )
        val ARROW_NARROW_RIGHT_SOLID = OceanIcons(
            token = "arrownarrowrightsolid",
            icon = R.drawable.ocean_icon_arrow_narrow_right_solid,
            name = "ARROW_NARROW_RIGHT_SOLID"
        )
        val ARROW_NARROW_UP_OUTLINE = OceanIcons(
            token = "arrownarrowupoutline",
            icon = R.drawable.ocean_icon_arrow_narrow_up_outline,
            name = "ARROW_NARROW_UP_OUTLINE"
        )
        val ARROW_NARROW_UP_SOLID = OceanIcons(
            token = "arrownarrowupsolid",
            icon = R.drawable.ocean_icon_arrow_narrow_up_solid,
            name = "ARROW_NARROW_UP_SOLID"
        )
        val ARROW_RIGHT_OUTLINE = OceanIcons(
            token = "arrowrightoutline",
            icon = R.drawable.ocean_icon_arrow_right_outline,
            name = "ARROW_RIGHT_OUTLINE"
        )
        val ARROW_RIGHT_SOLID = OceanIcons(
            token = "arrowrightsolid",
            icon = R.drawable.ocean_icon_arrow_right_solid,
            name = "ARROW_RIGHT_SOLID"
        )
        val ARROW_UP_OUTLINE = OceanIcons(
            token = "arrowupoutline",
            icon = R.drawable.ocean_icon_arrow_up_outline,
            name = "ARROW_UP_OUTLINE"
        )
        val ARROW_UP_SOLID = OceanIcons(
            token = "arrowupsolid",
            icon = R.drawable.ocean_icon_arrow_up_solid,
            name = "ARROW_UP_SOLID"
        )
        val ARROWS_EXPAND_OUTLINE = OceanIcons(
            token = "arrowsexpandoutline",
            icon = R.drawable.ocean_icon_arrows_expand_outline,
            name = "ARROWS_EXPAND_OUTLINE"
        )
        val ARROWS_EXPAND_SOLID = OceanIcons(
            token = "arrowsexpandsolid",
            icon = R.drawable.ocean_icon_arrows_expand_solid,
            name = "ARROWS_EXPAND_SOLID"
        )
        val AT_SYMBOL_OUTLINE = OceanIcons(
            token = "atsymboloutline",
            icon = R.drawable.ocean_icon_at_symbol_solid,
            name = "AT_SYMBOL_OUTLINE"
        )
        val AT_SYMBOL_SOLID = OceanIcons(
            token = "atsymbolsolid",
            icon = R.drawable.ocean_icon_at_symbol_solid,
            name = "AT_SYMBOL_SOLID"
        )
        val BACKSPACE_OUTLINE = OceanIcons(
            token = "backspaceoutline",
            icon = R.drawable.ocean_icon_backspace_outline,
            name = "BACKSPACE_OUTLINE"
        )
        val BACKSPACE_SOLID = OceanIcons(
            token = "backspacesolid",
            icon = R.drawable.ocean_icon_backspace_solid,
            name = "BACKSPACE_SOLID"
        )
        val BADGE_CHECK_OUTLINE = OceanIcons(
            token = "badgecheckoutline",
            icon = R.drawable.ocean_icon_badge_check_outline,
            name = "BADGE_CHECK_OUTLINE"
        )
        val BADGE_CHECK_SOLID = OceanIcons(
            token = "badgechecksolid",
            icon = R.drawable.ocean_icon_badge_check_solid,
            name = "BADGE_CHECK_SOLID"
        )
        val BAN_OUTLINE = OceanIcons(
            token = "banoutline",
            icon = R.drawable.ocean_icon_ban_outline,
            name = "BAN_OUTLINE"
        )
        val BAN_SOLID = OceanIcons(
            token = "bansolid",
            icon = R.drawable.ocean_icon_ban_solid,
            name = "BAN_SOLID"
        )
        val BARCODE_BUBBLE_OUTLINE = OceanIcons(
            token = "barcodebubbleoutline",
            icon = R.drawable.ocean_icon_barcode_bubble_outline,
            name = "BARCODE_BUBBLE_OUTLINE"
        )
        val BARCODE_BUBBLE_SOLID = OceanIcons(
            token = "barcodebubblesolid",
            icon = R.drawable.ocean_icon_barcode_bubble_solid,
            name = "BARCODE_BUBBLE_SOLID"
        )
        val BARCODE_OUTLINE = OceanIcons(
            token = "barcodeoutline",
            icon = R.drawable.ocean_icon_barcode_outline,
            name = "BARCODE_OUTLINE"
        )
        val BARCODE_SOLID = OceanIcons(
            token = "barcodesolid",
            icon = R.drawable.ocean_icon_barcode_solid,
            name = "BARCODE_SOLID"
        )
        val BEAKER_OUTLINE = OceanIcons(
            token = "beakeroutline",
            icon = R.drawable.ocean_icon_beaker_outline,
            name = "BEAKER_OUTLINE"
        )
        val BEAKER_SOLID = OceanIcons(
            token = "beakersolid",
            icon = R.drawable.ocean_icon_beaker_solid,
            name = "BEAKER_SOLID"
        )
        val BELL_OUTLINE = OceanIcons(
            token = "belloutline",
            icon = R.drawable.ocean_icon_bell_outline,
            name = "BELL_OUTLINE"
        )
        val BELL_SOLID = OceanIcons(
            token = "bellsolid",
            icon = R.drawable.ocean_icon_bell_solid,
            name = "BELL_SOLID"
        )
        val BIKE_OUTLINE = OceanIcons(
            token = "bikeoutline",
            icon = R.drawable.ocean_icon_bike_outline,
            name = "BIKE_OUTLINE"
        )
        val BLU_LOGO_OUTLINE = OceanIcons(
            token = "blulogooutline",
            icon = R.drawable.ocean_icon_blu_logo_outline,
            name = "BLU_LOGO_OUTLINE"
        )
        val BMS_OUTLINE = OceanIcons(
            token = "bmsoutline",
            icon = R.drawable.ocean_icon_bms_outline,
            name = "BMS_OUTLINE"
        )
        val BOOK_OPEN_OUTLINE = OceanIcons(
            token = "bookopenoutline",
            icon = R.drawable.ocean_icon_book_open_outline,
            name = "BOOK_OPEN_OUTLINE"
        )
        val BOOK_OPEN_SOLID = OceanIcons(
            token = "bookopensolid",
            icon = R.drawable.ocean_icon_book_open_solid,
            name = "BOOK_OPEN_SOLID"
        )
        val BOOKMARK_ALT_OUTLINE = OceanIcons(
            token = "bookmarkoutline",
            icon = R.drawable.ocean_icon_bookmark_alt_outline,
            name = "BOOKMARK_ALT_OUTLINE"
        )
        val BOOKMARK_ALT_SOLID = OceanIcons(
            token = "bookmarkaltsolid",
            icon = R.drawable.ocean_icon_bookmark_alt_solid,
            name = "BOOKMARK_ALT_SOLID"
        )
        val BOOKMARK_OUTLINE = OceanIcons(
            token = "bookmarkoutline",
            icon = R.drawable.ocean_icon_bookmark_alt_outline,
            name = "BOOKMARK_OUTLINE"
        )
        val BOOKMARK_SOLID = OceanIcons(
            token = "bookmarksolid",
            icon = R.drawable.ocean_icon_bookmark_solid,
            name = "BOOKMARK_SOLID"
        )
        val BRIEFCASE_OUTLINE = OceanIcons(
            token = "briefcaseoutline",
            icon = R.drawable.ocean_icon_briefcase_outline,
            name = "BRIEFCASE_OUTLINE"
        )
        val BRIEFCASE_SOLID = OceanIcons(
            token = "briefcasesolid",
            icon = R.drawable.ocean_icon_briefcase_solid,
            name = "BRIEFCASE_SOLID"
        )
        val BTOKEN_OUTLINE = OceanIcons(
            token = "btokenoutline",
            icon = R.drawable.ocean_icon_btoken_outline,
            name = "BTOKEN_OUTLINE"
        )
        val CAKE_OUTLINE = OceanIcons(
            token = "cakeoutline",
            icon = R.drawable.ocean_icon_cake_outline,
            name = "CAKE_OUTLINE"
        )
        val CAKE_SOLID = OceanIcons(
            token = "cakesolid",
            icon = R.drawable.ocean_icon_cake_solid,
            name = "CAKE_SOLID"
        )
        val CALCULATOR_OUTLINE = OceanIcons(
            token = "calculatoroutline",
            icon = R.drawable.ocean_icon_calculator_outline,
            name = "CALCULATOR_OUTLINE"
        )
        val CALCULATOR_SOLID = OceanIcons(
            token = "calculatorsolid",
            icon = R.drawable.ocean_icon_calculator_solid,
            name = "CALCULATOR_SOLID"
        )
        val CALENDAR_OUTLINE = OceanIcons(
            token = "calendaroutline",
            icon = R.drawable.ocean_icon_calendar_outline,
            name = "CALENDAR_OUTLINE"
        )
        val CALENDAR_SOLID = OceanIcons(
            token = "calendarsolid",
            icon = R.drawable.ocean_icon_calendar_solid,
            name = "CALENDAR_SOLID"
        )
        val CAMERA_OUTLINE = OceanIcons(
            token = "cameraoutline",
            icon = R.drawable.ocean_icon_camera_outline,
            name = "CAMERA_OUTLINE"
        )
        val CAMERA_SOLID = OceanIcons(
            token = "camerasolid",
            icon = R.drawable.ocean_icon_camera_solid,
            name = "CAMERA_SOLID"
        )
        val CARD_MACHINE_OUTLINE = OceanIcons(
            token = "cardmachineoutline",
            icon = R.drawable.ocean_icon_card_machine_outline,
            name = "CARD_MACHINE_OUTLINE"
        )
        val CASH_BACK_OUTLINE = OceanIcons(
            token = "cashbackoutline",
            icon = R.drawable.ocean_icon_cashback_outline,
            name = "CASH_BACK_OUTLINE"
        )
        val CASH_OUTLINE = OceanIcons(
            token = "cashoutline",
            icon = R.drawable.ocean_icon_cash_outline,
            name = "CASH_OUTLINE"
        )
        val CASH_SOLID = OceanIcons(
            token = "cashsolid",
            icon = R.drawable.ocean_icon_cash_solid,
            name = "CASH_SOLID"
        )
        val CHARGE_OUTLINE = OceanIcons(
            token = "chargeoutline",
            icon = R.drawable.ocean_icon_charge_outline,
            name = "CHARGE_OUTLINE"
        )
        val CHARGE_SOLID = OceanIcons(
            token = "chargesolid",
            icon = R.drawable.ocean_icon_charge_solid,
            name = "CHARGE_SOLID"
        )
        val CHART_BAR_OUTLINE = OceanIcons(
            token = "chartbaroutline",
            icon = R.drawable.ocean_icon_chart_bar_outline,
            name = "CHART_BAR_OUTLINE"
        )
        val CHART_BAR_SOLID = OceanIcons(
            token = "chartbarsolid",
            icon = R.drawable.ocean_icon_chart_bar_solid,
            name = "CHART_BAR_SOLID"
        )
        val CHART_PIE_OUTLINE = OceanIcons(
            token = "chartpieoutline",
            icon = R.drawable.ocean_icon_chart_pie_outline,
            name = "CHART_PIE_OUTLINE"
        )
        val CHART_PIE_SOLID = OceanIcons(
            token = "chartpiesolid",
            icon = R.drawable.ocean_icon_chart_pie_solid,
            name = "CHART_PIE_SOLID"
        )
        val CHART_SQUARE_BAR_OUTLINE = OceanIcons(
            token = "chartsquarebaroutline",
            icon = R.drawable.ocean_icon_chart_square_bar_outline,
            name = "CHART_SQUARE_BAR_OUTLINE"
        )
        val CHART_SQUARE_BAR_SOLID = OceanIcons(
            token = "chartsquarebarsolid",
            icon = R.drawable.ocean_icon_chart_square_bar_outline,
            name = "CHART_SQUARE_BAR_SOLID"
        )
        val CHAT_ALT_OUTLINE = OceanIcons(
            token = "chataltoutline",
            icon = R.drawable.ocean_icon_chat_alt_outline,
            name = "CHAT_ALT_OUTLINE"
        )
        val CHAT_ALT_SOLID = OceanIcons(
            token = "chataltsolid",
            icon = R.drawable.ocean_icon_chat_alt_solid,
            name = "CHAT_ALT_SOLID"
        )
        val CHAT_ALT_TWO_OUTLINE = OceanIcons(
            token = "chatalt2outline",
            icon = R.drawable.ocean_icon_chat_alt_2_outline,
            name = "CHAT_ALT_TWO_OUTLINE"
        )
        val CHAT_ALT_TWO_SOLID = OceanIcons(
            token = "chatalt2solid",
            icon = R.drawable.ocean_icon_chat_alt_2_solid,
            name = "CHAT_ALT_TWO_SOLID"
        )
        val CHAT_ALT_THREE_OUTLINE = OceanIcons(
            token = "chatalt3outline",
            icon = R.drawable.ocean_icon_chat_alt_3_outline,
            name = "CHAT_ALT_THREE_OUTLINE"
        )
        val CHAT_OUTLINE = OceanIcons(
            token = "chatoutline",
            icon = R.drawable.ocean_icon_chat_outline,
            name = "CHAT_OUTLINE"
        )
        val CHAT_SOLID = OceanIcons(
            token = "chatsolid",
            icon = R.drawable.ocean_icon_chat_solid,
            name = "CHAT_SOLID"
        )
        val CHECK_CIRCLE_OUTLINE = OceanIcons(
            token = "checkcircleoutline",
            icon = R.drawable.ocean_icon_check_circle_outline,
            name = "CHECK_CIRCLE_OUTLINE"
        )
        val CHECK_CIRCLE_SOLID = OceanIcons(
            token = "checkcirclesolid",
            icon = R.drawable.ocean_icon_check_circle_solid,
            name = "CHECK_CIRCLE_SOLID"
        )
        val CHECK_OUTLINE = OceanIcons(
            token = "checkoutline",
            icon = R.drawable.ocean_icon_check_outline,
            name = "CHECK_OUTLINE"
        )
        val CHECK_SOLID = OceanIcons(
            token = "checksolid",
            icon = R.drawable.ocean_icon_check_solid,
            name = "CHECK_SOLID"
        )
        val CHEVRON_DOUBLE_DOWN_OUTLINE = OceanIcons(
            token = "chevrondoubledownoutline",
            icon = R.drawable.ocean_icon_chevron_double_down_outline,
            name = "CHEVRON_DOUBLE_DOWN_OUTLINE"
        )
        val CHEVRON_DOUBLE_DOWN_SOLID = OceanIcons(
            token = "chevrondoubledownsolid",
            icon = R.drawable.ocean_icon_chevron_double_down_solid,
            name = "CHEVRON_DOUBLE_DOWN_SOLID"
        )
        val CHEVRON_DOUBLE_LEFT_OUTLINE = OceanIcons(
            token = "chevrondoubleleftoutline",
            icon = R.drawable.ocean_icon_chevron_double_left_outline,
            name = "CHEVRON_DOUBLE_LEFT_OUTLINE"
        )
        val CHEVRON_DOUBLE_LEFT_SOLID = OceanIcons(
            token = "chevrondoubleleftsolid",
            icon = R.drawable.ocean_icon_chevron_double_left_solid,
            name = "CHEVRON_DOUBLE_LEFT_SOLID"
        )
        val CHEVRON_DOUBLE_RIGHT_OUTLINE = OceanIcons(
            token = "chevrondoublerightoutline",
            icon = R.drawable.ocean_icon_chevron_double_right_outline,
            name = "CHEVRON_DOUBLE_RIGHT_OUTLINE"
        )
        val CHEVRON_DOUBLE_RIGHT_SOLID = OceanIcons(
            token = "chevrondoublerightsolid",
            icon = R.drawable.ocean_icon_chevron_double_right_solid,
            name = "CHEVRON_DOUBLE_RIGHT_SOLID"
        )
        val CHEVRON_DOUBLE_UP_OUTLINE = OceanIcons(
            token = "chevrondoubleupoutline",
            icon = R.drawable.ocean_icon_chevron_double_up_outline,
            name = "CHEVRON_DOUBLE_UP_OUTLINE"
        )
        val CHEVRON_DOUBLE_UP_SOLID = OceanIcons(
            token = "chevrondoubleupsolid",
            icon = R.drawable.ocean_icon_chevron_double_up_solid,
            name = "CHEVRON_DOUBLE_UP_SOLID"
        )
        val CHEVRON_DOWN_OUTLINE = OceanIcons(
            token = "chevrondownoutline",
            icon = R.drawable.ocean_icon_chevron_down_outline,
            name = "CHEVRON_DOWN_OUTLINE"
        )
        val CHEVRON_DOWN_SOLID = OceanIcons(
            token = "chevrondownsolid",
            icon = R.drawable.ocean_icon_chevron_down_solid,
            name = "CHEVRON_DOWN_SOLID"
        )
        val CHEVRON_LEFT_OUTLINE = OceanIcons(
            token = "chevronleftoutline",
            icon = R.drawable.ocean_icon_chevron_left_outline,
            name = "CHEVRON_LEFT_OUTLINE"
        )
        val CHEVRON_LEFT_SOLID = OceanIcons(
            token = "chevronleftsolid",
            icon = R.drawable.ocean_icon_chevron_left_solid,
            name = "CHEVRON_LEFT_SOLID"
        )
        val CHEVRON_RIGHT_OUTLINE = OceanIcons(
            token = "chevronrightoutline",
            icon = R.drawable.ocean_icon_chevron_right_outline,
            name = "CHEVRON_RIGHT_OUTLINE"
        )
        val CHEVRON_RIGHT_SOLID = OceanIcons(
            token = "chevronrightsolid",
            icon = R.drawable.ocean_icon_chevron_right_solid,
            name = "CHEVRON_RIGHT_SOLID"
        )
        val CHEVRON_UP_OUTLINE = OceanIcons(
            token = "chevronupoutline",
            icon = R.drawable.ocean_icon_chevron_up_outline,
            name = "CHEVRON_UP_OUTLINE"
        )
        val CHEVRON_UP_SOLID = OceanIcons(
            token = "chevronupsolid",
            icon = R.drawable.ocean_icon_chevron_up_solid,
            name = "CHEVRON_UP_SOLID"
        )
        val CHIP_OUTLINE = OceanIcons(
            token = "chipoutline",
            icon = R.drawable.ocean_icon_chip_outline,
            name = "CHIP_OUTLINE"
        )
        val CHIP_SOLID = OceanIcons(
            token = "chipsolid",
            icon = R.drawable.ocean_icon_chip_solid,
            name = "CHIP_SOLID"
        )
        val CLIPBOARD_CHECK_OUTLINE = OceanIcons(
            token = "clipboardcheckoutline",
            icon = R.drawable.ocean_icon_clipboard_check_outline,
            name = "CLIPBOARD_CHECK_OUTLINE"
        )
        val CLIPBOARD_CHECK_SOLID = OceanIcons(
            token = "clipboardchecksolid",
            icon = R.drawable.ocean_icon_clipboard_check_solid,
            name = "CLIPBOARD_CHECK_SOLID"
        )
        val CLIPBOARD_COPY_OUTLINE = OceanIcons(
            token = "clipboardcopyoutline",
            icon = R.drawable.ocean_icon_clipboard_copy_outline,
            name = "CLIPBOARD_COPY_OUTLINE"
        )
        val CLIPBOARD_COPY_SOLID = OceanIcons(
            token = "clipboardcopysolid",
            icon = R.drawable.ocean_icon_clipboard_copy_solid,
            name = "CLIPBOARD_COPY_SOLID"
        )
        val CLIPBOARD_LIST_OUTLINE = OceanIcons(
            token = "clipboardlistoutline",
            icon = R.drawable.ocean_icon_clipboard_list_outline,
            name = "CLIPBOARD_LIST_OUTLINE"
        )
        val CLIPBOARD_LIST_SOLID = OceanIcons(
            token = "clipboardlistsolid",
            icon = R.drawable.ocean_icon_clipboard_list_solid,
            name = "CLIPBOARD_LIST_SOLID"
        )
        val CLIPBOARD_OUTLINE = OceanIcons(
            token = "clipboardoutline",
            icon = R.drawable.ocean_icon_clipboard_outline,
            name = "CLIPBOARD_OUTLINE"
        )
        val CLIPBOARD_SOLID = OceanIcons(
            token = "clipboardsolid",
            icon = R.drawable.ocean_icon_clipboard_solid,
            name = "CLIPBOARD_SOLID"
        )
        val CLOCK_OUTLINE = OceanIcons(
            token = "clockoutline",
            icon = R.drawable.ocean_icon_clock_outline,
            name = "CLOCK_OUTLINE"
        )
        val CLOCK_SOLID = OceanIcons(
            token = "clocksolid",
            icon = R.drawable.ocean_icon_clock_solid,
            name = "CLOCK_SOLID"
        )
        val CLOUD_DOWNLOAD_OUTLINE = OceanIcons(
            token = "clouddownloadoutline",
            icon = R.drawable.ocean_icon_cloud_download_outline,
            name = "CLOUD_DOWNLOAD_OUTLINE"
        )
        val CLOUD_DOWNLOAD_SOLID = OceanIcons(
            token = "clouddownloadsolid",
            icon = R.drawable.ocean_icon_cloud_download_solid,
            name = "CLOUD_DOWNLOAD_SOLID"
        )
        val CLOUD_UPLOAD_OUTLINE = OceanIcons(
            token = "clouduploadoutline",
            icon = R.drawable.ocean_icon_cloud_upload_outline,
            name = "CLOUD_UPLOAD_OUTLINE"
        )
        val CLOUD_UPLOAD_SOLID = OceanIcons(
            token = "clouduploadsolid",
            icon = R.drawable.ocean_icon_cloud_upload_solid,
            name = "CLOUD_UPLOAD_SOLID"
        )
        val CLOUD_OUTLINE = OceanIcons(
            token = "cloudoutline",
            icon = R.drawable.ocean_icon_cloud_outline,
            name = "CLOUD_OUTLINE"
        )
        val CLOUD_SOLID = OceanIcons(
            token = "cloudsolid",
            icon = R.drawable.ocean_icon_cloud_solid,
            name = "CLOUD_SOLID"
        )
        val CODE_OUTLINE = OceanIcons(
            token = "codeoutline",
            icon = R.drawable.ocean_icon_code_outline,
            name = "CODE_OUTLINE"
        )
        val CODE_SOLID = OceanIcons(
            token = "codesolid",
            icon = R.drawable.ocean_icon_code_solid,
            name = "CODE_SOLID"
        )
        val COG_OUTLINE = OceanIcons(
            token = "cogoutline",
            icon = R.drawable.ocean_icon_cog_outline,
            name = "COG_OUTLINE"
        )
        val COG_SOLID = OceanIcons(
            token = "cogsolid",
            icon = R.drawable.ocean_icon_cog_solid,
            name = "COG_SOLID"
        )
        val COLLECTION_OUTLINE = OceanIcons(
            token = "collectionoutline",
            icon = R.drawable.ocean_icon_collection_outline,
            name = "COLLECTION_OUTLINE"
        )
        val COLLECTION_SOLID = OceanIcons(
            token = "collectionsolid",
            icon = R.drawable.ocean_icon_collection_solid,
            name = "COLLECTION_SOLID"
        )
        val COLOR_SWATCH_OUTLINE = OceanIcons(
            token = "colorswatchoutline",
            icon = R.drawable.ocean_icon_color_swatch_outline,
            name = "COLOR_SWATCH_OUTLINE"
        )
        val COLOR_SWATCH_SOLID = OceanIcons(
            token = "colorswatchsolid",
            icon = R.drawable.ocean_icon_color_swatch_solid,
            name = "COLOR_SWATCH_SOLID"
        )
        val CONSTRUCTION_MATERIAL_OUTLINE = OceanIcons(
            token = "constructionmaterialoutline",
            icon = R.drawable.ocean_icon_construction_material_outline,
            name = "CONSTRUCTION_MATERIAL_OUTLINE"
        )
        val CONSULT_OUTLINE = OceanIcons(
            token = "consultoutline",
            icon = R.drawable.ocean_icon_consult_outline,
            name = "CONSULT_OUTLINE"
        )
        val CONTACT_BOOK_OUTLINE = OceanIcons(
            token = "contactbookoutline",
            icon = R.drawable.ocean_icon_contact_book_outline,
            name = "CONTACT_BOOK_OUTLINE"
        )
        val CREDIT_CARD_OUTLINE = OceanIcons(
            token = "creditcardoutline",
            icon = R.drawable.ocean_icon_credit_card_outline,
            name = "CREDIT_CARD_OUTLINE"
        )
        val CREDIT_CARD_SOLID = OceanIcons(
            token = "creditcardsolid",
            icon = R.drawable.ocean_icon_credit_card_solid,
            name = "CREDIT_CARD_SOLID"
        )
        val CUBE_OUTLINE = OceanIcons(
            token = "cubeoutline",
            icon = R.drawable.ocean_icon_cube_outline,
            name = "CUBE_OUTLINE"
        )
        val CUBE_SOLID = OceanIcons(
            token = "cubesolid",
            icon = R.drawable.ocean_icon_cube_solid,
            name = "CUBE_SOLID"
        )
        val CUBE_TRANSPARENT_OUTLINE = OceanIcons(
            token = "cubetransparentoutline",
            icon = R.drawable.ocean_icon_cube_transparent_outline,
            name = "CUBE_TRANSPARENT_OUTLINE"
        )
        val CUBE_TRANSPARENT_SOLID = OceanIcons(
            token = "cubetransparentsolid",
            icon = R.drawable.ocean_icon_cube_transparent_solid,
            name = "CUBE_TRANSPARENT_SOLID"
        )
        val CURRENCY_BANGLADESHI_OUTLINE = OceanIcons(
            token = "currencybangladeshioutline",
            icon = R.drawable.ocean_icon_currency_bangladeshi_outline,
            name = "CURRENCY_BANGLADESHI_OUTLINE"
        )
        val CURRENCY_BANGLADESHI_SOLID = OceanIcons(
            token = "currencybangladeshisolid",
            icon = R.drawable.ocean_icon_currency_bangladeshi_solid,
            name = "CURRENCY_BANGLADESHI_SOLID"
        )
        val CURRENCY_DOLLAR_OUTLINE = OceanIcons(
            token = "currencydollaroutline",
            icon = R.drawable.ocean_icon_currency_dollar_outline,
            name = "CURRENCY_DOLLAR_OUTLINE"
        )
        val CURRENCY_DOLLAR_OUTLINE_LIGHT_DEEP = OceanIcons(
            token = "currencydollaroutlinelightdeep",
            icon = R.drawable.ocean_icon_currency_dollar_outline_light_deep,
            name = "CURRENCY_DOLLAR_OUTLINE_LIGHT_DEEP"
        )
        val CURRENCY_DOLLAR_SOLID = OceanIcons(
            token = "currencydollarsolid",
            icon = R.drawable.ocean_icon_currency_dollar_solid,
            name = "CURRENCY_DOLLAR_SOLID"
        )
        val CURRENCY_EURO_OUTLINE = OceanIcons(
            token = "currencyeurooutline",
            icon = R.drawable.ocean_icon_currency_euro_outline,
            name = "CURRENCY_EURO_OUTLINE"
        )
        val CURRENCY_EURO_SOLID = OceanIcons(
            token = "currencyeurosolid",
            icon = R.drawable.ocean_icon_currency_euro_solid,
            name = "CURRENCY_EURO_SOLID"
        )
        val CURRENCY_POUND_OUTLINE = OceanIcons(
            token = "currencypoundoutline",
            icon = R.drawable.ocean_icon_currency_pound_outline,
            name = "CURRENCY_POUND_OUTLINE"
        )
        val CURRENCY_POUND_SOLID = OceanIcons(
            token = "currencypoundsolid",
            icon = R.drawable.ocean_icon_currency_pound_solid,
            name = "CURRENCY_POUND_SOLID"
        )
        val CURRENCY_RUPEE_OUTLINE = OceanIcons(
            token = "currencyrupeeoutline",
            icon = R.drawable.ocean_icon_currency_rupee_outline,
            name = "CURRENCY_RUPEE_OUTLINE"
        )
        val CURRENCY_RUPEE_SOLID = OceanIcons(
            token = "currencyrupeesolid",
            icon = R.drawable.ocean_icon_currency_rupee_solid,
            name = "CURRENCY_RUPEE_SOLID"
        )
        val CURRENCY_YEN_OUTLINE = OceanIcons(
            token = "currencyyenoutline",
            icon = R.drawable.ocean_icon_currency_yen_outline,
            name = "CURRENCY_YEN_OUTLINE"
        )
        val CURRENCY_YEN_SOLID = OceanIcons(
            token = "currencyyensolid",
            icon = R.drawable.ocean_icon_currency_yen_solid,
            name = "CURRENCY_YEN_SOLID"
        )
        val CURSOR_CLICK_OUTLINE = OceanIcons(
            token = "cursorclickoutline",
            icon = R.drawable.ocean_icon_cursor_click_outline,
            name = "CURSOR_CLICK_OUTLINE"
        )
        val CURSOR_CLICK_SOLID = OceanIcons(
            token = "cursorclicksolid",
            icon = R.drawable.ocean_icon_cursor_click_solid,
            name = "CURSOR_CLICK_SOLID"
        )
        val DATABASE_OUTLINE = OceanIcons(
            token = "databaseoutline",
            icon = R.drawable.ocean_icon_database_outline,
            name = "DATABASE_OUTLINE"
        )
        val DATABASE_SOLID = OceanIcons(
            token = "databasesolid",
            icon = R.drawable.ocean_icon_database_solid,
            name = "DATABASE_SOLID"
        )
        val DESKTOP_COMPUTER_OUTLINE = OceanIcons(
            token = "desktopcomputeroutline",
            icon = R.drawable.ocean_icon_desktop_computer_outline,
            name = "DESKTOP_COMPUTER_OUTLINE"
        )
        val DESKTOP_COMPUTER_SOLID = OceanIcons(
            token = "desktopcomputersolid",
            icon = R.drawable.ocean_icon_desktop_computer_solid,
            name = "DESKTOP_COMPUTER_SOLID"
        )
        val DEVICE_MOBILE_OUTLINE = OceanIcons(
            token = "devicemobileoutline",
            icon = R.drawable.ocean_icon_device_mobile_outline,
            name = "DEVICE_MOBILE_OUTLINE"
        )
        val DEVICE_MOBILE_SOLID = OceanIcons(
            token = "devicemobilesolid",
            icon = R.drawable.ocean_icon_device_mobile_solid,
            name = "DEVICE_MOBILE_SOLID"
        )
        val DEVICE_TABLET_OUTLINE = OceanIcons(
            token = "devicetabletoutline",
            icon = R.drawable.ocean_icon_device_tablet_outline,
            name = "DEVICE_TABLET_OUTLINE"
        )
        val DEVICE_TABLET_SOLID = OceanIcons(
            token = "devicetabletsolid",
            icon = R.drawable.ocean_icon_device_tablet_solid,
            name = "DEVICE_TABLET_SOLID"
        )
        val DOCUMENT_ADD_OUTLINE = OceanIcons(
            token = "documentaddoutline",
            icon = R.drawable.ocean_icon_document_add_outline,
            name = "DOCUMENT_ADD_OUTLINE"
        )
        val DOCUMENT_ADD_SOLID = OceanIcons(
            token = "documentaddsolid",
            icon = R.drawable.ocean_icon_document_add_solid,
            name = "DOCUMENT_ADD_SOLID"
        )
        val DOCUMENT_DOWNLOAD_OUTLINE = OceanIcons(
            token = "documentdownloadoutline",
            icon = R.drawable.ocean_icon_document_download_outline,
            name = "DOCUMENT_DOWNLOAD_OUTLINE"
        )
        val DOCUMENT_DOWNLOAD_SOLID = OceanIcons(
            token = "documentdownloadsolid",
            icon = R.drawable.ocean_icon_document_download_solid,
            name = "DOCUMENT_DOWNLOAD_SOLID"
        )
        val DOCUMENT_DUPLICATE_OUTLINE = OceanIcons(
            token = "documentduplicateoutline",
            icon = R.drawable.ocean_icon_document_duplicate_outline,
            name = "DOCUMENT_DUPLICATE_OUTLINE"
        )
        val DOCUMENT_DUPLICATE_SOLID = OceanIcons(
            token = "documentduplicatesolid",
            icon = R.drawable.ocean_icon_document_duplicate_solid,
            name = "DOCUMENT_DUPLICATE_SOLID"
        )
        val DOCUMENT_REMOVE_OUTLINE = OceanIcons(
            token = "documentremoveoutline",
            icon = R.drawable.ocean_icon_document_outline,
            name = "DOCUMENT_REMOVE_OUTLINE"
        )
        val DOCUMENT_REMOVE_SOLID = OceanIcons(
            token = "documentremovesolid",
            icon = R.drawable.ocean_icon_document_remove_solid,
            name = "DOCUMENT_REMOVE_SOLID"
        )
        val DOCUMENT_REPORT_OUTLINE = OceanIcons(
            token = "documentreportoutline",
            icon = R.drawable.ocean_icon_document_report_outline,
            name = "DOCUMENT_REPORT_OUTLINE"
        )
        val DOCUMENT_REPORT_SOLID = OceanIcons(
            token = "documentreportsolid",
            icon = R.drawable.ocean_icon_document_report_solid,
            name = "DOCUMENT_REPORT_SOLID"
        )
        val DOCUMENT_SEARCH_OUTLINE = OceanIcons(
            token = "documentsearchoutline",
            icon = R.drawable.ocean_icon_document_search_outline,
            name = "DOCUMENT_SEARCH_OUTLINE"
        )
        val DOCUMENT_SEARCH_OUTLINE_BRAND_DOWN = OceanIcons(
            token = "documentsearchoutlinebranddown",
            icon = R.drawable.ocean_icon_document_search_outline_brand_down,
            name = "DOCUMENT_SEARCH_OUTLINE_BRAND_DOWN"
        )
        val DOCUMENT_SEARCH_SOLID = OceanIcons(
            token = "documentsearchsolid",
            icon = R.drawable.ocean_icon_document_search_solid,
            name = "DOCUMENT_SEARCH_SOLID"
        )
        val DOCUMENT_TEXT_OUTLINE = OceanIcons(
            token = "documenttextoutline",
            icon = R.drawable.ocean_icon_document_text_outline,
            name = "DOCUMENT_TEXT_OUTLINE"
        )
        val DOCUMENT_TEXT_SOLID = OceanIcons(
            token = "documenttextsolid",
            icon = R.drawable.ocean_icon_document_text_solid,
            name = "DOCUMENT_TEXT_SOLID"
        )
        val DOCUMENT_OUTLINE = OceanIcons(
            token = "documentoutline",
            icon = R.drawable.ocean_icon_document_outline,
            name = "DOCUMENT_OUTLINE"
        )
        val DOCUMENT_SOLID = OceanIcons(
            token = "documentsolid",
            icon = R.drawable.ocean_icon_document_solid,
            name = "DOCUMENT_SOLID"
        )
        val DOTS_CIRCLE_HORIZONTAL_OUTLINE = OceanIcons(
            token = "dotscirclehorizontaloutline",
            icon = R.drawable.ocean_icon_dots_circle_horizontal_outline,
            name = "DOTS_CIRCLE_HORIZONTAL_OUTLINE"
        )
        val DOTS_CIRCLE_HORIZONTAL_SOLID = OceanIcons(
            token = "dotscirclehorizontalsolid",
            icon = R.drawable.ocean_icon_dots_circle_horizontal_solid,
            name = "DOTS_CIRCLE_HORIZONTAL_SOLID"
        )
        val DOTS_HORIZONTAL_OUTLINE = OceanIcons(
            token = "dotshorizontaloutline",
            icon = R.drawable.ocean_icon_dots_horizontal_outline,
            name = "DOTS_HORIZONTAL_OUTLINE"
        )
        val DOTS_HORIZONTAL_SOLID = OceanIcons(
            token = "dotshorizontalsolid",
            icon = R.drawable.ocean_icon_dots_horizontal_solid,
            name = "DOTS_HORIZONTAL_SOLID"
        )
        val DOTS_VERTICAL_OUTLINE = OceanIcons(
            token = "dotsverticaloutline",
            icon = R.drawable.ocean_icon_dots_vertical_outline,
            name = "DOTS_VERTICAL_OUTLINE"
        )
        val DOTS_VERTICAL_SOLID = OceanIcons(
            token = "dotsverticalsolid",
            icon = R.drawable.ocean_icon_dots_vertical_solid,
            name = "DOTS_VERTICAL_SOLID"
        )
        val DOWNLOAD_OUTLINE = OceanIcons(
            token = "downloadoutline",
            icon = R.drawable.ocean_icon_download_outline,
            name = "DOWNLOAD_OUTLINE"
        )
        val DOWNLOAD_SOLID = OceanIcons(
            token = "downloadsolid",
            icon = R.drawable.ocean_icon_download_solid,
            name = "DOWNLOAD_SOLID"
        )
        val DUPLICATE_OUTLINE = OceanIcons(
            token = "duplicateoutline",
            icon = R.drawable.ocean_icon_duplicate_outline,
            name = "DUPLICATE_OUTLINE"
        )
        val DUPLICATE_SOLID = OceanIcons(
            token = "duplicatesolid",
            icon = R.drawable.ocean_icon_duplicate_solid,
            name = "DUPLICATE_SOLID"
        )
        val EMOJI_HAPPY_OUTLINE = OceanIcons(
            token = "emojihappyoutline",
            icon = R.drawable.ocean_icon_emoji_happy_outline,
            name = "EMOJI_HAPPY_OUTLINE"
        )
        val EMOJI_HAPPY_SOLID = OceanIcons(
            token = "emojihappysolid",
            icon = R.drawable.ocean_icon_emoji_happy_solid,
            name = "EMOJI_HAPPY_SOLID"
        )
        val EMOJI_SAD_OUTLINE = OceanIcons(
            token = "emojisadoutline",
            icon = R.drawable.ocean_icon_emoji_sad_outline,
            name = "EMOJI_SAD_OUTLINE"
        )
        val EMOJI_SAD_SOLID = OceanIcons(
            token = "emojisadsolid",
            icon = R.drawable.ocean_icon_emoji_sad_solid,
            name = "EMOJI_SAD_SOLID"
        )
        val EXCLAMATION_CIRCLE_OUTLINE = OceanIcons(
            token = "exclamationcircleoutline",
            icon = R.drawable.ocean_icon_exclamation_circle_outline,
            name = "EXCLAMATION_CIRCLE_OUTLINE"
        )
        val EXCLAMATION_CIRCLE_OUTLINE_LIGHT_DEEP = OceanIcons(
            token = "exclamationcircleoutlinelightdeep",
            icon = R.drawable.ocean_icon_exclamation_circle_outline_light_deep,
            name = "EXCLAMATION_CIRCLE_OUTLINE_LIGHT_DEEP"
        )
        val EXCLAMATION_CIRCLE_SOLID = OceanIcons(
            token = "exclamationcirclesolid",
            icon = R.drawable.ocean_icon_exclamation_circle_solid,
            name = "EXCLAMATION_CIRCLE_SOLID"
        )
        val EXCLAMATION_OUTLINE = OceanIcons(
            token = "exclamationoutline",
            icon = R.drawable.ocean_icon_exclamation_outline,
            name = "EXCLAMATION_OUTLINE"
        )
        val EXCLAMATION_SOLID = OceanIcons(
            token = "exclamationsolid",
            icon = R.drawable.ocean_icon_exclamation_solid,
            name = "EXCLAMATION_SOLID"
        )
        val EXTERNAL_LINK_OUTLINE = OceanIcons(
            token = "externallinkoutline",
            icon = R.drawable.ocean_icon_external_link_outline,
            name = "EXTERNAL_LINK_OUTLINE"
        )
        val EXTERNAL_LINK_SOLID = OceanIcons(
            token = "externallinksolid",
            icon = R.drawable.ocean_icon_external_link_solid,
            name = "EXTERNAL_LINK_SOLID"
        )
        val EYEGLASS_STORE_OUTLINE = OceanIcons(
            token = "eyeglassstoreoutline",
            icon = R.drawable.ocean_icon_eyeglass_store_outline,
            name = "EYEGLASS_STORE_OUTLINE"
        )
        val EYE_OFF_OUTLINE = OceanIcons(
            token = "eyeoffoutline",
            icon = R.drawable.ocean_icon_eye_off_outline,
            name = "EYE_OFF_OUTLINE"
        )
        val EYE_OFF_ALT_OUTLINE = OceanIcons(
            token = "eyeoffaltoutline",
            icon = R.drawable.ocean_icon_eye_off_alt_outline,
            name = "EYE_OFF_ALT_OUTLINE"
        )
        val EYE_OFF_SOLID = OceanIcons(
            token = "eyeoffsolid",
            icon = R.drawable.ocean_icon_eye_off_solid,
            name = "EYE_OFF_SOLID"
        )
        val EYE_OUTLINE = OceanIcons(
            token = "eyeoutline",
            icon = R.drawable.ocean_icon_eye_outline,
            name = "EYE_OUTLINE"
        )
        val EYE_SOLID = OceanIcons(
            token = "eyesolid",
            icon = R.drawable.ocean_icon_eye_solid,
            name = "EYE_SOLID"
        )
        val FAST_FORWARD_OUTLINE = OceanIcons(
            token = "fastforwardoutline",
            icon = R.drawable.ocean_icon_fast_forward_outline,
            name = "FAST_FORWARD_OUTLINE"
        )
        val FAST_FORWARD_SOLID = OceanIcons(
            token = "fastforwardsolid",
            icon = R.drawable.ocean_icon_fast_forward_solid,
            name = "FAST_FORWARD_SOLID"
        )
        val FILM_OUTLINE = OceanIcons(
            token = "filmoutline",
            icon = R.drawable.ocean_icon_film_outline,
            name = "FILM_OUTLINE"
        )
        val FILM_SOLID = OceanIcons(
            token = "filmsolid",
            icon = R.drawable.ocean_icon_film_solid,
            name = "FILM_SOLID"
        )
        val FILTER_OUTLINE = OceanIcons(
            token = "filteroutline",
            icon = R.drawable.ocean_icon_filter_outline,
            name = "FILTER_OUTLINE"
        )
        val FILTER_SOLID = OceanIcons(
            token = "filtersolid",
            icon = R.drawable.ocean_icon_filter_solid,
            name = "FILTER_SOLID"
        )
        val FINGERPRINT_OUTLINE = OceanIcons(
            token = "fingerprintoutline",
            icon = R.drawable.ocean_icon_finger_print_outline,
            name = "FINGERPRINT_OUTLINE"
        )
        val FINGERPRINT_SOLID = OceanIcons(
            token = "fingerprintsolid",
            icon = R.drawable.ocean_icon_finger_print_solid,
            name = "FINGERPRINT_SOLID"
        )
        val FIRE_OUTLINE = OceanIcons(
            token = "fireoutline",
            icon = R.drawable.ocean_icon_fire_outline,
            name = "FIRE_OUTLINE"
        )
        val FIRE_SOLID = OceanIcons(
            token = "firesolid",
            icon = R.drawable.ocean_icon_fire_solid,
            name = "FIRE_SOLID"
        )
        val FLAG_OUTLINE = OceanIcons(
            token = "flagoutline",
            icon = R.drawable.ocean_icon_flag_outline,
            name = "FLAG_OUTLINE"
        )
        val FLAG_SOLID = OceanIcons(
            token = "flagsolid",
            icon = R.drawable.ocean_icon_flag_solid,
            name = "FLAG_SOLID"
        )
        val FOLDER_ADD_OUTLINE = OceanIcons(
            token = "folderaddoutline",
            icon = R.drawable.ocean_icon_folder_add_outline,
            name = "FOLDER_ADD_OUTLINE"
        )
        val FOLDER_ADD_SOLID = OceanIcons(
            token = "folderaddsolid",
            icon = R.drawable.ocean_icon_folder_add_solid,
            name = "FOLDER_ADD_SOLID"
        )
        val FOLDER_DOWNLOAD_OUTLINE = OceanIcons(
            token = "folderdownloadoutline",
            icon = R.drawable.ocean_icon_folder_download_outline,
            name = "FOLDER_DOWNLOAD_OUTLINE"
        )
        val FOLDER_DOWNLOAD_SOLID = OceanIcons(
            token = "folderdownloadsolid",
            icon = R.drawable.ocean_icon_folder_download_solid,
            name = "FOLDER_DOWNLOAD_SOLID"
        )
        val FOLDER_OPEN_OUTLINE = OceanIcons(
            token = "folderopenoutline",
            icon = R.drawable.ocean_icon_folder_open_outline,
            name = "FOLDER_OPEN_OUTLINE"
        )
        val FOLDER_OPEN_SOLID = OceanIcons(
            token = "folderopensolid",
            icon = R.drawable.ocean_icon_folder_open_solid,
            name = "FOLDER_OPEN_SOLID"
        )
        val FOLDER_REMOVE_OUTLINE = OceanIcons(
            token = "folderremoveoutline",
            icon = R.drawable.ocean_icon_folder_remove_outline,
            name = "FOLDER_REMOVE_OUTLINE"
        )
        val FOLDER_REMOVE_SOLID = OceanIcons(
            token = "folderremovesolid",
            icon = R.drawable.ocean_icon_folder_remove_solid,
            name = "FOLDER_REMOVE_SOLID"
        )
        val FOLDER_OUTLINE = OceanIcons(
            token = "folderoutline",
            icon = R.drawable.ocean_icon_folder_outline,
            name = "FOLDER_OUTLINE"
        )
        val FOLDER_SOLID = OceanIcons(
            token = "foldersolid",
            icon = R.drawable.ocean_icon_folder_solid,
            name = "FOLDER_SOLID"
        )
        val FOOT_WARE_OUTLINE = OceanIcons(
            token = "footwareoutline",
            icon = R.drawable.ocean_icon_footware_outline,
            name = "FOOT_WARE_OUTLINE"
        )
        val GIFT_OUTLINE = OceanIcons(
            token = "giftoutline",
            icon = R.drawable.ocean_icon_gift_outline,
            name = "GIFT_OUTLINE"
        )
        val GIFT_SOLID = OceanIcons(
            token = "giftsolid",
            icon = R.drawable.ocean_icon_gift_solid,
            name = "GIFT_SOLID"
        )
        val GLOBE_ALT_OUTLINE = OceanIcons(
            token = "globealtoutline",
            icon = R.drawable.ocean_icon_globe_alt_outline,
            name = "GLOBE_ALT_OUTLINE"
        )
        val GLOBE_ALT_SOLID = OceanIcons(
            token = "globealtsolid",
            icon = R.drawable.ocean_icon_globe_alt_solid,
            name = "GLOBE_ALT_SOLID"
        )
        val GLOBE_OUTLINE = OceanIcons(
            token = "globeoutline",
            icon = R.drawable.ocean_icon_globe_outline,
            name = "GLOBE_OUTLINE"
        )
        val GLOBE_SOLID = OceanIcons(
            token = "globesolid",
            icon = R.drawable.ocean_icon_globe_solid,
            name = "GLOBE_SOLID"
        )
        val HAND_OUTLINE = OceanIcons(
            token = "handoutline",
            icon = R.drawable.ocean_icon_hand_outline,
            name = "HAND_OUTLINE"
        )
        val HAND_SOLID = OceanIcons(
            token = "handsolid",
            icon = R.drawable.ocean_icon_hand_solid,
            name = "HAND_SOLID"
        )
        val HASHTAG_OUTLINE = OceanIcons(
            token = "hashtagoutline",
            icon = R.drawable.ocean_icon_hashtag_outline,
            name = "HASHTAG_OUTLINE"
        )
        val HASHTAG_SOLID = OceanIcons(
            token = "hashtagsolid",
            icon = R.drawable.ocean_icon_hashtag_solid,
            name = "HASHTAG_SOLID"
        )
        val HEART_OUTLINE = OceanIcons(
            token = "heartoutline",
            icon = R.drawable.ocean_icon_heart_outline,
            name = "HEART_OUTLINE"
        )
        val HEART_SOLID = OceanIcons(
            token = "heartsolid",
            icon = R.drawable.ocean_icon_heart_solid,
            name = "HEART_SOLID"
        )
        val HOME_OUTLINE = OceanIcons(
            token = "homeoutline",
            icon = R.drawable.ocean_icon_home_outline,
            name = "HOME_OUTLINE"
        )
        val HOME_SOLID = OceanIcons(
            token = "homesolid",
            icon = R.drawable.ocean_icon_home_solid,
            name = "HOME_SOLID"
        )
        val IDENTIFICATION_OUTLINE = OceanIcons(
            token = "identificationoutline",
            icon = R.drawable.ocean_icon_identification_outline,
            name = "IDENTIFICATION_OUTLINE"
        )
        val IDENTIFICATION_SOLID = OceanIcons(
            token = "identificationsolid",
            icon = R.drawable.ocean_icon_identification_solid,
            name = "IDENTIFICATION_SOLID"
        )
        val INBOX_IN_OUTLINE = OceanIcons(
            token = "inboxinoutline",
            icon = R.drawable.ocean_icon_inbox_in_outline,
            name = "INBOX_IN_OUTLINE"
        )
        val INBOX_IN_SOLID = OceanIcons(
            token = "inboxinsolid",
            icon = R.drawable.ocean_icon_inbox_in_solid,
            name = "INBOX_IN_SOLID"
        )
        val INBOX_OUTLINE = OceanIcons(
            token = "inboxoutline",
            icon = R.drawable.ocean_icon_inbox_outline,
            name = "INBOX_OUTLINE"
        )
        val INBOX_SOLID = OceanIcons(
            token = "inboxsolid",
            icon = R.drawable.ocean_icon_inbox_solid,
            name = "INBOX_SOLID"
        )
        val INFORMATION_CIRCLE_OUTLINE = OceanIcons(
            token = "informationcircleoutline",
            icon = R.drawable.ocean_icon_information_circle_outline,
            name = "INFORMATION_CIRCLE_OUTLINE"
        )
        val INFORMATION_CIRCLE_SOLID = OceanIcons(
            token = "informationcirclesolid",
            icon = R.drawable.ocean_icon_information_circle_solid,
            name = "INFORMATION_CIRCLE_SOLID"
        )
        val KEY_OUTLINE = OceanIcons(
            token = "keyoutline",
            icon = R.drawable.ocean_icon_key_outline,
            name = "KEY_OUTLINE"
        )
        val KEY_SOLID = OceanIcons(
            token = "keysolid",
            icon = R.drawable.ocean_icon_key_solid,
            name = "KEY_SOLID"
        )
        val LIBRARY_OUTLINE = OceanIcons(
            token = "libraryoutline",
            icon = R.drawable.ocean_icon_library_outline,
            name = "LIBRARY_OUTLINE"
        )
        val LIBRARY_SOLID = OceanIcons(
            token = "librarysolid",
            icon = R.drawable.ocean_icon_library_solid,
            name = "LIBRARY_SOLID"
        )
        val LIGHT_BULB_OUTLINE = OceanIcons(
            token = "lightbulboutline",
            icon = R.drawable.ocean_icon_light_bulb_outline,
            name = "LIGHT_BULB_OUTLINE"
        )
        val LIGHT_BULB_SOLID = OceanIcons(
            token = "lightbulbsolid",
            icon = R.drawable.ocean_icon_light_bulb_solid,
            name = "LIGHT_BULB_SOLID"
        )
        val LIGHTHOUSE_OUTLINE = OceanIcons(
            token = "lighthouseoutline",
            icon = R.drawable.ocean_icon_lighthouse_outline,
            name = "LIGHTHOUSE_OUTLINE"
        )
        val LIGHTNING_BOLT_OUTLINE = OceanIcons(
            token = "lightningboltoutline",
            icon = R.drawable.ocean_icon_lightning_bolt_outline,
            name = "LIGHTNING_BOLT_OUTLINE"
        )
        val LIGHTNING_BOLT_SOLID = OceanIcons(
            token = "lightningboltsolid",
            icon = R.drawable.ocean_icon_lightning_bolt_solid,
            name = "LIGHTNING_BOLT_SOLID"
        )
        val LINK_OUTLINE = OceanIcons(
            token = "linkoutline",
            icon = R.drawable.ocean_icon_link_outline,
            name = "LINK_OUTLINE"
        )
        val LINK_SOLID = OceanIcons(
            token = "linksolid",
            icon = R.drawable.ocean_icon_link_solid,
            name = "LINK_SOLID"
        )
        val LOCATION_MARKER_OUTLINE = OceanIcons(
            token = "locationmarkeroutline",
            icon = R.drawable.ocean_icon_location_marker_outline,
            name = "LOCATION_MARKER_OUTLINE"
        )
        val LOCATION_MARKER_SOLID = OceanIcons(
            token = "locationmarkersolid",
            icon = R.drawable.ocean_icon_location_marker_solid,
            name = "LOCATION_MARKER_SOLID"
        )
        val LOCK_CLOSED_OUTLINE = OceanIcons(
            token = "lockclosedoutline",
            icon = R.drawable.ocean_icon_lock_closed_outline,
            name = "LOCK_CLOSED_OUTLINE"
        )
        val LOCK_CLOSED_SOLID = OceanIcons(
            token = "lockclosedsolid",
            icon = R.drawable.ocean_icon_lock_closed_solid,
            name = "LOCK_CLOSED_SOLID"
        )
        val LOCK_OPEN_OUTLINE = OceanIcons(
            token = "lockopenoutline",
            icon = R.drawable.ocean_icon_lock_open_outline,
            name = "LOCK_OPEN_OUTLINE"
        )
        val LOCK_OPEN_SOLID = OceanIcons(
            token = "lockopensolid",
            icon = R.drawable.ocean_icon_lock_open_solid,
            name = "LOCK_OPEN_SOLID"
        )
        val LOGIN_OUTLINE = OceanIcons(
            token = "loginoutline",
            icon = R.drawable.ocean_icon_login_outline,
            name = "LOGIN_OUTLINE"
        )
        val LOGIN_SOLID = OceanIcons(
            token = "loginsolid",
            icon = R.drawable.ocean_icon_login_solid,
            name = "LOGIN_SOLID"
        )
        val LOGOUT_OUTLINE = OceanIcons(
            token = "logoutoutline",
            icon = R.drawable.ocean_icon_logout_outline,
            name = "LOGOUT_OUTLINE"
        )
        val LOGOUT_SOLID = OceanIcons(
            token = "logoutsolid",
            icon = R.drawable.ocean_icon_logout_solid,
            name = "LOGOUT_SOLID"
        )
        val MAIL_OPEN_OUTLINE = OceanIcons(
            token = "mailopenoutline",
            icon = R.drawable.ocean_icon_mail_open_outline,
            name = "MAIL_OPEN_OUTLINE"
        )
        val MAIL_OPEN_SOLID = OceanIcons(
            token = "mailopensolid",
            icon = R.drawable.ocean_icon_mail_open_solid,
            name = "MAIL_OPEN_SOLID"
        )
        val MAIL_OUTLINE = OceanIcons(
            token = "mailoutline",
            icon = R.drawable.ocean_icon_mail_outline,
            name = "MAIL_OUTLINE"
        )
        val MAIL_SOLID = OceanIcons(
            token = "mailsolid",
            icon = R.drawable.ocean_icon_mail_solid,
            name = "MAIL_SOLID"
        )
        val MAP_OUTLINE = OceanIcons(
            token = "mapoutline",
            icon = R.drawable.ocean_icon_map_outline,
            name = "MAP_OUTLINE"
        )
        val MAP_SOLID = OceanIcons(
            token = "mapsolid",
            icon = R.drawable.ocean_icon_map_solid,
            name = "MAP_SOLID"
        )
        val MENU_OUTLINE = OceanIcons(
            token = "menuoutline",
            icon = R.drawable.ocean_icon_menu_outline,
            name = "MENU_OUTLINE"
        )
        val MENU_SOLID = OceanIcons(
            token = "menusolid",
            icon = R.drawable.ocean_icon_menu_solid,
            name = "MENU_SOLID"
        )
        val MENU_ALT_ONE_OUTLINE = OceanIcons(
            token = "menualt1outline",
            icon = R.drawable.ocean_icon_menu_alt_1_outline,
            name = "MENU_ALT_ONE_OUTLINE"
        )
        val MENU_ALT_ONE_SOLID = OceanIcons(
            token = "menualt1solid",
            icon = R.drawable.ocean_icon_menu_alt_1_solid,
            name = "MENU_ALT_ONE_SOLID"
        )
        val MENU_ALT_TWO_OUTLINE = OceanIcons(
            token = "menualt2outline",
            icon = R.drawable.ocean_icon_menu_alt_2_outline,
            name = "MENU_ALT_TWO_OUTLINE"
        )
        val MENU_ALT_TWO_SOLID = OceanIcons(
            token = "menualt2solid",
            icon = R.drawable.ocean_icon_menu_alt_2_solid,
            name = "MENU_ALT_TWO_SOLID"
        )
        val MENU_ALT_THREE_OUTLINE = OceanIcons(
            token = "menualt3outline",
            icon = R.drawable.ocean_icon_menu_alt_3_outline,
            name = "MENU_ALT_THREE_OUTLINE"
        )
        val MENU_ALT_THREE_SOLID = OceanIcons(
            token = "menualt3solid",
            icon = R.drawable.ocean_icon_menu_alt_3_solid,
            name = "MENU_ALT_THREE_SOLID"
        )
        val MENU_ALT_FOUR_OUTLINE = OceanIcons(
            token = "menualt4outline",
            icon = R.drawable.ocean_icon_menu_alt_4_outline,
            name = "MENU_ALT_FOUR_OUTLINE"
        )
        val MENU_ALT_FOUR_SOLID = OceanIcons(
            token = "menualt4solid",
            icon = R.drawable.ocean_icon_menu_alt_4_solid,
            name = "MENU_ALT_FOUR_SOLID"
        )
        val MICROPHONE_OUTLINE = OceanIcons(
            token = "microphoneoutline",
            icon = R.drawable.ocean_icon_microphone_outline,
            name = "MICROPHONE_OUTLINE"
        )
        val MICROPHONE_SOLID = OceanIcons(
            token = "microphonesolid",
            icon = R.drawable.ocean_icon_microphone_solid,
            name = "MICROPHONE_SOLID"
        )
        val MINUS_CIRCLE_OUTLINE = OceanIcons(
            token = "minuscircleoutline",
            icon = R.drawable.ocean_icon_minus_circle_outline,
            name = "MINUS_CIRCLE_OUTLINE"
        )
        val MINUS_CIRCLE_SOLID = OceanIcons(
            token = "minuscirclesolid",
            icon = R.drawable.ocean_icon_minus_circle_solid,
            name = "MINUS_CIRCLE_SOLID"
        )
        val MINUS_OUTLINE = OceanIcons(
            token = "minusoutline",
            icon = R.drawable.ocean_icon_minus_outline,
            name = "MINUS_OUTLINE"
        )
        val MINUS_SM_OUTLINE = OceanIcons(
            token = "minussmoutline",
            icon = R.drawable.ocean_icon_minus_sm_outline,
            name = "MINUS_SM_OUTLINE"
        )
        val MINUS_SM_SOLID = OceanIcons(
            token = "minussmsolid",
            icon = R.drawable.ocean_icon_minus_sm_solid,
            name = "MINUS_SM_SOLID"
        )
        val MINUS_SOLID = OceanIcons(
            token = "minussolid",
            icon = R.drawable.ocean_icon_minus_solid,
            name = "MINUS_SOLID"
        )
        val MONEY_INFLOW = OceanIcons(
            token = "moneyinflow",
            icon = R.drawable.ocean_icon_money_inflow,
            name = "MONEY_INFLOW"
        )
        val MONEY_OUTFLOW = OceanIcons(
            token = "moneyoutflow",
            icon = R.drawable.ocean_icon_money_outflow,
            name = "MONEY_OUTFLOW"
        )
        val MOON_OUTLINE = OceanIcons(
            token = "moonoutline",
            icon = R.drawable.ocean_icon_moon_outline,
            name = "MOON_OUTLINE"
        )
        val MOON_SOLID = OceanIcons(
            token = "moonsolid",
            icon = R.drawable.ocean_icon_moon_solid,
            name = "MOON_SOLID"
        )
        val MUSIC_NOTE_OUTLINE = OceanIcons(
            token = "musicnoteoutline",
            icon = R.drawable.ocean_icon_music_note_outline,
            name = "MUSIC_NOTE_OUTLINE"
        )
        val MUSIC_NOTE_SOLID = OceanIcons(
            token = "musicnotesolid",
            icon = R.drawable.ocean_icon_music_note_solid,
            name = "MUSIC_NOTE_SOLID"
        )
        val NEWSPAPER_OUTLINE = OceanIcons(
            token = "newspaperoutline",
            icon = R.drawable.ocean_icon_newspaper_outline,
            name = "NEWSPAPER_OUTLINE"
        )
        val NEWSPAPER_SOLID = OceanIcons(
            token = "newspapersolid",
            icon = R.drawable.ocean_icon_newspaper_solid,
            name = "NEWSPAPER_SOLID"
        )
        val OFFICE_BUILDING_OUTLINE = OceanIcons(
            token = "officebuildingoutline",
            icon = R.drawable.ocean_icon_office_building_outline,
            name = "OFFICE_BUILDING_OUTLINE"
        )
        val OFFICE_BUILDING_SOLID = OceanIcons(
            token = "officebuildingsolid",
            icon = R.drawable.ocean_icon_office_building_solid,
            name = "OFFICE_BUILDING_SOLID"
        )
        val PAGBLU_SOLID = OceanIcons(
            token = "pagblusolid",
            icon = R.drawable.ocean_icon_pagblu_solid,
            name = "PAGBLU_SOLID"
        )
        val PAGBLU_OUTLINE = OceanIcons(
            token = "pagbluoutline",
            icon = R.drawable.ocean_icon_pagblu_outline,
            name = "PAGBLU_OUTLINE"
        )
        val PAPER_AIRPLANE_OUTLINE = OceanIcons(
            token = "paperairplaneoutline",
            icon = R.drawable.ocean_icon_paper_airplane_outline,
            name = "PAPER_AIRPLANE_OUTLINE"
        )
        val PAPER_AIRPLANE_SOLID = OceanIcons(
            token = "paperairplanesolid",
            icon = R.drawable.ocean_icon_paper_airplane_solid,
            name = "PAPER_AIRPLANE_SOLID"
        )
        val PAPER_CLIP_OUTLINE = OceanIcons(
            token = "paperclipoutline",
            icon = R.drawable.ocean_icon_paper_clip_outline,
            name = "PAPER_CLIP_OUTLINE"
        )
        val PAPER_CLIP_SOLID = OceanIcons(
            token = "paperclipsolid",
            icon = R.drawable.ocean_icon_paper_clip_solid,
            name = "PAPER_CLIP_SOLID"
        )
        val PAUSE_OUTLINE = OceanIcons(
            token = "pauseoutline",
            icon = R.drawable.ocean_icon_pause_outline,
            name = "PAUSE_OUTLINE"
        )
        val PAUSE_SOLID = OceanIcons(
            token = "pausesolid",
            icon = R.drawable.ocean_icon_pause_solid,
            name = "PAUSE_SOLID"
        )
        val PENCIL_OUTLINE = OceanIcons(
            token = "penciloutline",
            icon = R.drawable.ocean_icon_pencil_outline,
            name = "PENCIL_OUTLINE"
        )
        val PENCIL_SOLID = OceanIcons(
            token = "pencilsolid",
            icon = R.drawable.ocean_icon_pencil_solid,
            name = "PENCIL_SOLID"
        )
        val PENCIL_ALT_OUTLINE = OceanIcons(
            token = "pencilaltoutline",
            icon = R.drawable.ocean_icon_pencil_alt_outline,
            name = "PENCIL_ALT_OUTLINE"
        )
        val PENCIL_ALT_SOLID = OceanIcons(
            token = "pencilaltsolid",
            icon = R.drawable.ocean_icon_pencil_alt_solid,
            name = "PENCIL_ALT_SOLID"
        )
        val PERSON_OUTLINE = OceanIcons(
            token = "personoutline",
            icon = R.drawable.ocean_icon_person_outline,
            name = "PERSON_OUTLINE"
        )
        val PETSHOP_OUTLINE = OceanIcons(
            token = "petshopoutline",
            icon = R.drawable.ocean_icon_petshop_outline,
            name = "PETSHOP_OUTLINE"
        )
        val PHONE_INCOMING_OUTLINE = OceanIcons(
            token = "phoneincomingoutline",
            icon = R.drawable.ocean_icon_phone_incoming_outline,
            name = "PHONE_INCOMING_OUTLINE"
        )
        val PHONE_INCOMING_SOLID = OceanIcons(
            token = "phoneincomingsolid",
            icon = R.drawable.ocean_icon_phone_incoming_solid,
            name = "PHONE_INCOMING_SOLID"
        )
        val PHONE_MISSED_CALL_OUTLINE = OceanIcons(
            token = "phonemissedcalloutline",
            icon = R.drawable.ocean_icon_phone_missed_call_outline,
            name = "PHONE_MISSED_CALL_OUTLINE"
        )
        val PHONE_MISSED_CALL_SOLID = OceanIcons(
            token = "phonemissedcallsolid",
            icon = R.drawable.ocean_icon_phone_missed_call_solid,
            name = "PHONE_MISSED_CALL_SOLID"
        )
        val PHONE_OUTGOING_OUTLINE = OceanIcons(
            token = "phoneoutgoingoutline",
            icon = R.drawable.ocean_icon_phone_outgoing_outline,
            name = "PHONE_OUTGOING_OUTLINE"
        )
        val PHONE_OUTGOING_SOLID = OceanIcons(
            token = "phoneoutgoingsolid",
            icon = R.drawable.ocean_icon_phone_outgoing_solid,
            name = "PHONE_OUTGOING_SOLID"
        )
        val PHONE_OUTLINE = OceanIcons(
            token = "phoneoutline",
            icon = R.drawable.ocean_icon_phone_outline,
            name = "PHONE_OUTLINE"
        )
        val PHONE_SOLID = OceanIcons(
            token = "phonesolid",
            icon = R.drawable.ocean_icon_phone_solid,
            name = "PHONE_SOLID"
        )
        val PHOTOGRAPH_OUTLINE = OceanIcons(
            token = "photographoutline",
            icon = R.drawable.ocean_icon_photograph_outline,
            name = "PHOTOGRAPH_OUTLINE"
        )
        val PHOTOGRAPH_SOLID = OceanIcons(
            token = "photographsolid",
            icon = R.drawable.ocean_icon_photograph_solid,
            name = "PHOTOGRAPH_SOLID"
        )
        val PLAY_OUTLINE = OceanIcons(
            token = "playoutline",
            icon = R.drawable.ocean_icon_play_outline,
            name = "PLAY_OUTLINE"
        )
        val PLAY_SOLID = OceanIcons(
            token = "playsolid",
            icon = R.drawable.ocean_icon_play_solid,
            name = "PLAY_SOLID"
        )
        val PLUS_CIRCLE_OUTLINE = OceanIcons(
            token = "pluscircleoutline",
            icon = R.drawable.ocean_icon_plus_circle_outline,
            name = "PLUS_CIRCLE_OUTLINE"
        )
        val PLUS_CIRCLE_SOLID = OceanIcons(
            token = "pluscirclesolid",
            icon = R.drawable.ocean_icon_plus_circle_solid,
            name = "PLUS_CIRCLE_SOLID"
        )
        val PLUS_OUTLINE = OceanIcons(
            token = "plusoutline",
            icon = R.drawable.ocean_icon_plus_outline,
            name = "PLUS_OUTLINE"
        )
        val PLUS_SOLID = OceanIcons(
            token = "plussolid",
            icon = R.drawable.ocean_icon_plus_solid,
            name = "PLUS_SOLID"
        )
        val PLUS_SM_OUTLINE = OceanIcons(
            token = "plussmoutline",
            icon = R.drawable.ocean_icon_plus_sm_outline,
            name = "PLUS_SM_OUTLINE"
        )
        val PLUS_SM_SOLID = OceanIcons(
            token = "plussmsolid",
            icon = R.drawable.ocean_icon_plus_sm_solid,
            name = "PLUS_SM_SOLID"
        )
        val PRESENTATION_CHART_BAR_OUTLINE = OceanIcons(
            token = "presentationchartbaroutline",
            icon = R.drawable.ocean_icon_presentation_chart_bar_outline,
            name = "PRESENTATION_CHART_BAR_OUTLINE"
        )
        val PRESENTATION_CHART_BAR_SOLID = OceanIcons(
            token = "presentationchartbarsolid",
            icon = R.drawable.ocean_icon_presentation_chart_bar_solid,
            name = "PRESENTATION_CHART_BAR_SOLID"
        )
        val PRESENTATION_CHART_LINE_OUTLINE = OceanIcons(
            token = "presentationchartlineoutline",
            icon = R.drawable.ocean_icon_presentation_chart_line_outline,
            name = "PRESENTATION_CHART_LINE_OUTLINE"
        )
        val PRESENTATION_CHART_LINE_SOLID = OceanIcons(
            token = "presentationchartlinesolid",
            icon = R.drawable.ocean_icon_presentation_chart_line_solid,
            name = "PRESENTATION_CHART_LINE_SOLID"
        )
        val PRINTER_OUTLINE = OceanIcons(
            token = "printeroutline",
            icon = R.drawable.ocean_icon_printer_outline,
            name = "PRINTER_OUTLINE"
        )
        val PRINTER_SOLID = OceanIcons(
            token = "printersolid",
            icon = R.drawable.ocean_icon_printer_solid,
            name = "PRINTER_SOLID"
        )
        val PUZZLE_OUTLINE = OceanIcons(
            token = "puzzleoutline",
            icon = R.drawable.ocean_icon_puzzle_outline,
            name = "PUZZLE_OUTLINE"
        )
        val PUZZLE_SOLID = OceanIcons(
            token = "puzzlesolid",
            icon = R.drawable.ocean_icon_puzzle_solid,
            name = "PUZZLE_SOLID"
        )
        val QRCODE_DISPLAY_OUTLINE = OceanIcons(
            token = "qrcodedisplayoutline",
            icon = R.drawable.ocean_icon_qrcode_display_outline,
            name = "QRCODE_DISPLAY_OUTLINE"
        )
        val QRCODE_OUTLINE = OceanIcons(
            token = "qrcodeoutline",
            icon = R.drawable.ocean_icon_qrcode_outline,
            name = "QRCODE_OUTLINE"
        )
        val QRCODE_OUTLINE_HIGHLIGHT = OceanIcons(
            token = "qrcodeoutlinehighlight",
            icon = R.drawable.ocean_icon_qrcode_outline_highlight,
            name = "QRCODE_OUTLINE_HIGHLIGHT"
        )
        val QRCODE_SOLID = OceanIcons(
            token = "qrcodesolid",
            icon = R.drawable.ocean_icon_qrcode_solid,
            name = "QRCODE_SOLID"
        )
        val QUESTION_MARK_CIRCLE_OUTLINE = OceanIcons(
            token = "questionmarkcircleoutline",
            icon = R.drawable.ocean_icon_question_mark_circle_outline,
            name = "QUESTION_MARK_CIRCLE_OUTLINE"
        )
        val QUESTION_MARK_CIRCLE_SOLID = OceanIcons(
            token = "questionmarkcirclesolid",
            icon = R.drawable.ocean_icon_question_mark_circle_solid,
            name = "QUESTION_MARK_CIRCLE_SOLID"
        )
        val RECEIPT_REFUND_OUTLINE = OceanIcons(
            token = "receiptrefundoutline",
            icon = R.drawable.ocean_icon_receipt_refund_outline,
            name = "RECEIPT_REFUND_OUTLINE"
        )
        val RECEIPT_REFUND_SOLID = OceanIcons(
            token = "receiptrefundsolid",
            icon = R.drawable.ocean_icon_receipt_refund_solid,
            name = "RECEIPT_REFUND_SOLID"
        )
        val RECEIPT_TAX_OUTLINE = OceanIcons(
            token = "receipttaxoutline",
            icon = R.drawable.ocean_icon_receipt_tax_outline,
            name = "RECEIPT_TAX_OUTLINE"
        )
        val RECEIPT_TAX_SOLID = OceanIcons(
            token = "receipttaxsolid",
            icon = R.drawable.ocean_icon_receipt_tax_solid,
            name = "RECEIPT_TAX_SOLID"
        )
        val REFRESH_OUTLINE = OceanIcons(
            token = "refreshoutline",
            icon = R.drawable.ocean_icon_refresh_outline,
            name = "REFRESH_OUTLINE"
        )
        val REFRESH_SOLID = OceanIcons(
            token = "refreshsolid",
            icon = R.drawable.ocean_icon_refresh_solid,
            name = "REFRESH_SOLID"
        )
        val REPLY_OUTLINE = OceanIcons(
            token = "replyoutline",
            icon = R.drawable.ocean_icon_reply_outline,
            name = "REPLY_OUTLINE"
        )
        val REPLY_SOLID = OceanIcons(
            token = "replysolid",
            icon = R.drawable.ocean_icon_reply_solid,
            name = "REPLY_SOLID"
        )
        val REPORT_SOLID = OceanIcons(
            token = "reportsolid",
            icon = R.drawable.ocean_icon_report_solid,
            name = "REPORT_SOLID"
        )
        val REPORT_OUTLINE = OceanIcons(
            token = "reportoutline",
            icon = R.drawable.ocean_icon_report_outline,
            name = "REPORT_OUTLINE"
        )
        val REWIND_OUTLINE = OceanIcons(
            token = "rewindoutline",
            icon = R.drawable.ocean_icon_rewind_outline,
            name = "REWIND_OUTLINE"
        )
        val REWIND_SOLID = OceanIcons(
            token = "rewindsolid",
            icon = R.drawable.ocean_icon_rewind_solid,
            name = "REWIND_SOLID"
        )
        val RSS_OUTLINE = OceanIcons(
            token = "rssoutline",
            icon = R.drawable.ocean_icon_rss_outline,
            name = "RSS_OUTLINE"
        )
        val RSS_SOLID = OceanIcons(
            token = "rsssolid",
            icon = R.drawable.ocean_icon_rss_solid,
            name = "RSS_SOLID"
        )
        val SAVE_AS_OUTLINE = OceanIcons(
            token = "saveasoutline",
            icon = R.drawable.ocean_icon_save_as_outline,
            name = "SAVE_AS_OUTLINE"
        )
        val SAVE_AS_SOLID = OceanIcons(
            token = "saveassolid",
            icon = R.drawable.ocean_icon_save_as_solid,
            name = "SAVE_AS_SOLID"
        )
        val SAVE_OUTLINE = OceanIcons(
            token = "saveoutline",
            icon = R.drawable.ocean_icon_save_outline,
            name = "SAVE_OUTLINE"
        )
        val SAVE_SOLID = OceanIcons(
            token = "savesolid",
            icon = R.drawable.ocean_icon_save_solid,
            name = "SAVE_SOLID"
        )
        val SCALE_OUTLINE = OceanIcons(
            token = "scaleoutline",
            icon = R.drawable.ocean_icon_scale_outline,
            name = "SCALE_OUTLINE"
        )
        val SCALE_SOLID = OceanIcons(
            token = "scalesolid",
            icon = R.drawable.ocean_icon_scale_solid,
            name = "SCALE_SOLID"
        )
        val SCISSORS_OUTLINE = OceanIcons(
            token = "scissorsoutline",
            icon = R.drawable.ocean_icon_scissors_outline,
            name = "SCISSORS_OUTLINE"
        )
        val SCISSORS_SOLID = OceanIcons(
            token = "scissorssolid",
            icon = R.drawable.ocean_icon_scissors_solid,
            name = "SCISSORS_SOLID"
        )
        val SEARCH_CIRCLE_OUTLINE = OceanIcons(
            token = "searchcircleoutline",
            icon = R.drawable.ocean_icon_search_circle_outline,
            name = "SEARCH_CIRCLE_OUTLINE"
        )
        val SEARCH_CIRCLE_SOLID = OceanIcons(
            token = "searchcirclesolid",
            icon = R.drawable.ocean_icon_search_circle_solid,
            name = "SEARCH_CIRCLE_SOLID"
        )
        val SEARCH_OUTLINE = OceanIcons(
            token = "searchoutline",
            icon = R.drawable.ocean_icon_search_outline,
            name = "SEARCH_OUTLINE"
        )
        val SEARCH_SOLID = OceanIcons(
            token = "searchsolid",
            icon = R.drawable.ocean_icon_search_solid,
            name = "SEARCH_SOLID"
        )
        val SELECTOR_OUTLINE = OceanIcons(
            token = "selectoroutline",
            icon = R.drawable.ocean_icon_selector_outline,
            name = "SELECTOR_OUTLINE"
        )
        val SELECTOR_SOLID = OceanIcons(
            token = "selectorsolid",
            icon = R.drawable.ocean_icon_selector_solid,
            name = "SELECTOR_SOLID"
        )
        val SERVER_OUTLINE = OceanIcons(
            token = "serveroutline",
            icon = R.drawable.ocean_icon_server_outline,
            name = "SERVER_OUTLINE"
        )
        val SERVER_SOLID = OceanIcons(
            token = "serversolid",
            icon = R.drawable.ocean_icon_server_solid,
            name = "SERVER_SOLID"
        )
        val SHARE_OUTLINE = OceanIcons(
            token = "shareoutline",
            icon = R.drawable.ocean_icon_share_outline,
            name = "SHARE_OUTLINE"
        )
        val SHARE_SOLID = OceanIcons(
            token = "sharesolid",
            icon = R.drawable.ocean_icon_share_solid,
            name = "SHARE_SOLID"
        )
        val SHIELD_CHECK_OUTLINE = OceanIcons(
            token = "shieldcheckoutline",
            icon = R.drawable.ocean_icon_shield_check_outline,
            name = "SHIELD_CHECK_OUTLINE"
        )
        val SHIELD_CHECK_SOLID = OceanIcons(
            token = "shieldchecksolid",
            icon = R.drawable.ocean_icon_shield_check_solid,
            name = "SHIELD_CHECK_SOLID"
        )
        val SHIELD_EXCLAMATION_OUTLINE = OceanIcons(
            token = "shieldexclamationoutline",
            icon = R.drawable.ocean_icon_shield_exclamation_outline,
            name = "SHIELD_EXCLAMATION_OUTLINE"
        )
        val SHIELD_EXCLAMATION_SOLID = OceanIcons(
            token = "shieldexclamationsolid",
            icon = R.drawable.ocean_icon_shield_exclamation_solid,
            name = "SHIELD_EXCLAMATION_SOLID"
        )
        val SHOPPING_BAG_OUTLINE = OceanIcons(
            token = "shoppingbagoutline",
            icon = R.drawable.ocean_icon_shopping_bag_outline,
            name = "SHOPPING_BAG_OUTLINE"
        )
        val SHOPPING_BAG_SOLID = OceanIcons(
            token = "shoppingbagsolid",
            icon = R.drawable.ocean_icon_shopping_bag_solid,
            name = "SHOPPING_BAG_SOLID"
        )
        val SHOPPING_CART_OUTLINE = OceanIcons(
            token = "shoppingcartoutline",
            icon = R.drawable.ocean_icon_shopping_cart_outline,
            name = "SHOPPING_CART_OUTLINE"
        )
        val SHOPPING_CART_SOLID = OceanIcons(
            token = "shoppingcartsolid",
            icon = R.drawable.ocean_icon_shopping_cart_solid,
            name = "SHOPPING_CART_SOLID"
        )
        val SORT_ASCENDING_OUTLINE = OceanIcons(
            token = "sortascendingoutline",
            icon = R.drawable.ocean_icon_sort_ascending_outline,
            name = "SORT_ASCENDING_OUTLINE"
        )
        val SORT_ASCENDING_SOLID = OceanIcons(
            token = "sortascendingsolid",
            icon = R.drawable.ocean_icon_sort_ascending_solid,
            name = "SORT_ASCENDING_SOLID"
        )
        val SORT_DESCENDING_OUTLINE = OceanIcons(
            token = "sortdescendingoutline",
            icon = R.drawable.ocean_icon_sort_descending_outline,
            name = "SORT_DESCENDING_OUTLINE"
        )
        val SORT_DESCENDING_SOLID = OceanIcons(
            token = "sortdescendingsolid",
            icon = R.drawable.ocean_icon_sort_descending_solid,
            name = "SORT_DESCENDING_SOLID"
        )
        val SPARKLES_OUTLINE = OceanIcons(
            token = "sparklesoutline",
            icon = R.drawable.ocean_icon_sparkles_outline,
            name = "SPARKLES_OUTLINE"
        )
        val SPARKLES_SOLID = OceanIcons(
            token = "sparklessolid",
            icon = R.drawable.ocean_icon_sparkles_solid,
            name = "SPARKLES_SOLID"
        )
        val SPARKLES_ALT_SOLID = OceanIcons(
            token = "sparklesaltsolid",
            icon = R.drawable.ocean_icon_sparkles_alt_solid,
            name = "SPARKLES_ALT_SOLID"
        )
        val SPEAKERPHONE_OUTLINE = OceanIcons(
            token = "speakerphoneoutline",
            icon = R.drawable.ocean_icon_speakerphone_outline,
            name = "SPEAKERPHONE_OUTLINE"
        )
        val SPEAKERPHONE_SOLID = OceanIcons(
            token = "speakerphonesolid",
            icon = R.drawable.ocean_icon_speakerphone_solid,
            name = "SPEAKERPHONE_SOLID"
        )
        val STAR_OUTLINE = OceanIcons(
            token = "staroutline",
            icon = R.drawable.ocean_icon_star_outline,
            name = "STAR_OUTLINE"
        )
        val STAR_SOLID = OceanIcons(
            token = "starsolid",
            icon = R.drawable.ocean_icon_star_solid,
            name = "STAR_SOLID"
        )
        val STATUS_OFFLINE_OUTLINE = OceanIcons(
            token = "statusofflineoutline",
            icon = R.drawable.ocean_icon_status_offline_outline,
            name = "STATUS_OFFLINE_OUTLINE"
        )
        val STATUS_OFFLINE_SOLID = OceanIcons(
            token = "statusofflinesolid",
            icon = R.drawable.ocean_icon_status_offline_solid,
            name = "STATUS_OFFLINE_SOLID"
        )
        val STATUS_ONLINE_OUTLINE = OceanIcons(
            token = "statusonlineoutline",
            icon = R.drawable.ocean_icon_status_online_outline,
            name = "STATUS_ONLINE_OUTLINE"
        )
        val STATUS_ONLINE_SOLID = OceanIcons(
            token = "statusonlinesolid",
            icon = R.drawable.ocean_icon_status_online_solid,
            name = "STATUS_ONLINE_SOLID"
        )
        val STOP_OUTLINE = OceanIcons(
            token = "stopoutline",
            icon = R.drawable.ocean_icon_stop_outline,
            name = "STOP_OUTLINE"
        )
        val STOP_SOLID = OceanIcons(
            token = "stopsolid",
            icon = R.drawable.ocean_icon_stop_solid,
            name = "STOP_SOLID"
        )
        val SUN_OUTLINE = OceanIcons(
            token = "sunoutline",
            icon = R.drawable.ocean_icon_sun_outline,
            name = "SUN_OUTLINE"
        )
        val SUN_SOLID = OceanIcons(
            token = "sunsolid",
            icon = R.drawable.ocean_icon_sun_solid,
            name = "SUN_SOLID"
        )
        val SUPPORT_OUTLINE = OceanIcons(
            token = "supportoutline",
            icon = R.drawable.ocean_icon_support_outline,
            name = "SUPPORT_OUTLINE"
        )
        val SUPPORT_SOLID = OceanIcons(
            token = "supportsolid",
            icon = R.drawable.ocean_icon_support_solid,
            name = "SUPPORT_SOLID"
        )
        val SWITCH_HORIZONTAL_OUTLINE = OceanIcons(
            token = "switchhorizontaloutline",
            icon = R.drawable.ocean_icon_switch_horizontal_outline,
            name = "SWITCH_HORIZONTAL_OUTLINE"
        )
        val SWITCH_HORIZONTAL_SOLID = OceanIcons(
            token = "switchhorizontalsolid",
            icon = R.drawable.ocean_icon_switch_horizontal_solid,
            name = "SWITCH_HORIZONTAL_SOLID"
        )
        val SWITCH_VERTICAL_OUTLINE = OceanIcons(
            token = "switchverticaloutline",
            icon = R.drawable.ocean_icon_switch_vertical_outline,
            name = "SWITCH_VERTICAL_OUTLINE"
        )
        val SWITCH_VERTICAL_SOLID = OceanIcons(
            token = "switchverticalsolid",
            icon = R.drawable.ocean_icon_switch_vertical_solid,
            name = "SWITCH_VERTICAL_SOLID"
        )
        val TABLE_OUTLINE = OceanIcons(
            token = "tableoutline",
            icon = R.drawable.ocean_icon_table_outline,
            name = "TABLE_OUTLINE"
        )
        val TABLE_SOLID = OceanIcons(
            token = "tablesolid",
            icon = R.drawable.ocean_icon_table_solid,
            name = "TABLE_SOLID"
        )
        val TAG_OUTLINE = OceanIcons(
            token = "tagoutline",
            icon = R.drawable.ocean_icon_tag_outline,
            name = "TAG_OUTLINE"
        )
        val TAG_SOLID = OceanIcons(
            token = "tagsolid",
            icon = R.drawable.ocean_icon_tag_solid,
            name = "TAG_SOLID"
        )
        val TEMPLATE_OUTLINE = OceanIcons(
            token = "templateoutline",
            icon = R.drawable.ocean_icon_template_outline,
            name = "TEMPLATE_OUTLINE"
        )
        val TEMPLATE_SOLID = OceanIcons(
            token = "templatesolid",
            icon = R.drawable.ocean_icon_template_solid,
            name = "TEMPLATE_SOLID"
        )
        val TERMINAL_OUTLINE = OceanIcons(
            token = "terminaloutline",
            icon = R.drawable.ocean_icon_terminal_outline,
            name = "TERMINAL_OUTLINE"
        )
        val TERMINAL_SOLID = OceanIcons(
            token = "terminalsolid",
            icon = R.drawable.ocean_icon_terminal_solid,
            name = "TERMINAL_SOLID"
        )
        val THUMB_DOWN_OUTLINE = OceanIcons(
            token = "thumbdownoutline",
            icon = R.drawable.ocean_icon_thumb_down_outline,
            name = "THUMB_DOWN_OUTLINE"
        )
        val THUMB_DOWN_SOLID = OceanIcons(
            token = "thumbdownsolid",
            icon = R.drawable.ocean_icon_thumb_down_solid,
            name = "THUMB_DOWN_SOLID"
        )
        val THUMB_UP_OUTLINE = OceanIcons(
            token = "thumbupoutline",
            icon = R.drawable.ocean_icon_thumb_up_outline,
            name = "THUMB_UP_OUTLINE"
        )
        val THUMB_UP_SOLID = OceanIcons(
            token = "thumbupsolid",
            icon = R.drawable.ocean_icon_thumb_up_solid,
            name = "THUMB_UP_SOLID"
        )
        val TICKET_OUTLINE = OceanIcons(
            token = "ticketoutline",
            icon = R.drawable.ocean_icon_ticket_outline,
            name = "TICKET_OUTLINE"
        )
        val TICKET_SOLID = OceanIcons(
            token = "ticketsolid",
            icon = R.drawable.ocean_icon_ticket_solid,
            name = "TICKET_SOLID"
        )
        val TRANSACTIONS_OUTLINE = OceanIcons(
            token = "transactionsoutline",
            icon = R.drawable.ocean_icon_transactions_outline,
            name = "TRANSACTIONS_OUTLINE"
        )
        val TRANSLATE_OUTLINE = OceanIcons(
            token = "translateoutline",
            icon = R.drawable.ocean_icon_translate_outline,
            name = "TRANSLATE_OUTLINE"
        )
        val TRANSLATE_SOLID = OceanIcons(
            token = "translatesolid",
            icon = R.drawable.ocean_icon_translate_solid,
            name = "TRANSLATE_SOLID"
        )
        val TRASH_OUTLINE = OceanIcons(
            token = "trashoutline",
            icon = R.drawable.ocean_icon_trash_outline,
            name = "TRASH_OUTLINE"
        )
        val TRASH_SOLID = OceanIcons(
            token = "trashsolid",
            icon = R.drawable.ocean_icon_trash_solid,
            name = "TRASH_SOLID"
        )
        val TRENDING_DOWN_OUTLINE = OceanIcons(
            token = "trendingdownoutline",
            icon = R.drawable.ocean_icon_trending_down_outline,
            name = "TRENDING_DOWN_OUTLINE"
        )
        val TRENDING_DOWN_SOLID = OceanIcons(
            token = "trendingdownsolid",
            icon = R.drawable.ocean_icon_trending_down_solid,
            name = "TRENDING_DOWN_SOLID"
        )
        val TRENDING_UP_OUTLINE = OceanIcons(
            token = "trendingupoutline",
            icon = R.drawable.ocean_icon_trending_up_outline,
            name = "TRENDING_UP_OUTLINE"
        )
        val TRENDING_UP_SOLID = OceanIcons(
            token = "trendingupsolid",
            icon = R.drawable.ocean_icon_trending_up_solid,
            name = "TRENDING_UP_SOLID"
        )
        val TRUCK_OUTLINE = OceanIcons(
            token = "truckoutline",
            icon = R.drawable.ocean_icon_truck_outline,
            name = "TRUCK_OUTLINE"
        )
        val TRUCK_SOLID = OceanIcons(
            token = "trucksolid",
            icon = R.drawable.ocean_icon_truck_solid,
            name = "TRUCK_SOLID"
        )
        val UPLOAD_OUTLINE = OceanIcons(
            token = "uploadoutline",
            icon = R.drawable.ocean_icon_upload_outline,
            name = "UPLOAD_OUTLINE"
        )
        val UPLOAD_SOLID = OceanIcons(
            token = "uploadsolid",
            icon = R.drawable.ocean_icon_upload_solid,
            name = "UPLOAD_SOLID"
        )
        val USER_ADD_OUTLINE = OceanIcons(
            token = "useraddoutline",
            icon = R.drawable.ocean_icon_user_add_outline,
            name = "USER_ADD_OUTLINE"
        )
        val USER_ADD_SOLID = OceanIcons(
            token = "useraddsolid",
            icon = R.drawable.ocean_icon_user_add_solid,
            name = "USER_ADD_SOLID"
        )
        val USER_CIRCLE_OUTLINE = OceanIcons(
            token = "usercircleoutline",
            icon = R.drawable.ocean_icon_user_circle_outline,
            name = "USER_CIRCLE_OUTLINE"
        )
        val USER_CIRCLE_SOLID = OceanIcons(
            token = "usercirclesolid",
            icon = R.drawable.ocean_icon_user_circle_solid,
            name = "USER_CIRCLE_SOLID"
        )
        val USER_GROUP_OUTLINE = OceanIcons(
            token = "usergroupoutline",
            icon = R.drawable.ocean_icon_user_group_outline,
            name = "USER_GROUP_OUTLINE"
        )
        val USER_GROUP_SOLID = OceanIcons(
            token = "usergroupsolid",
            icon = R.drawable.ocean_icon_user_group_solid,
            name = "USER_GROUP_SOLID"
        )
        val USER_REMOVE_OUTLINE = OceanIcons(
            token = "userremoveoutline",
            icon = R.drawable.ocean_icon_user_remove_outline,
            name = "USER_REMOVE_OUTLINE"
        )
        val USER_REMOVE_SOLID = OceanIcons(
            token = "userremovesolid",
            icon = R.drawable.ocean_icon_user_remove_solid,
            name = "USER_REMOVE_SOLID"
        )
        val USER_OUTLINE = OceanIcons(
            token = "useroutline",
            icon = R.drawable.ocean_icon_user_outline,
            name = "USER_OUTLINE"
        )
        val USER_SOLID = OceanIcons(
            token = "usersolid",
            icon = R.drawable.ocean_icon_user_solid,
            name = "USER_SOLID"
        )
        val USERS_OUTLINE = OceanIcons(
            token = "usersoutline",
            icon = R.drawable.ocean_icon_users_outline,
            name = "USERS_OUTLINE"
        )
        val USERS_SOLID = OceanIcons(
            token = "userssolid",
            icon = R.drawable.ocean_icon_users_solid,
            name = "USERS_SOLID"
        )
        val VARIABLE_OUTLINE = OceanIcons(
            token = "variableoutline",
            icon = R.drawable.ocean_icon_variable_outline,
            name = "VARIABLE_OUTLINE"
        )
        val VARIABLE_SOLID = OceanIcons(
            token = "variablesolid",
            icon = R.drawable.ocean_icon_variable_solid,
            name = "VARIABLE_SOLID"
        )
        val VIDEO_CAMERA_OUTLINE = OceanIcons(
            token = "videocameraoutline",
            icon = R.drawable.ocean_icon_video_camera_outline,
            name = "VIDEO_CAMERA_OUTLINE"
        )
        val VIDEO_CAMERA_SOLID = OceanIcons(
            token = "videocamerasolid",
            icon = R.drawable.ocean_icon_video_camera_solid,
            name = "VIDEO_CAMERA_SOLID"
        )
        val VIEW_BOARDS_OUTLINE = OceanIcons(
            token = "viewboardsoutline",
            icon = R.drawable.ocean_icon_view_boards_outline,
            name = "VIEW_BOARDS_OUTLINE"
        )
        val VIEW_BOARDS_SOLID = OceanIcons(
            token = "viewboardssolid",
            icon = R.drawable.ocean_icon_view_boards_solid,
            name = "VIEW_BOARDS_SOLID"
        )
        val VIEW_GRID_ADD_OUTLINE = OceanIcons(
            token = "viewgridaddoutline",
            icon = R.drawable.ocean_icon_view_grid_add_outline,
            name = "VIEW_GRID_ADD_OUTLINE"
        )
        val VIEW_GRID_ADD_SOLID = OceanIcons(
            token = "viewgridaddsolid",
            icon = R.drawable.ocean_icon_view_grid_add_solid,
            name = "VIEW_GRID_ADD_SOLID"
        )
        val VIEW_GRID_OUTLINE = OceanIcons(
            token = "viewgridoutline",
            icon = R.drawable.ocean_icon_view_grid_outline,
            name = "VIEW_GRID_OUTLINE"
        )
        val VIEW_GRID_SOLID = OceanIcons(
            token = "viewgridsolid",
            icon = R.drawable.ocean_icon_view_grid_solid,
            name = "VIEW_GRID_SOLID"
        )
        val VIEW_LIST_OUTLINE = OceanIcons(
            token = "viewlistoutline",
            icon = R.drawable.ocean_icon_view_list_outline,
            name = "VIEW_LIST_OUTLINE"
        )
        val VIEW_LIST_SOLID = OceanIcons(
            token = "viewlistsolid",
            icon = R.drawable.ocean_icon_view_list_solid,
            name = "VIEW_LIST_SOLID"
        )
        val VOLUME_OFF_OUTLINE = OceanIcons(
            token = "volumeoffoutline",
            icon = R.drawable.ocean_icon_volume_off_outline,
            name = "VOLUME_OFF_OUTLINE"
        )
        val VOLUME_OFF_SOLID = OceanIcons(
            token = "volumeoffsolid",
            icon = R.drawable.ocean_icon_volume_off_solid,
            name = "VOLUME_OFF_SOLID"
        )
        val VOLUME_UP_OUTLINE = OceanIcons(
            token = "volumeupoutline",
            icon = R.drawable.ocean_icon_volume_up_outline,
            name = "VOLUME_UP_OUTLINE"
        )
        val VOLUME_UP_SOLID = OceanIcons(
            token = "volumeupsolid",
            icon = R.drawable.ocean_icon_volume_up_solid,
            name = "VOLUME_UP_SOLID"
        )
        val WIFI_OUTLINE = OceanIcons(
            token = "wifioutline",
            icon = R.drawable.ocean_icon_wifi_outline,
            name = "WIFI_OUTLINE"
        )
        val WIFI_SOLID = OceanIcons(
            token = "wifisolid",
            icon = R.drawable.ocean_icon_wifi_solid,
            name = "WIFI_SOLID"
        )
        val X_CIRCLE_OUTLINE = OceanIcons(
            token = "xcircleoutline",
            icon = R.drawable.ocean_icon_x_circle_outline,
            name = "X_CIRCLE_OUTLINE"
        )
        val X_CIRCLE_SOLID = OceanIcons(
            token = "xcirclesolid",
            icon = R.drawable.ocean_icon_x_circle_solid,
            name = "X_CIRCLE_SOLID"
        )
        val X_OUTLINE = OceanIcons(
            token = "xoutline",
            icon = R.drawable.ocean_icon_x_outline,
            name = "X_OUTLINE"
        )
        val X_SOLID = OceanIcons(
            token = "xsolid",
            icon = R.drawable.ocean_icon_x_solid,
            name = "X_SOLID"
        )
        val ZOOM_IN_OUTLINE = OceanIcons(
            token = "zoominoutline",
            icon = R.drawable.ocean_icon_zoom_in_outline,
            name = "ZOOM_IN_OUTLINE"
        )
        val ZOOM_IN_SOLID = OceanIcons(
            token = "zoominsolid",
            icon = R.drawable.ocean_icon_zoom_in_solid,
            name = "ZOOM_IN_SOLID"
        )
        val ZOOM_OUT_OUTLINE = OceanIcons(
            token = "zoomoutoutline",
            icon = R.drawable.ocean_icon_zoom_out_outline,
            name = "ZOOM_OUT_OUTLINE"
        )
        val ZOOM_OUT_SOLID = OceanIcons(
            token = "zoomoutsolid",
            icon = R.drawable.ocean_icon_zoom_out_solid,
            name = "ZOOM_OUT_SOLID"
        )
        val DRUGSTORE_OUTLINE = OceanIcons(
            token = "drugstoreoutline",
            icon = R.drawable.ocean_icon_drugstore_outline,
            name = "DRUGSTORE_OUTLINE"
        )
        val EQUAL_OUTLINE = OceanIcons(
            token = "equaloutline",
            icon = R.drawable.ocean_icon_equal_outline,
            name = "EQUAL_OUTLINE"
        )
        val EVENTS_OUTLINE = OceanIcons(
            token = "eventsoutline",
            icon = R.drawable.ocean_icon_events_outline,
            name = "EVENTS_OUTLINE"
        )
        val EYE_GLASS_STORE_OUTLINE = OceanIcons(
            token = "eyeglassstoreoutline",
            icon = R.drawable.ocean_icon_eyeglass_store_outline,
            name = "EYE_GLASS_STORE_OUTLINE"
        )
        val FLASH_OFF_OUTLINE = OceanIcons(
            token = "flashoffoutline",
            icon = R.drawable.ocean_icon_flash_off_outline,
            name = "FLASH_OFF_OUTLINE"
        )
        val FLASH_ON_OUTLINE = OceanIcons(
            token = "flashonoutline",
            icon = R.drawable.ocean_icon_flash_on_outline,
            name = "FLASH_ON_OUTLINE"
        )
        val FLASH_OFF_ALT_OUTLINE = OceanIcons(
            token = "flashoffaltoutline",
            icon = R.drawable.icon_flash_off_alt_outline,
            name = "FLASH_OFF_ALT_OUTLINE"
        )
        val FLASH_ON_ALT_OUTLINE = OceanIcons(
            token = "flashonaltoutline",
            icon = R.drawable.icon_flash_alt_outline,
            name = "FLASH_ON_ALT_OUTLINE"
        )
        val FOOD_AND_BEVERAGE_OUTLINE = OceanIcons(
            token = "foodandbeverageoutline",
            icon = R.drawable.ocean_icon_food_and_beverage_outline,
            name = "FOOD_AND_BEVERAGE_OUTLINE"
        )
        val FOOTWEAR_OUTLINE = OceanIcons(
            token = "footwareoutline",
            icon = R.drawable.ocean_icon_footware_outline,
            name = "FOOTWEAR_OUTLINE"
        )
        val FURNITURE_OUTLINE = OceanIcons(
            token = "furnitureoutline",
            icon = R.drawable.ocean_icon_furniture_outline,
            name = "FURNITURE_OUTLINE"
        )
        val GAS_STATION_OUTLINE = OceanIcons(
            token = "gasstationoutline",
            icon = R.drawable.ocean_icon_gas_station_outline,
            name = "GAS_STATION_OUTLINE"
        )
        val GLASSES_OUTLINE = OceanIcons(
            token = "glassesoutline",
            icon = R.drawable.ocean_icon_glasses_outline,
            name = "GLASSES_OUTLINE"
        )
        val HELP_OUTLINE = OceanIcons(
            token = "helpoutline",
            icon = R.drawable.ocean_icon_help_outline,
            name = "HELP_OUTLINE"
        )
        val INFO_OUTLINE = OceanIcons(
            token = "infooutline",
            icon = R.drawable.ocean_icon_info_outline,
            name = "INFO_OUTLINE"
        )
        val INSTAGRAM_OUTLINE = OceanIcons(
            token = "instagramoutline",
            icon = R.drawable.ocean_icon_instagram_outline,
            name = "INSTAGRAM_OUTLINE"
        )
        val MATTRESS_OUTLINE = OceanIcons(
            token = "mattressoutline",
            icon = R.drawable.ocean_icon_mattress_outline,
            name = "MATTRESS_OUTLINE"
        )
        val ORDER_OUTLINE = OceanIcons(
            token = "orderoutline",
            icon = R.drawable.ocean_icon_order_outline,
            name = "ORDER_OUTLINE"
        )
        val OTHER_OUTLINE = OceanIcons(
            token = "otheroutline",
            icon = R.drawable.ocean_icon_other_outline,
            name = "OTHER_OUTLINE"
        )
        val PAG_BLU_SOLID = OceanIcons(
            token = "pagblusolid",
            icon = R.drawable.ocean_icon_pagblu_solid,
            name = "PAG_BLU_SOLID"
        )
        val PAG_BLU_OUTLINE = OceanIcons(
            token = "pagbluoutline",
            icon = R.drawable.ocean_icon_pagblu_outline,
            name = "PAG_BLU_OUTLINE"
        )
        val PAPER_PLANE_OUTLINE = OceanIcons(
            token = "paperplaneoutline",
            icon = R.drawable.ocean_icon_paper_plane_outline,
            name = "PAPER_PLANE_OUTLINE"
        )
        val PAYMENT_OUTLINE = OceanIcons(
            token = "paymentoutline",
            icon = R.drawable.ocean_icon_payment_outline,
            name = "PAYMENT_OUTLINE"
        )
        val PET_SHOP_OUTLINE = OceanIcons(
            token = "petshopoutline",
            icon = R.drawable.ocean_icon_petshop_outline,
            name = "PET_SHOP_OUTLINE"
        )
        val PIX_OUTLINE = OceanIcons(
            token = "pixoutline",
            icon = R.drawable.ocean_icon_pix_outline,
            name = "PIX_OUTLINE"
        )
        val PLACEHOLDER_OUTLINE = OceanIcons(
            token = "placeholderoutline",
            icon = R.drawable.ocean_icon_placeholder_outline,
            name = "PLACEHOLDER_OUTLINE"
        )
        val POINT_UP_OUTLINE = OceanIcons(
            token = "pointupoutline",
            icon = R.drawable.ocean_icon_point_up_outline,
            name = "POINT_UP_OUTLINE"
        )
        val QR_CODE_DISPLAY_OUTLINE = OceanIcons(
            token = "qrcodedisplayoutline",
            icon = R.drawable.ocean_icon_qrcode_display_outline,
            name = "QR_CODE_DISPLAY_OUTLINE"
        )
        val RAW_MATERIAL_OUTLINE = OceanIcons(
            token = "rawmaterialoutline",
            icon = R.drawable.ocean_icon_raw_material_outline,
            name = "RAW_MATERIAL_OUTLINE"
        )
        val RETAILER_OUTLINE = OceanIcons(
            token = "retaileroutline",
            icon = R.drawable.ocean_icon_retailer_outline,
            name = "RETAILER_OUTLINE"
        )
        val RETAILER_SOLID = OceanIcons(
            token = "retailersolid",
            icon = R.drawable.ocean_icon_retailer_solid,
            name = "RETAILER_SOLID"
        )
        val SCAN_OUTLINE = OceanIcons(
            token = "scanoutline",
            icon = R.drawable.ocean_icon_scan_outline,
            name = "SCAN_OUTLINE"
        )
        val SORT_OUTLINE = OceanIcons(
            token = "sortoutline",
            icon = R.drawable.ocean_icon_sort_outline,
            name = "SORT_OUTLINE"
        )
        val SUPPLIER_OUTLINE = OceanIcons(
            token = "supplieroutline",
            icon = R.drawable.ocean_icon_supplier_outline,
            name = "SUPPLIER_OUTLINE"
        )
        val TAX_OUTLINE = OceanIcons(
            token = "taxoutline",
            icon = R.drawable.ocean_icon_tax_outline,
            name = "TAX_OUTLINE"
        )
        val WARLIKE_OUTLINE = OceanIcons(
            token = "warlikeoutline",
            icon = R.drawable.ocean_icon_warlike_outline,
            name = "WARLIKE_OUTLINE"
        )
        val WHATSAPP_OUTLINE = OceanIcons(
            token = "whatsappoutline",
            icon = R.drawable.ocean_icon_whatsapp_outline,
            name = "WHATSAPP_OUTLINE"
        )
        val YOUTUBE_OUTLINE = OceanIcons(
            token = "youtubeoutline",
            icon = R.drawable.ocean_icon_youtube_outline,
            name = "YOUTUBE_OUTLINE"
        )
        val ZERO_OUTLINE = OceanIcons(
            token = "zerooutline",
            icon = R.drawable.ocean_icon_zero_outline,
            name = "ZERO_OUTLINE"
        )
        val ARROW_RIGHT_UP_SOLID = OceanIcons(
            token = "arrowrightupsolid",
            icon = R.drawable.ocean_icon_arrow_right_up_solid,
            name = "ARROW_RIGHT_UP_SOLID"
        )
        val BLU_LOGO_SOLID = OceanIcons(
            token = "blulogosolid",
            icon = R.drawable.ocean_icon_blu_logo_solid,
            name = "BLU_LOGO_SOLID"
        )
        val EQUAL_SOLID = OceanIcons(
            token = "equalsolid",
            icon = R.drawable.ocean_icon_equal_solid,
            name = "EQUAL_SOLID"
        )
        val FLASH_ON_SOLID = OceanIcons(
            token = "flashonsolid",
            icon = R.drawable.ocean_icon_flash_on_solid,
            name = "FLASH_ON_SOLID"
        )
        val GLASSES_SOLID = OceanIcons(
            token = "glassessolid",
            icon = R.drawable.ocean_icon_glasses_solid,
            name = "GLASSES_SOLID"
        )
        val HELP_SOLID = OceanIcons(
            token = "helpsolid",
            icon = R.drawable.ocean_icon_help_outline,
            name = "HELP_SOLID"
        )
        val INFO_SOLID = OceanIcons(
            token = "infosolid",
            icon = R.drawable.ocean_icon_info_solid,
            name = "INFO_SOLID"
        )
        val INSTAGRAM_SOLID = OceanIcons(
            token = "instagramsolid",
            icon = R.drawable.ocean_icon_instagram_solid,
            name = "INSTAGRAM_SOLID"
        )
        val ORDER_SOLID = OceanIcons(
            token = "ordersolid",
            icon = R.drawable.ocean_icon_order_solid,
            name = "ORDER_SOLID"
        )
        val OTHER_SOLID = OceanIcons(
            token = "othersolid",
            icon = R.drawable.ocean_icon_other_solid,
            name = "OTHER_SOLID"
        )
        val PAPER_PLANE_SOLID = OceanIcons(
            token = "paperplanesolid",
            icon = R.drawable.ocean_icon_paper_plane_solid,
            name = "PAPER_PLANE_SOLID"
        )
        val PAYMENT_SOLID = OceanIcons(
            token = "paymentsolid",
            icon = R.drawable.ocean_icon_payment_solid,
            name = "PAYMENT_SOLID"
        )
        val PIX_SOLID = OceanIcons(
            token = "pixsolid",
            icon = R.drawable.ocean_icon_pix_solid,
            name = "PIX_SOLID"
        )
        val PLACEHOLDER_SOLID = OceanIcons(
            token = "placeholdersolid",
            icon = R.drawable.ocean_icon_placeholder_solid,
            name = "PLACEHOLDER_SOLID"
        )
        val SORT_SOLID = OceanIcons(
            token = "sortsolid",
            icon = R.drawable.ocean_icon_sort_solid,
            name = "SORT_SOLID"
        )
        val WHATSAPP_SOLID = OceanIcons(
            token = "whatsappsolid",
            icon = R.drawable.ocean_icon_whatsapp_solid,
            name = "WHATSAPP_SOLID"
        )
        val YOUTUBE_SOLID = OceanIcons(
            token = "youtubesolid",
            icon = R.drawable.ocean_icon_youtube_solid,
            name = "YOUTUBE_SOLID"
        )
        val QR_CODE_OUTLINE_HIGHLIGHT = OceanIcons(
            token = "qrcodeoutlinehighlight",
            icon = R.drawable.ocean_icon_qrcode_outline_highlight,
            name = "QR_CODE_OUTLINE_HIGHLIGHT"
        )
        val BARCODE_OUTLINE_HIGHLIGHT = OceanIcons(
            token = "barcodeoutlinehighlight",
            icon = R.drawable.ocean_icon_barcode_outline_highlight,
            name = "BARCODE_OUTLINE_HIGHLIGHT"
        )
        val MONEY_INFLOW_OUTLINE = OceanIcons(
            token = "moneyinflowoutline",
            icon = R.drawable.ocean_icon_money_inflow,
            name = "MONEY_INFLOW_OUTLINE"
        )
        val MONEY_OUTFLOW_OUTLINE = OceanIcons(
            token = "moneyoutflowoutline",
            icon = R.drawable.ocean_icon_money_outflow,
            name = "MONEY_OUTFLOW_OUTLINE"
        )
        val SCORE_OUTLINE = OceanIcons(
            token = "scoreoutline",
            icon = R.drawable.ocean_icon_score_outline,
            name = "SCORE_OUTLINE"
        )
        val CONTACTLESS_OUTLINE = OceanIcons(
            token = "contactlessoutline",
            icon = R.drawable.ocean_icon_contactless_outline,
            name = "CONTACTLESS_OUTLINE"
        )
        val COINS_HAND_OUTLINE = OceanIcons(
            token = "coinshandoutline",
            icon = R.drawable.ocean_icon_coins_hand_outline,
            name = "COINS_HAND_OUTLINE"
        )
        val PIGGY_BANK_OUTLINE = OceanIcons(
            token = "piggybankoutline",
            icon = R.drawable.icon_piggybankoutline,
            name = "PIGGY_BANK_OUTLINE"
        )
        val TARGET_OUTLINE = OceanIcons(
            token = "targetoutline",
            icon = R.drawable.icon_targetoutline,
            name = "TARGET_OUTLINE"
        )
        val COINS_SWAP_OUTLINE = OceanIcons(
            token = "coinsswapoutline",
            icon = R.drawable.icon_coinsswapoutline,
            name = "COINS_SWAP_OUTLINE"
        )
        val CONTACT_BOOK_OUTLINE_2 = OceanIcons(
            token = "contactbookoutline2",
            icon = R.drawable.icon_contactbookoutline2,
            name = "CONTACT_BOOK_OUTLINE_2"
        )
        val FACTORY_OUTLINE = OceanIcons(
            token = "factoryoutline",
            icon = R.drawable.icon_factoryoutline,
            name = "FACTORY_OUTLINE"
        )
        val EASTER_EGGS = OceanIcons(
            token = "eastereggs",
            icon = R.drawable.icon_easter_eggs,
            name = "EASTER_EGGS"
        )
        val UNDEFINED = OceanIcons(
            token = "",
            icon = R.drawable.ocean_icon_placeholder_solid,
            name = "UNDEFINED"
        )
        val ACQUIRER_PAGBANK = OceanIcons(
            token = "acquirer_pagbank",
            icon = R.drawable.acquirer_pagbank,
            name = "ACQUIRER_PAGBANK"
        )
        val ACQUIRER_PAGSEGURO = OceanIcons(
            token = "acquirer_pagseguro",
            icon = R.drawable.acquirer_pagbank,
            name = "ACQUIRER_PAGSEGURO"
        )
        val ACQUIRER_REDE = OceanIcons(
            token = "acquirer_rede",
            icon = R.drawable.acquirer_rede,
            name = "ACQUIRER_REDE"
        )
        val ACQUIRER_SAFRA = OceanIcons(
            token = "acquirer_safra",
            icon = R.drawable.acquirer_safra,
            name = "ACQUIRER_SAFRA"
        )
        val ACQUIRER_BANCOSAFRA = OceanIcons(
            token = "acquirer_bancosafra",
            icon = R.drawable.acquirer_safra,
            name = "ACQUIRER_BANCOSAFRA"
        )
        val ACQUIRER_SICOOB = OceanIcons(
            token = "acquirer_sicoob",
            icon = R.drawable.acquirer_sicoob,
            name = "ACQUIRER_SICOOB"
        )
        val ACQUIRER_STONE = OceanIcons(
            token = "acquirer_stone",
            icon = R.drawable.acquirer_stone,
            name = "ACQUIRER_STONE"
        )
        val ACQUIRER_SUMUP = OceanIcons(
            token = "acquirer_sumup",
            icon = R.drawable.acquirer_sumup,
            name = "ACQUIRER_SUMUP"
        )
        val ACQUIRER_PAGARME = OceanIcons(
            token = "acquirer_pagarme",
            icon = R.drawable.acquirer_pagarme,
            name = "ACQUIRER_PAGARME"
        )
        val ACQUIRER_MERCADOPAGO = OceanIcons(
            token = "acquirer_mercadopago",
            icon = R.drawable.acquirer_mercadopago,
            name = "ACQUIRER_MERCADOPAGO"
        )
        val ACQUIRER_INFINITEPAY = OceanIcons(
            token = "acquirer_infinitepay",
            icon = R.drawable.acquirer_infinitepay,
            name = "ACQUIRER_INFINITEPAY"
        )
        val ACQUIRER_GETNET = OceanIcons(
            token = "acquirer_getnet",
            icon = R.drawable.acquirer_getnet,
            name = "ACQUIRER_GETNET"
        )
        val ACQUIRER_CIELO = OceanIcons(
            token = "acquirer_cielo",
            icon = R.drawable.acquirer_cielo,
            name = "ACQUIRER_CIELO"
        )
        val ACQUIRER_ASAAS = OceanIcons(
            token = "acquirer_asaas",
            icon = R.drawable.acquirer_asaas,
            name = "ACQUIRER_ASAAS"
        )
        val ACQUIRER_BLU = OceanIcons(
            token = "acquirer_blu",
            icon = R.drawable.acquirer_blu,
            name = "ACQUIRER_BLU"
        )
        val BRAND_AMEX = OceanIcons(
            token = "brand_amex",
            icon = R.drawable.brand_amex,
            name = "BRAND_AMEX"
        )
        val BRAND_BANESCARD = OceanIcons(
            token = "brand_banescard",
            icon = R.drawable.brand_banescard,
            name = "BRAND_BANESCARD"
        )
        val BRAND_CABAL = OceanIcons(
            token = "brand_cabal",
            icon = R.drawable.brand_cabal,
            name = "BRAND_CABAL"
        )
        val BRAND_DINERS = OceanIcons(
            token = "brand_diners",
            icon = R.drawable.brand_diners,
            name = "BRAND_DINERS"
        )
        val BRAND_ELO = OceanIcons(
            token = "brand_elo",
            icon = R.drawable.brand_elo,
            name = "BRAND_ELO"
        )
        val BRAND_MAESTROCARD = OceanIcons(
            token = "brand_maestrocard",
            icon = R.drawable.brand_maestrocard,
            name = "BRAND_MAESTROCARD"
        )
        val BRAND_MASTERCARD = OceanIcons(
            token = "brand_mastercard",
            icon = R.drawable.brand_mastercard,
            name = "BRAND_MASTERCARD"
        )
        val BRAND_PIX = OceanIcons(
            token = "brand_pix",
            icon = R.drawable.brand_pix,
            name = "BRAND_PIX"
        )
        val BRAND_VISA = OceanIcons(
            token = "brand_visa",
            icon = R.drawable.brand_visa,
            name = "BRAND_VISA"
        )
        val SALE_REVERSAL_OUTLINE = OceanIcons(
            token = "sale_reversal_outline",
            icon = R.drawable.sale_reversal_outline,
            name = "SALE_REVERSAL_OUTLINE"
        )
        val LINK_ALT_OUTLINE = OceanIcons(
            token = "linkaltoutline",
            icon = R.drawable.icon_link_alt_outline,
            name = "LINK_ALT_OUTLINE"
        )
        val ARROW_U_TURN_LEFT_OUTLINE = OceanIcons(
            token = "arrowuturnleftoutline",
            icon = R.drawable.icon_arrow_u_turn_left_outline,
            name = "ARROW_U_TURN_LEFT_OUTLINE"
        )
        val NUMBER_ONE_OUTLINE = OceanIcons(
            token = "numberoneoutline",
            icon = R.drawable.icon_number_one_outline,
            name = "NUMBER_ONE_OUTLINE"
        )
        val NUMBER_TWO_OUTLINE = OceanIcons(
            token = "numbertwooutline",
            icon = R.drawable.icon_number_two_outline,
            name = "NUMBER_TWO_OUTLINE"
        )
        val NUMBER_THREE_OUTLINE = OceanIcons(
            token = "numberthreeoutline",
            icon = R.drawable.icon_number_three_outline,
            name = "NUMBER_THREE_OUTLINE"
        )
        val NUMBER_FOUR_OUTLINE = OceanIcons(
            token = "numberfouroutline",
            icon = R.drawable.icon_number_four_outline,
            name = "NUMBER_FOUR_OUTLINE"
        )
        val NUMBER_FIVE_OUTLINE = OceanIcons(
            token = "numberfiveoutline",
            icon = R.drawable.icon_number_five_outline,
            name = "NUMBER_FIVE_OUTLINE"
        )
        val NUMBER_SIX_OUTLINE = OceanIcons(
            token = "numbersixoutline",
            icon = R.drawable.icon_number_six_outline,
            name = "NUMBER_SIX_OUTLINE"
        )
        val NUMBER_SEVEN_OUTLINE = OceanIcons(
            token = "numbersevenoutline",
            icon = R.drawable.icon_number_seven_outline,
            name = "NUMBER_SEVEN_OUTLINE"
        )
        val NUMBER_EIGHT_OUTLINE = OceanIcons(
            token = "numbereightoutline",
            icon = R.drawable.icon_number_eight_outline,
            name = "NUMBER_EIGHT_OUTLINE"
        )
        val NUMBER_NINE_OUTLINE = OceanIcons(
            token = "numbernineoutline",
            icon = R.drawable.icon_number_nine_outline,
            name = "NUMBER_NINE_OUTLINE"
        )
        val RESTART_OUTLINE = OceanIcons(
            token = "restartoutline",
            icon = R.drawable.icon_restart_outline,
            name = "RESTART_OUTLINE"
        )
        val QRCODE_LIST_OUTLINE = OceanIcons(
            token = "qrcodelistoutline",
            icon = R.drawable.ocean_icon_qrcode_list_outline,
            name = "QRCODE_LIST_OUTLINE"
        )
        val QRCODE_PLUS_OUTLINE = OceanIcons(
            token = "qrcodeplusoutline",
            icon = R.drawable.ocean_icon_qr_code_plus_outline,
            name = "QRCODE_PLUS_OUTLINE"
        )
        val COPY_ALT_OUTLINE = OceanIcons(
            token = "copyaltoutline",
            icon = R.drawable.ocean_icon_copy_alt_outline,
            name = "COPY_ALT_OUTLINE"
        )
        val BLU_ANALISYS_OUTLINE = OceanIcons(
            token = "bluanalisysoutline",
            icon = R.drawable.ocean_icon_blu_analisys_outline,
            name = "BLU_ANALISYS_OUTLINE"
        )
        val INFLOW_OUTLINE = OceanIcons(
            token = "inflowoutline",
            icon = R.drawable.icon_inflow_outline,
            name = "INFLOW_OUTLINE"
        )
        val OUTFLOW_OUTLINE = OceanIcons(
            token = "outflowoutline",
            icon = R.drawable.icon_outflow_outline,
            name = "OUTFLOW_OUTLINE"
        )
        val ANNOUNCEMENT_OUTLINE = OceanIcons(
            token = "announcementoutline",
            icon = R.drawable.ocean_icon_announcement_outline,
            name = "ANNOUNCEMENT_OUTLINE"
        )
        val BLU_ANALYSIS_OUTLINE = OceanIcons(
            token = "bluanalysisoutline",
            icon = R.drawable.ocean_icon_blu_analisys_outline,
            name = "BLU_ANALYSIS_OUTLINE"
        )
        val COINS_HAND_SOLID = OceanIcons(
            token = "coinshandsolid",
            icon = R.drawable.ocean_icon_coins_hand_solid,
            name = "COINS_HAND_SOLID"
        )
        val CREDIT_CARD_REFRESH_OUTLINE = OceanIcons(
            token = "creditcardrefreshoutline",
            icon = R.drawable.ocean_icon_credit_card_refresh_outline,
            name = "CREDIT_CARD_REFRESH_OUTLINE"
        )
        val FACEBOOK_OUTLINE = OceanIcons(
            token = "facebookoutline",
            icon = R.drawable.ocean_icon_facebook_outline,
            name = "FACEBOOK_OUTLINE"
        )
        val FACEBOOK_SOLID = OceanIcons(
            token = "facebooksolid",
            icon = R.drawable.ocean_icon_facebook_solid,
            name = "FACEBOOK_SOLID"
        )
        val FLAG_ALT_OUTLINE = OceanIcons(
            token = "flagaltoutline",
            icon = R.drawable.ocean_icon_flag_alt_outline,
            name = "FLAG_ALT_OUTLINE"
        )
        val FLASH_OFF_SOLID = OceanIcons(
            token = "flashoffsolid",
            icon = R.drawable.ocean_icon_flash_off_solid,
            name = "FLASH_OFF_SOLID"
        )
        val LIGHTHOUSE_SOLID = OceanIcons(
            token = "lighthousesolid",
            icon = R.drawable.ocean_icon_lighthouse_solid,
            name = "LIGHTHOUSE_SOLID"
        )
        val LIMIT_OUTLINE = OceanIcons(
            token = "limitoutline",
            icon = R.drawable.ocean_icon_limit_outline,
            name = "LIMIT_OUTLINE"
        )
        val LINKEDIN_OUTLINE = OceanIcons(
            token = "linkedinoutline",
            icon = R.drawable.ocean_icon_linkedin_outline,
            name = "LINKEDIN_OUTLINE"
        )
        val LINKEDIN_SOLID = OceanIcons(
            token = "linkedinsolid",
            icon = R.drawable.ocean_icon_linkedin_solid,
            name = "LINKEDIN_SOLID"
        )
        val LOCK_TAX_OUTLINE = OceanIcons(
            token = "locktaxoutline",
            icon = R.drawable.ocean_icon_lock_tax_outline,
            name = "LOCK_TAX_OUTLINE"
        )
        val MONEY_OUTLINE = OceanIcons(
            token = "moneyoutline",
            icon = R.drawable.ocean_icon_money_outline,
            name = "MONEY_OUTLINE"
        )
        val PRESENTATION_QRCODE_OUTLINE = OceanIcons(
            token = "presentationqrcodeoutline",
            icon = R.drawable.ocean_icon_presentation_qrcode_outline,
            name = "PRESENTATION_QRCODE_OUTLINE"
        )
        val REFER_OUTLINE = OceanIcons(
            token = "referoutline",
            icon = R.drawable.ocean_icon_refer_outline,
            name = "REFER_OUTLINE"
        )
        val REFER_SOLID = OceanIcons(
            token = "refersolid",
            icon = R.drawable.ocean_icon_refer_solid,
            name = "REFER_SOLID"
        )
        val SALES_OUTLINE = OceanIcons(
            token = "salesoutline",
            icon = R.drawable.ocean_icon_sales_outline,
            name = "SALES_OUTLINE"
        )
        val SEND_OUTLINE = OceanIcons(
            token = "sendoutline",
            icon = R.drawable.ocean_icon_send_outline,
            name = "SEND_OUTLINE"
        )
        val SEND_SOLID = OceanIcons(
            token = "sendsolid",
            icon = R.drawable.ocean_icon_send_solid,
            name = "SEND_SOLID"
        )
        val SETTINGS_OUTLINE = OceanIcons(
            token = "settingsoutline",
            icon = R.drawable.ocean_icon_settings_outline,
            name = "SETTINGS_OUTLINE"
        )
        val WALLET_OUTLINE = OceanIcons(
            token = "walletoutline",
            icon = R.drawable.ocean_icon_wallet_outline,
            name = "WALLET_OUTLINE"
        )
        val ZERO_SOLID = OceanIcons(
            token = "zerosolid",
            icon = R.drawable.ocean_icon_zero_solid,
            name = "ZERO_SOLID"
        )
        val BLANK_ICON = OceanIcons(
            token = "localblankicon",
            icon = R.drawable.local_blank_icon,
            name = "BLANK_ICON"
        )

        val entries: List<OceanIcons> by lazy {
            listOf(
                APPAREL_OUTLINE,
                APPLIANCES_OUTLINE,
                ARROW_RIGHT_UP_OUTLINE,
                BACKGROUND_OUTLINE,
                ACADEMIC_CAP_OUTLINE,
                ACADEMIC_CAP_SOLID,
                ADJUSTMENTS_OUTLINE,
                ADJUSTMENTS_OUTLINE_HORIZONTAL,
                ADJUSTMENTS_SOLID,
                ANNOTATION_OUTLINE,
                ANNOTATION_SOLID,
                ARCHIVE_OUTLINE,
                ARCHIVE_SOLID,
                ARROW_CIRCLE_DOWN_OUTLINE,
                ARROW_CIRCLE_DOWN_SOLID,
                ARROW_CIRCLE_LEFT_OUTLINE,
                ARROW_CIRCLE_LEFT_SOLID,
                ARROW_CIRCLE_RIGHT_OUTLINE,
                ARROW_CIRCLE_RIGHT_SOLID,
                ARROW_CIRCLE_UP_OUTLINE,
                ARROW_CIRCLE_UP_SOLID,
                ARROW_DOWN_OUTLINE,
                ARROW_DOWN_SOLID,
                ARROW_LEFT_OUTLINE,
                ARROW_LEFT_SOLID,
                ARROW_NARROW_DOWN_OUTLINE,
                ARROW_NARROW_DOWN_SOLID,
                ARROW_NARROW_LEFT_OUTLINE,
                ARROW_NARROW_LEFT_SOLID,
                ARROW_NARROW_RIGHT_OUTLINE,
                ARROW_NARROW_RIGHT_SOLID,
                ARROW_NARROW_UP_OUTLINE,
                ARROW_NARROW_UP_SOLID,
                ARROW_RIGHT_OUTLINE,
                ARROW_RIGHT_SOLID,
                ARROW_UP_OUTLINE,
                ARROW_UP_SOLID,
                ARROWS_EXPAND_OUTLINE,
                ARROWS_EXPAND_SOLID,
                AT_SYMBOL_OUTLINE,
                AT_SYMBOL_SOLID,
                BACKSPACE_OUTLINE,
                BACKSPACE_SOLID,
                BADGE_CHECK_OUTLINE,
                BADGE_CHECK_SOLID,
                BAN_OUTLINE,
                BAN_SOLID,
                BARCODE_BUBBLE_OUTLINE,
                BARCODE_BUBBLE_SOLID,
                BARCODE_OUTLINE,
                BARCODE_SOLID,
                BEAKER_OUTLINE,
                BEAKER_SOLID,
                BELL_OUTLINE,
                BELL_SOLID,
                BIKE_OUTLINE,
                BLU_LOGO_OUTLINE,
                BMS_OUTLINE,
                BOOK_OPEN_OUTLINE,
                BOOK_OPEN_SOLID,
                BOOKMARK_ALT_OUTLINE,
                BOOKMARK_ALT_SOLID,
                BOOKMARK_OUTLINE,
                BOOKMARK_SOLID,
                BRIEFCASE_OUTLINE,
                BRIEFCASE_SOLID,
                BTOKEN_OUTLINE,
                CAKE_OUTLINE,
                CAKE_SOLID,
                CALCULATOR_OUTLINE,
                CALCULATOR_SOLID,
                CALENDAR_OUTLINE,
                CALENDAR_SOLID,
                CAMERA_OUTLINE,
                CAMERA_SOLID,
                CARD_MACHINE_OUTLINE,
                CASH_BACK_OUTLINE,
                CASH_OUTLINE,
                CASH_SOLID,
                CHARGE_OUTLINE,
                CHARGE_SOLID,
                CHART_BAR_OUTLINE,
                CHART_BAR_SOLID,
                CHART_PIE_OUTLINE,
                CHART_PIE_SOLID,
                CHART_SQUARE_BAR_OUTLINE,
                CHART_SQUARE_BAR_SOLID,
                CHAT_ALT_OUTLINE,
                CHAT_ALT_SOLID,
                CHAT_ALT_TWO_OUTLINE,
                CHAT_ALT_TWO_SOLID,
                CHAT_ALT_THREE_OUTLINE,
                CHAT_OUTLINE,
                CHAT_SOLID,
                CHECK_CIRCLE_OUTLINE,
                CHECK_CIRCLE_SOLID,
                CHECK_OUTLINE,
                CHECK_SOLID,
                CHEVRON_DOUBLE_DOWN_OUTLINE,
                CHEVRON_DOUBLE_DOWN_SOLID,
                CHEVRON_DOUBLE_LEFT_OUTLINE,
                CHEVRON_DOUBLE_LEFT_SOLID,
                CHEVRON_DOUBLE_RIGHT_OUTLINE,
                CHEVRON_DOUBLE_RIGHT_SOLID,
                CHEVRON_DOUBLE_UP_OUTLINE,
                CHEVRON_DOUBLE_UP_SOLID,
                CHEVRON_DOWN_OUTLINE,
                CHEVRON_DOWN_SOLID,
                CHEVRON_LEFT_OUTLINE,
                CHEVRON_LEFT_SOLID,
                CHEVRON_RIGHT_OUTLINE,
                CHEVRON_RIGHT_SOLID,
                CHEVRON_UP_OUTLINE,
                CHEVRON_UP_SOLID,
                CHIP_OUTLINE,
                CHIP_SOLID,
                CLIPBOARD_CHECK_OUTLINE,
                CLIPBOARD_CHECK_SOLID,
                CLIPBOARD_COPY_OUTLINE,
                CLIPBOARD_COPY_SOLID,
                CLIPBOARD_LIST_OUTLINE,
                CLIPBOARD_LIST_SOLID,
                CLIPBOARD_OUTLINE,
                CLIPBOARD_SOLID,
                CLOCK_OUTLINE,
                CLOCK_SOLID,
                CLOUD_DOWNLOAD_OUTLINE,
                CLOUD_DOWNLOAD_SOLID,
                CLOUD_UPLOAD_OUTLINE,
                CLOUD_UPLOAD_SOLID,
                CLOUD_OUTLINE,
                CLOUD_SOLID,
                CODE_OUTLINE,
                CODE_SOLID,
                COG_OUTLINE,
                COG_SOLID,
                COLLECTION_OUTLINE,
                COLLECTION_SOLID,
                COLOR_SWATCH_OUTLINE,
                COLOR_SWATCH_SOLID,
                CONSTRUCTION_MATERIAL_OUTLINE,
                CONSULT_OUTLINE,
                CONTACT_BOOK_OUTLINE,
                CREDIT_CARD_OUTLINE,
                CREDIT_CARD_SOLID,
                CUBE_OUTLINE,
                CUBE_SOLID,
                CUBE_TRANSPARENT_OUTLINE,
                CUBE_TRANSPARENT_SOLID,
                CURRENCY_BANGLADESHI_OUTLINE,
                CURRENCY_BANGLADESHI_SOLID,
                CURRENCY_DOLLAR_OUTLINE,
                CURRENCY_DOLLAR_OUTLINE_LIGHT_DEEP,
                CURRENCY_DOLLAR_SOLID,
                CURRENCY_EURO_OUTLINE,
                CURRENCY_EURO_SOLID,
                CURRENCY_POUND_OUTLINE,
                CURRENCY_POUND_SOLID,
                CURRENCY_RUPEE_OUTLINE,
                CURRENCY_RUPEE_SOLID,
                CURRENCY_YEN_OUTLINE,
                CURRENCY_YEN_SOLID,
                CURSOR_CLICK_OUTLINE,
                CURSOR_CLICK_SOLID,
                DATABASE_OUTLINE,
                DATABASE_SOLID,
                DESKTOP_COMPUTER_OUTLINE,
                DESKTOP_COMPUTER_SOLID,
                DEVICE_MOBILE_OUTLINE,
                DEVICE_MOBILE_SOLID,
                DEVICE_TABLET_OUTLINE,
                DEVICE_TABLET_SOLID,
                DOCUMENT_ADD_OUTLINE,
                DOCUMENT_ADD_SOLID,
                DOCUMENT_DOWNLOAD_OUTLINE,
                DOCUMENT_DOWNLOAD_SOLID,
                DOCUMENT_DUPLICATE_OUTLINE,
                DOCUMENT_DUPLICATE_SOLID,
                DOCUMENT_REMOVE_OUTLINE,
                DOCUMENT_REMOVE_SOLID,
                DOCUMENT_REPORT_OUTLINE,
                DOCUMENT_REPORT_SOLID,
                DOCUMENT_SEARCH_OUTLINE,
                DOCUMENT_SEARCH_OUTLINE_BRAND_DOWN,
                DOCUMENT_SEARCH_SOLID,
                DOCUMENT_TEXT_OUTLINE,
                DOCUMENT_TEXT_SOLID,
                DOCUMENT_OUTLINE,
                DOCUMENT_SOLID,
                DOTS_CIRCLE_HORIZONTAL_OUTLINE,
                DOTS_CIRCLE_HORIZONTAL_SOLID,
                DOTS_HORIZONTAL_OUTLINE,
                DOTS_HORIZONTAL_SOLID,
                DOTS_VERTICAL_OUTLINE,
                DOTS_VERTICAL_SOLID,
                DOWNLOAD_OUTLINE,
                DOWNLOAD_SOLID,
                DUPLICATE_OUTLINE,
                DUPLICATE_SOLID,
                EMOJI_HAPPY_OUTLINE,
                EMOJI_HAPPY_SOLID,
                EMOJI_SAD_OUTLINE,
                EMOJI_SAD_SOLID,
                EXCLAMATION_CIRCLE_OUTLINE,
                EXCLAMATION_CIRCLE_OUTLINE_LIGHT_DEEP,
                EXCLAMATION_CIRCLE_SOLID,
                EXCLAMATION_OUTLINE,
                EXCLAMATION_SOLID,
                EXTERNAL_LINK_OUTLINE,
                EXTERNAL_LINK_SOLID,
                EYEGLASS_STORE_OUTLINE,
                EYE_OFF_OUTLINE,
                EYE_OFF_ALT_OUTLINE,
                EYE_OFF_SOLID,
                EYE_OUTLINE,
                EYE_SOLID,
                FAST_FORWARD_OUTLINE,
                FAST_FORWARD_SOLID,
                FILM_OUTLINE,
                FILM_SOLID,
                FILTER_OUTLINE,
                FILTER_SOLID,
                FINGERPRINT_OUTLINE,
                FINGERPRINT_SOLID,
                FIRE_OUTLINE,
                FIRE_SOLID,
                FLAG_OUTLINE,
                FLAG_SOLID,
                FOLDER_ADD_OUTLINE,
                FOLDER_ADD_SOLID,
                FOLDER_DOWNLOAD_OUTLINE,
                FOLDER_DOWNLOAD_SOLID,
                FOLDER_OPEN_OUTLINE,
                FOLDER_OPEN_SOLID,
                FOLDER_REMOVE_OUTLINE,
                FOLDER_REMOVE_SOLID,
                FOLDER_OUTLINE,
                FOLDER_SOLID,
                FOOT_WARE_OUTLINE,
                GIFT_OUTLINE,
                GIFT_SOLID,
                GLOBE_ALT_OUTLINE,
                GLOBE_ALT_SOLID,
                GLOBE_OUTLINE,
                GLOBE_SOLID,
                HAND_OUTLINE,
                HAND_SOLID,
                HASHTAG_OUTLINE,
                HASHTAG_SOLID,
                HEART_OUTLINE,
                HEART_SOLID,
                HOME_OUTLINE,
                HOME_SOLID,
                IDENTIFICATION_OUTLINE,
                IDENTIFICATION_SOLID,
                INBOX_IN_OUTLINE,
                INBOX_IN_SOLID,
                INBOX_OUTLINE,
                INBOX_SOLID,
                INFORMATION_CIRCLE_OUTLINE,
                INFORMATION_CIRCLE_SOLID,
                KEY_OUTLINE,
                KEY_SOLID,
                LIBRARY_OUTLINE,
                LIBRARY_SOLID,
                LIGHT_BULB_OUTLINE,
                LIGHT_BULB_SOLID,
                LIGHTHOUSE_OUTLINE,
                LIGHTNING_BOLT_OUTLINE,
                LIGHTNING_BOLT_SOLID,
                LINK_OUTLINE,
                LINK_SOLID,
                LOCATION_MARKER_OUTLINE,
                LOCATION_MARKER_SOLID,
                LOCK_CLOSED_OUTLINE,
                LOCK_CLOSED_SOLID,
                LOCK_OPEN_OUTLINE,
                LOCK_OPEN_SOLID,
                LOGIN_OUTLINE,
                LOGIN_SOLID,
                LOGOUT_OUTLINE,
                LOGOUT_SOLID,
                MAIL_OPEN_OUTLINE,
                MAIL_OPEN_SOLID,
                MAIL_OUTLINE,
                MAIL_SOLID,
                MAP_OUTLINE,
                MAP_SOLID,
                MENU_OUTLINE,
                MENU_SOLID,
                MENU_ALT_ONE_OUTLINE,
                MENU_ALT_ONE_SOLID,
                MENU_ALT_TWO_OUTLINE,
                MENU_ALT_TWO_SOLID,
                MENU_ALT_THREE_OUTLINE,
                MENU_ALT_THREE_SOLID,
                MENU_ALT_FOUR_OUTLINE,
                MENU_ALT_FOUR_SOLID,
                MICROPHONE_OUTLINE,
                MICROPHONE_SOLID,
                MINUS_CIRCLE_OUTLINE,
                MINUS_CIRCLE_SOLID,
                MINUS_OUTLINE,
                MINUS_SM_OUTLINE,
                MINUS_SM_SOLID,
                MINUS_SOLID,
                MONEY_INFLOW,
                MONEY_OUTFLOW,
                MOON_OUTLINE,
                MOON_SOLID,
                MUSIC_NOTE_OUTLINE,
                MUSIC_NOTE_SOLID,
                NEWSPAPER_OUTLINE,
                NEWSPAPER_SOLID,
                OFFICE_BUILDING_OUTLINE,
                OFFICE_BUILDING_SOLID,
                PAGBLU_SOLID,
                PAGBLU_OUTLINE,
                PAPER_AIRPLANE_OUTLINE,
                PAPER_AIRPLANE_SOLID,
                PAPER_CLIP_OUTLINE,
                PAPER_CLIP_SOLID,
                PAUSE_OUTLINE,
                PAUSE_SOLID,
                PENCIL_OUTLINE,
                PENCIL_SOLID,
                PENCIL_ALT_OUTLINE,
                PENCIL_ALT_SOLID,
                PERSON_OUTLINE,
                PETSHOP_OUTLINE,
                PHONE_INCOMING_OUTLINE,
                PHONE_INCOMING_SOLID,
                PHONE_MISSED_CALL_OUTLINE,
                PHONE_MISSED_CALL_SOLID,
                PHONE_OUTGOING_OUTLINE,
                PHONE_OUTGOING_SOLID,
                PHONE_OUTLINE,
                PHONE_SOLID,
                PHOTOGRAPH_OUTLINE,
                PHOTOGRAPH_SOLID,
                PLAY_OUTLINE,
                PLAY_SOLID,
                PLUS_CIRCLE_OUTLINE,
                PLUS_CIRCLE_SOLID,
                PLUS_OUTLINE,
                PLUS_SOLID,
                PLUS_SM_OUTLINE,
                PLUS_SM_SOLID,
                PRESENTATION_CHART_BAR_OUTLINE,
                PRESENTATION_CHART_BAR_SOLID,
                PRESENTATION_CHART_LINE_OUTLINE,
                PRESENTATION_CHART_LINE_SOLID,
                PRINTER_OUTLINE,
                PRINTER_SOLID,
                PUZZLE_OUTLINE,
                PUZZLE_SOLID,
                QRCODE_DISPLAY_OUTLINE,
                QRCODE_OUTLINE,
                QRCODE_OUTLINE_HIGHLIGHT,
                QRCODE_SOLID,
                QUESTION_MARK_CIRCLE_OUTLINE,
                QUESTION_MARK_CIRCLE_SOLID,
                RECEIPT_REFUND_OUTLINE,
                RECEIPT_REFUND_SOLID,
                RECEIPT_TAX_OUTLINE,
                RECEIPT_TAX_SOLID,
                REFRESH_OUTLINE,
                REFRESH_SOLID,
                REPLY_OUTLINE,
                REPLY_SOLID,
                REPORT_SOLID,
                REPORT_OUTLINE,
                REWIND_OUTLINE,
                REWIND_SOLID,
                RSS_OUTLINE,
                RSS_SOLID,
                SAVE_AS_OUTLINE,
                SAVE_AS_SOLID,
                SAVE_OUTLINE,
                SAVE_SOLID,
                SCALE_OUTLINE,
                SCALE_SOLID,
                SCISSORS_OUTLINE,
                SCISSORS_SOLID,
                SEARCH_CIRCLE_OUTLINE,
                SEARCH_CIRCLE_SOLID,
                SEARCH_OUTLINE,
                SEARCH_SOLID,
                SELECTOR_OUTLINE,
                SELECTOR_SOLID,
                SERVER_OUTLINE,
                SERVER_SOLID,
                SHARE_OUTLINE,
                SHARE_SOLID,
                SHIELD_CHECK_OUTLINE,
                SHIELD_CHECK_SOLID,
                SHIELD_EXCLAMATION_OUTLINE,
                SHIELD_EXCLAMATION_SOLID,
                SHOPPING_BAG_OUTLINE,
                SHOPPING_BAG_SOLID,
                SHOPPING_CART_OUTLINE,
                SHOPPING_CART_SOLID,
                SORT_ASCENDING_OUTLINE,
                SORT_ASCENDING_SOLID,
                SORT_DESCENDING_OUTLINE,
                SORT_DESCENDING_SOLID,
                SPARKLES_OUTLINE,
                SPARKLES_SOLID,
                SPARKLES_ALT_SOLID,
                SPEAKERPHONE_OUTLINE,
                SPEAKERPHONE_SOLID,
                STAR_OUTLINE,
                STAR_SOLID,
                STATUS_OFFLINE_OUTLINE,
                STATUS_OFFLINE_SOLID,
                STATUS_ONLINE_OUTLINE,
                STATUS_ONLINE_SOLID,
                STOP_OUTLINE,
                STOP_SOLID,
                SUN_OUTLINE,
                SUN_SOLID,
                SUPPORT_OUTLINE,
                SUPPORT_SOLID,
                SWITCH_HORIZONTAL_OUTLINE,
                SWITCH_HORIZONTAL_SOLID,
                SWITCH_VERTICAL_OUTLINE,
                SWITCH_VERTICAL_SOLID,
                TABLE_OUTLINE,
                TABLE_SOLID,
                TAG_OUTLINE,
                TAG_SOLID,
                TEMPLATE_OUTLINE,
                TEMPLATE_SOLID,
                TERMINAL_OUTLINE,
                TERMINAL_SOLID,
                THUMB_DOWN_OUTLINE,
                THUMB_DOWN_SOLID,
                THUMB_UP_OUTLINE,
                THUMB_UP_SOLID,
                TICKET_OUTLINE,
                TICKET_SOLID,
                TRANSACTIONS_OUTLINE,
                TRANSLATE_OUTLINE,
                TRANSLATE_SOLID,
                TRASH_OUTLINE,
                TRASH_SOLID,
                TRENDING_DOWN_OUTLINE,
                TRENDING_DOWN_SOLID,
                TRENDING_UP_OUTLINE,
                TRENDING_UP_SOLID,
                TRUCK_OUTLINE,
                TRUCK_SOLID,
                UPLOAD_OUTLINE,
                UPLOAD_SOLID,
                USER_ADD_OUTLINE,
                USER_ADD_SOLID,
                USER_CIRCLE_OUTLINE,
                USER_CIRCLE_SOLID,
                USER_GROUP_OUTLINE,
                USER_GROUP_SOLID,
                USER_REMOVE_OUTLINE,
                USER_REMOVE_SOLID,
                USER_OUTLINE,
                USER_SOLID,
                USERS_OUTLINE,
                USERS_SOLID,
                VARIABLE_OUTLINE,
                VARIABLE_SOLID,
                VIDEO_CAMERA_OUTLINE,
                VIDEO_CAMERA_SOLID,
                VIEW_BOARDS_OUTLINE,
                VIEW_BOARDS_SOLID,
                VIEW_GRID_ADD_OUTLINE,
                VIEW_GRID_ADD_SOLID,
                VIEW_GRID_OUTLINE,
                VIEW_GRID_SOLID,
                VIEW_LIST_OUTLINE,
                VIEW_LIST_SOLID,
                VOLUME_OFF_OUTLINE,
                VOLUME_OFF_SOLID,
                VOLUME_UP_OUTLINE,
                VOLUME_UP_SOLID,
                WIFI_OUTLINE,
                WIFI_SOLID,
                X_CIRCLE_OUTLINE,
                X_CIRCLE_SOLID,
                X_OUTLINE,
                X_SOLID,
                ZOOM_IN_OUTLINE,
                ZOOM_IN_SOLID,
                ZOOM_OUT_OUTLINE,
                ZOOM_OUT_SOLID,
                DRUGSTORE_OUTLINE,
                EQUAL_OUTLINE,
                EVENTS_OUTLINE,
                EYE_GLASS_STORE_OUTLINE,
                FLASH_OFF_OUTLINE,
                FLASH_ON_OUTLINE,
                FLASH_OFF_ALT_OUTLINE,
                FLASH_ON_ALT_OUTLINE,
                FOOD_AND_BEVERAGE_OUTLINE,
                FOOTWEAR_OUTLINE,
                FURNITURE_OUTLINE,
                GAS_STATION_OUTLINE,
                GLASSES_OUTLINE,
                HELP_OUTLINE,
                INFO_OUTLINE,
                INSTAGRAM_OUTLINE,
                MATTRESS_OUTLINE,
                ORDER_OUTLINE,
                OTHER_OUTLINE,
                PAG_BLU_SOLID,
                PAG_BLU_OUTLINE,
                PAPER_PLANE_OUTLINE,
                PAYMENT_OUTLINE,
                PET_SHOP_OUTLINE,
                PIX_OUTLINE,
                PLACEHOLDER_OUTLINE,
                POINT_UP_OUTLINE,
                QR_CODE_DISPLAY_OUTLINE,
                RAW_MATERIAL_OUTLINE,
                RETAILER_OUTLINE,
                RETAILER_SOLID,
                SCAN_OUTLINE,
                SORT_OUTLINE,
                SUPPLIER_OUTLINE,
                TAX_OUTLINE,
                WARLIKE_OUTLINE,
                WHATSAPP_OUTLINE,
                YOUTUBE_OUTLINE,
                ZERO_OUTLINE,
                ARROW_RIGHT_UP_SOLID,
                BLU_LOGO_SOLID,
                EQUAL_SOLID,
                FLASH_ON_SOLID,
                GLASSES_SOLID,
                HELP_SOLID,
                INFO_SOLID,
                INSTAGRAM_SOLID,
                ORDER_SOLID,
                OTHER_SOLID,
                PAPER_PLANE_SOLID,
                PAYMENT_SOLID,
                PIX_SOLID,
                PLACEHOLDER_SOLID,
                SORT_SOLID,
                WHATSAPP_SOLID,
                YOUTUBE_SOLID,
                QR_CODE_OUTLINE_HIGHLIGHT,
                BARCODE_OUTLINE_HIGHLIGHT,
                MONEY_INFLOW_OUTLINE,
                MONEY_OUTFLOW_OUTLINE,
                SCORE_OUTLINE,
                CONTACTLESS_OUTLINE,
                COINS_HAND_OUTLINE,
                PIGGY_BANK_OUTLINE,
                TARGET_OUTLINE,
                COINS_SWAP_OUTLINE,
                CONTACT_BOOK_OUTLINE_2,
                FACTORY_OUTLINE,
                EASTER_EGGS,
                UNDEFINED,
                ACQUIRER_PAGBANK,
                ACQUIRER_PAGSEGURO,
                ACQUIRER_REDE,
                ACQUIRER_SAFRA,
                ACQUIRER_BANCOSAFRA,
                ACQUIRER_SICOOB,
                ACQUIRER_STONE,
                ACQUIRER_SUMUP,
                ACQUIRER_PAGARME,
                ACQUIRER_MERCADOPAGO,
                ACQUIRER_INFINITEPAY,
                ACQUIRER_GETNET,
                ACQUIRER_CIELO,
                ACQUIRER_ASAAS,
                ACQUIRER_BLU,
                BRAND_AMEX,
                BRAND_BANESCARD,
                BRAND_CABAL,
                BRAND_DINERS,
                BRAND_ELO,
                BRAND_MAESTROCARD,
                BRAND_MASTERCARD,
                BRAND_PIX,
                BRAND_VISA,
                SALE_REVERSAL_OUTLINE,
                LINK_ALT_OUTLINE,
                ARROW_U_TURN_LEFT_OUTLINE,
                NUMBER_ONE_OUTLINE,
                NUMBER_TWO_OUTLINE,
                NUMBER_THREE_OUTLINE,
                NUMBER_FOUR_OUTLINE,
                NUMBER_FIVE_OUTLINE,
                NUMBER_SIX_OUTLINE,
                NUMBER_SEVEN_OUTLINE,
                NUMBER_EIGHT_OUTLINE,
                NUMBER_NINE_OUTLINE,
                RESTART_OUTLINE,
                QRCODE_LIST_OUTLINE,
                QRCODE_PLUS_OUTLINE,
                COPY_ALT_OUTLINE,
                BLU_ANALISYS_OUTLINE,
                INFLOW_OUTLINE,
                OUTFLOW_OUTLINE,
                ANNOUNCEMENT_OUTLINE,
                BLU_ANALYSIS_OUTLINE,
                COINS_HAND_SOLID,
                CREDIT_CARD_REFRESH_OUTLINE,
                FACEBOOK_OUTLINE,
                FACEBOOK_SOLID,
                FLAG_ALT_OUTLINE,
                FLASH_OFF_SOLID,
                LIGHTHOUSE_SOLID,
                LIMIT_OUTLINE,
                LINKEDIN_OUTLINE,
                LINKEDIN_SOLID,
                LOCK_TAX_OUTLINE,
                MONEY_OUTLINE,
                PRESENTATION_QRCODE_OUTLINE,
                REFER_OUTLINE,
                REFER_SOLID,
                SALES_OUTLINE,
                SEND_OUTLINE,
                SEND_SOLID,
                SETTINGS_OUTLINE,
                WALLET_OUTLINE,
                ZERO_SOLID,
                BLANK_ICON
            )
        }

        fun fromToken(token: String): OceanIcons {
            val newToken = token.trim().replace("-", "")
            return entries.firstOrNull { it.token.equals(newToken, ignoreCase = true) }
                ?: OceanIconRegistry.provider?.invoke(newToken.lowercase())
                ?: UNDEFINED
        }
    }
}

object OceanIconRegistry {
    /** Ícones que não são do Ocean (ex.: Product Icons do app). Consultado só quando o token não é do Ocean. Recebe o token normalizado (sem espaços, sem "-", minúsculo). */
    @Volatile
    var provider: ((String) -> OceanIcons?)? = null
}
