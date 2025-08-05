package com.example.androidinternship.ui.screens.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidinternship.data.User
import com.example.androidinternship.domain.interactors.users.UsersInterator

class UserViewModel(userId: Int) : ViewModel() {
    private val usersInteractor = UsersInterator.getInstance()
    private val _user = usersInteractor.getUserById(userId)
    val user: User? = _user

    var commentsOpened by mutableStateOf(false)
        private set



    fun toggleComments() {
        commentsOpened = !commentsOpened
    }

    fun showAllComments() {
        commentsOpened = true
    }

    fun hideAllComments() {
        commentsOpened = false
    }
}
