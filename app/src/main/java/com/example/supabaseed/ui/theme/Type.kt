package com.example.supabaseed.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.supabaseed.R

val LexendDeca = FontFamily(
    Font(R.font.lexend_deca),
    Font(R.font.lexend_deca_medium, FontWeight.Medium)
)

val Cabin = FontFamily(
    Font(R.font.cabin),
    Font(R.font.cabin_medium, FontWeight.Medium),
    Font(R.font.cabin_semibold, FontWeight.SemiBold),
    Font(R.font.cabin_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = LexendDeca,
        fontWeight = FontWeight.Medium,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    titleLarge = Typography().titleLarge.copy(fontFamily = Cabin),
    titleMedium = Typography().titleMedium.copy(fontFamily = Cabin),
    titleSmall = Typography().titleSmall.copy(fontFamily = Cabin),

    labelLarge = Typography().labelLarge.copy(fontFamily = Cabin),
    labelMedium = Typography().labelMedium.copy(fontFamily = Cabin),
    labelSmall = Typography().labelSmall.copy(fontFamily = Cabin),

    bodyLarge = Typography().bodyLarge.copy(fontFamily = Cabin),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = Cabin),
    bodySmall = Typography().bodySmall.copy(fontFamily = Cabin),
)