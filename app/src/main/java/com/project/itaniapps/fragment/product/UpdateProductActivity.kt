package com.project.itaniapps.fragment.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.itaniapps.R
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.databinding.ActivityUpdateProductBinding
import com.project.itaniapps.home.HomeActivity
import com.project.itaniapps.model.Product

class UpdateProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProductBinding
    private lateinit var db: DatabaseHelper
    private var idProduct: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_layout))
        db = DatabaseHelper(this)

        idProduct = intent.getStringExtra("idProduct").toString()

        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }

        binding.changeProductBtn.setOnClickListener {
            updateProduct()
        }
    }

    private fun updateProduct() {
        val nameProduct = binding.productName.text.toString().trim()
        val stockProduct = binding.productStock.text.toString().trim().toInt()
        val priceProduct = binding.productPrice.text.toString().trim().toInt()
        val imgProd = binding.urlImageProduct.text.toString().trim()

        if (checkvalidation(nameProduct, imgProd, stockProduct, priceProduct)) {
            val product = Product(
                id = idProduct.toInt(),
                name = nameProduct,
                stock = stockProduct,
                image = imgProd,
                price = priceProduct,
            )
            db.updateProduct(
                product
            )
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            Toast.makeText(
                applicationContext,
                "Berhasil Diubah",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun checkvalidation(
        name: String,
        img: String,
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
        } else if (img.isEmpty()) {
            binding.urlImageProduct.error = "Masukkan URL Produk"
            binding.urlImageProduct.requestFocus()
        } else {
            return true
        }
        return false
    }
}