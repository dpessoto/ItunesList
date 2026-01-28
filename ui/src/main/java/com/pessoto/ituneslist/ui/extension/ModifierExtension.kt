package com.pessoto.ituneslist.ui.extension

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.pessoto.ituneslist.ui.theme.CornerShape
import com.pessoto.ituneslist.ui.theme.colorGray400

fun Modifier.shimmerEffect(shape: Shape = CornerShape.SmallCornerShape): Modifier =
    composed {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }
        val transition = rememberInfiniteTransition(label = "")
        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec =
                infiniteRepeatable(
                    animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
                ),
            label = "",
        )

        background(
            brush =
                Brush.linearGradient(
                    colors =
                        listOf(
                            colorGray400.copy(alpha = 0.5f),
                            colorGray400,
                            colorGray400.copy(alpha = 0.3f),
                        ),
                    start = Offset(startOffsetX, 0f),
                    end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat()),
                ),
            shape = shape,
        ).onGloballyPositioned {
            size = it.size
        }
    }
