package com.example.domain.repositories

import com.example.domain.apis.PostApi
import com.example.domain.data.Post

class PostRepository {
    suspend fun getPosts(): List<Post> {
        return PostApi.getPosts()
    }

    suspend fun getPost(postId: Int): Post {
        return PostApi.getPost(postId)
    }
}