package com.hopkins.kgraphics

import kotlin.math.max
import kotlin.math.min

data class Triangle(val p1: Vector2i, val p2: Vector2i, val p3: Vector2i) {
    constructor(x0: Int, y0: Int, x1: Int, y1: Int, x2: Int, y2: Int) :
            this(Vector2i(x0, y0), Vector2i(x1, y1), Vector2i(x2, y2))

    val points: List<Vector2i>
        get() = listOf(p1, p2, p3)

    val bounds: Rect
        get() = Rect(
            min(min(p1.x, p2.x), p3.x),
            min(min(p1.y, p2.y), p3.y),
            max(max(p1.x, p2.x), p3.x),
            max(max(p1.y, p2.y), p3.y),
        )
}