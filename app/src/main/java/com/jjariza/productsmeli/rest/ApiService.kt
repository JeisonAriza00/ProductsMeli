package com.jjariza.productsmeli.rest

import android.content.Context
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import io.reactivex.android.schedulers.AndroidSchedulers

private const val BASE_URL = "https://api.mercadolibre.com/sites/MCO/"

class ApiService(val context: Context) {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor(context))
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)

    private val apiInterface: ApiInterface by lazy {
        getRetrofitBuilder(
            okHttpClient,
            BASE_URL
        ).create(ApiInterface::class.java)
    }

    private fun rxConfig(jsonObjectObservable: Observable<JsonObject>): Observable<JsonObject> {
        return jsonObjectObservable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn(this::getError)
    }

    internal fun getError(throwable: Throwable): JsonObject {
        val jsonObject = JsonObject()
        jsonObject.addProperty("error", 1)
        jsonObject.addProperty("response", throwable.toString())
        return jsonObject
    }

    private fun getRetrofitBuilder(
        httpClient: OkHttpClient.Builder,
        url: String
    ) =
        Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun searchProducts(q: String, limit: String, offset: String ) = rxConfig(
        apiInterface.searchProducts(q, limit, offset)
    )
}