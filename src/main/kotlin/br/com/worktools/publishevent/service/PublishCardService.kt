package br.com.worktools.publishevent.service

import br.com.pagseguro.emission.card.core.domain.enum.AccountStatusEnum
import br.com.pagseguro.emission.card.core.domain.enum.TrailTypeEnum
import br.com.worktools.publishevent.enums.emission.card.TrailStatusEnum
import br.com.worktools.publishevent.mapper.authorizer.CardMapper
import br.com.worktools.publishevent.web.controller.request.CardCreatedRequest
import java.time.Instant
import java.time.LocalDate
import java.util.UUID
import mu.KLogger
import mu.KotlinLogging
import net.datafaker.Faker
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import br.com.pagseguro.events.avro.issuer.digital.virtual.card.IssuerDigitalVirtualCardChangedEvent
import br.com.worktools.publishevent.arrange.extension.panMask

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