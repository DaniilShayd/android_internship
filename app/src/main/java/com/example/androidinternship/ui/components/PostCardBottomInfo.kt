package com.example.androidinternship.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import com.example.androidinternship.data.Post
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.androidinternship.R


@Composable
fun PostCardBottomInfo(post: Post, onLikeClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = post.userName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onLikeClick,
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_medium))
        ) {
            Icon(
                imageVector = if (post.isLiked) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                contentDescription = stringResource(R.string.like),
                tint = if (post.isLiked) Color.Red else MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.6f
                )
            )
        }
    }
}