package br.com.pagseguro.emission.card.app.adapter.builder

import br.com.pagseguro.emission.card.core.domain.enum.AccountStatusEnum
import net.datafaker.Faker
import java.time.Instant
import br.com.pagseguro.emission.events.avro.emission.issuer.card.IssuerCardChangedEventAccount

fun buildRandomIssuerCardChangedEventAccount(): IssuerCardChangedEventAccount =
    IssuerCardChangedEventAccount.newBuilder()
        .setIDTACCOUNT(Faker().number().randomNumber())
        .setCODCUSTOMER("c50c9bd4-fd6d-467d-89c1-b2a5b9373d03")
        .setCODPARTNERACCOUNT(Faker().number().randomNumber())
        .setCODPARTNERPERSON(Faker().number().randomNumber())
        .setCODPARTNERPRODUCT(Faker().number().randomNumber())
        .setDESSTATUSACCOUNT(AccountStatusEnum.values().toList().shuffled().first().name)
        .setDATCREATION(Instant.now().toString())
        .setDATUPDATE(Instant.now().toString())
        .build()
