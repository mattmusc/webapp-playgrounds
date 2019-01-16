package io.mattmusc.domain.poi.entity

import io.mattmusc.domain.location.jpa.Coordinate
import io.mattmusc.domain.poi.api.dto.CreatePoiDto
import io.mattmusc.domain.poi.api.dto.PoiDto
import io.mattmusc.domain.poi.api.dto.UpdatePoiDto
import java.time.LocalDateTime
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "poi")
internal data class PoiEntity(
        @Id val id: Long? = null,
        val name: String,
        val description: String? = null,
        @Embedded val location: Coordinate,
        val updatedAt: LocalDateTime = LocalDateTime.now(),
        val createdAt: LocalDateTime = LocalDateTime.now()) {

    fun toDto(): PoiDto = PoiDto(
            id = this.id!!,
            name = this.name,
            description = this.description,
            location = this.location.toDto(),
            updatedAt = this.updatedAt,
            createdAt = this.createdAt
    )

    companion object {

        fun fromDto(dto: PoiDto) = PoiEntity(
                id = dto.id,
                name = dto.name,
                description = dto.description,
                location = Coordinate.fromDto(dto.location),
                updatedAt = dto.updatedAt,
                createdAt = dto.createdAt)

        fun fromDto(dto: CreatePoiDto) = PoiEntity(
                id = dto.id,
                name = dto.name,
                description = dto.description,
                location = Coordinate(dto.location.longitude, dto.location.latitude))

        fun fromDto(dto: UpdatePoiDto, defaultCity: PoiEntity) = PoiEntity(
                id = defaultCity.id!!,
                name = dto.name ?: defaultCity.name,
                description = dto.description ?: defaultCity.description,
                location = if (dto.location != null) Coordinate.fromDto(dto.location) else defaultCity.location,
                updatedAt = LocalDateTime.now(),
                createdAt = defaultCity.createdAt)

    }

}