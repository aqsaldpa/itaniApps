package com.project.itaniapps.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.itaniapps.R
import com.project.itaniapps.fragment.home.adapter.HomeAdapter
import com.project.itaniapps.model.Const

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listNews = Const.getNews()
        val itemAdapter = HomeAdapter(listNews)
        val date = view.findViewById<TextView>(R.id.date)
        val title = view.findViewById<TextView>(R.id.header_title)
        val image = view.findViewById<ImageView>(R.id.imageHeader)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvNews)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemAdapter
        title.text = listNews.first().title

        date.text = listNews.first().date



        Glide.with(image.context)
            .load(listNews.first().image)
            .into(image)
    }
}