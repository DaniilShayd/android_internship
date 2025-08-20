package com.example.androidinternship.domain.repositories

import com.example.androidinternship.data.POSTS
import com.example.androidinternship.data.Post
import com.example.androidinternship.data.USERS
import com.example.androidinternship.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UsersRepository {
    fun getUsers (): List<User> {
        return USERS;
    }
}

