@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.androidinternship.ui.screens.user

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androidinternship.R
import com.example.androidinternship.data.User
import com.example.androidinternship.data.UserSharedElementType
import com.example.androidinternship.data.users
import com.example.androidinternship.ui.components.NestedScreenAppBar
import com.example.androidinternship.ui.components.cards.CommentCard
import com.example.androidinternship.ui.composables.UIButton

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserScreen(
    userId: Int,
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val user = users.find { it.id == userId }

    Column {
        NestedScreenAppBar(
            color = MaterialTheme.colorScheme.surfaceVariant,
            onBackClick = {
                navController.popBackStack()
            }
        )

        if (user != null) {
            UserContent(
                user = user,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            )
        } else {
            UserNotFound()
        }
    }
}

@Composable
private fun UserContent(
    user: User,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserHeaderBackground()

            UserAvatar(
                user = user,
                animatedContentScope = animatedContentScope
            )

            UserNameDisplay(
                user = user,
                animatedContentScope = animatedContentScope
            )

            UserCommentSection(user)
        }
    }
}

@Composable
private fun UserHeaderBackground(
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
}

@Composable
private fun SharedTransitionScope.UserAvatar(
    user: User,
    animatedContentScope: AnimatedContentScope
) {
    val avatarSize = 100.dp

    Box(
        modifier = Modifier
            .size(avatarSize)
            .offset(y = (-50).dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(avatarSize)
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "user_avatar_${user.id}"),
                    animatedVisibilityScope = animatedContentScope
                )
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            UserAvatarText(user.name)
        }
    }
}

@Composable
private fun UserAvatarText(userName: String) {
    Text(
        text = getInitials(userName),
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.graphicsLayer {
            scaleX = 1f
            scaleY = 1f
        }
    )
}

@Composable
private fun SharedTransitionScope.UserNameDisplay(
    user: User,
    animatedContentScope: AnimatedContentScope
) {
    Text(
        text = user.name,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .sharedElement(
                sharedContentState = rememberSharedContentState(key = "user_name_${user.id}"),
                animatedVisibilityScope = animatedContentScope
            )
    )
}

@Composable
private fun UserCommentSection(user: User) {
    var commentsIsOpened by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium_large)))
        Text(
            text = stringResource(R.string.comments),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        val comments = user.comments

        if (!commentsIsOpened) {
            comments.take(2).forEach { comment ->
                CommentCard(comment = comment)
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium_large)))

            UIButton(
                text = stringResource(R.string.show_comments),
                onClick = { commentsIsOpened = true }
            )
        } else {
            comments.forEach { comment ->
                CommentCard(comment = comment)
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium_large)))

            UIButton(
                text = stringResource(R.string.hide_comments),
                onClick = { commentsIsOpened = false }
            )
        }
    }
}

@Composable
private fun UserNotFound() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.user_not_found),
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