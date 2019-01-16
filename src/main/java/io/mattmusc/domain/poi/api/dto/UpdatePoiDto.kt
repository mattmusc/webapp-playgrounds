package io.mattmusc.domain.poi.api.dto

import io.mattmusc.domain.location.api.CoordinateDto

data class UpdatePoiDto(
        val name: String?,
        val description: String?,
        val location: CoordinateDto?)