package com.example.androidinternship.ui.screens.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidinternship.R
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
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        if (posts?.isLoading() == true || posts == null) {
            CircularProgressIndicator(
                modifier = Modifier.width(dimensionResource(R.dimen.icon_size_medium)),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
            return

        }
        if (posts?.isError() == true) {
            Text("Посты не прогрузились, сорян")
            return
        }

        LazyColumn {
            items(posts?.unwrap() ?: emptyList()) { post ->
                PostCard(
                    post = post,
                    onLikeClick = { viewModel.toggleLike(post.id ?: -1) },
                    navController = navController,
                )

            }
        }
    }
}