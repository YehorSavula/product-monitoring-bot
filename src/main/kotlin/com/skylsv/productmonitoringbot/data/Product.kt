package com.skylsv.productmonitoringbot.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Product : Cloneable {

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

    public override fun clone(): Product {
        val product = Product()
        product.id = this.id
        product.chatId = this.chatId
        product.price = this.price
        product.productId = this.productId
        product.productLink = this.productLink
        product.seller = this.seller
        product.state = this.state
        return product
    }
}