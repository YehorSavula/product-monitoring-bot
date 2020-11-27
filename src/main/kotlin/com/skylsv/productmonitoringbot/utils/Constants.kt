package com.skylsv.productmonitoringbot.utils

class Constants {
    companion object {
        val ROZETKA_STATUSES = mapOf(
                "unavailable" to "Нет в наличии",
                "active" to "В наличии",
                "limited" to "Заканчивается",
                "available" to "В наличии",
                "not_in_stock" to "Товар закончился"
        )
    }
}
