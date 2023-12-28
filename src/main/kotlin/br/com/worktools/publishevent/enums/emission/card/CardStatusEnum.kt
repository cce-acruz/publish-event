package br.com.pagseguro.emission.card.core.domain.enum

enum class CardStatusEnum {
    ACTIVE,
    INACTIVE,
    CANCELLED,
    BLOCKED,
    BLOCKED_BY_ACCOUNT,
    BLOCKED_BY_EMISSION,
    RECHARGE_BLOCKED,
    TEMPORARILY_BLOCKED,
    UNKNOWN,
}
