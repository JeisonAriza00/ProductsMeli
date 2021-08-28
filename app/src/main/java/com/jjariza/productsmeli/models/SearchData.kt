package com.jjariza.productsmeli.models

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("site_id")
    var site_id: String,

    @SerializedName("query")
    var query: String,

    @SerializedName("paging")
    var paging: Paging,

    @SerializedName("results")
    var results: ArrayList<Results> =  ArrayList()
)