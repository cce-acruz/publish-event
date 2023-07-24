package br.com.worktools.publishevent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PublishEventApplication

fun main(args: Array<String>) {
	runApplication<PublishEventApplication>(*args)
}
