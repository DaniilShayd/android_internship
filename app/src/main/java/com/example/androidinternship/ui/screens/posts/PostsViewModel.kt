package com.example.androidinternship.ui.screens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidinternship.data.Post
import com.example.androidinternship.domain.interactors.post.PostInteractor
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val postInteractor = PostInteractor.getInstance()

    val posts: StateFlow<List<Post>> = postInteractor.posts

    fun toggleLike(postId: Int) {
        viewModelScope.launch {
            postInteractor.likePost(postId)
        }
    }
}