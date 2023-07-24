package br.com.worktools.publishevent.service

import br.com.worktools.publishevent.mapper.authorizer.CardMapper
import br.com.worktools.publishevent.web.controller.request.CardCreatedRequest
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class PublishCardService(
    private val streamBridge: StreamBridge,
    private val mapper: CardMapper,
    private val log: KLogger = KotlinLogging.logger {}
) {
    fun cardCreated(request: CardCreatedRequest) {
        val message = MessageBuilder.withPayload(mapper.toEvent(request)).build()
        log.info("Sending message ${message.payload}")
        streamBridge.send("fct.dsr.bank.cards.multiplecard.card-created", message)
    }
}