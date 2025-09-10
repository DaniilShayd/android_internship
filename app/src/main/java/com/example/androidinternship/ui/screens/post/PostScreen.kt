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
import com.example.domain.resources.AppSettings.COMMENTS_SHOW_LIMIT
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.androidinternship.R
import com.example.androidinternship.ui.components.PostCardBottomInfo
import com.example.androidinternship.ui.components.cards.CommentCard


import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.data.*

@Composable
fun PostScreen(
    navController: NavController,
    viewModel: PostViewModel = viewModel()
) {
    val post by viewModel.postState.collectAsStateWithLifecycle()

    if (post?.isLoading() == true || post == null) {
        CircularProgressIndicator(
            modifier = Modifier.width(dimensionResource(R.dimen.icon_size_medium)),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
        return

    }
    if (post?.isError() == true) {
        Text("Пост не прогрузился, сорян")
        return
    }

    Scaffold(
        topBar = {
            PostScreenTopBar(
                navController = navController,
                post = post!!.unwrap(),
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
            if (post!!.unwrap().id == -1) {
                PostNotFound()
            } else {
                PostList(
                    post = post!!.unwrap(),
                    onLikeClick = viewModel::toggleLike,
                    viewModel = viewModel
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
        title = { Text(post.title ?: "") },
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
                    imageVector = if (post.isLiked == true) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = stringResource(R.string.like),
                    tint = if (post.isLiked == true) Color.Red else MaterialTheme.colorScheme.onSurface
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
private fun PostList(post: Post, onLikeClick: () -> Unit, viewModel: PostViewModel) {
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
                ExpandableComments(comments = post.comments, viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun ExpandableComments(comments: List<Comment>, viewModel: PostViewModel) {
    val commentsIsOpen by viewModel.commentsIsOpen.collectAsStateWithLifecycle()

    if (!commentsIsOpen) {
        TextButton(
            onClick = viewModel::changeCommentVisibility,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.show_comments)
            )
        }
    }


    if (commentsIsOpen) {
        comments.drop(COMMENTS_SHOW_LIMIT).forEach { comment ->
            CommentCard(comment = comment)
        }

        TextButton(
            onClick = viewModel::changeCommentVisibility,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.hide_comments)
            )
        }
    }
}

@Composable
fun PostDetails(post: Post, onLikeClick: () -> Unit) {
    Column {
        Text(
            text = post.title ?: "",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_height_small)))
        Text(
            text = post.body ?: "",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_height_medium)))
        PostCardBottomInfo(post = post, onLikeClick = onLikeClick)
    }
}