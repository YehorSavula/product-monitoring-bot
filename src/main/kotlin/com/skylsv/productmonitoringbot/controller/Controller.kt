package com.skylsv.productmonitoringbot.controller

import com.skylsv.productmonitoringbot.bot.ProductMonitoringBot
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@RestController
class Controller(private val monitoringBot: ProductMonitoringBot) {

    @PostMapping("/")
    fun onWebhookUpdateReceived(@RequestBody update: Update): BotApiMethod<*> {
        return monitoringBot.onWebhookUpdateReceived(update)
    }
}