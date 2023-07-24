package br.com.worktools.publishevent.web.controller

import br.com.worktools.publishevent.service.PublishCardService
import br.com.worktools.publishevent.web.controller.request.CardCreatedRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/card")
class CardController(
    private val service: PublishCardService
) {
    @PostMapping
    fun cardCreated(@RequestBody cardCreatedRequest: CardCreatedRequest){
        service.cardCreated(cardCreatedRequest)
    }
}