package pixels.model

import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId
import java.util.*


class Canvas @BsonCreator constructor(
    @BsonProperty("_id")  var _id : ObjectId,
    @BsonProperty("width")  val width : Double,
    @BsonProperty("height") val height : Double,
    @BsonProperty("shapes")  val shapes : List<Shape> = emptyList()
)

class CanvasOutputModel(val id : String, val width : Double, val height : Double, val shapes : List<Shape> = emptyList())

fun Canvas.toCanvasOutputModel() : CanvasOutputModel = CanvasOutputModel(
            Base64.getUrlEncoder().encodeToString(_id.toByteArray()),
            width, height, shapes)

data class CanvasInputModel(val width : Double,val height : Double)

fun CanvasInputModel.toCanvas() : Canvas = Canvas(ObjectId(), width, height)


