package com.jjariza.productsmeli.models

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("id")
    var id: String = "",

    @SerializedName("site_id")
    var site_id: String = "",

    @SerializedName("title")
    var title: String = "",

//        @SerializedName("seller")
//        var seller: Seller,

//        @SerializedName("prices")
//        var prices: Prices,

    @SerializedName("price")
    var price: Double = 0.0,

    @SerializedName("sale_price")
    var sale_price: Double = 0.0,

    @SerializedName("currency_id")
    var currency_id: String = "",

    @SerializedName("available_quantity")
    var available_quantity: Int = 0,

    @SerializedName("sold_quantity")
    var sold_quantity: Int = 0,

    @SerializedName("buying_mode")
    var buying_mode: String = "",

    @SerializedName("listing_type_id")
    var listing_type_id: String = "",

    @SerializedName("stop_time")
    var stop_time: String = "",

    @SerializedName("condition")
    var condition: String = "",

    @SerializedName("permalink")
    var permalink: String = "",

    @SerializedName("thumbnail")
    var thumbnail: String = "",

    @SerializedName("thumbnail_id")
    var thumbnail_id: String = "",

    @SerializedName("accepts_mercadopago")
    var accepts_mercadopago: Boolean,

    @SerializedName("original_price")
    var original_price: Double = 0.0,

    @SerializedName("category_id")
    var category_id: String = "",

    @SerializedName("official_store_id")
    var official_store_id: String = "",

    @SerializedName("domain_id")
    var domain_id: String = "",

    @SerializedName("catalog_product_id")
    var catalog_product_id: String = "",

    @SerializedName("catalog_listing")
    var catalog_listing: Boolean,

    @SerializedName("use_thumbnail_id")
    var use_thumbnail_id: Boolean,

    @SerializedName("order_backend")
    var order_backend: Int = 0,

    @SerializedName("shipping")
    var shipping: Shipping,

    @SerializedName("address")
    var address: Address

){
    data class Shipping(
        @SerializedName("free_shipping")
        var freeShipping: Boolean

    )

    data class Address(
        @SerializedName("state_name")
        var stateName: String = "",

        @SerializedName("city_name")
        var cityName: String = "",
    )
}