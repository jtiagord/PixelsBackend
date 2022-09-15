package pixels.codecs

import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import pixels.model.Shape
import pixels.model.ShapeType

class ShapeCodec : Codec<Shape> {
    override fun encode(writer: BsonWriter, value: Shape, encoderContext: EncoderContext) {
        TODO("Not yet implemented")
    }

    override fun getEncoderClass(): Class<Shape> {
        TODO("Not yet implemented")
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): Shape {
        val type = reader.readString("type")

        var typeEnum : ShapeType
        for(shapeType in ShapeType.values()){
            if(type == shapeType.type) typeEnum = shapeType
        }

        when(shapeType){

        }
    }
}