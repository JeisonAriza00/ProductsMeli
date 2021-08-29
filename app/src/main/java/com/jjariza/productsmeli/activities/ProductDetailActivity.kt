package com.jjariza.productsmeli.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jjariza.productsmeli.R
import com.jjariza.productsmeli.databinding.ActivityProductDetailBinding
import com.jjariza.productsmeli.models.Results
import com.jjariza.productsmeli.utils.get
import com.jjariza.productsmeli.utils.put
import com.jjariza.productsmeli.utils.toJson
import com.jjariza.productsmeli.utils.toPrice
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity(), ProductDetailActivityVM.Listener {

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
    lateinit var viewModel: ProductDetailActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
        viewModel = ViewModelProvider(this).get(ProductDetailActivityVM::class.java)
        viewModel.listener = this
        binding.viewModel = viewModel

        Picasso.get().load(product.thumbnail).fit().into(binding.imageProduct)
        viewModel.nameProduct.set(product.title)
        viewModel.priceProduct.set(toPrice(product.price.toString()))
        viewModel.freeShipping.set(product.shipping.freeShipping)
        viewModel.acceptMercadoPago.set(product.accepts_mercadopago)
        viewModel.soldQuantity.set(product.sold_quantity.toString())
        viewModel.availableQuantity.set(product.available_quantity.toString())
        viewModel.stateName.set(product.address.stateName)
        viewModel.cityName.set(product.address.cityName)
    }
}

class ProductDetailActivityVM: ViewModel(){
    var listener : Listener? = null
    var nameProduct = ObservableField<String>("")
    var priceProduct = ObservableField<String>("")
    var freeShipping = ObservableField<Boolean>(false)
    var acceptMercadoPago = ObservableField<Boolean>(false)
    var soldQuantity = ObservableField<String>("")
    var availableQuantity = ObservableField<String>("")
    var stateName = ObservableField<String>("")
    var cityName = ObservableField<String>("")

    interface Listener{

    }
}