package com.skylsv.productmonitoringbot.services

import com.skylsv.productmonitoringbot.bot.ProductMonitoringBot
import com.skylsv.productmonitoringbot.data.Product
import com.skylsv.productmonitoringbot.data.RozetkaProductInfo
import com.skylsv.productmonitoringbot.data.Seller
import com.skylsv.productmonitoringbot.repository.MonitoredProductsStorage
import com.skylsv.productmonitoringbot.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class RozetkaScheduledService(
        private val monitoredProductsStorage: MonitoredProductsStorage,
        private val rozetkaClient: RozetkaClient
) {

    @Autowired
    @Lazy
    private lateinit var telegramBot: ProductMonitoringBot

    @Scheduled(fixedDelay = 300000)
    fun processSubscribedProducts() {
        monitoredProductsStorage.findAll()
                .filter { it.seller == Seller.ROZETKA }
                .forEach {product ->
                    val currentProductInfo = rozetkaClient.getProductInfo(product.productId)
                    if (currentProductInfo.priceString != product.price || currentProductInfo.sellStatus != product.state) {
                        notifyUserAboutUpdate(product, currentProductInfo)
                        product.price = currentProductInfo.priceString
                        product.state = currentProductInfo.sellStatus
                        monitoredProductsStorage.deleteByProductIdAndSeller(product.productId, product.seller)
                        monitoredProductsStorage.save(product)
                    }
                }
    }

    private fun notifyUserAboutUpdate(product: Product, currentProductInfo: RozetkaProductInfo) {
        var message = ""
        if (product.price != currentProductInfo.priceString) {
            message += "Изменилась цена: было - ${product.price}, стало - ${currentProductInfo.priceString}\n"
        }
        if (product.state != currentProductInfo.sellStatus) {
            message += "Изменился статус товара: был - ${Constants.ROZETKA_STATUSES[product.state]}" +
                    ", стал - ${Constants.ROZETKA_STATUSES[currentProductInfo.sellStatus]}\n"
        }
        if (message.isNotEmpty()) {
            message += product.productLink
        }
        telegramBot.sendInlineKeyBoardMessage(product.chatId, message, "Отписаться", "remove|${product.productId}|${product.seller.name}")
    }

}