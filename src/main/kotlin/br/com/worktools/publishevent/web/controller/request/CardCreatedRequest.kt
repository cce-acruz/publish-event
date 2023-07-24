package br.com.worktools.publishevent.web.controller.request

import br.com.pagseguro.multiple.card.events.enums.CardStatusEnum
import java.time.LocalDate
import java.time.LocalDateTime

data class CardCreatedRequest(
    var creationDate: LocalDateTime = LocalDateTime.now(),
    var customerId: String = "CUSTOMER:DAE35245A41B4221BBDCB7E3734EC5CC",
    var expirationDate: LocalDate = LocalDate.of(2100, 1, 1),
    var id: Long = 1,
    var orderId: Long = 100,
    var panMask: String = "",
    var partnerAccountCode: Long = 100,
    var partnerCardCode: Long = 100,
    var passwordId: String = "123",
    var status: CardStatusEnum = CardStatusEnum.ACTIVE
)
