package com.example.androidinternship.ui.screens.users

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androidinternship.R
import com.example.androidinternship.ui.components.cards.UserListCard

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UsersScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: UsersViewModel = viewModel()
) {
    val users by viewModel.users.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        if (users?.isLoading() == true || users == null) {
            CircularProgressIndicator(
                modifier = Modifier.width(dimensionResource(R.dimen.icon_size_medium)),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
            return
        }

        if (users?.isError() == true) {
            Text("Пользователи не прогрузились, сорян")
            return
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
        ) {
            items(users!!.unwrap().size) { user ->
                UserListCard(
                    user = users!!.unwrap()[user],
                    onClick = {
                        navController.navigate("user_detail/${users!!.unwrap()[user].id}")
                    },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope
                )
            }
        }
    }
}