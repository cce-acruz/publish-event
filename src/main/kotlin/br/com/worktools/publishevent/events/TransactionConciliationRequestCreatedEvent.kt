package br.com.worktools.publishevent.events

import br.com.pagseguro.multiple.card.events.enums.TransactionConciliationRequestOperationTypeEnum
import br.com.worktools.publishevent.enums.TransactionConciliationRequestOriginEnum
import br.com.worktools.publishevent.enums.TransactionConciliationRequestStatusEnum
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class TransactionConciliationRequestCreatedEvent(

    val id: Long,

    val partnerTransactionCode: UUID? = null,

    val nsu: String,

    val authorizationCode: String? = null,

    val localDate: String,

    val operationType: TransactionConciliationRequestOperationTypeEnum,

    val status: TransactionConciliationRequestStatusEnum,

    val replacementAmount: BigDecimal? = null,

    val origin: TransactionConciliationRequestOriginEnum,

    val partnerCardCode: Long,

    val creationDate: LocalDateTime
)
