package com.skylsv.productmonitoringbot.repository

import com.skylsv.productmonitoringbot.data.Product
import com.skylsv.productmonitoringbot.data.Seller
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MonitoredProductsStorage : MongoRepository<Product, String> {

    fun findByChatId(chatId: String) : List<Product>

    fun deleteByProductIdAndSeller(productId: String, seller: Seller)
}