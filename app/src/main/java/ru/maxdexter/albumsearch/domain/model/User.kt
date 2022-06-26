package ru.maxdexter.albumsearch.domain.model

data class User(
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val hashPassword: String
)
