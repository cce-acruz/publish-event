package br.com.worktools.publishevent.service

import br.com.worktools.publishevent.arrange.emission.notification.api.buildUserNotificationEventV2
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class PublishUserNotificationService(
    private val streamBridge: StreamBridge
) {

    private val log: KLogger = KotlinLogging.logger {}

    fun publishUserNotificationEvent() {
        val event = buildUserNotificationEventV2()
        val message = MessageBuilder.withPayload(event).build()
        log.info("Sending message {}", message)
        streamBridge.send("user-notification-out-0", message)
    }
}