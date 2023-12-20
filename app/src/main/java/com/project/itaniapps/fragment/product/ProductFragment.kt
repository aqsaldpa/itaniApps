package com.project.itaniapps.fragment.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.R
import com.project.itaniapps.fragment.product.adapter.ProductAdapter

class ProductFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    private lateinit var db: DatabaseHelper

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = DatabaseHelper(requireContext())

        db.getAllProduct()


        val listProduct = db.getAllProduct()
        val itemAdapter = ProductAdapter(requireContext(), listProduct)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvProduct)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemAdapter

    }
}