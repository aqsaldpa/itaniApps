package com.project.itaniapps.intro.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import com.project.itaniapps.config.General
import com.project.itaniapps.config.SharedPref
import com.project.itaniapps.databinding.ActivitySplashScreenBinding
import com.project.itaniapps.home.HomeActivity
import com.project.itaniapps.intro.login.LoginActivity


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val sharedPref by lazy {
        SharedPref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }

        runSplashScreen()
    }

    private fun runSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedPref.isLogin() == true) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(
                    Intent(this, LoginActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
            finish()
        }, General.HANDLER_TIME.toLong())
    }
}