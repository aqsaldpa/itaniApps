package com.project.itaniapps.fragment.home.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.R
import com.project.itaniapps.databinding.ActivityDetailNewsBinding
import com.project.itaniapps.home.HomeActivity

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding
    private lateinit var db: DatabaseHelper

    private var idNews: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_layout))
        db = DatabaseHelper(this)
        idNews = intent.getStringExtra("idNews").toString()
        binding.headerTitle.text = db.getNewsById(idNews).title
        binding.date.text = db.getNewsById(idNews).date
        binding.typeNews.text = db.getNewsById(idNews).type
        binding.headerDesc.text = db.getNewsById(idNews).desc
        Glide.with(binding.imageHeader)
            .load(db.getNewsById(idNews).image)
            .into(binding.imageHeader)
        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }

        binding.updateNews.setOnClickListener {
            val intent = Intent(this, UpdateNewsActivity::class.java)
            intent.putExtra(
                "idNews",idNews
            )
            startActivity(intent)

        }

        binding.deleteNews.setOnClickListener {
            db.deleteNewsbyId(idNews)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            Toast.makeText(
                applicationContext,
                "Berhasil Dihapus",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}