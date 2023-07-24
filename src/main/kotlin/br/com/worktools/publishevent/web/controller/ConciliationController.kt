package br.com.worktools.publishevent.web.controller

import br.com.worktools.publishevent.service.PublishConciliationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/conciliation")
class ConciliationController(
    private val service: PublishConciliationService
) {

    @PostMapping
    fun conciliationRequested(@RequestBody transactionConciliationId: Long) {
        service.conciliationRequested(transactionConciliationId)
    }
}