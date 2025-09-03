package com.example.androidinternship.data

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val name: String? = null,
    val catchPhrase: String? = null,
    val bs: String? = null
)