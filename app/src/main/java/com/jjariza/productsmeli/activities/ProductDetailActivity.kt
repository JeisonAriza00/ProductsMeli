package com.jjariza.productsmeli.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jjariza.productsmeli.R
import com.jjariza.productsmeli.databinding.ActivityProductDetailBinding
import com.jjariza.productsmeli.models.Results
import com.jjariza.productsmeli.utils.get
import com.jjariza.productsmeli.utils.put
import com.jjariza.productsmeli.utils.toJson
import com.jjariza.productsmeli.utils.toPrice
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {

    companion object{
        fun startActivity(context: Context, product: Results){
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.put(product)
            context.startActivity(intent)
        }
    }

    private val product: Results by lazy {
        get(Results::class.java)
    }

    lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        Picasso.get().load(product.thumbnail).fit().into(binding.imageProduct)
        binding.textNameProduct.text = product.title
        binding.textPriceProduct.text = toPrice(product.price.toString())
        binding.textFreeShipping.visibility = if(product.shipping.freeShipping) View.VISIBLE else View.GONE
        binding.textMercadoPago.visibility = if(product.accepts_mercadopago) View.VISIBLE else View.GONE
        binding.textVendidasValue.text = product.sold_quantity.toString()
        binding.textDisponiblesValue.text = product.available_quantity.toString()
        binding.textUbicacionValue.text = "${product.address.stateName} - ${product.address.cityName}"
    }
}