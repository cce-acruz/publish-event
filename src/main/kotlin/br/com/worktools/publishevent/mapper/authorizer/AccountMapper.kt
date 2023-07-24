package br.com.worktools.publishevent.mapper.authorizer

import br.com.pagseguro.multiple.card.events.account.AccountCreatedEvent
import br.com.worktools.publishevent.web.controller.request.AccountRequest
import org.mapstruct.Mapper

@Mapper
interface AccountMapper {
    fun toEvent(request: AccountRequest): AccountCreatedEvent
}