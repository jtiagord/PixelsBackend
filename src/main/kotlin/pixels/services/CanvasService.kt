package pixels.services

import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Updates
import org.springframework.stereotype.Component


import org.bson.types.ObjectId
import pixels.model.*
import pixels.utils.toBase64URLtoHex


@Component
class CanvasService(database: MongoDatabase){
    companion object{
        const val COLLECTION_NAME = "canvas"
    }
    val collection = database.getCollection(COLLECTION_NAME,Canvas::class.java)

    fun getCanvas(id : String) : Canvas? {
        val decodedId = id.toBase64URLtoHex()
        if(!ObjectId.isValid(decodedId)) return null
        return collection.find(eq("_id",ObjectId(decodedId))).first()

    }

    fun createCanvas(c: Canvas) : Canvas?{
        val result = collection.insertOne(c)
        val id = result.insertedId
        return if (id == null) null else Canvas(id.asObjectId().value,c.width, c.height)
    }

    fun addLine(canvasId : String, shape : Shape) : Boolean{
        val id = canvasId.toBase64URLtoHex()
        val update = Updates.push("shapes", shape)
        val result = collection.updateOne(eq("_id",ObjectId(id)), update)
        return result.wasAcknowledged()
    }
}

