package com.hopkins.kgraphics.lessons

import com.hopkins.kgraphics.ColorInt
import com.hopkins.kgraphics.FrameBuffer
import com.hopkins.kgraphics.ImageWriter
import com.hopkins.kgraphics.ObjModel
import java.io.File

fun main(args: Array<String>) {

    val modelFile = File("models/african_head.obj")
    println("Model File")
    println("- path: ${modelFile.absolutePath}")
    println("- size: ${modelFile.length()}")

    val model = modelFile.bufferedReader().use {
        ObjModel.Parser().parse(it)
    }
    println("Model")
    println(" - vertices: ${model.vertices.size}")
    println(" - faces: ${model.faces.size}")
    val xRange = findRange(model.vertices.map { it.x })
    val yRange = findRange(model.vertices.map { it.y })
    println(" - x range: $xRange")
    println(" - y range: $yRange")

    val width: Int = 800
    val height: Int = 800
    val buffer = FrameBuffer(width, height)

    for (face in model.faces) {
        for (i0 in 0 until 3) {
            val i1 = (i0 + 1) % 3
            val p0 = model.vertices[face[i0] - 1]
            val p1 = model.vertices[face[i1] - 1]
            buffer.drawLine(
                p0.toScreenVector2i(width, height),
                p1.toScreenVector2i(width, height),
                ColorInt.WHITE)
        }
    }


    ImageWriter.write(buffer, File("lesson1c-output.png"))
}

private fun findRange(input: List<Float>): Pair<Float, Float> {
    return input.min() to input.max()
}