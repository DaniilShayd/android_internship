package com.example.androidinternship.ui.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.data.User
import com.example.domain.utils.ErrorData
import com.example.domain.utils.StatefulData
import com.example.domain.utils.SuccessData
import com.example.domain.repositories.UsersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val repository: UsersRepository = UsersRepository()
) : ViewModel() {

    private val _users = MutableStateFlow<StatefulData<List<User>>?>(null)
    val users: StateFlow<StatefulData<List<User>>?> = _users.asStateFlow()

    init {
        refreshUsers()
    }

    fun getUserById(userId: Int): User? {
        return _users.value?.unwrap()?.find { it.id == userId }
    }

    private fun refreshUsers() {
        viewModelScope.launch {
            try {
                val usersFromApi = repository.getUsers()
                _users.value = SuccessData(usersFromApi)
            } catch (e: Exception) {
                _users.value = ErrorData(e)
            }
        }
    }
}