package pixels.model

import kotlin.reflect.KClass

enum class ShapeType(val type: String){
    CIRCLE("CIRCLE"),
    LINE("LINE"),
    FREEDRAW("FREE"),
    RECTANGLE("RECT"),
}

open class Shape(val type : ShapeType)

data class Line  (
    val pos1 : Position,
    val pos2 : Position,
) : Shape(ShapeType.LINE)

data class Position(val x : Double, val y :Double)

data class FreeDraw  (
    val  positions : List<Position>
) : Shape(ShapeType.FREEDRAW)

data class Circle  (
    val startPos : Position,
    val radius : Double
) : Shape(ShapeType.CIRCLE)

data class Square  (
    val pos1 : Position,
    val pos2 : Position,
) : Shape(ShapeType.RECTANGLE)