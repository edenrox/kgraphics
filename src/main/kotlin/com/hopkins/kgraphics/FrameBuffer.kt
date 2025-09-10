package com.hopkins.kgraphics

class FrameBuffer(val width: Int, val height: Int) {
    val data = IntArray(width * height)

    fun getPixel(x: Int, y: Int): Int {
        return data[y * width + x]
    }

    fun getPixelColor(point: Vector2i): ColorInt {
        return ColorInt(getPixel(point.x, point.y))
    }

    fun setPixel(x: Int, y: Int, color: Int) {
        data[y * width + x] = color
    }

    fun setPixel(point: Vector2i, color: ColorInt) {
        return setPixel(point.x, point.y, color.value)
    }
}