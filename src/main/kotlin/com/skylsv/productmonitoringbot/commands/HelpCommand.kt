package com.skylsv.productmonitoringbot.commands

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class HelpCommand() : Command {

    override fun canHandle(text: String): Boolean = text == "Помощь"

    override fun execute(update: Update): SendMessage {
        val sendMessage = SendMessage()
        sendMessage.text = "Чтобы начать отслеживать, просто вставьте ссылку на товар и нажмите Enter"
        sendMessage.chatId = update.message.chatId.toString()

        return sendMessage
    }
}