package br.com.worktools.publishevent.web.controller

import br.com.worktools.publishevent.service.PublishInteractiveCampaignUserAcceptService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/interactive-campaign")
class InteractiveCampaignController(
    private val service: PublishInteractiveCampaignUserAcceptService
) {

    @GetMapping("user-accept")
    fun userAccept() {
        service.userAccept()
    }

    @GetMapping("credit-card-changed")
    fun creditCardChanged() {
        service.bankCreditCardChange()
    }
}