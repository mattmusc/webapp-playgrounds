package io.mattmusc.web.resource

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.mattmusc.domain.poi.api.dto.PoiDto
import org.springframework.hateoas.ResourceSupport

data class PoiResource
@JsonCreator
constructor(
		@JsonProperty("id") val _id: String,
		@JsonProperty("name") val name: String,
		@JsonProperty("desc") val description: String?,
		@JsonProperty("loc") val location: CoordinateType) : ResourceSupport()
{
	// this is used to add static member
	companion object
	{
		fun fromDto(dto: PoiDto): PoiResource =
				PoiResource(
						_id = dto.id.toString(),
						name = dto.name,
						description = dto.description,
						location = CoordinateType.fromDto(dto.location)
				)
	}
}