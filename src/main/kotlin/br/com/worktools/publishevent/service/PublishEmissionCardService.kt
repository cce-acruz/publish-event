package br.com.worktools.publishevent.service

import br.com.pagseguro.emission.card.app.adapter.builder.buildRandomEmissionCardChangedEventCardCancelled
import br.com.pagseguro.emission.card.core.domain.enum.AccountStatusEnum
import br.com.pagseguro.emission.card.core.domain.enum.TrailTypeEnum
import br.com.pagseguro.events.avro.issuer.digital.virtual.card.IssuerDigitalVirtualCardChangedEvent
import br.com.worktools.publishevent.arrange.extension.panMask
import br.com.worktools.publishevent.enums.emission.card.TrailStatusEnum
import br.com.worktools.publishevent.web.controller.request.IssuerCardStatusChangeRequest
import java.time.Instant
import java.time.LocalDate
import java.util.UUID
import mu.KLogger
import mu.KotlinLogging
import net.datafaker.Faker
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class PublishEmissionCardService(
    private val streamBridge: StreamBridge
) {

    private val log: KLogger = KotlinLogging.logger {}

    private val faker = Faker()

    fun publishCardChangedEvent(request: IssuerCardStatusChangeRequest) {
        val event = buildRandomEmissionCardChangedEventCardCancelled()
        request.codCustomer?.apply {
            event.issuerCardChangedEventTrails.forEach{
                it.issuerCardChangedEventAccount.codcustomer = this.toString()
            }
        }
        val message = MessageBuilder.withPayload(event)
            .setHeader(KafkaHeaders.MESSAGE_KEY, event.issuerCardChangedEventTrails.first().issuerCardChangedEventAccount.codcustomer)
            .build()
        log.info("Sending message {}", message)
        streamBridge.send("emission-card-card-changed-out-0", message)
    }

    fun digitalVirtualCardCreated() {
        val event = IssuerDigitalVirtualCardChangedEvent.newBuilder()
            .setCODCUSTOMER(UUID.randomUUID().toString())
            .setCODPARTNERPERSON(faker.number().randomNumber())
            .setCODPARTNERCARD(faker.number().randomNumber())
            .setCODPARTNERACCOUNT(faker.number().randomNumber())
            .setCODACCOUNTSTATUS(
                AccountStatusEnum.entries.filter { it != AccountStatusEnum.UNKNOWN }.shuffled().first().name
            )
            .setINDOPERATIONTYPE(TrailTypeEnum.entries.filter { it != TrailTypeEnum.UNKNOWN }.shuffled().first().name)
            .setCODPARTNERPRODUCT(faker.number().randomNumber())
            .setCODEMISSIONPRODUCT(UUID.fromString("aab7552b-d890-4f59-8e5b-74267db8ce9d").toString())
            .setCODPARTNERCARD(faker.number().randomNumber())
            .setCODPARTNERCARDTYPE(faker.number().randomNumber())
            .setCODCARDSTATUS(TrailStatusEnum.entries.filter { it != TrailStatusEnum.UNKNOWN }.shuffled().first().name)
            .setCODPANMASK(faker.panMask())
            .setDATCARDEXPIRY(LocalDate.now().plusYears(1).toString())
            .setDATCARDCREATION(Instant.now().toString())
            .setDATCARDUPDATE(Instant.now().toString())
            .build()
        val message = MessageBuilder.withPayload(event).build()
        log.info("Sending message {}", message)
        streamBridge.send("digital-virtual-card-changed-out-0", message)
    }
}