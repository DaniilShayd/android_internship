package com.example.androidinternship.ui.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androidinternship.data.User
import com.example.androidinternship.data.users
import com.example.androidinternship.resources.Localization.COMMENTS
import com.example.androidinternship.resources.Localization.HIDE_COMMENTS
import com.example.androidinternship.resources.Localization.SHOW_COMMENTS
import com.example.androidinternship.resources.Localization.USER_NOT_FOUND
import com.example.androidinternship.resources.UIDimentions
import com.example.androidinternship.ui.components.NestedScreenAppBar
import com.example.androidinternship.ui.components.cards.CommentCard
import com.example.androidinternship.ui.composables.UIButton

@Composable
fun UserScreen(userId: Int, navController: NavHostController) {
    val user = users.find { it.id == userId }

    Column {
        NestedScreenAppBar(
            color = MaterialTheme.colorScheme.surfaceVariant,
            navController = navController,
        )

        if (user != null) {
            UserContent(user)
        } else {
            UserNotFound()
        }
    }
}

@Composable
private fun UserContent(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserHeader()
        UserAvatar(user.name)
        UserName(user.name)
        UserCommentSection(user)
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
private fun UserAvatar(fullName: String) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .offset(y = (-50).dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getInitials(fullName),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun UserName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun UserCommentSection(user: User) {
    var commentsIsOpened by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(UIDimentions.mediumPadding)) {
        Spacer(modifier = Modifier.height(UIDimentions.mediumLargePadding))
        Text(
            text = COMMENTS,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        val comments = user.comments

        if (!commentsIsOpened) {
            comments.take(2).forEach { comment ->
                CommentCard(comment = comment)
            }

            Spacer(modifier = Modifier.height(UIDimentions.mediumLargePadding))

            UIButton(
                text = SHOW_COMMENTS,
                onClick = { commentsIsOpened = true }
            )
        } else {
            comments.forEach { comment ->
                CommentCard(comment = comment)
            }

            Spacer(modifier = Modifier.height(UIDimentions.mediumLargePadding))

            UIButton(
                text = HIDE_COMMENTS,
                onClick = { commentsIsOpened = false }
            )
        }
    }
}

@Composable
private fun UserNotFound() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = USER_NOT_FOUND,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}

private fun getInitials(fullName: String): String {
    val names = fullName.split(" ").filter { it.isNotBlank() }
    return when {
        names.size >= 2 -> "${names[0].first()}${names[1].first()}".uppercase()
        names.size == 1 -> names[0].take(2).uppercase()
        else -> ""
    }
}