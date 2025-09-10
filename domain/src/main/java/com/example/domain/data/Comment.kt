package com.example.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val id: Int? = null,
    val postId: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val body: String? = null,
)
