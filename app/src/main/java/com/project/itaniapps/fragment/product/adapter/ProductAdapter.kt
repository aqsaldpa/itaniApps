package com.project.itaniapps.fragment.product.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.R
import com.project.itaniapps.fragment.product.UpdateProductActivity
import com.project.itaniapps.model.Product


class ProductAdapter(val context: Context, private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    private lateinit var db: DatabaseHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.items_product, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        db = DatabaseHelper(context)

        holder.name.text = productList[position].name
        holder.price.text = "Rp. ${productList[position].price}"
        holder.stock.text = productList[position].stock.toString()
        Glide.with(holder.gambar.context)
            .load(productList[position].image)
            .into(holder.gambar)
        holder.btnChangeProduct.setOnClickListener {
            val intent = Intent(context, UpdateProductActivity::class.java)
            intent.putExtra(
                "idProduct", productList[position].id.toString(),

                )
            context.startActivity(intent)

        }
        holder.btnDelete.setOnClickListener {
            db.deleteProductbyId(productList[position].id.toString())
            removeItem(position)
            Toast.makeText(
                context,
                "Berhasil Dihapus",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun removeItem(position: Int) {
        productList.removeAt(position)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambar: ImageView = itemView.findViewById(R.id.iv_image_product)
        val item: CardView = itemView.findViewById(R.id.itemcard)
        val name: TextView = itemView.findViewById(R.id.product_name)
        val price: TextView = itemView.findViewById(R.id.product_price)
        val stock: TextView = itemView.findViewById(R.id.product_stock)
        val btnDelete: Button = itemView.findViewById(R.id.delete_product)
        val btnChangeProduct: TextView = itemView.findViewById(R.id.change_product)
    }
}