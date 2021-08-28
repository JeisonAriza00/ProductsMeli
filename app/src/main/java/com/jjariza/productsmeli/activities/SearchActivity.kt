package com.jjariza.productsmeli.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.jjariza.productsmeli.R
import com.jjariza.productsmeli.databinding.ActivitySearchBinding
import com.jjariza.productsmeli.models.SearchData
import com.jjariza.productsmeli.rest.ApiService
import com.jjariza.productsmeli.utils.fromJson
import com.squareup.picasso.Picasso

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var binding: ActivitySearchBinding
    lateinit var searchView: SearchView
    val apiService = ApiService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        searchView = binding.svSearch
        searchView.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        apiService.searchProducts(query, "50", "0").subscribe {
            Log.e("Response -->", Gson().toJson(it))
            val searchData = fromJson(Gson().toJson(it), SearchData::class.java)
            ProductListActivity.startActivity(this, searchData)
        }
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {

        return false
    }
}
