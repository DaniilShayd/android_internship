@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.androidinternship.ui.screens.user

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidinternship.R
import com.example.androidinternship.data.User
import com.example.androidinternship.ui.components.NestedScreenAppBar
import com.example.androidinternship.ui.components.cards.CommentCard
import com.example.androidinternship.ui.composables.UIButton
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.lifecycle.compose.collectAsStateWithLifecycle

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun UserScreen(
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: UserViewModel = viewModel()
) {
    val userState by viewModel.user.collectAsStateWithLifecycle()
    val commentsOpened by viewModel.commentsOpened.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NestedScreenAppBar(
            color = MaterialTheme.colorScheme.surfaceVariant,
            onBackClick = { navController.popBackStack() }
        )

        when {
            userState?.isLoading() == true || userState == null -> {
                LoadingIndicator()
            }
            userState?.isError() == true -> {
                ErrorMessage()
            }
            else -> {
                val user = userState?.unwrap()
                if (user != null) {
                    UserContent(
                        user = user,
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope,
                        commentsOpened = commentsOpened,
                        onToggleComments = viewModel::toggleComments
                    )
                } else {
                    UserNotFound()
                }
            }
        }
    }
}

@Composable
private fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(dimensionResource(R.dimen.icon_size_medium)),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun ErrorMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Не удалось загрузить данные пользователя",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun UserContent(
    user: User,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    commentsOpened: Boolean,
    onToggleComments: () -> Unit
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

            UserCommentSection(
                user = user,
                commentsOpened = commentsOpened,
                onToggleComments = onToggleComments
            )
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
            UserAvatarText(user.name ?: "")
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
        text = user.name ?: "",
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
private fun UserCommentSection(
    user: User,
    commentsOpened: Boolean,
    onToggleComments: () -> Unit
) {
    val comments = user.comments

    Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium_large)))
        Text(
            text = stringResource(R.string.comments),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        if (comments.isEmpty()) {
            Text(
                text = "Нет комментариев",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else if (!commentsOpened) {
            comments.take(2).forEach { comment ->
                CommentCard(comment = comment)
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium_large)))

            UIButton(
                text = stringResource(R.string.show_comments),
                onClick = onToggleComments
            )
        } else {
            comments.forEach { comment ->
                CommentCard(comment = comment)
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium_large)))

            UIButton(
                text = stringResource(R.string.hide_comments),
                onClick = onToggleComments
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