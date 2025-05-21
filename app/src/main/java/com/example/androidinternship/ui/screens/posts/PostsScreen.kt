package com.example.androidinternship.ui.screens.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.NavHostController
import com.example.androidinternship.data.*
import com.example.androidinternship.ui.components.cards.PostCard

@Composable
fun PostsScreen(navController: NavHostController) {
    var posts by remember {
        mutableStateOf(posts)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn {
            items(posts) { post ->
                PostCard(
                    post = post,
                    onLikeClick = {
                        posts = posts.map {
                            if (it.id == post.id) it.copy(isLiked = !it.isLiked) else it
                        }
                    },
                    navController = navController,
                )
            }
        }
    }
}


