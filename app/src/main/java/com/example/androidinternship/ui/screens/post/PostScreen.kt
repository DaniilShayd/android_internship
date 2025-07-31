package com.example.androidinternship.ui.screens.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.androidinternship.data.*
import com.example.androidinternship.resources.AppSettings.COMMENTS_SHOW_LIMIT
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.androidinternship.R
import com.example.androidinternship.ui.components.PostCardBottomInfo
import com.example.androidinternship.ui.components.cards.CommentCard


import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PostScreen(
    navController: NavController,
    viewModel: PostViewModel = viewModel(
    )
) {

    val post by viewModel.postState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            PostScreenTopBar(
                navController = navController,
                post = post,
                onLikeClick = viewModel::toggleLike
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (post.id == -1) {
                PostNotFound()
            } else {
                PostContent(
                    post = post,
                    onLikeClick = viewModel::toggleLike
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostScreenTopBar(
    navController: NavController,
    post: Post,
    onLikeClick: () -> Unit
) {
    TopAppBar(
        title = { Text(post.title) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Default.ArrowBackIosNew, stringResource(R.string.back)
                )
            }
        },
        actions = {
            IconButton(onClick = onLikeClick) {
                Icon(
                    imageVector = if (post.isLiked) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = stringResource(R.string.like),
                    tint = if (post.isLiked) Color.Red else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}

@Composable
private fun PostNotFound() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.post_not_found),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun PostContent(post: Post, onLikeClick: () -> Unit) {
    PostList(post = post, onLikeClick = onLikeClick)
}

@Composable
private fun PostList(post: Post, onLikeClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
    ) {
        item {
            PostDetails(post = post, onLikeClick = onLikeClick)
        }
        items(post.comments.take(COMMENTS_SHOW_LIMIT)) { comment ->
            CommentCard(comment = comment)
        }
        if (post.comments.size > COMMENTS_SHOW_LIMIT) {
            item {
                ExpandableComments(comments = post.comments)
            }
        }
    }
}

@Composable
private fun ExpandableComments(comments: List<Comment>) {
    var expanded by remember { mutableStateOf(false) }

    TextButton(
        onClick = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = if (expanded) stringResource(R.string.hide_comments) else stringResource(R.string.show_comments)
        )
    }

    if (expanded) {
        comments.drop(COMMENTS_SHOW_LIMIT).forEach { comment ->
            CommentCard(comment = comment)
        }
    }
}

@Composable
fun PostDetails(post: Post, onLikeClick: () -> Unit) {
    Column {
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_height_small)))
        Text(
            text = post.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_height_medium)))
        PostCardBottomInfo(post = post, onLikeClick = onLikeClick)
    }
}