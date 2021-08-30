package com.jjariza.productsmeli.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.jjariza.productsmeli.R
import com.jjariza.productsmeli.databinding.ActivitySearchBinding
import com.jjariza.productsmeli.models.SearchData
import com.jjariza.productsmeli.rest.ApiService
import com.jjariza.productsmeli.utils.State
import com.jjariza.productsmeli.utils.fromJson
import com.jjariza.productsmeli.utils.toJson
import com.squareup.picasso.Picasso

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener, SearchActivityVM.Listener {

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var binding: ActivitySearchBinding
    lateinit var viewModel: SearchActivityVM
    lateinit var searchView: SearchView
    lateinit var searchData: SearchData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel = ViewModelProvider(this).get(SearchActivityVM::class.java)
        viewModel.listener = this

        searchView = binding.svSearch
        searchView.setOnQueryTextListener(this)
        observers()
    }

    private fun observers(){
        viewModel.listSearchData.observe(this, Observer { state ->
            when (state){
                is State.Loading -> ""
                is State.Success -> {
                    this.searchData = state.responseTo()
                    ProductListActivity.startActivity(this, searchData)
                }
                is State.Failed -> ""
                is State.Empty -> ""
            }
        })
    }

    @SuppressLint("CheckResult")
    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.getSearchData(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }

}

class SearchActivityVM: ViewModel(){
    var listener: Listener? = null
    private lateinit var searchData: SearchData
    private val _listSearchData = MutableLiveData<State>()
    val listSearchData: LiveData<State> get() = _listSearchData


    @SuppressLint("CheckResult")
    fun getSearchData(query: String){
        val apiService = ApiService()
        _listSearchData.value = State.Loading
        apiService.searchProducts(query, "0").subscribe {
            searchData = fromJson(Gson().toJson(it), SearchData::class.java)
            _listSearchData.value = State.Success(searchData)
        }
    }

    interface Listener{
    }
}
