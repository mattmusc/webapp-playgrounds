package io.mattmusc.domain.poi.repository

import io.mattmusc.domain.poi.entity.PoiEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
internal interface PoiRepository : JpaRepository<PoiEntity, Long>
