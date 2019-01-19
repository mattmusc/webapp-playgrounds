package io.mattmusc.domain.poi.api.dto

import io.mattmusc.domain.location.api.CoordinateDto
import java.time.LocalDateTime

data class PoiDto(
        var id: Long,
        var name: String,
        var description: String? = null,
        var location: CoordinateDto,
        var updatedAt: LocalDateTime,
        var createdAt: LocalDateTime)