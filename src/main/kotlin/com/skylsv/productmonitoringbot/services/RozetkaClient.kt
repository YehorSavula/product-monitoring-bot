package com.skylsv.productmonitoringbot.services

import com.google.gson.Gson
import com.skylsv.productmonitoringbot.data.RozetkaProductInfo
import org.slf4j.LoggerFactory
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RozetkaClient {

    private val GET_PRODUCT_INFO = "https://common-api.rozetka.com.ua/v2/goods/get-price/?id={productId}"
    private val logger = LoggerFactory.getLogger(RozetkaClient::class.java)

    fun getProductInfo(productId: String?): RozetkaProductInfo {
        val restTemplate = RestTemplate(getClientHttpRequestFactory())
        logger.info("Sending request to the ROZETKA, getting info for product = {}", productId)
        return Gson().fromJson(restTemplate.getForObject(GET_PRODUCT_INFO, String::class.java, productId),
                RozetkaProductInfo::class.java)
    }

    fun getClientHttpRequestFactory() : ClientHttpRequestFactory {
        val clientHttpRequestFactory = HttpComponentsClientHttpRequestFactory()
        clientHttpRequestFactory.setConnectTimeout(5000)
        return clientHttpRequestFactory
    }
}