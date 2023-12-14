package com.project.itaniapps.config

import android.content.Context
import android.content.SharedPreferences
import com.project.itaniapps.config.General.IS_LOGIN
import com.project.itaniapps.config.General.PREF_NAME
import com.project.itaniapps.config.General.PRIVATE_MODE
import com.project.itaniapps.fragment.setting.SettingFragment
import com.project.itaniapps.intro.login.LoginActivity

class SharedPref(context: Context) {

    var pref: SharedPreferences? = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    fun setSignIn(isLogin: Boolean) {
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }

    fun setName(name: String) {
        editor?.putString("name", name)
        editor?.commit()
    }

    fun isLogin(): Boolean? {
        return pref?.getBoolean(IS_LOGIN, false)
    }

    fun isLogout() {
        editor?.clear()
        editor?.commit()
    }

}