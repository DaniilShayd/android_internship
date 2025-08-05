package com.example.androidinternship.ui.screens.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidinternship.data.Post
import com.example.androidinternship.domain.interactors.post.PostInteractor
import kotlinx.coroutines.flow.*

class PostViewModel(
    private  val postId: Int,
) : ViewModel() {
    private val postInteractor = PostInteractor.getInstance()
    private val _commentsIsOpen = MutableStateFlow<Boolean>(false)
    val commentsIsOpen: StateFlow<Boolean> = _commentsIsOpen.asStateFlow()

    fun changeCommentVisibility() {
        _commentsIsOpen.value = !_commentsIsOpen.value
    }


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