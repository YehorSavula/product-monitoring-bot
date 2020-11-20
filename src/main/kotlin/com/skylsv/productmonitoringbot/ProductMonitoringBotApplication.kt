package com.skylsv.productmonitoringbot

import com.skylsv.productmonitoringbot.bot.configuration.TelegramBotConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(TelegramBotConfig::class)
class ProductMonitoringBotApplication()

fun main(args: Array<String>) {
	runApplication<ProductMonitoringBotApplication>(*args)
}
