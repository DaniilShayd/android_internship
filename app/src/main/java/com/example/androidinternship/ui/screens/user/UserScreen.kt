package com.example.androidinternship.ui.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androidinternship.data.User
import com.example.androidinternship.data.users
import com.example.androidinternship.resources.UIDimentions

@Composable
fun UserScreen(userId: Int) {
    val user = users.find { it.id == userId }

    if (user != null) {
        UserContent(user)
    } else {
        UserNotFound()
    }
}

@Composable
private fun UserContent(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
            .padding(horizontal = UIDimentions.mediumPadding)
    ) {
        Spacer(modifier = Modifier.height(UIDimentions.mediumPadding))
        UserHeader()
        UserAvatar()
        UserName(user.name)
        UserCommentSection()
        ShowMoreButton()
    }
}

@Composable
private fun UserHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
}

@Composable
private fun UserAvatar() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .offset(y = (-50).dp)
            .background(
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                shape = CircleShape
            ),

        )

}

@Composable
private fun UserName(name: String) {
    Spacer(modifier = Modifier.height(60.dp))
    Text(
        text = name,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun UserCommentSection() {
    Spacer(modifier = Modifier.height(UIDimentions.mediumLargePadding))
    Text(
        text = "Комментарии:",
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
    )

    repeat(2) {
        Spacer(modifier = Modifier.height(UIDimentions.smallPadding))
        CommentItem()
    }
}

@Composable
private fun CommentItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .padding(UIDimentions.mediumSmallPadding)
    ) {
        Text(
            text = "User 2",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )
        Text(
            text = "Comment",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
private fun ShowMoreButton() {
    Spacer(modifier = Modifier.height(UIDimentions.mediumPadding))
    Text(
        text = "Показать больше",
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun UserNotFound() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "User not found",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}