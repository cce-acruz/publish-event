package br.com.worktools.publishevent.web.controller.request

import br.com.pagseguro.multiple.card.events.enums.AccountStatusEnum
import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountRequest(
    var id: Long = 1,
    var partnerAccountCode: Long = 100,
    var partnerPersonCode: Long = 100,
    var safePayUserId: Long = 100,
    var customerId: String = "CUSTOMER:DAE35245A41B4221BBDCB7E3734EC5CC",
    var bankCode: String? = "0001",
    var agencyCode: String? = "0001",
    var digitalAccountCode: String? = "0001",
    var status: AccountStatusEnum = AccountStatusEnum.ACTIVE,
    var customerType: String = "PF",
    var name: String = "Bento Alexandre Barros",
    var document: String = "47951267040",
    var email: String = "bento_alexandre_barros@recatec.com.br",
    var dueDay: Int = 15,
    var bestBuyDay: Int = 15,
    var creditLimit: BigDecimal = BigDecimal("0.1"),
    var creationDate: LocalDateTime = LocalDateTime.now(),
    var codGroup: String = "0001"
)
