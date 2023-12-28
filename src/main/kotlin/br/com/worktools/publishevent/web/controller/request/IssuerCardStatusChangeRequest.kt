package br.com.worktools.publishevent.web.controller.request

import java.util.UUID

data class IssuerCardStatusChangeRequest(
    val codCustomer: UUID? = null,
)
