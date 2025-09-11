package com.hopkins.kgraphics

data class ColorInt(val value: Int) {
    val alpha: Int
        get() = value shr 24 and 0xFF

    val red: Int
        get() = (value shr 16) and 0xFF

    val green: Int
        get() = (value shr 8) and 0xFF

    val blue: Int
        get() = value and 0xFF

    companion object {
        val TRANSPARENT = ofRgba(0, 0, 0, 0)
        val BLACK = ofRgba(0, 0, 0, 255)
        val WHITE = ofRgba(255, 255, 255, 255)
        val RED = ofRgba(255, 0, 0, 255)
        val GREEN = ofRgba(0, 255, 0, 255)
        val BLUE = ofRgba(0, 0, 255, 255)
        val YELLOW = ofRgba(255, 200, 0, 255)

        fun ofRgba(red: Int, green: Int, blue: Int, alpha: Int): ColorInt {
            // TYPE_INT_ARGB
            val value: Int = ((alpha and 0xFF) shl 24) or
                    ((red and 0xFF) shl 16) or
                    ((green and 0xFF) shl 8) or
                    (blue and 0xFF)
            return ColorInt(value)
        }
    }
}