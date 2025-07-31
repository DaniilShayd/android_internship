package com.example.androidinternship.ui.screens.post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidinternship.data.Post
import com.example.androidinternship.domain.interactors.post.PostInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PostViewModel(
    private  val postId: Int,
) : ViewModel() {
    private val postInteractor = PostInteractor.getInstance()


    val postState = postInteractor.posts
        .map { posts ->
            posts.find { it.id == postId } ?: Post.notFound()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Post.notFound()
        )

    fun toggleLike() {
        if (postId != -1) {
            postInteractor.likePost(postId)
        }
    }
}