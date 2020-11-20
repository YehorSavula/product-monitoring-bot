package com.skylsv.productmonitoringbot.bot.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("telegram.bot")
data class TelegramBotConfig(val username: String, val token: String)