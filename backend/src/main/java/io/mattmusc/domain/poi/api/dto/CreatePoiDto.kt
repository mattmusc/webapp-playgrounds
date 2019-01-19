package io.mattmusc.domain.poi.api.dto

import io.mattmusc.domain.location.api.CoordinateDto
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

data class CreatePoiDto(
        var id: Long,
        @NotEmpty var name: String,
        var description: String? = null,
        @Valid var location: CoordinateDto)