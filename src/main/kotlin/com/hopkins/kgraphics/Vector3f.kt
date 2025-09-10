package com.hopkins.kgraphics

data class Vector3f(val x: Float, val y: Float, val z: Float) {
    override fun toString(): String =
        "[$x, $y, $z]"
}