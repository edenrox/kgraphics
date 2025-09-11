package com.hopkins.kgraphics

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object ImageWriter {

    fun write(buffer: FrameBuffer, outputFile: File) {
        val image = BufferedImage(buffer.width, buffer.height, BufferedImage.TYPE_INT_ARGB)

        image.raster.setDataElements(0, 0, image.width, image.height, buffer.data)

        ImageIO.write(image, "png", outputFile)
    }
}