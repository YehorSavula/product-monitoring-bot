package com.skylsv.productmonitoringbot.data

data class Product (
        val seller: Seller,
        val productLink: String,
        val chatId: String,
        var price: String,
        var state: String,
        val productId: String
)