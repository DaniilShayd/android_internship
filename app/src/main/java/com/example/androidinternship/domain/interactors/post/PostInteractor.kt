package com.example.androidinternship.domain.interactors.post

import com.example.androidinternship.data.POSTS
import com.example.androidinternship.data.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostInteractor private constructor() {
    private val _posts = MutableStateFlow(POSTS)
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    fun likePost(postId: Int) {
        val posts = _posts.value.toMutableList()
        val index = posts.indexOfFirst { it.id == postId }
        if (index != -1) {
            posts[index] = posts[index].copy(isLiked = !posts[index].isLiked)
            _posts.value = posts
        }
    }

    companion object {
        @Volatile
        private var instance: PostInteractor? = null

        fun getInstance(): PostInteractor {
            return instance ?: synchronized(this) {
                instance ?: PostInteractor().also { instance = it }
            }
        }
    }
}