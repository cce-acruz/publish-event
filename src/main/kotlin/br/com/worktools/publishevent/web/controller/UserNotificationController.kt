package br.com.worktools.publishevent.web.controller

import br.com.worktools.publishevent.service.PublishUserNotificationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/emission-notification")
class UserNotificationController(
    private val publishUserNotificationService: PublishUserNotificationService
) {

    @GetMapping("/user-notification")
    @ResponseStatus(HttpStatus.OK)
    fun publishCardChanged() {
        publishUserNotificationService.publishUserNotificationEvent()
    }
}