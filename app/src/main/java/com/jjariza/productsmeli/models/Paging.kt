package com.jjariza.productsmeli.models

import com.google.gson.annotations.SerializedName

data class Paging(
    @SerializedName("total")
    var total: Int = 0,

    @SerializedName("primary_results")
    var primary_results: Int = 0,

    @SerializedName("offset")
    var offset: Int = 0,

    @SerializedName("limit")
    var limit: Int = 0
)