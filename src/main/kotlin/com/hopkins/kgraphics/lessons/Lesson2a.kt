package com.hopkins.kgraphics.lessons

import com.hopkins.kgraphics.ColorInt
import com.hopkins.kgraphics.FrameBuffer
import com.hopkins.kgraphics.ImageWriter
import com.hopkins.kgraphics.Triangle
import com.hopkins.kgraphics.Vector2i
import java.io.File
import java.util.Vector
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

fun main() {
    // Lesson 2a from: https://haqr.eu/tinyrenderer/rasterization/
    val width = 128;
    val height = 128;
    val buffer = FrameBuffer(width, height)

    val t1 = Triangle(7,45,35,100,45,60)
    val t2 = Triangle(120,35,90,5,45,110)
    val t3 = Triangle(115, 83, 80, 90, 85, 120)

    buffer.scanLineTriangle(t1, ColorInt.RED)
    buffer.scanLineTriangle(t2, ColorInt.WHITE)
    buffer.scanLineTriangle(t3, ColorInt.GREEN)

    if (false) {
        // For triangle rendering testing only
        renderTestTriangleCircle(buffer)
    }

    val outputFile = File("lesson2a-output.png")
    ImageWriter.write(buffer, outputFile)
}

private fun renderTestTriangleCircle(buffer: FrameBuffer) {
    // Test triangles (in a circle
    val origin = Vector2i(64, 64)
    val radius = 60

    val colors = listOf(
        ColorInt.ofRgba(0, 0, 255, 255),
        ColorInt.ofRgba(0, 0, 213, 255),
        ColorInt.ofRgba(0, 0, 171, 255),
        ColorInt.ofRgba(0, 0, 128, 255),
        ColorInt.ofRgba(0, 0, 85, 255),
        ColorInt.ofRgba(0, 0, 42, 255),
    )
    var angle = 0.0
    val angleInc = 2 * PI / 24
    var index = 0
    var p1 = Vector2i(origin.x + radius, origin.y)
    while (angle < 2 * PI) {
        angle += angleInc
        val p2 = p1
        val dx = (cos(angle) * radius).roundToInt()
        val dy = (sin(angle) * radius).roundToInt()
        p1 = Vector2i(origin.x + dx, origin.y + dy)
        val t = Triangle(origin, p1, p2)
        buffer.scanLineTriangle(t, colors[index % colors.size])

        index += 1
    }
}

private fun FrameBuffer.scanLineTriangle(t: Triangle, color: ColorInt) {
    val bounds = t.bounds
    val numDistinct = t.points.map { it.y }.distinct().count()
    if (numDistinct == 1) {
        drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, color.value)
    } else {
        scanLineSimpleTriangle(t, color)
    }
}

private fun FrameBuffer.scanLineSimpleTriangle(t: Triangle, color: ColorInt) {
    val p = t.points.sortedBy { it.y }
    val top = p[0]
    val middle = p[1]
    val bottom = p[2]

    val sTopBottom = (top.x - bottom.x).toFloat() / (top.y - bottom.y)
    val sTopMiddle = (top.x - middle.x).toFloat() / (top.y - middle.y)
    val sMiddleBottom = (middle.x - bottom.x).toFloat() / (middle.y - bottom.y)


    for (y in top.y .. bottom.y) {
        val dyTop = y - top.y
        val x1 = top.x + (sTopBottom * dyTop).roundToInt()
        val x2 =
            if (y <= middle.y && !sTopMiddle.isInfinite()) {
                top.x + (sTopMiddle * dyTop).roundToInt()
            } else {
                val dyMiddle = y - middle.y
                middle.x + (sMiddleBottom * dyMiddle).roundToInt()
            }
        drawLine(x1, y, x2, y, color.value)
    }
}