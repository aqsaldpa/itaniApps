package com.project.itaniapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.MenuItem
import android.widget.Toast
import com.project.itaniapps.databinding.ActivityLoginBinding
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
        val password = binding.password.text.toString().trim()
        val confirmPass = binding.confirmPassword.text.toString().trim()

        if (checkvalidation(username, password, confirmPass)) {
            val user = User(username = username, password = password)
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
        } else {
            return true
        }
        return false
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