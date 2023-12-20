package com.project.itaniapps.config

import android.content.Context
import android.content.SharedPreferences
import com.project.itaniapps.config.General.IS_LOGIN
import com.project.itaniapps.config.General.PREF_NAME

class SharedPref(context: Context) {

    var pref: SharedPreferences? = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    fun setSignIn(isLogin: Boolean) {
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }

    fun setUsertoSharedPref(username: String, address: String, number: String, email: String){
        editor?.putString("username",username)
        editor?.putString("address",address)
        editor?.putString("number",number)
        editor?.putString("email",email)
        editor?.commit()
    }

    fun isLogin(): Boolean? {
        return pref?.getBoolean(IS_LOGIN, false)
    }

    fun getStringFromSharedPref(value: String?): String? {
        return pref?.getString(value, "")
    }
}