package com.example.androidinternship.ui.screens.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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


@Composable
fun PostScreen(
    postId: Int,
    navController: NavController,
) {
    var post by remember {
        mutableStateOf(posts.find { it.id == postId } ?: Post(
            id = null,
            description = "",
            title = "",
            isLiked = false,
            userName = "",
            comments = emptyList(),
        ))
    }

    Scaffold(
        topBar = {
            PostScreenTopBar(
                navController = navController, post = post
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (post.id == null) {
                PostNotFound()
            } else {
                PostContent(post = post, onLikeClick = {
                    post = post.copy(isLiked = !post.isLiked)
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostScreenTopBar(navController: NavController, post: Post) {
    TopAppBar(
        title = {
            post.title
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = stringResource(R.string.back)
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