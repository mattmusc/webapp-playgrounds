package io.mattmusc.domain.poi.api

import io.mattmusc.domain.poi.api.dto.CreatePoiDto
import io.mattmusc.domain.poi.api.dto.PoiDto
import io.mattmusc.domain.poi.api.dto.UpdatePoiDto

interface PoiService {

    fun retrievePoi(cityId: Long): PoiDto?

    fun retrievePois(): List<PoiDto>

    fun addPoi(city: CreatePoiDto): PoiDto

    fun updatePoi(id: Long, city: UpdatePoiDto): PoiDto?
}