package io.mattmusc.web.controller

import io.mattmusc.domain.poi.api.PoiService
import io.mattmusc.domain.poi.api.dto.CreatePoiDto
import io.mattmusc.domain.poi.api.dto.UpdatePoiDto
import io.mattmusc.logger
import io.mattmusc.web.POIS_PATH
import io.mattmusc.web.resource.PoiResource
import org.springframework.hateoas.Resources
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping(
		value = [POIS_PATH],
		produces = [MediaType.APPLICATION_JSON_VALUE])
open class PoiController(private val poiService: PoiService)
{
	val log = logger<PoiController>()

	@GetMapping
	fun retrievePois(): HttpEntity<Resources<PoiResource>>
	{
		log.debug("Retrieving points of interests")

		val result = poiService.retrievePois()
		return ResponseEntity.ok(Resources(result.map { PoiResource.fromDto(it) }))
	}

	@GetMapping("{id}")
	fun retrievePoi(@PathVariable("id") poiId: String): HttpEntity<PoiResource> {
		log.debug("Retrieving city: {}", poiId)

		val result = poiService.retrievePoi(poiId.toLong())
		if (result != null) {
			val resource = PoiResource.fromDto(result)
			return ResponseEntity.ok(resource)
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
		}
	}

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
	fun addPoi(@RequestBody city: CreatePoiDto, uriBuilder: UriComponentsBuilder): HttpEntity<PoiResource> {
		log.debug("Request to add a poi")

		val result = poiService.addPoi(city)
		val resource = PoiResource.fromDto(result)
		return ResponseEntity
				.created(uriBuilder.path("${POIS_PATH}/{id}").buildAndExpand(result.id).toUri())
				.body(resource)
	}

	@PutMapping("{id}")
	fun updatePoi(@PathVariable("id") poiId: String, @RequestBody city: UpdatePoiDto): HttpEntity<PoiResource> {
		log.debug("Request to update poi: {}", poiId)

		val result = poiService.updatePoi(poiId.toLong(), city)
		if (result != null) {
			val resource = PoiResource.fromDto(result)
			return ResponseEntity.ok(resource)
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
		}
	}
}