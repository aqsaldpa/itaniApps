package com.project.itaniapps.intro.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.itaniapps.DatabaseHelper
import com.project.itaniapps.RegisterActivity
import com.project.itaniapps.config.SharedPref
import com.project.itaniapps.databinding.ActivityLoginBinding
import com.project.itaniapps.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val sharedPref by lazy {
        SharedPref(this)
    }
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()
            if (checkvalidation(username, password)) {
                login()
            }
        }

    }

    private fun checkvalidation(username: String, pass: String): Boolean {
        if (username.isEmpty()) {
            binding.username.error = "Masukkan Username Anda"
            binding.username.requestFocus()
        } else if (pass.isEmpty()) {
            binding.password.error = "Masukkan Password Anda"
            binding.password.requestFocus()
        } else {
            return true
        }
        return false
    }

    private fun login() {
        if (!db.checkUserLogin(
                binding.username.text.toString().trim(),
                binding.password.toString().trim()
            )
        ) {
            Toast.makeText(
                applicationContext,
                db.checkUserLogin(
                    binding.username.text.toString().trim(),
                    binding.password.toString().trim()
                ).toString(),
                Toast.LENGTH_SHORT
            ).show()
//            Toast.makeText(
//                applicationContext, "Login Berhasil , Selamat Datang", Toast.LENGTH_SHORT
//            ).show()
//            val username = binding.username.text.trim().toString()
//            sharedPref.setName(username)
//            sharedPref.setSignIn(true)
//            startActivity(Intent(this, HomeActivity::class.java))
//            finish()
        } else {
            Toast.makeText(
                applicationContext,
                db.checkUserLogin(
                    binding.username.text.toString().trim(),
                    binding.password.toString().trim()
                ).toString(),
                Toast.LENGTH_SHORT
            ).show()
//            Toast.makeText(
//                    applicationContext,
//            "User tidak terdaftar , Silahkan Daftar dahulu",
//            Toast.LENGTH_SHORT
//            ).show()
        }

    }
}
