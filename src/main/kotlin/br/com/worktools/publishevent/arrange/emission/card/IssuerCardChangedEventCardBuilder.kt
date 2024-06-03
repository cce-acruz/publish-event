package br.com.pagseguro.emission.card.app.adapter.builder

import br.com.pagseguro.emission.card.core.domain.enum.CardBrandEnum
import br.com.pagseguro.emission.card.core.domain.enum.EmissionProductCardTypeEnum
import br.com.pagseguro.emission.card.core.domain.enum.EmissionProductGroupEnum
import br.com.pagseguro.emission.events.avro.emission.issuer.card.IssuerCardChangedEventCard
import net.datafaker.Faker
import java.time.Instant
import java.util.UUID

fun buildRandomCardChangedEventCard(): IssuerCardChangedEventCard =
    IssuerCardChangedEventCard.newBuilder()
        .setIDTCARD(Faker().number().randomNumber())
        .setFLGINSURANCE(Faker().bool().bool())
        .setINDEMISSIONPRODUCTGROUP(EmissionProductGroupEnum.MULTIPLECARD.name)
        .setINDEMISSIONPRODUCTCARDTYPE(EmissionProductCardTypeEnum.PHYSICAL_CARD.name)
        .setCODEMISSIONPIN(Faker().number().randomNumber().toString())
        .setINDCARDBRAND(CardBrandEnum.MASTERCARD.name)
        .setDATPARTNERCARDCREATION(Instant.now().toString())
        .setDATPARTNEREXPIRATION(Instant.now().toString())
        .setNUMCARDSEQUENCE(Faker().number().randomNumber())
        .setCODEMISSIONPRODUCT(UUID.randomUUID().toString())
        .setCODEMISSIONCARDREQUEST(UUID.randomUUID().toString())
        .setDATACQUISITION(Instant.now().toString())
        .setDATCANCELLATION(Instant.now().toString())
        .setDATCREATION(Instant.now().toString())
        .setDATUPDATE(Instant.now().toString())
        .build()
