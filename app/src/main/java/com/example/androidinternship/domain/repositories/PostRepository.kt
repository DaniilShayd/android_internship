package com.example.androidinternship.domain.repositories

import com.example.androidinternship.data.POSTS
import com.example.androidinternship.data.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PostRepository {
    fun getPosts (): List<Post> {
        return POSTS
    }
}
