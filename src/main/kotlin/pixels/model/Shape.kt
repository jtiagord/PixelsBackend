package pixels.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonDiscriminator
import org.bson.codecs.pojo.annotations.BsonProperty
import kotlin.reflect.KClass

enum class ShapeType(val type: String){
    CIRCLE("CIRCLE"),
    LINE("LINE"),
    FREEDRAW("FREE"),
    RECTANGLE("RECT"),
}
@BsonDiscriminator(key = "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = Rectangle::class, name = "RECT"),
    JsonSubTypes.Type(value = Circle::class, name = "CIRCLE"),
    JsonSubTypes.Type(value = FreeDraw::class, name = "FREE"),
    JsonSubTypes.Type(value = Line::class, name = "LINE"),
)
abstract class Shape(val type : ShapeType)

@BsonDiscriminator(key= "type", value = "LINE")
data class Line @BsonCreator constructor  (
@BsonProperty("pos1") val pos1 : Position,
@BsonProperty("pos2") val pos2 : Position,
) : Shape(ShapeType.LINE)

data class  Position @BsonCreator constructor (
    @BsonProperty("x")  val x : Double,
    @BsonProperty("y")  val y : Double)

@BsonDiscriminator(key= "type", value =  "FREE")
data class FreeDraw  (
    val positions : List<Position> = emptyList()
) : Shape(ShapeType.FREEDRAW)

@BsonDiscriminator(key= "type", value =  "CIRCLE")
class Circle  (
    val centerPos : Position,
    val radius : Double
) : Shape(ShapeType.CIRCLE)

@BsonDiscriminator(key= "type", value =  "RECT")
data class Rectangle  (
    val pos1 : Position,
    val pos2 : Position,
) : Shape(ShapeType.RECTANGLE)