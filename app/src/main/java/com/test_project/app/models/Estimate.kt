package com.test_project.app.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Estimate(
	val number: Int? = null,
	val address: String? = null,
	val requested_by: String? = null,
	val revision_number: Int? = null,
	val contact: String? = null,
	val company: String? = null,
	@PrimaryKey
	val id: String,
	val created_date: String? = null,
	val created_by: String? = null
)
