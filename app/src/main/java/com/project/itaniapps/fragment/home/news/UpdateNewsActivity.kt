package com.project.itaniapps.fragment.home.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.itaniapps.R
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.databinding.ActivityUpdateNewsBinding
import com.project.itaniapps.home.HomeActivity
import com.project.itaniapps.model.News

class UpdateNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNewsBinding
    private lateinit var db: DatabaseHelper
    private var idNews: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_layout))
        db = DatabaseHelper(this)

        idNews = intent.getStringExtra("idNews").toString()

        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }

        binding.changeNewsBtn.setOnClickListener {
            updateNews()
        }
    }

    private fun updateNews() {
        val newsType = binding.typeNews.text.toString().trim()
        val newsTitle = binding.titleNews.text.toString().trim()
        val newsDate = binding.dateNews.text.toString().trim()
        val newsDesc = binding.descNews.text.toString().trim()
        val imgDate = binding.urlImageNews.text.toString().trim()

        if (checkvalidation(newsType, newsTitle, newsDate, newsDesc, imgDate)) {
            val news = News(
                id = idNews.toInt(),
                image = imgDate,
                title = newsTitle,
                desc = newsDesc,
                type = newsType,
                date = newsDate,
            )
            db.updateNews(
                news
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
        type: String,
        title: String,
        date: String,
        desc: String,
        url: String
    ): Boolean {
        if (type.isEmpty()) {
            binding.typeNews.error = "Masukkan Tipe Berita"
            binding.typeNews.requestFocus()
        } else if (title.isEmpty()) {
            binding.titleNews.error = "Masukkan Judul Berita"
            binding.titleNews.requestFocus()
        } else if (url.isEmpty()) {
            binding.urlImageNews.error = "Masukkan URL Berita"
            binding.urlImageNews.requestFocus()
        } else if (date.isEmpty()) {
            binding.dateNews.error = "Masukkan Tanggal Berita"
            binding.dateNews.requestFocus()
        } else if (desc.isEmpty()) {
            binding.descNews.error = "Masukkan Deskripsi Berita"
            binding.descNews.requestFocus()
        } else {
            return true
        }
        return false
    }
}