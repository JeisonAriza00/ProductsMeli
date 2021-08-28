package com.jjariza.productsmeli.utils

import android.app.Activity
import android.content.Intent
import com.google.gson.Gson
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun Intent.put(any: Any) {
    this.putExtra(any::class.java.name, toJson(any))
}

fun <T> Activity.get(classOfT: Class<T>): T {
    val json = this.intent.extras?.getString(classOfT.name, "{}") ?: "{}"
    return fromJson(json, classOfT)
}

fun toJson(any: Any): String = Gson().toJson(any)

fun <T> fromJson(json: String, classOfT: Class<T>): T {
    return try {
        Gson().fromJson(json, classOfT)
    } catch (error: Exception) {
        Gson().fromJson("{}", classOfT)
    }
}

fun toPrice(text: String?): String {
    if (text.isNullOrEmpty()) return "$ 0"
    val sym = DecimalFormatSymbols.getInstance()
    sym.groupingSeparator = '.'
    return try {
        DecimalFormat("$ ###,###", sym).format(text.replace(",", ".").toFloat())
    } catch (e: Exception) {
        ""
    }
}