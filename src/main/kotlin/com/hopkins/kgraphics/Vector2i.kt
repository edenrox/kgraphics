package com.hopkins.kgraphics

data class Vector2i(val x: Int, val y: Int) {
    override fun toString(): String = 
        "[$x, $y]"

    companion object {
        val ORIGIN = Vector2i(0, 0)
    }
}