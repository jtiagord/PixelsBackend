package pixels


import com.mongodb.MongoClientSettings.getDefaultCodecRegistry
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@SpringBootApplication
class PixelsApplication

@Configuration
class AppConfig {
	@Bean
	fun mongoDB() : MongoDatabase{
		val uri = "mongodb://bitas:123@localhost:27018"
		val pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build()
		val pojoCodecRegistry: CodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider))
		val mongoClient = MongoClients.create(uri)

		return mongoClient.getDatabase("pixelsDB").withCodecRegistry(pojoCodecRegistry)
	}
}

fun main(args: Array<String>) {
	runApplication<PixelsApplication>(*args)
}
