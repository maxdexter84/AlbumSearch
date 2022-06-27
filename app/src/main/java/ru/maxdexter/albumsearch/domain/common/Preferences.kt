package ru.maxdexter.albumsearch.domain.common

interface Preferences {
    fun setIsLogin(key: String, value: Boolean)

    fun getIsLogin(key: String): Boolean

    fun setLoggedUserEmail(key: String, value: String)

    fun getLoggedUserEmail(key: String): String?
}