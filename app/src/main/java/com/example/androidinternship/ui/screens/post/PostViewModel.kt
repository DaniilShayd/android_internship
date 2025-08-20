package com.example.androidinternship.ui.screens.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidinternship.data.Post
import com.example.androidinternship.domain.repositories.PostRepository
import kotlinx.coroutines.flow.*

class PostViewModel(
    private val postId: Int,
    private val repository: PostRepository = PostRepository()
) : ViewModel() {

    private val _posts = MutableStateFlow(repository.getPosts())
    private val _commentsIsOpen = MutableStateFlow(false)

    val commentsIsOpen: StateFlow<Boolean> = _commentsIsOpen.asStateFlow()

    val postState: StateFlow<Post> = _posts
        .map { posts -> posts.find { it.id == postId } ?: Post.notFound() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Post.notFound()
        )

    fun changeCommentVisibility() {
        _commentsIsOpen.value = !_commentsIsOpen.value
    }

    fun toggleLike() {
        val updatedPosts = _posts.value.toMutableList()
        val index = updatedPosts.indexOfFirst { it.id == postId }
        if (index != -1) {
            updatedPosts[index] = updatedPosts[index].copy(isLiked = !updatedPosts[index].isLiked)
            _posts.value = updatedPosts
        }
    }

    fun refreshPost() {
        _posts.value = repository.getPosts()
    }
}