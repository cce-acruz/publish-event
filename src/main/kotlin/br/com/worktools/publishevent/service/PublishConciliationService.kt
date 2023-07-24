package br.com.worktools.publishevent.service

import br.com.pagseguro.multiple.card.events.enums.TransactionConciliationRequestOperationTypeEnum
import br.com.worktools.publishevent.enums.TransactionConciliationRequestOriginEnum
import br.com.worktools.publishevent.enums.TransactionConciliationRequestStatusEnum
import br.com.worktools.publishevent.events.TransactionConciliationRequestCreatedEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Service
class PublishConciliationService(
    private val streamBridge: StreamBridge
) {

    private val log: KLogger = KotlinLogging.logger {}

    fun conciliationRequested(transactionConciliationId: Long) {
        val event = TransactionConciliationRequestCreatedEvent(
            id = transactionConciliationId,
            partnerCardCode = 0,
            creationDate = LocalDateTime.now(),
            authorizationCode = "",
            localDate = "1201",
            nsu = "",
            operationType = TransactionConciliationRequestOperationTypeEnum.CANCELLATION,
            origin = TransactionConciliationRequestOriginEnum.BATCH,
            partnerTransactionCode = UUID.randomUUID(),
            status = TransactionConciliationRequestStatusEnum.PROCESSING,
            replacementAmount = BigDecimal.ONE
        )
        val mapper = JsonMapper.builder()
            .addModule(ParameterNamesModule())
            .addModule(Jdk8Module())
            .addModule(JavaTimeModule())
            .build()
        val eventJson = mapper.writeValueAsString(event)
        val message = MessageBuilder.withPayload(event).build()
        log.info("Sending message {}", message)
        streamBridge.send("conciliation-requested-out-0", message)
    }
}