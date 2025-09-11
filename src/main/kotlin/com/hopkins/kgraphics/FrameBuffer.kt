package com.hopkins.kgraphics

import kotlin.math.abs

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

    fun fillRect(rect: Rect, color: ColorInt) {
        for (y in rect.top until rect.bottom) {
            for (x in rect.left until rect.right) {
                setPixel(x, y, color.value)
            }
        }
    }

    fun drawLine(x0: Int, y0: Int, x1: Int, y1: Int, color: Int) {
        var cx = x0
        var cy = y0

        val dx = abs(x1 - x0)
        val dy = abs(y1 - y0)

        val sx = if (x0 < x1) 1 else -1
        val sy = if (y0 < y1) 1 else -1

        var err = dx - dy

        while (true) {
            setPixel(cx, cy, color)

            if (cx == x1 && cy == y1) {
                return
            }

            val e2 = err * 2
            if (e2 > -dy) {
                err -= dy
                cx += sx
            }
            if (e2 < dx) {
                err += dx
                cy += sy
            }
        }
    }

    fun drawLine(p0: Vector2i, p1: Vector2i, color: ColorInt) {
        drawLine(p0.x, p0.y, p1.x, p1.y, color.value)
    }
}