package com.example.androidinternship.ui.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidinternship.data.User
import com.example.androidinternship.domain.repositories.UsersRepository
import com.example.androidinternship.utils.ErrorData
import com.example.androidinternship.utils.StatefulData
import com.example.androidinternship.utils.SuccessData
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