package com.project.itaniapps.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.R
import com.project.itaniapps.config.SharedPref
import com.project.itaniapps.fragment.home.adapter.HomeAdapter


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private val sharedPref by lazy {
        SharedPref(requireContext())
    }
    private lateinit var db: DatabaseHelper
    var username: String = ""

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = DatabaseHelper(requireContext())

        db.getAllNews()


        val listNews = db.getAllNews()
        val itemAdapter = HomeAdapter(requireContext(), listNews)
        val tvusername = view.findViewById<TextView>(R.id.username)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvNews)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemAdapter
        username = sharedPref.getStringFromSharedPref("username").toString()
        tvusername.text = "Selamat Datang, $username !"

    }
}