package com.skylsv.productmonitoringbot.commands

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class StartCommand : Command {

    override fun canHandle(text: String): Boolean = text == "/start"

    override fun execute(update: Update): SendMessage {
        val sendMessage = SendMessage()
        sendMessage.text = "Привет, ${update.message.chat.firstName} ${update.message.chat.lastName} я умею отслеживать изменение наличия и цен товаров на розетке, я уведомлю тебя как только что-то изменится"
        sendMessage.chatId = update.message.chatId.toString()

        return sendMessage
    }
}