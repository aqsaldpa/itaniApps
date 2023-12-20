package com.project.itaniapps.fragment.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.R
import com.project.itaniapps.databinding.ActivityAddProductBinding
import com.project.itaniapps.model.Product

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_layout))
        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }
        binding.addProductBtn.setOnClickListener {
            submitProduct()
        }
        db = DatabaseHelper(this)
    }

    private fun submitProduct() {
        val imageUrl = binding.urlImageProduct.text.toString().trim()
        val nameProduct = binding.productName.text.toString().trim()
        val stockProduct = binding.productStock.text.toString().trim().toInt()
        val priceProduct = binding.productPrice.text.toString().trim().toInt()

        if (checkvalidation(nameProduct, imageUrl, stockProduct, priceProduct)) {
            val product = Product(
                name = nameProduct,
                image = imageUrl,
                stock = stockProduct,
                price = priceProduct,
            )
            db.submitProduct(product)
            Toast.makeText(this, "Berhasil menambahkan berita", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun checkvalidation(
        name: String,
        url: String,
        stock: Int,
        price: Int,
    ): Boolean {
        if (name.isEmpty()) {
            binding.productName.error = "Masukkan Nama Produk"
            binding.productName.requestFocus()
        } else if (stock == 0) {
            binding.productStock.error = "Masukkan Stock Produk"
            binding.productStock.requestFocus()
        } else if (price == 0) {
            binding.productPrice.error = "Masukkan Harga Produk"
            binding.productPrice.requestFocus()
        } else if (url.isEmpty()) {
            binding.urlImageProduct.error = "Masukkan Link Foto Product"
            binding.urlImageProduct.requestFocus()
        } else {
            return true
        }
        return false
    }
}