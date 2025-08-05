package com.example.androidinternship.domain.interactors.users

import com.example.androidinternship.data.USERS
import com.example.androidinternship.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersInterator private constructor() {
    private val _users = MutableStateFlow(USERS)
    val users: StateFlow<List<User>> = _users.asStateFlow()

    fun getUserById(userId: Int) : User? {
        return _users.value.find { it.id == userId }
    }

    companion object {
        @Volatile
        private var instance: UsersInterator? = null

        fun getInstance(): UsersInterator {
            return instance ?: synchronized(this) {
                instance ?: UsersInterator().also { instance = it }
            }
        }
    }
}