package com.jjariza.productsmeli.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.jjariza.productsmeli.R
import com.jjariza.productsmeli.adapters.ProductAdapter
import com.jjariza.productsmeli.databinding.ActivityProductListBinding
import com.jjariza.productsmeli.models.Results
import com.jjariza.productsmeli.models.SearchData
import com.jjariza.productsmeli.utils.get
import com.jjariza.productsmeli.utils.put
import com.jjariza.productsmeli.utils.toJson

class ProductListActivity : AppCompatActivity() {

    companion object{
        fun startActivity(context: Context, searchData: SearchData){
            val intent = Intent(context, ProductListActivity::class.java)
            intent.put(searchData)
            context.startActivity(intent)
        }
    }

    private val searchData: SearchData by lazy {
        get(SearchData::class.java)
    }

    lateinit var binding: ActivityProductListBinding
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_list)
        binding.textQuantityResults.text = "${searchData.paging.total} Resultados"
        initAdapter()
    }

    fun initAdapter(){
        adapter = ProductAdapter(this, searchData.results)
        binding.recyclerProducts.adapter = adapter

        adapter.onClickProduct = { position ->
            val resutlado = searchData.results[position]
            ProductDetailActivity.startActivity(this, resutlado)
        }
    }
}