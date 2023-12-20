package com.project.itaniapps.fragment.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.fragment.home.news.DetailNewsActivity
import com.project.itaniapps.R
import com.project.itaniapps.model.News


class HomeAdapter(val context: Context, private val newslist: ArrayList<News>) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    private lateinit var db: DatabaseHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.items_news, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newslist.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        db = DatabaseHelper(context)

        holder.type.text = newslist[position].type
        holder.judul.text = newslist[position].title
        holder.tanggal.text = newslist[position].date
        holder.desc.text = newslist[position].desc
        Glide.with(holder.gambar.context)
            .load(newslist[position].image)
            .into(holder.gambar)
        holder.item.setOnClickListener {
            val idNewsPosition : String = newslist[position].id.toString()
            val intent = Intent(context, DetailNewsActivity::class.java)
            intent.putExtra(
                "idNews",idNewsPosition
            )
            context.startActivity(intent)
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: LinearLayout = itemView.findViewById(R.id.item)
        val gambar: ImageView = itemView.findViewById(R.id.iv_image_news)
        val type: TextView = itemView.findViewById(R.id.tv_typeNews)
        val judul: TextView = itemView.findViewById(R.id.tv_judulnews)
        val desc: TextView = itemView.findViewById(R.id.tv_Deskripsinews)
        val tanggal: TextView = itemView.findViewById(R.id.tv_date)
    }
}