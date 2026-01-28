package com.pessoto.ituneslist.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

val DefaultContentPadding = PaddingValues(
    start = Padding.Horizontal,
    top = Padding.Vertical,
    end = Padding.Horizontal,
    bottom = Padding.Vertical,
)

object Padding {
    val Horizontal = 16.dp
    val Vertical = 16.dp
}

object Spacing {
    val Mini = 4.dp
    val Small = 8.dp
    val Medium = 16.dp
}

object CornerShape {
    val SmallCornerShape = RoundedCornerShape(8.dp)
}

object Elevation {
    val Tiny = 2.dp
}
