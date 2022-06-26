package ru.maxdexter.albumsearch.data.localdatasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class DbUser(
    @PrimaryKey()
    val email: String,
    val name: String,
    val surname: String,
    val phone: String,
    val hashPassword: String,
    val dayOfBirth: String
)
