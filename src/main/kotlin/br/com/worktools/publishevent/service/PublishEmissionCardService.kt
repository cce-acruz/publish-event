package br.com.worktools.publishevent.service

import br.com.pagseguro.emission.card.app.adapter.builder.buildRandomEmissionCardChangedEventCardCancelled
import br.com.worktools.publishevent.web.controller.request.IssuerCardStatusChangeRequest
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class PublishEmissionCardService(
    private val streamBridge: StreamBridge
) {

    private val log: KLogger = KotlinLogging.logger {}

    fun publishCardChangedEvent(request: IssuerCardStatusChangeRequest) {
        val event = buildRandomEmissionCardChangedEventCardCancelled()
        request.codCustomer?.apply {
            event.issuerCardChangedEventTrails.forEach{
                it.issuerCardChangedEventAccount.codcustomer = this.toString()
            }
        }
        val message = MessageBuilder.withPayload(event).build()
        log.info("Sending message {}", message)
        streamBridge.send("emission-card-card-changed-out-0", message)
    }
}