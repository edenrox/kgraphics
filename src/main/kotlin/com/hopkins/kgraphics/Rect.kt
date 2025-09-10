package com.hopkins.kgraphics

data class Rect(val left: Int, val top: Int, val right: Int, val bottom: Int) {
    val height: Int
        get() = bottom - top

    val width: Int
        get() = right - left
}