package com.android.concept.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.concept.MainApplication
import com.android.concept.R
import com.android.concept.data.models.Product
import com.android.concept.ui.MainViewModel
import com.bumptech.glide.Glide

class RecyclerViewAdapterForProducts(var productList: MutableList<Product>, val isSavedProducts : Boolean,val viewModel: MainViewModel):RecyclerView.Adapter<RecyclerViewAdapterForProducts.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_product_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product : Product = productList[position]

        holder.textViewName.text = product.name
        holder.textViewPrice.text = product.price.toString() + "/-"

        Glide.with(MainApplication.appContext)
            .load(product.imageUrl)
            .into(holder.imageView)

        if(isSavedProducts) holder.button.text = "Delete"

        holder.button.setOnClickListener{
            if(isSavedProducts){
                println("Data fetched from db")
                productList.remove(product)
                notifyDataSetChanged()
                viewModel.deleteProductFromDb(product)
            }else{
                viewModel.addProductToDb(product)
            }
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val textViewName : TextView = view.findViewById(R.id.textViewProductName)
        val textViewPrice : TextView = view.findViewById(R.id.textViewPrice)
        val button: Button = view.findViewById(R.id.button)
        val imageView : ImageView = view.findViewById(R.id.product_image)

    }

}