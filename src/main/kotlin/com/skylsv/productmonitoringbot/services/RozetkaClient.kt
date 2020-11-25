package com.skylsv.productmonitoringbot.services

import com.google.gson.Gson
import com.skylsv.productmonitoringbot.data.RozetkaProductInfo
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RozetkaClient {

    private val GET_PRODUCT_INFO = "https://common-api.rozetka.com.ua/v2/goods/get-price/?id=%s"
    private val logger = LoggerFactory.getLogger(RozetkaClient::class.java)

    fun getProductInfo(productId: String?): RozetkaProductInfo {
        return Gson().fromJson(executeGetRequest(GET_PRODUCT_INFO.format(productId)), RozetkaProductInfo::class.java)
    }

    private fun executeGetRequest(requestUrl: String): String {
        val client = HttpClient(Apache)
        logger.info("Sending request to the $requestUrl")

        return runBlocking {
            client.get(requestUrl)
        }
    }
}