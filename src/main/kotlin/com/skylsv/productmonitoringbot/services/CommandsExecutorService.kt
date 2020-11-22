package com.skylsv.productmonitoringbot.services

import com.skylsv.productmonitoringbot.commands.Command
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class CommandsExecutorService(
        private val commandsList: List<Command>
) {

    fun executeCommand(update: Update): SendMessage {
        val command = if (update.hasCallbackQuery()) getCommandFromCallback(update) else getCommandFromMessage(update)
        val commandExecutor = commandsList.firstOrNull() { it.canHandle(command) }
        if (commandExecutor != null) {
            return commandExecutor.execute(update)
        }

        val chatId: String = if (update.hasMessage()) update.message.chatId.toString() else update.callbackQuery.from.id.toString()
        return SendMessage(chatId, "Smth wrong")
    }

    private fun getCommandFromCallback(update: Update): String {
        return update.callbackQuery.data.split("|")[0]
    }

    private fun getCommandFromMessage(update: Update): String {
        return update.message?.text ?: "/fail"
    }
}