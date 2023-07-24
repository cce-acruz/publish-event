package br.com.worktools.publishevent.mapper.authorizer

import br.com.pagseguro.multiple.card.events.authorizer.TransactionConciliationRequestCreateEvent
import br.com.pagseguro.multiple.card.events.authorizer.TransactionCreatedEvent
import br.com.pagseguro.multiple.card.events.authorizer.TransactionUpdatedEvent
import br.com.worktools.publishevent.dto.authorizer.TransactionsDTO
import br.com.worktools.publishevent.web.controller.request.TransactionConciliationRequestCreateRequest
import br.com.worktools.publishevent.web.controller.request.TransactionsRequest
import org.mapstruct.Mapper

@Mapper
interface TransactionsMapper {
    fun toDTO(transactionsRequest: TransactionsRequest): TransactionsDTO
    fun toCreatedEvent(transactionsDTO: TransactionsDTO): TransactionCreatedEvent
    fun toUpdatedEvent(transactionsDTO: TransactionsDTO): TransactionUpdatedEvent
    fun toTransactionConciliationRequestCreateEvent(request: TransactionConciliationRequestCreateRequest): TransactionConciliationRequestCreateEvent
}