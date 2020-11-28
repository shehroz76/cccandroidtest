package com.test_project.app.models
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Person(
    val last_name: String? = null,
    val phone_number: String? = null,
    @PrimaryKey
    val id: String,
    val first_name: String? = null,
    val email: String? = null
)
