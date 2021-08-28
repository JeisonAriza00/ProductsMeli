package com.jjariza.productsmeli.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjariza.productsmeli.R
import com.jjariza.productsmeli.activities.ProductListActivity
import com.jjariza.productsmeli.databinding.HolderProductBinding
import com.jjariza.productsmeli.models.Results
import com.jjariza.productsmeli.utils.toPrice
import com.squareup.picasso.Picasso

class ProductAdapter(var activity: ProductListActivity ,var results: ArrayList<Results>): RecyclerView.Adapter<ProductAdapter.HolderProduct>() {

    var onClickProduct: (position: Int) -> Unit = { _: Int ->}

    override fun getItemCount(): Int = results.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderProduct {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_product, parent, false)
        return HolderProduct(view)
    }

    override fun onBindViewHolder(holder: HolderProduct, position: Int) {
        Picasso.get().load(results[position].thumbnail).fit().into(holder.binding.imageProduct)
        holder.binding.textNameProduct.text = results[position].title
        holder.binding.textPriceProduct.text = toPrice(results[position].price.toString())
        holder.binding.textFreeShipping.visibility = if(results[position].shipping.freeShipping) View.VISIBLE else View.GONE
        holder.binding.constraintProduct.setOnClickListener {
            onClickProduct(position)
        }
    }

    class HolderProduct(view: View): RecyclerView.ViewHolder(view){
        var binding: HolderProductBinding = HolderProductBinding.bind(view)
    }

}
