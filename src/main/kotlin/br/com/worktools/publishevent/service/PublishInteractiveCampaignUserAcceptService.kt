package br.com.worktools.publishevent.service

import br.com.pagseguro.interactivecampaing.acceptCampaign.InteractiveCampaignUserAcceptCampaign
import io.confluent.kafka.serializers.KafkaAvroSerializer
import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.common.serialization.ByteArraySerializer
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.stereotype.Service
import pags.bank.creditcard.update.BankCreditCardChangedEvent
import java.time.LocalDateTime
import java.util.UUID

@Service
class PublishInteractiveCampaignUserAcceptService(
    private val streamBridge: StreamBridge
) {

    private val log: KLogger = KotlinLogging.logger {}

    fun userAccept() {
        val body = InteractiveCampaignUserAcceptCampaign(
            1489L,
            "CUSTOMER:57F593988BDA4CE7B672587A67EC9BA9",//"CUSTOMER:" + UUID.randomUUID().toString().replace("-", "").uppercase(),
            "2023-07-20T11:40:45.838Z",
            "PARTICIPATING",
            "Teste"
        );

        log.info { "Sending message [$body]" }

        streamBridge.send("interactive-campaign-user-accept-campaign-out-0", body);
    }

    fun bankCreditCardChange() {
        val event = BankCreditCardChangedEvent(
                1L,
                1L,
                1L,
                "CARD",
                "ATIVO",
                "",
                "COD_DF_CC_HASH",
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                "A",
                "Alan",
                "",
                "A",
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                1L,
                1L,
                1L,
                1L,
                "CUSTOMER",
                LocalDateTime.now().toString(),
                "LoCAL",
                "REASON"
        )

        streamBridge.send("bank-credit-card-changed-out-0", event);
    }
}