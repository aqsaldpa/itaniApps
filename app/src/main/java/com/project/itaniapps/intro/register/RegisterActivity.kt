package com.project.itaniapps.intro.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.widget.Toast
import com.project.itaniapps.R
import com.project.itaniapps.config.DatabaseHelper
import com.project.itaniapps.databinding.ActivityRegisterBinding
import com.project.itaniapps.model.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_layout))

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        db = DatabaseHelper(this)

    }

    private fun registerUser() {
        val username = binding.username.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val address = binding.address.text.toString().trim()
        val phoneNumber = binding.number.text.toString().trim()
        val password = binding.password.text.toString().trim()
        val confirmPass = binding.confirmPassword.text.toString().trim()

        if (checkvalidation(email,username, password, confirmPass)) {
            val user = User(
                username = username,
                password = password,
                email = email,
                address = address,
                number = phoneNumber
            )
            if (!db.checkUser(username)) {
                db.registerUser(user)
                Toast.makeText(this, "User berhasil terdaftar", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "User ini sudah terdaftar", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun checkvalidation(
        email:String,
        username: String,
        pass: String,
        confirmPass: String
    ): Boolean {
        if (username.isEmpty()) {
            binding.username.error = "Masukkan Username Anda"
            binding.username.requestFocus()
        } else if (pass.isEmpty()) {
            binding.password.error = "Masukkan Password Anda"
            binding.password.requestFocus()
        } else if (pass != confirmPass) {
            binding.confirmPassword.error = "Password Anda tidak sama!"
            binding.confirmPassword.requestFocus()
        } else if (!isValidEmail(email)) {
            binding.email.error = "Email tidak sesuai !"
            binding.email.requestFocus()
        }  else {
            return true
        }
        return false
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}