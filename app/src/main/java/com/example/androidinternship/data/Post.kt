package com.example.androidinternship.data

import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val id : Int? = null,
    val body : String? = null,
    val title: String? = null,
    val isLiked: Boolean? = null,
    val userName: String? = null,
    val comments : List<Comment> = emptyList()
){
    companion object {
        fun notFound() = Post(
            id = -1,
            body = "",
            title = "",
            isLiked = false,
            userName = "",
            comments = emptyList(),
        )
    }
}
