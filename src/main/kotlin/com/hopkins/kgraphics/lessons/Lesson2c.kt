package com.hopkins.kgraphics.lessons

import com.hopkins.kgraphics.ColorInt
import com.hopkins.kgraphics.FrameBuffer
import com.hopkins.kgraphics.ImageWriter
import com.hopkins.kgraphics.ObjModel
import com.hopkins.kgraphics.Triangle
import java.io.File

fun main() {
    // Lesson 2c from: https://haqr.eu/tinyrenderer/rasterization/

    val width = 800
    val height = 800
    val buffer = FrameBuffer(width, height)

    val modelFile = File("models/african_head.obj")
    val model = modelFile.bufferedReader().use {
        ObjModel.Parser().parse(it)
    }

    for (face in model.faces) {
        if (face.size == 3) {
            val p0 = model.vertices[face[0] - 1]
            val p1 = model.vertices[face[1] - 1]
            val p2 = model.vertices[face[2] - 1]
            val color = ColorInt.randomColor()
            val t = Triangle(
                p0.toScreenVector2i(width, height),
                p1.toScreenVector2i(width, height),
                p2.toScreenVector2i(width, height))
            if (t.signedArea > 0) {
                buffer.drawTriangle(t, color)
            }
        }
    }

    ImageWriter.write(buffer, File("lesson2c-output.png"))
}