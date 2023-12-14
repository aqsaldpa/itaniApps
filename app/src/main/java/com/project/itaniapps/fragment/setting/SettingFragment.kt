package com.project.itaniapps.fragment.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.project.itaniapps.R
import com.project.itaniapps.config.SharedPref
import com.project.itaniapps.home.HomeActivity
import com.project.itaniapps.intro.splash.SplashScreenActivity

class SettingFragment : Fragment() {
    private val sharedPref by lazy {
        SharedPref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardAddNews = view.findViewById<CardView>(R.id.cardAddNews)
        val cardAddProduct = view.findViewById<CardView>(R.id.cardAddProduct)
        val cardSeeProfile = view.findViewById<CardView>(R.id.cardProfile)
        val cardLogout = view.findViewById<CardView>(R.id.cardLogout)

        cardLogout.setOnClickListener {
            sharedPref.setSignIn(false)
            startActivity(Intent(activity, SplashScreenActivity::class.java))
            Toast.makeText(activity, "Logout Berhasil", Toast.LENGTH_SHORT).show()
        }

    }
}