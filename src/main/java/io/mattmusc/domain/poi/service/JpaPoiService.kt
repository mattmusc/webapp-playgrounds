package io.mattmusc.domain.poi.service

import io.mattmusc.domain.poi.api.PoiService
import io.mattmusc.domain.poi.api.dto.CreatePoiDto
import io.mattmusc.domain.poi.api.dto.PoiDto
import io.mattmusc.domain.poi.api.dto.UpdatePoiDto
import io.mattmusc.domain.poi.entity.PoiEntity
import io.mattmusc.domain.poi.repository.PoiRepository
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
internal open class JpaPoiService(val poiRepo: PoiRepository, val log: Logger) : PoiService {
    override fun retrievePoi(poiId: Long): PoiDto? {
        log.debug("Retrieving poi: {}", poiId)
        return poiRepo.findById(poiId)
                .map { it.toDto() }
                .orElse(null)
    }

    override fun retrievePois(): List<PoiDto> {
        log.debug("Retrieving Points of Interest")
        return poiRepo.findAll().map { it.toDto() }
    }

    override fun updatePoi(id: Long, poiDto: UpdatePoiDto): PoiDto? {
        log.debug("Updating poi: {} with data: {}", id, poiDto)
        return poiRepo.findById(id)
                .map { poiRepo.save(PoiEntity.fromDto(poiDto, it)).toDto() }
                .orElse(null)
    }

    override fun addPoi(poiDto: CreatePoiDto): PoiDto {
        log.debug("Adding Poi: {}", poiDto)
        return poiRepo.save(PoiEntity.fromDto(poiDto)).toDto()
    }
}