package io.mattmusc.web.resource

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.mattmusc.domain.location.api.CoordinateDto

@JsonSerialize(using = CoordinateSerializer::class)
@JsonDeserialize(using = CoordinateDeserializer::class)
data class CoordinateType(
		val latitude: Double,
		val longitude: Double)
{
	companion object {

		fun fromDto(dto: CoordinateDto): CoordinateType = CoordinateType(
				latitude = dto.latitude,
				longitude = dto.longitude
		)

	}
}

class CoordinateSerializer : JsonSerializer<CoordinateType>() {

	override fun serialize(
			value: CoordinateType?,
			gen: JsonGenerator?,
			serializers: SerializerProvider?) {
		if (gen != null && value != null) {
			gen.writeStartObject()
			gen.writeNumberField("lat", value.latitude)
			gen.writeNumberField("lng", value.longitude)
			gen.writeEndObject()
		}
	}

}

class CoordinateDeserializer : JsonDeserializer<CoordinateType?>() {
	override fun deserialize(
			p: JsonParser?,
			ctxt: DeserializationContext?): CoordinateType? {
		if (p != null && ctxt != null) {
			val node: JsonNode = p.codec.readTree(p)
			return CoordinateType(
					latitude = node.get("lat").doubleValue(),
					longitude = node.get("lng").doubleValue())
		} else {
			return null
		}
	}

}