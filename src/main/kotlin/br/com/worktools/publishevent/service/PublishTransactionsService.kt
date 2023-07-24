package br.com.worktools.publishevent.service

import br.com.pagseguro.multiple.card.events.authorizer.HEADER_EVENT_TYPE
import br.com.pagseguro.multiple.card.events.authorizer.TransactionConciliationRequestCreateEvent
import br.com.pagseguro.multiple.card.events.authorizer.TransactionIntegrationEventTypeConstants
import br.com.pagseguro.multiple.card.events.authorizer.TransactionIntegrationEventTypeConstants.TRANSACTION_CREATED
import br.com.pagseguro.multiple.card.events.authorizer.TransactionIntegrationEventTypeConstants.TRANSACTION_UPDATED
import br.com.worktools.publishevent.dto.authorizer.TransactionsDTO
import br.com.worktools.publishevent.mapper.authorizer.TransactionsMapper
import br.com.worktools.publishevent.web.controller.request.TransactionConciliationRequestCreateRequest
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class PublishTransactionsService(
    private val streamBridge: StreamBridge,
    private val transactionMapper: TransactionsMapper,
    private val log: KLogger = KotlinLogging.logger {}
) {

    fun transactionCreated(transactionType: String, transactionDTO: TransactionsDTO) {
        val message = MessageBuilder
            .withPayload(
                when (transactionType) {
                    TRANSACTION_CREATED -> transactionMapper.toCreatedEvent(transactionDTO)
                    TRANSACTION_UPDATED -> transactionMapper.toUpdatedEvent(transactionDTO)
                    else -> RuntimeException("Transaction type event not found")
                }
            )
            .setHeader(HEADER_EVENT_TYPE, transactionType)
            .build()

        log.info("Sending message transactionType {}, transactionDTO {}", transactionType, transactionDTO)

        streamBridge.send("multiple-card-authorizer-transactions-out-0", message)
    }

    fun transactionConciliationCreate(request: TransactionConciliationRequestCreateRequest) {
        val event = transactionMapper.toTransactionConciliationRequestCreateEvent(request)
        val message = MessageBuilder
            .withPayload(event)
            .setHeader(HEADER_EVENT_TYPE,
                TransactionIntegrationEventTypeConstants.TRANSACTION_CONCILIATION_REQUEST_CREATE
            ).build()
        log.info("Sending message {}", message)
        streamBridge.send("multiple-card-authorizer-transactions-out-0", message)
    }
}