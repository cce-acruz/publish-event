package br.com.worktools.publishevent.web.controller.request

import br.com.pagseguro.multiple.card.events.enums.TransactionConciliationRequestOperationTypeEnum
import br.com.pagseguro.multiple.card.events.enums.TransactionConciliationRequestOriginEnum
import java.util.UUID

data class TransactionConciliationRequestCreateRequest(
    val authorizationCode: String = "",
    val localDate: String = "",
    val nsu: String = "",
    val operationType: TransactionConciliationRequestOperationTypeEnum = TransactionConciliationRequestOperationTypeEnum.CANCELLATION,
    val origin: TransactionConciliationRequestOriginEnum = TransactionConciliationRequestOriginEnum.BATCH,
    val partnerCardCode: Long = 0,
    val partnerTransactionCode: UUID = UUID.randomUUID()
)
