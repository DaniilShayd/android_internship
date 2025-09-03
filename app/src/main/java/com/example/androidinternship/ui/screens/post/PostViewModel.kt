package com.example.androidinternship.ui.screens.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidinternship.data.Post
import com.example.androidinternship.domain.repositories.CommentsRepository
import com.example.androidinternship.domain.repositories.PostRepository
import com.example.androidinternship.utils.ErrorData
import com.example.androidinternship.utils.StatefulData
import com.example.androidinternship.utils.SuccessData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val postId: Int,
    private val repository: PostRepository = PostRepository(),
    private val commentsRepository: CommentsRepository = CommentsRepository()
) : ViewModel() {

    private val _post = MutableStateFlow<StatefulData<Post>?>(null)
    val postState: StateFlow<StatefulData<Post>?> = _post

    private val _commentsIsOpen = MutableStateFlow(false)
    val commentsIsOpen: StateFlow<Boolean> = _commentsIsOpen.asStateFlow()

    init {
        refreshPost()
    }

    fun changeCommentVisibility() {
        _commentsIsOpen.value = !_commentsIsOpen.value
    }

    fun toggleLike() {
        _post.value = SuccessData(
            (_post.value?.unwrap() ?: Post()).copy(
                isLiked = !(_post.value?.unwrap()?.isLiked ?: false)
            )
        )
    }

    private fun refreshPost() {
        viewModelScope.launch {
            try {

                val postFromApi = repository.getPost(postId)


                val commentsFromApi = commentsRepository.getComments(postId)


                _post.value = SuccessData(
                    postFromApi.copy(comments = commentsFromApi)
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _post.value = ErrorData(e)
            }
        }
    }
}
