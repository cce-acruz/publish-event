package br.com.worktools.publishevent.service

import br.com.pagseguro.multiple.card.events.acquisition.AcquisitionOrderUpdatedEvent
import br.com.worktools.publishevent.mapper.authorizer.AccountMapper
import br.com.worktools.publishevent.web.controller.request.AccountRequest
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import ps.account.operation.event.OperationAccount
import java.util.UUID

@Service
class PublishAccountService(
    private val streamBridge: StreamBridge,
    private val accountMapper: AccountMapper,
    private val log: KLogger = KotlinLogging.logger {}
) {

    fun accountCreated(accountRequest: AccountRequest) {
        val message = MessageBuilder.withPayload(accountMapper.toEvent(accountRequest)).build()
        log.info("Sending message ${message.payload}")
        streamBridge.send("fct.dsr.bank.cards.multiplecard.account-created", message)
    }

    fun orderUpdated(event: AcquisitionOrderUpdatedEvent) {
        val message = MessageBuilder.withPayload(event).build()
        log.info("Sending message ${message.payload}")
        streamBridge.send("fct.dsr.bank.cards.multiplecard.order-updated", message)
    }

    fun operationAccountUpdated() {
        val customerId = UUID.randomUUID().toString()
        val operationAccount: OperationAccount = OperationAccount(
            customerId.toString(),
            customerId.toString(),
            1,
            1,
            "",
            "",
            "C1"
        )

        val message = MessageBuilder
            .withPayload(operationAccount)
            .setHeader("customerId", customerId.toString())
            .build()
        streamBridge.send("operation-account-updated-stream-out-0", message)
    }
}