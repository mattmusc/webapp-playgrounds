package io.mattmusc.web.controller

import io.mattmusc.domain.poi.api.PoiService
import io.mattmusc.web.resource.PoiResource
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping(
		value = ["pois"],
		produces = [MediaType.APPLICATION_JSON_VALUE])
class PoiController(private val poiService: PoiService)
{
	private val log = LoggerFactory.getLogger(PoiController::class.java)

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
			val link = linkTo(methodOn(this::class.java).retrievePoi(result.id.toString())).withSelfRel()
			resource.add(link)
			return ResponseEntity.ok(resource)
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
		}
	}

}