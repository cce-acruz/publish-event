package br.com.worktools.publishevent.mapper.authorizer

import br.com.pagseguro.multiple.card.events.card.CardCreatedEvent
import br.com.worktools.publishevent.web.controller.request.CardCreatedRequest
import org.mapstruct.Mapper

@Mapper
interface CardMapper {
    fun toEvent(cardCreatedRequest: CardCreatedRequest): CardCreatedEvent
}