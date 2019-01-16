package io.mattmusc.domain.poi

import io.mattmusc.domain.poi.entity.PoiEntity
import io.mattmusc.domain.poi.repository.PoiRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackageClasses = arrayOf(PoiRepository::class))
@EntityScan(basePackageClasses = arrayOf(PoiEntity::class))
@EnableTransactionManagement
internal open class InternalPoiConfig