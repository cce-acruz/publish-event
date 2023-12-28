package br.com.worktools.publishevent.arrange.emission.card

import net.datafaker.Faker
import java.util.UUID

fun Faker.panMask(): String = this.regexify("([0-9]){4}XXXXXXXX([0-9]){4}")

fun Faker.codCustomer(): String = "CUSTOMER:${UUID.randomUUID().toString().replace("-", "").uppercase()}"
