package pixels


import com.mongodb.MongoClientSettings.getDefaultCodecRegistry
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecRegistries.*
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.ClassModel
import org.bson.codecs.pojo.PojoCodecProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pixels.model.Circle
import pixels.model.Line
import pixels.model.Rectangle
import pixels.model.Shape


@SpringBootApplication
class PixelsApplication

@Configuration
class AppConfig {
	@Bean
	fun mongoDB() : MongoDatabase{
		//TODO add connectionString to environment variable
		val uri = "mongodb://bitas:123@localhost:27018"
		val pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build()
		val shapeModel: ClassModel<Shape> = ClassModel.builder(Shape::class.java)
			.enableDiscriminator(true).build()
		val rectangleModel: ClassModel<Rectangle> =
			ClassModel.builder(Rectangle::class.java).enableDiscriminator(true).build()
		val circleModel: ClassModel<Circle> =
			ClassModel.builder(Circle::class.java).enableDiscriminator(true).build()
		val lineModel = ClassModel.builder(Line::class.java).enableDiscriminator(true).build()
		val discProvider =
			PojoCodecProvider.builder().register(shapeModel, rectangleModel, circleModel,lineModel).build();
		var pojoCodecRegistry: CodecRegistry = fromRegistries(getDefaultCodecRegistry(),
			fromProviders(discProvider,pojoCodecProvider))
		//pojoCodecRegistry = fromRegistries(fromCodecs(ShapeCodec(pojoCodecRegistry)),pojoCodecRegistry)

		val mongoClient = MongoClients.create(uri)

		return mongoClient.getDatabase("pixelsDB").withCodecRegistry(pojoCodecRegistry)
	}
}

fun main(args: Array<String>) {
	runApplication<PixelsApplication>(*args)
}
