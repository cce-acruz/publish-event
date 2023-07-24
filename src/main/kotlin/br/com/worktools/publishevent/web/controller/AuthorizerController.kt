package br.com.worktools.publishevent.web.controller

import br.com.worktools.publishevent.mapper.authorizer.TransactionsMapper
import br.com.worktools.publishevent.service.PublishTransactionsService
import br.com.worktools.publishevent.web.controller.request.TransactionConciliationRequestCreateRequest
import br.com.worktools.publishevent.web.controller.request.TransactionsRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authorizer")
class AuthorizerController(
    private val service: PublishTransactionsService,
    private val transactionsMapper: TransactionsMapper
) {

    @PostMapping("/transactions/{transactionType}")
    fun transactionCreated(
        @PathVariable transactionType: String,
        @RequestBody transactionRequest: TransactionsRequest
    ) {
        service.transactionCreated(transactionType, transactionsMapper.toDTO(transactionRequest))
    }

    @PostMapping("/transaction-conciliate-create")
    fun transactionConciliateRequestCreate(@RequestBody request: TransactionConciliationRequestCreateRequest) {
        service.transactionConciliationCreate(request)
    }
}