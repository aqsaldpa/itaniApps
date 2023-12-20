package com.project.itaniapps.fragment.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.itaniapps.R
import com.project.itaniapps.config.SharedPref
import com.project.itaniapps.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private val sharedPref by lazy {
        SharedPref(this)
    }
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_layout))
        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }

        binding.profileEmail.setText(
            sharedPref.getStringFromSharedPref("email").toString()
        )
        binding.profilePhonenumb.setText(
            sharedPref.getStringFromSharedPref("number").toString()
        )
        binding.profileFullname.setText(
            sharedPref.getStringFromSharedPref("username").toString()
        )
        binding.profileAddress.setText(
            sharedPref.getStringFromSharedPref("address").toString()
        )
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

}