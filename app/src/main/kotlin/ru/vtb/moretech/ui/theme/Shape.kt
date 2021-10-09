package ru.vtb.moretech.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

class Message() : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = drawMessage(size))
    }

}

fun drawMessage(size: Size): Path {

    val cornerRadius: Float = 50f*7/5f

    return Path().apply {
        arcTo(
            rect = Rect(
                left = 0f,
                top = 0f,
                right = cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 180.0f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )
        lineTo(size.width - cornerRadius, 0f)
        // Top right arc
        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = 0f,
                right = size.width,
                bottom = cornerRadius
            ),
            startAngleDegrees = -90.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )
        lineTo(x = size.width, y = size.height - cornerRadius - 50f)
        // Bottom right arc
        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = size.height - cornerRadius- 50f,
                right = size.width,
                bottom = size.height- 50f
            ),
            startAngleDegrees = 0.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )
        lineTo(x = size.width/3.5f, y = size.height-50f)
        lineTo(x = size.width/3.2f - cornerRadius, size.height)
        lineTo(x = size.width/2.5f - cornerRadius*2, size.height-50f)
        // Bottom left arc
        arcTo(
            rect = Rect(
                left = 0f,
                top = size.height- 50f - cornerRadius,
                right = cornerRadius,
                bottom = size.height- 50f
            ),
            startAngleDegrees = 90.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )
        lineTo(x = 0f, y = cornerRadius)
        close()
    }
}