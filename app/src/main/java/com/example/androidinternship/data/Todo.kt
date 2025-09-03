package com.example.androidinternship.data

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val completed: Boolean? = null,
)
