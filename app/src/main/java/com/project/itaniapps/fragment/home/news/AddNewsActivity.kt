package com.project.itaniapps.fragment.home.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.R
import com.project.itaniapps.databinding.ActivityAddNewsBinding
import com.project.itaniapps.model.News

class AddNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewsBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar_layout))
        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }

        binding.addNewsBtn.setOnClickListener {
            submitNews()
        }
        db = DatabaseHelper(this)

    }

    private fun submitNews() {
        val imageUrl = binding.urlImageNews.text.toString().trim()
        val newsType = binding.typeNews.text.toString().trim()
        val newsTitle = binding.titleNews.text.toString().trim()
        val descTitle = binding.descNews.text.toString().trim()
        val newsDate = binding.dateNews.text.toString().trim()

        if (checkvalidation(newsType, newsTitle,descTitle, newsDate, imageUrl)) {
            val news = News(
                image = imageUrl,
                title = newsTitle,
                desc= descTitle,
                type = newsType,
                date = newsDate,
            )
            db.submitNews(news)
            Toast.makeText(this, "Berhasil menambahkan berita", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun checkvalidation(
        type: String,
        title: String,
        desc: String,
        date: String,
        url: String
    ): Boolean {
        if (type.isEmpty()) {
            binding.typeNews.error = "Masukkan Tipe Berita"
            binding.typeNews.requestFocus()
        } else if (title.isEmpty()) {
            binding.titleNews.error = "Masukkan Judul Berita"
            binding.titleNews.requestFocus()
        } else if (date.isEmpty()) {
            binding.dateNews.error = "Masukkan Tanggal Berita"
            binding.dateNews.requestFocus()
        } else if (desc.isEmpty()) {
            binding.descNews.error = "Masukkan Deskripsi Berita"
            binding.descNews.requestFocus()
        } else if (url.isEmpty()) {
            binding.urlImageNews.error = "Masukkan Link Foto Berita"
            binding.urlImageNews.requestFocus()
        } else {
            return true
        }
        return false
    }

}