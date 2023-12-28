package br.com.worktools.publishevent.arrange.emission.notification.api

import br.com.pagseguro.issuer.supporting.notification.event.v2.UserNotificationEventV2
import br.com.worktools.publishevent.arrange.emission.card.codCustomer
import net.datafaker.Faker

fun buildUserNotificationEventV2(): UserNotificationEventV2 = UserNotificationEventV2(
    email = "cce_acruz@uolinc.com",
    configName = "cancelamento.cartao.conta",
    notificationReference = "teste",
    customerId = "CUSTOMER:F3D454AFCD084646BC0D703D2E66938A"
)