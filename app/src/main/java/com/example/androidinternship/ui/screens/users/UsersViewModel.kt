package com.example.androidinternship.ui.screens.users

import androidx.lifecycle.ViewModel
import com.example.androidinternship.data.User
import com.example.androidinternship.domain.interactors.users.UsersInterator
import kotlinx.coroutines.flow.StateFlow

class UsersViewModel : ViewModel() {
    private val usersInteractor = UsersInterator.getInstance()

    val users: StateFlow<List<User>> = usersInteractor.users
}