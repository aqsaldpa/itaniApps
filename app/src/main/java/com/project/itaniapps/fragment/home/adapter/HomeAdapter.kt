package com.project.itaniapps.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.itaniapps.R
import com.project.itaniapps.model.News

class HomeAdapter(private val newslist: ArrayList<News>) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.items_news, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newslist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listNews = newslist[position]

        holder.type.text = listNews.type
        holder.judul.text = listNews.title
        holder.tanggal.text = listNews.date
        Glide.with(holder.gambar.context)
            .load(listNews.image)
            .into(holder.gambar)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gambar: ImageView = itemView.findViewById(R.id.iv_image_news)
        val type: TextView = itemView.findViewById(R.id.tv_typeNews)
        val judul: TextView = itemView.findViewById(R.id.tv_judulnews)
        val tanggal: TextView = itemView.findViewById(R.id.tv_date)
    }
}