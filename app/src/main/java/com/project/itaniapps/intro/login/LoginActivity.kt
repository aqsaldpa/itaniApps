package com.project.itaniapps.intro.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.intro.register.RegisterActivity
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
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            if (checkvalidation(email, password)) {
                login()
            }
        }

    }

    private fun checkvalidation(email: String, pass: String): Boolean {
        if (email.isEmpty()) {
            binding.email.error = "Masukkan Email Anda"
            binding.email.requestFocus()
        } else if (pass.isEmpty()) {
            binding.password.error = "Masukkan Password Anda"
            binding.password.requestFocus()
        } else if (!isValidEmail(email)) {
            binding.email.error = "Email tidak sesuai !"
            binding.email.requestFocus()
        } else {
            return true
        }
        return false
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun login() {
        if (db.checkUserLogin(
                binding.email.text.toString().trim(),
                binding.password.text.toString().trim()
            )
        ) {
            Toast.makeText(
                applicationContext,
                "Login Sukses",
                Toast.LENGTH_SHORT
            ).show()
            var name = db.getUser(binding.email.text.toString()).username
            var emails = db.getUser(binding.email.text.toString()).email
            var adresss = db.getUser(binding.email.text.toString()).address
            var phonenumber = db.getUser(binding.email.text.toString()).number
            sharedPref.setSignIn(true)
            sharedPref.setUsertoSharedPref(
                username = name,
                email = emails,
                address = adresss,
                number = phonenumber,
            )
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            binding.password.clearFocus()
            binding.email.clearFocus()

            Toast.makeText(
                applicationContext,
                "User tidak terdaftar , Silahkan Daftar dahulu",
                Toast.LENGTH_SHORT
            ).show()

        }

    }
}
