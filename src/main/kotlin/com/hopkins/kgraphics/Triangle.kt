package com.hopkins.kgraphics

import kotlin.math.max
import kotlin.math.min

data class Triangle(val p1: Vector2i, val p2: Vector2i, val p3: Vector2i) {
    val bounds: Rect
        get() = Rect(
            min(min(p1.x, p2.x), p3.x),
            min(min(p1.y, p2.y), p3.y),
            max(max(p1.x, p2.x), p3.x),
            max(max(p1.y, p2.y), p3.y),
        )
}