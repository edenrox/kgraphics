package com.hopkins.kgraphics

data class Vector3f(val x: Float, val y: Float, val z: Float) {
    override fun toString(): String =
        "[$x, $y, $z]"

    fun toScreenVector2i(width: Int, height: Int): Vector2i {
        return Vector2i(
            normalize(x, width.toFloat()).toInt(),
            normalize(y, height.toFloat()).toInt())
    }

    /** Convert a value from the range [-1f, 1f] to the range [0, scale) */
    private fun normalize(value: Float, scale: Float): Float =
         (value + 1f) / 2f * (scale - 1)
}