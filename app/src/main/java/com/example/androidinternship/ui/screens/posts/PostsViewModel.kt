package com.example.androidinternship.ui.screens.posts

import androidx.lifecycle.ViewModel
import com.example.domain.data.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import com.example.domain.utils.ErrorData
import com.example.domain.utils.StatefulData
import com.example.domain.utils.SuccessData
import com.example.domain.repositories.PostRepository
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: PostRepository = PostRepository()
) : ViewModel() {

    private val _posts = MutableStateFlow<StatefulData<List<Post>>?>(null)
    val posts: StateFlow<StatefulData<List<Post>>?> = _posts.asStateFlow()

    init {
        refreshPosts()
    }

    fun toggleLike(postId: Int) {
        val updatedPosts = (_posts.value?.unwrap() ?: emptyList()).toMutableList()
        val index = updatedPosts.indexOfFirst { it.id == postId }
        if (index != -1) {
            updatedPosts[index] = updatedPosts[index].copy(
                isLiked = !(updatedPosts[index].isLiked ?: false)
            )
            _posts.value = SuccessData(updatedPosts)
        }
    }

    private fun refreshPosts() {
        viewModelScope.launch {
            try {
                val postsFromApi = repository.getPosts()
                _posts.value = SuccessData(postsFromApi)
            } catch (e: Exception) {
                _posts.value = ErrorData(e)
            }
        }
    }
}
