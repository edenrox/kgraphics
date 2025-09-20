package com.hopkins.kgraphics.lessons

import com.hopkins.kgraphics.ColorInt
import com.hopkins.kgraphics.FrameBuffer
import com.hopkins.kgraphics.ImageWriter
import com.hopkins.kgraphics.Triangle
import java.io.File

fun main() {
    // Lesson 2b from: https://haqr.eu/tinyrenderer/rasterization/

    val width = 128;
    val height = 128;
    val buffer = FrameBuffer(width, height)

    val t1 = Triangle(7,45,35,100,45,60)
    val t2 = Triangle(120,35,90,5,45,110)
    val t3 = Triangle(115, 83, 80, 90, 85, 120)

    // Bounding boxes
    if (false) {
        buffer.drawTriangleBoundingBox(t1, ColorInt.RED)
        buffer.drawTriangleBoundingBox(t2, ColorInt.WHITE)
        buffer.drawTriangleBoundingBox(t3, ColorInt.GREEN)
    }

    // Triangles
    buffer.drawTriangle(t1, ColorInt.RED)
    buffer.drawTriangle(t2, ColorInt.WHITE)
    buffer.drawTriangle(t3, ColorInt.GREEN)

    val outputFile = File("lesson2b-output.png")
    ImageWriter.write(buffer, outputFile)
}

private fun FrameBuffer.drawTriangleBoundingBox(triangle: Triangle, color: ColorInt) {
    val bounds = triangle.bounds
    for (y in bounds.top .. bounds.bottom) {
        for (x in bounds.left .. bounds.right) {
            setPixel(x, y, color.value)
        }
    }
}