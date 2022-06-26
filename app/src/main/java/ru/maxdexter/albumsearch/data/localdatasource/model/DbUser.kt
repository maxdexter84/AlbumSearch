package ru.maxdexter.albumsearch.data.localdatasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class DbUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val hashPassword: String
)
