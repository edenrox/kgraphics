package com.hopkins.kgraphics.lessons

import com.hopkins.kgraphics.ColorInt
import com.hopkins.kgraphics.FrameBuffer
import com.hopkins.kgraphics.ImageWriter
import com.hopkins.kgraphics.Vector2i
import java.io.File
import kotlin.math.roundToInt

fun main(args: Array<String>) {
    // Lesson 1a from: https://haqr.eu/tinyrenderer/bresenham

    val width = 64
    val height = 64
    val buffer = FrameBuffer(width, height)

    val a = Vector2i(7, 3)
    val b = Vector2i(12, 37)
    val c = Vector2i(62, 53)

    buffer.drawLineSimple(a, b, ColorInt.BLUE)
    buffer.drawLineSimple(c, b, ColorInt.GREEN)
    buffer.drawLineSimple(c, a, ColorInt.YELLOW)
    buffer.drawLineSimple(a, c, ColorInt.RED)

    buffer.setPixel(a, ColorInt.WHITE)
    buffer.setPixel(b, ColorInt.WHITE)
    buffer.setPixel(c, ColorInt.WHITE)


    val outputFile = File("lesson1a-output.png")
    ImageWriter.write(buffer, outputFile)
}

private fun FrameBuffer.drawLineSimple(p1: Vector2i, p2: Vector2i, color: ColorInt) {
    var t = 0f
    val dx = p2.x - p1.x
    val dy = p2.y - p1.y
    while (t < 1f) {
        val cx = (p1.x + dx * t).roundToInt()
        val cy = (p1.y + dy * t).roundToInt()
        setPixel(cx, cy, color.value)
        t += 0.02f
    }
}