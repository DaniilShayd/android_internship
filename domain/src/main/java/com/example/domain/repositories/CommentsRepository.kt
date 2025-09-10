package com.example.domain.repositories

import com.example.domain.apis.CommentApi
import com.example.domain.data.Comment

class CommentsRepository{
    suspend fun getComments(postId: Int): List<Comment> {
        return CommentApi.getComments(postId)
    }

    suspend fun getAllComments(): List<Comment> {
        return CommentApi.getAllComments()
    }
}