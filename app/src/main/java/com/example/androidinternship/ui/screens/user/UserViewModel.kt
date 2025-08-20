package com.example.androidinternship.ui.screens.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidinternship.data.User
import com.example.androidinternship.domain.repositories.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel(
    private val userId: Int,
    private val repository: UsersRepository = UsersRepository()
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    var commentsOpened by mutableStateOf(false)
        private set

    init {
        loadUser()
    }

    private fun loadUser() {
        _user.value = repository.getUsers().find { it.id == userId }
    }

    fun toggleComments() {
        commentsOpened = !commentsOpened
    }
}
