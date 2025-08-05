package com.example.androidinternship.ui.screens.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidinternship.ui.components.cards.PostCard


@Composable
fun PostsScreen(
    navController: NavHostController,
    viewModel: PostsViewModel = viewModel()
) {

    val posts by viewModel.posts.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn {
            items(posts) { post ->
                PostCard(
                    post = post,
                    onLikeClick = { viewModel.toggleLike(post.id) },
                    navController = navController,
                )
            }
        }
    }
}