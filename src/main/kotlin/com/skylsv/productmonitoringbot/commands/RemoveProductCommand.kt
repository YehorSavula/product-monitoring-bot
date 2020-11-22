package com.skylsv.productmonitoringbot.commands

import com.skylsv.productmonitoringbot.data.Seller
import com.skylsv.productmonitoringbot.repository.MonitoredProductsStorage
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class RemoveProductCommand(
        private val monitoredProductsStorage: MonitoredProductsStorage
) : Command {

    override fun canHandle(text: String): Boolean = text == "remove"

    override fun execute(update: Update): SendMessage {
        val callbackData = update.callbackQuery.data
        val productId = callbackData.split("|")[1]
        val seller = callbackData.split("|")[2]

        monitoredProductsStorage.deleteByProductIdAndSeller(productId, Seller.valueOf(seller))
        return SendMessage(update.callbackQuery.from.id.toString(), "Продукт удален")
    }
}