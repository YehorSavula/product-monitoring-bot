package com.skylsv.productmonitoringbot.commands

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class FailCommand : Command {
    override fun canHandle(text: String) = text == "/fail"

    override fun execute(update: Update): SendMessage {
        val sendMessage = SendMessage()
        sendMessage.text = "Что-то пошло не так, обратитесь к администратору @YehorSavula"
        sendMessage.chatId = update.message.chatId.toString()

        return sendMessage
    }
}