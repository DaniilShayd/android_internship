package com.example.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    val lat: String? = null,
    val lng: String? = null
)