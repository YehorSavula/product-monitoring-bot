package com.skylsv.productmonitoringbot.repository

import com.skylsv.productmonitoringbot.data.Product
import com.skylsv.productmonitoringbot.data.Seller
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MonitoredProductsStorage : CrudRepository<Product, Long> {

    fun findByChatId(chatId: String) : List<Product>

    fun findBySellerAndChatIdAndProductId(seller: Seller, chatId: String, productId: String) : Product?

    fun deleteByChatId(chatId: String) : Long
}