package ru.maxdexter.albumsearch.presenter

import android.content.Context
import android.content.SharedPreferences
import ru.maxdexter.albumsearch.domain.common.Preferences
import javax.inject.Inject

class AppPreferences @Inject constructor(context: Context) : Preferences  {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("App_Pref", Context.MODE_PRIVATE)


    override fun setIsLogin(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    override fun getIsLogin(key: String): Boolean {
        return preferences.getBoolean(key, DEFAULT_LOGGED_VALUE)
    }

    override fun setLoggedUserEmail(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    override fun getLoggedUserEmail(key: String): String? {
        return preferences.getString(key, DEFAULT_EMAIL_VALUE)
    }

    companion object {
        const val DEFAULT_LOGGED_VALUE = false
        const val DEFAULT_EMAIL_VALUE = ""
    }
}