package com.skylsv.productmonitoringbot.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var seller: Seller? = null
    var productLink: String? = null
    var chatId: String? = null
    var price: String? = null
    var state: String? = null
    var productId: String? = null

    constructor(){}
    constructor(seller: Seller, productLink: String, chatId: String, price: String, state: String, productId: String) {
        this.seller = seller
        this.productLink = productLink
        this.chatId = chatId
        this.price = price
        this.state = state
        this.productId = productId
    }
}