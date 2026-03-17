package com.skylsv.productmonitoringbot.bot

import com.skylsv.productmonitoringbot.bot.configuration.TelegramBotConfig
import com.skylsv.productmonitoringbot.repository.MonitoredProductsStorage
import com.skylsv.productmonitoringbot.services.CommandsExecutorService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException


@Component
class ProductMonitoringBot(
        val config: TelegramBotConfig,
        val commandsExecutorService: CommandsExecutorService,
        val monitoredProductStorage: MonitoredProductsStorage
) : TelegramWebhookBot() {

    override fun getBotToken() = config.token
    override fun getBotUsername() = config.username
    override fun getBotPath() = config.token

    override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*> {
        val sendMessage: SendMessage = commandsExecutorService.executeCommand(update)

        val keyboard = ReplyKeyboardMarkup()
        keyboard.resizeKeyboard = true
        keyboard.keyboard = listOf(
                KeyboardRow().apply {
                    add(KeyboardButton("Помощь"))
                    add(KeyboardButton("Показать список товаров"))
                }
        )

        sendMessage.replyMarkup = keyboard
        return sendMessage
    }

    fun sendInlineKeyBoardMessage(chatId: String, messageText: String, buttonText: String, callbackDataParameter: String) {
        val sendMessage = SendMessage()
        val inlineKeyboardMarkup = InlineKeyboardMarkup().apply {
            keyboard = listOf(
                    listOf(
                            InlineKeyboardButton(buttonText).apply {
                                callbackData = callbackDataParameter
                            }
                    )
            )
        }
        sendMessage.text = messageText
        sendMessage.chatId = chatId
        sendMessage.replyMarkup = inlineKeyboardMarkup

        try {
            execute(sendMessage)
        } catch (e: TelegramApiRequestException) {
            if (e.apiResponse == "Forbidden: bot was blocked by the user") {
                commandsExecutorService.removeAllRecordsForUser(chatId)
            }
        }
    }
}