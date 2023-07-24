package br.com.worktools.publishevent.dto.authorizer

import br.com.pagseguro.multiple.card.events.enums.TransactionOperationTypeEnum
import br.com.pagseguro.multiple.card.events.enums.TransactionStatusEnum
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class TransactionsDTO(
    val partnerCardCode: Long,
    val partnerTransactionCode: UUID,
    val correlationId: UUID ,
    val nsu: String,
    val authorizationCode: String,
    val localDate: String,
    val localTime: String,
    val operationType: TransactionOperationTypeEnum,
    val status: TransactionStatusEnum,
    val currencyCode: String,
    val amount: BigDecimal,
    val iof: BigDecimal,
    val fee: BigDecimal,
    val customerId: String,
    val creationDate: LocalDateTime,
    val id: Long? = null,
    val updateDate: LocalDateTime? = null
)
