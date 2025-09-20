package com.hopkins.kgraphics

import java.io.Reader

class ObjModel(
    val vertices: List<Vector3f>,
    val faces: List<List<Int>>) {

    class Parser() {
        fun parse(input: Reader): ObjModel {
            val lines = input.readLines()
            val vertices = lines.filter { it.startsWith("v ")}.map { parseVertex3f(it) }
            val faces = lines.filter { it.startsWith("f ")}.map { parseFace(it) }
            return ObjModel(vertices, faces)
        }

        private fun parseVertex3f(line: String): Vector3f {
            val points = line.drop(1).trim().split(WHITE_SPACE).map { it.toFloat() }
            return Vector3f(points[0], points[1], points[2])
        }

        private fun parseFace(line: String): List<Int> {
            return line.drop(1).trim().split(WHITE_SPACE).map { it.split("/")[0].toInt()}
        }
    }

    companion object {
        val WHITE_SPACE = "\\s+".toRegex()
    }
}