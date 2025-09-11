package com.hopkins.kgraphics.lessons

import com.hopkins.kgraphics.ColorInt
import com.hopkins.kgraphics.FrameBuffer
import com.hopkins.kgraphics.ImageWriter
import com.hopkins.kgraphics.Vector2i
import java.io.File

fun main() {
    // Lesson 0 from: https://haqr.eu/tinyrenderer/

    val width = 64
    val height = 64
    val buffer = FrameBuffer(width, height)

    val a = Vector2i(7, 3)
    val b = Vector2i(12, 37)
    val c = Vector2i(62, 53)

    buffer.setPixel(a, ColorInt.WHITE)
    buffer.setPixel(b, ColorInt.WHITE)
    buffer.setPixel(c, ColorInt.WHITE)


    val outputFile = File("lesson0-output.png")
    ImageWriter.write(buffer, outputFile)
}