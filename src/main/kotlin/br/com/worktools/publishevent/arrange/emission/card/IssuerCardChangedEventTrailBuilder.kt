package br.com.pagseguro.emission.card.app.adapter.builder

import br.com.pagseguro.emission.card.core.domain.enum.CardStatusEnum
import br.com.pagseguro.emission.card.core.domain.enum.TrailTypeEnum
import br.com.pagseguro.emission.events.avro.emission.issuer.card.IssuerCardChangedEventTrail
import net.datafaker.Faker
import java.time.Instant

fun buildRandomIssuerCardChangedEventTrail(): IssuerCardChangedEventTrail =
    IssuerCardChangedEventTrail.newBuilder()
        .setIDTTRAIL(Faker().number().randomNumber())
        .setDESTRAILTYPE(TrailTypeEnum.values().toList().shuffled().first().name)
        .setFGLMAINTRAIL(true)
        .setNUMBINNUMBER(Faker().number().randomNumber())
        .setDESPANMASK("5322XXXXXXXX7536")
        .setINDTRAILSTATUS(CardStatusEnum.values().toList().shuffled().first().name)
        .setCODPARTNERCARD(28710631L)
        .setCODPARTNERCARDTYPE(null)
        .setDATACTIVATION(Instant.now().toString())
        .setNUMINVOICEDUEDAY(Faker().number().randomDigit())
        .setDATCREATION(Instant.now().toString())
        .setDATUPDATE(Instant.now().toString())
        .setIssuerCardChangedEventAccount(buildRandomIssuerCardChangedEventAccount())
        .build()

fun buildIssuerCardChangedEventTrailCardCancelled(): IssuerCardChangedEventTrail =
    buildRandomIssuerCardChangedEventTrail().apply {
        indtrailstatus = CardStatusEnum.CANCELLED.name
        destrailtype = TrailTypeEnum.DEBIT.name
    }
