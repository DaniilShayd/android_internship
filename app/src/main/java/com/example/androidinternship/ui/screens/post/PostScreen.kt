package com.example.androidinternship.ui.screens.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.androidinternship.data.*
import com.example.androidinternship.resources.UIDimentions
import com.example.androidinternship.ui.components.PostCardBottomInfo
import com.example.androidinternship.ui.components.cards.CommentCard

@Composable
fun PostScreen(postId: Int) {
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

    if (post.id == null) {
        PostNotFound()
        return
    }

    PostContent(post = post, onLikeClick = { post = post.copy(isLiked = !post.isLiked) })
}

@Composable
private fun PostNotFound() {
    Text(
        text = "Post not found",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Composable
private fun PostContent(post: Post, onLikeClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        PostList(post = post, onLikeClick = onLikeClick)
    }
}

@Composable
private fun PostList(post: Post, onLikeClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(UIDimentions.mediumPadding)
    ) {
        item {
            PostDetails(post = post, onLikeClick = onLikeClick)
        }

        items(post.comments.take(2)) { comment ->
            CommentCard(comment = comment)
        }

        if (post.comments.size > 2) {
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
            text = if (expanded) "Скрыть комментарии" else "Показать все комментарии"
        )
    }

    if (expanded) {
        comments.drop(2).forEach { comment ->
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
        Spacer(modifier = Modifier.height(UIDimentions.smallSpacerHeight))
        Text(text = post.description, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(UIDimentions.mediumSpacerHeight))
        PostCardBottomInfo(post = post, onLikeClick = onLikeClick)
    }
}

