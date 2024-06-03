package br.com.worktools.publishevent.arrange.extension

import java.util.UUID
import net.datafaker.Faker

fun Faker.panMask(): String = this.regexify("([0-9]){4}XXXXXXXX([0-9]){4}")

fun Faker.codCustomer(): String = "CUSTOMER:${UUID.randomUUID().toString().replace("-", "").uppercase()}"
