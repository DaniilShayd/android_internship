package com.example.androidinternship.ui.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidinternship.data.User
import com.example.androidinternship.domain.repositories.CommentsRepository
import com.example.androidinternship.domain.repositories.UsersRepository
import com.example.androidinternship.utils.ErrorData
import com.example.androidinternship.utils.StatefulData
import com.example.androidinternship.utils.SuccessData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userId: Int,
    private val usersRepository: UsersRepository = UsersRepository(),
    private val commentsRepository: CommentsRepository = CommentsRepository()
) : ViewModel() {

    private val _user = MutableStateFlow<StatefulData<User>?>(null)
    val user: StateFlow<StatefulData<User>?> = _user.asStateFlow()

    private val _commentsOpened = MutableStateFlow(false)
    val commentsOpened: StateFlow<Boolean> = _commentsOpened.asStateFlow()

    init {
        loadUser()
    }

    fun toggleComments() {
        _commentsOpened.value = !_commentsOpened.value
    }

    private fun loadUser() {
        viewModelScope.launch {
            try {

                val userFromApi = usersRepository.getUser(userId)
                val allComments = commentsRepository.getAllComments()
                val userWithComments = userFromApi.copy(comments = allComments)

                _user.value = SuccessData(userWithComments)
            } catch (e: Exception) {
                e.printStackTrace()
                _user.value = ErrorData(e)
            }
        }
    }
}