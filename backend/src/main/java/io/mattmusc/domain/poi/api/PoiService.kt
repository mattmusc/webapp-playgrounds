package io.mattmusc.domain.poi.api

import io.mattmusc.domain.poi.api.dto.CreatePoiDto
import io.mattmusc.domain.poi.api.dto.PoiDto
import io.mattmusc.domain.poi.api.dto.UpdatePoiDto

interface PoiService {
    fun retrievePoi(poiId: Long): PoiDto?

    fun retrievePois(): List<PoiDto>

    fun addPoi(poiDto: CreatePoiDto): PoiDto

    fun updatePoi(id: Long, poiDto: UpdatePoiDto): PoiDto?
}