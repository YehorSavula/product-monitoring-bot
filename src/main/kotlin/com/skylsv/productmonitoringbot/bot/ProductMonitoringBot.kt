package com.skylsv.productmonitoringbot.bot

import com.skylsv.productmonitoringbot.bot.configuration.TelegramBotConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class ProductMonitoringBot() : TelegramWebhookBot() {

    @Autowired
    private lateinit var config: TelegramBotConfig

    override fun getBotToken() = config.token
    override fun getBotUsername() = config.username
    override fun getBotPath() = config.username

    override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*> {
        if (update.hasMessage()) {
            var sendMessage = SendMessage()
            sendMessage.chatId = update.message.chatId.toString()
            sendMessage.text = "Hi" + update.message.text

            return sendMessage
        }
        return SendMessage(update.message.chatId.toString(), "Smth wrong")
    }
}