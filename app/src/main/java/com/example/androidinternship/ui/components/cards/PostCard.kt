package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.androidinternship.data.Post
import com.example.androidinternship.resources.UIDimentions
import com.example.androidinternship.ui.components.PostCardBottomInfo

@Composable
fun PostCard(post: Post, onLikeClick: () -> Unit, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(UIDimentions.smallPadding)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(UIDimentions.smallElevation)
    ) {
        Column(
            modifier = Modifier
                .padding(UIDimentions.mediumPadding)
                .clickable {
                    navController.navigate("post_detail/${post.id}")
                }
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.height(UIDimentions.smallSpacerHeight)
            )
            Text(text = post.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(
                modifier = Modifier.height(UIDimentions.mediumSpacerHeight)
            )
            PostCardBottomInfo(post = post, onLikeClick = onLikeClick)
        }
    }
}
