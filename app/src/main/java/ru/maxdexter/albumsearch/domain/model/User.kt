package ru.maxdexter.albumsearch.domain.model

data class User(
    val email: String,
    val name: String,
    val surname: String,
    val phone: String,
    val hashPassword: String,
    val dayOfBirth: String
)
