package com.skylsv.productmonitoringbot.data

import com.google.gson.annotations.SerializedName

data class RozetkaProductInfo (
        @SerializedName("price") val price: Long,
        @SerializedName("price_formatted") val priceString: String,
        @SerializedName("sell_status") val sellStatus: String
)