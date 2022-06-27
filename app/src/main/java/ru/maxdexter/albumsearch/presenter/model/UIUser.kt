package ru.maxdexter.albumsearch.presenter.model

data class UIUser(
    val email: String,
    val name: String,
    val surname: String,
    val phone: String,
    val hashPassword: String,
    val dayOfBirth: String
){

}
