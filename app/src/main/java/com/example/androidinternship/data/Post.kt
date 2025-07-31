package com.example.androidinternship.data

data class Post(
    val id : Int,
    val description : String,
    val title: String,
    val isLiked: Boolean,
    val userName: String,
    val comments : List<Comment> = emptyList()
){
    companion object {
        fun notFound() = Post(
            id = -1,
            description = "",
            title = "",
            isLiked = false,
            userName = "",
            comments = emptyList(),
        )
    }
}
