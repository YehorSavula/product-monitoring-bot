package com.skylsv.productmonitoringbot.commands

import com.skylsv.productmonitoringbot.bot.ProductMonitoringBot
import com.skylsv.productmonitoringbot.repository.MonitoredProductsStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class ShowProductsListCommand(
        private val monitoredProductsStorage: MonitoredProductsStorage
) : Command {

    @Autowired
    @Lazy
    private lateinit var telegramBot: ProductMonitoringBot

    override fun canHandle(text: String): Boolean = text == "Показать список товаров"

    override fun execute(update: Update): SendMessage {
        val monitoredProducts = monitoredProductsStorage.findByChatId(update.message.chatId.toString())

        monitoredProducts.forEach { product ->
            telegramBot.sendInlineKeyBoardMessage(product.chatId!!, product.productLink!!, "Отписаться",
                    "remove|${product.id}")
        }

        return SendMessage(update.message.chatId.toString(), "Вот ваши подписки")
    }
}