package com.example.androidinternship.ui.screens.users

import androidx.lifecycle.ViewModel
import com.example.androidinternship.data.User
import com.example.androidinternship.domain.repositories.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class UsersViewModel(
    private val repository: UsersRepository = UsersRepository()
) : ViewModel() {

    private val _users = MutableStateFlow(repository.getUsers())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    fun getUserById(userId: Int): User? {
        return _users.value.find { it.id == userId }
    }


    fun refreshUsers() {
        _users.value = repository.getUsers()
    }
}