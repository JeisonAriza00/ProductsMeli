package com.jjariza.productsmeli.rest

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("search")
    fun searchProducts(@Query("q") q: String,
                       @Query("limit") limit : String,
                       @Query("offset") offset : String): Observable<JsonObject>
}