package br.com.worktools.publishevent.web.controller

import br.com.worktools.publishevent.service.PublishEmissionCardService
import br.com.worktools.publishevent.web.controller.request.IssuerCardStatusChangeRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/emission-card")
class EmissionCardController(
    private val publishEmissionCardService: PublishEmissionCardService
) {

    @PostMapping("/card-changed")
    @ResponseStatus(HttpStatus.OK)
    fun publishCardChanged(@RequestBody issuerCardStatusChangeRequest: IssuerCardStatusChangeRequest) {
        publishEmissionCardService.publishCardChangedEvent(issuerCardStatusChangeRequest)
    }
}