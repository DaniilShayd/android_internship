package com.example.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: Int? = null,
    val albumId: Int? = null,
    val title: String? = null,
    val url: String? = null,
    val thumbnailUrl: String? = null,
)
