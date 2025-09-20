package com.hopkins.kgraphics

import kotlin.math.max
import kotlin.math.min

data class Triangle(val p0: Vector2i, val p1: Vector2i, val p2: Vector2i) {
    constructor(x0: Int, y0: Int, x1: Int, y1: Int, x2: Int, y2: Int) :
            this(Vector2i(x0, y0), Vector2i(x1, y1), Vector2i(x2, y2))

    val points: List<Vector2i>
        get() = listOf(p0, p1, p2)

    val bounds: Rect
        get() = Rect(
            min(min(p0.x, p1.x), p2.x),
            min(min(p0.y, p1.y), p2.y),
            max(max(p0.x, p1.x), p2.x),
            max(max(p0.y, p1.y), p2.y),
        )
    val signedArea: Float
        get() = signedArea(p0.x, p0.y, p1.x, p1.y, p2.x, p2.y)

    companion object {
        fun signedArea(x0: Int, y0: Int, x1: Int, y1: Int, x2: Int, y2: Int): Float {
            return ((y1 - y0) * (x1 + x0) + (y2 - y1) * (x2 + x1) + (y0 - y2) * (x0 + x2)) / 2f
        }
    }
}