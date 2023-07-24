package br.com.worktools.publishevent.web.controller

import br.com.pagseguro.multiple.card.events.acquisition.AcquisitionOrderUpdatedEvent
import br.com.worktools.publishevent.service.PublishAccountService
import br.com.worktools.publishevent.web.controller.request.AccountRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController(
    private val service: PublishAccountService
) {

    @PostMapping("/created")
    fun accountCreated(@RequestBody accountRequest: AccountRequest) {
        service.accountCreated(accountRequest)
    }

    @PostMapping("/orderUpdated")
    fun orderUpdated(@RequestBody event: AcquisitionOrderUpdatedEvent) {
        service.orderUpdated(event)
    }

    @PostMapping("/operation-account-updated")
    @ResponseStatus(HttpStatus.OK)
    fun operationAccountUpdated() {
        service.operationAccountUpdated()
    }
}