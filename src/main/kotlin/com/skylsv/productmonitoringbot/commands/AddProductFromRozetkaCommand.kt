package com.skylsv.productmonitoringbot.commands

import com.skylsv.productmonitoringbot.data.Product
import com.skylsv.productmonitoringbot.data.Seller
import com.skylsv.productmonitoringbot.repository.MonitoredProductsStorage
import com.skylsv.productmonitoringbot.services.RozetkaClient
import com.skylsv.productmonitoringbot.utils.Constants
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class AddProductFromRozetkaCommand(
        private val monitoredProductsStorage: MonitoredProductsStorage,
        private val rozetkaClient: RozetkaClient
) : Command {

    private val ROZETKA_REGEXP = Regex(pattern = """https://rozetka.com.ua/.*/p(\d+)/""")

    override fun canHandle(text: String): Boolean = ROZETKA_REGEXP.matches(text)

    override fun execute(update: Update): SendMessage {
        val productLink = update.message.text
        val productId = ROZETKA_REGEXP.find(productLink)?.groupValues?.get(1)

        val productInfo = rozetkaClient.getProductInfo(productId)

        monitoredProductsStorage.save(Product(Seller.ROZETKA, productLink, update.message.chatId.toString(),
                productInfo.priceString, productInfo.sellStatus, productId!!))
        return SendMessage(update.message.chatId.toString(), "Продукт добавлен!\nЦена: ${productInfo.price}, " +
                "Статус: ${Constants.ROZETKA_STATUSES[productInfo.sellStatus]}")
    }
}