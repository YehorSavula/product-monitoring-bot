package com.skylsv.productmonitoringbot.commands

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

interface Command {

    fun canHandle(text: String) : Boolean

    fun execute(update: Update) : SendMessage
}