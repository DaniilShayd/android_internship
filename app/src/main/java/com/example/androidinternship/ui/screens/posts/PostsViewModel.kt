package com.example.androidinternship.ui.screens.posts

import androidx.lifecycle.ViewModel
import com.example.androidinternship.data.Post
import com.example.androidinternship.domain.repositories.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PostsViewModel(
    private val repository: PostRepository = PostRepository()
) : ViewModel() {

    private val _posts = MutableStateFlow(repository.getPosts())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    fun toggleLike(postId: Int) {
        val updatedPosts = _posts.value.toMutableList()
        val index = updatedPosts.indexOfFirst { it.id == postId }
        if (index != -1) {
            updatedPosts[index] = updatedPosts[index].copy(isLiked = !updatedPosts[index].isLiked)
            _posts.value = updatedPosts
        }
    }

    fun refreshPosts() {
        _posts.value = repository.getPosts()
    }
}