package com.example.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int? = null,
    val title: String? = null,
    val userId: String? = null,
)