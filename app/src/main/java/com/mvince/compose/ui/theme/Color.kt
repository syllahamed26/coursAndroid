package com.mvince.compose.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import kotlin.math.absoluteValue

val Green700 = Color(0xFF000000)
val GreyLink = Color(0xFF000000)
val wrong = Color(0xFFF44336)
val good = Color(0xFF4CAF50)
val GreyLight = Color(0xFF646664)
val SoftWhite = Color(0xC4FFFFFF)
val PrimaryGradiant = Color(0xFF000000)
val SecondaryGradiant = Color(0xFF073ABB)



@ColorInt
fun String.toHslColor(saturation: Float = 0.5f, lightness: Float = 0.4f): Int {
    val hue = fold(0) { acc, char -> char.code + acc * 37 } % 360
    return ColorUtils.HSLToColor(floatArrayOf(hue.absoluteValue.toFloat(), saturation, lightness))
}

