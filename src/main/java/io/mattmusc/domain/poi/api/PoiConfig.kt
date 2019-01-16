package io.mattmusc.domain.poi.api

import io.mattmusc.domain.poi.InternalPoiConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = arrayOf(InternalPoiConfig::class))
open class PoiConfig