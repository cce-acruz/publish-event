package br.com.worktools.publishevent.web.controller.request

import br.com.pagseguro.multiple.card.events.enums.TransactionOperationTypeEnum
import br.com.pagseguro.multiple.card.events.enums.TransactionStatusEnum
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class TransactionsRequest(
    val partnerCardCode: Long = 0,
    val partnerTransactionCode: UUID = UUID.randomUUID(),
    val correlationId: UUID = UUID.randomUUID(),
    val nsu: String = "",
    val authorizationCode: String = "",
    val localDate: String = "",
    val localTime: String = "",
    val operationType: TransactionOperationTypeEnum = TransactionOperationTypeEnum.CP,
    val status: TransactionStatusEnum = TransactionStatusEnum.AUTHORIZED,
    val currencyCode: String = "840",
    val amount: BigDecimal = BigDecimal.ZERO,
    val iof: BigDecimal = BigDecimal.ZERO,
    val fee: BigDecimal = BigDecimal.ZERO,
    val customerId: String = "CUSTOMER:DAE35245A41B4221BBDCB7E3734EC5CC",
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val id: Long? = null,
    val updateDate: LocalDateTime? = LocalDateTime.now()

)