package br.com.pagseguro.emission.card.app.adapter.builder

import br.com.pagseguro.emission.events.avro.emission.issuer.card.IssuerCardChangedEvent
import java.util.UUID

fun buildRandomEmissionCardChangedEvent(): IssuerCardChangedEvent =
    IssuerCardChangedEvent.newBuilder()
        .setCODPARTNERCOMMERCIALORIGIN(UUID.randomUUID().toString())
        .setDESFLOWTYPE(UUID.randomUUID().toString())
        .setINDREQUESTORIGIN(UUID.randomUUID().toString())
        .setIssuerCardChangedEventCard(buildRandomCardChangedEventCard())
        .setIssuerCardChangedEventTrails(listOf(buildRandomIssuerCardChangedEventTrail()))
        .build()

fun buildRandomEmissionCardChangedEventCardCancelled(): IssuerCardChangedEvent = buildRandomEmissionCardChangedEvent()
    .apply {
        issuerCardChangedEventTrails = listOf(buildIssuerCardChangedEventTrailCardCancelled())
    }
