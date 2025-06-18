package com.example.androidinternship.ui.screens.users

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidinternship.animation.LocalSharedElementState
import com.example.androidinternship.data.users
import com.example.androidinternship.ui.components.cards.UserListCard

@Composable
fun UsersScreen(navController: NavController) {
    val users = users
    val state = LocalSharedElementState.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        items(users) { user ->
            UserListCard(
                user = user,
                onClick = {
                    state.selectedUserId = user.id
                    navController.navigate("user_detail/${user.id}")
                }
            )
        }
    }
}