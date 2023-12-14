package com.project.itaniapps.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.project.itaniapps.R
import com.project.itaniapps.databinding.ActivityHomeBinding
import com.project.itaniapps.fragment.home.HomeFragment
import com.project.itaniapps.fragment.product.ProductFragment
import com.project.itaniapps.fragment.setting.SettingFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLayout)


        val homeFragment = HomeFragment()
        val productFragment = ProductFragment()
        val settingFragment = SettingFragment()

        setCurrentFragment(homeFragment)

        binding.bnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportActionBar?.show()
                    setCurrentFragment(homeFragment)
                }

                R.id.notification -> {
                    supportActionBar?.show()
                    setCurrentFragment(productFragment)
                }

                R.id.account -> {
                    supportActionBar?.show()
                    setCurrentFragment(settingFragment)
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_container, fragment)
            commit()
        }
}